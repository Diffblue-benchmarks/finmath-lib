package net.finmath.singleswaprate.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import net.finmath.singleswaprate.data.DataTable.TableConvention;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DataTableLightDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DataTableLight#DataTableLight(String, TableConvention)}
   *   <li>{@link DataTableLight#toString()}
   *   <li>{@link DataTableLight#getConvention()}
   *   <li>{@link DataTableLight#getName()}
   *   <li>{@link DataTableLight#getReferenceDate()}
   *   <li>{@link DataTableLight#getScheduleMetaData()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTableLight.<init>(String, TableConvention)",
    "TableConvention DataTableLight.getConvention()",
    "String DataTableLight.getName()",
    "LocalDate DataTableLight.getReferenceDate()",
    "net.finmath.time.SchedulePrototype DataTableLight.getScheduleMetaData()",
    "String DataTableLight.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DataTableLight actualDataTableLight = new DataTableLight("Name", TableConvention.MONTHS);
    String actualToStringResult = actualDataTableLight.toString();
    TableConvention actualConvention = actualDataTableLight.getConvention();
    String actualName = actualDataTableLight.getName();
    LocalDate actualReferenceDate = actualDataTableLight.getReferenceDate();

    // Assert
    assertEquals("Name", actualName);
    assertEquals("Name: Name, TableConvention: MONTHS,\n", actualToStringResult);
    assertNull(actualReferenceDate);
    assertNull(actualDataTableLight.getScheduleMetaData());
    assertEquals(TableConvention.MONTHS, actualConvention);
  }

  /**
   * Test {@link DataTableLight#DataTableLight(String, TableConvention, List, List, List)}.
   *
   * <ul>
   *   <li>Given {@code 0.5}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#DataTableLight(String, TableConvention, List, List,
   * List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataTableLight.<init>(String, TableConvention, List, List, List)"})
  public void testNewDataTableLight_given05_whenArrayListAdd05() {
    // Arrange
    ArrayList<Integer> maturities = new ArrayList<>();
    ArrayList<Integer> terminations = new ArrayList<>();

    ArrayList<Double> values = new ArrayList<>();
    values.add(0.5d);
    values.add(10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new DataTableLight("Name", TableConvention.MONTHS, maturities, terminations, values));
  }

  /**
   * Test {@link DataTableLight#DataTableLight(String, TableConvention, List, List, List)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>When {@link ArrayList#ArrayList()} add one.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#DataTableLight(String, TableConvention, List, List,
   * List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataTableLight.<init>(String, TableConvention, List, List, List)"})
  public void testNewDataTableLight_givenOne_whenArrayListAddOne() {
    // Arrange
    ArrayList<Integer> maturities = new ArrayList<>();
    maturities.add(1);
    maturities.add(2);
    ArrayList<Integer> terminations = new ArrayList<>();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new DataTableLight(
                "Name", TableConvention.MONTHS, maturities, terminations, new ArrayList<>()));
  }

  /**
   * Test {@link DataTableLight#DataTableLight(String, TableConvention, List, List, List)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>When {@link ArrayList#ArrayList()} add one.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#DataTableLight(String, TableConvention, List, List,
   * List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataTableLight.<init>(String, TableConvention, List, List, List)"})
  public void testNewDataTableLight_givenOne_whenArrayListAddOne2() {
    // Arrange
    ArrayList<Integer> maturities = new ArrayList<>();

    ArrayList<Integer> terminations = new ArrayList<>();
    terminations.add(1);
    terminations.add(2);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new DataTableLight(
                "Name", TableConvention.MONTHS, maturities, terminations, new ArrayList<>()));
  }

  /**
   * Test {@link DataTableLight#DataTableLight(String, TableConvention, List, List, List)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>When {@link ArrayList#ArrayList()} add one.
   *   <li>Then return Maturities size is one.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#DataTableLight(String, TableConvention, List, List,
   * List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataTableLight.<init>(String, TableConvention, List, List, List)"})
  public void testNewDataTableLight_givenOne_whenArrayListAddOne_thenReturnMaturitiesSizeIsOne() {
    // Arrange
    ArrayList<Integer> maturities = new ArrayList<>();
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);
    maturities.add(1);

    ArrayList<Integer> terminations = new ArrayList<>();
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);
    terminations.add(1);

    ArrayList<Double> values = new ArrayList<>();
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);
    values.add(10.0d);

    // Act
    DataTableLight actualDataTableLight =
        new DataTableLight("Name", TableConvention.MONTHS, maturities, terminations, values);

    // Assert
    assertEquals("Name", actualDataTableLight.getName());
    assertNull(actualDataTableLight.getReferenceDate());
    assertNull(actualDataTableLight.getScheduleMetaData());
    TreeSet<Integer> maturities2 = actualDataTableLight.getMaturities();
    assertEquals(1, maturities2.size());
    assertEquals(1, actualDataTableLight.size());
    assertEquals(TableConvention.MONTHS, actualDataTableLight.getConvention());
    assertTrue(maturities2.contains(1));
    assertEquals(maturities2, actualDataTableLight.getTerminations());
  }

  /**
   * Test {@link DataTableLight#DataTableLight(String, TableConvention, List, List, List)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>When {@link ArrayList#ArrayList()} add ten.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#DataTableLight(String, TableConvention, List, List,
   * List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataTableLight.<init>(String, TableConvention, List, List, List)"})
  public void testNewDataTableLight_givenTen_whenArrayListAddTen() {
    // Arrange
    ArrayList<Integer> maturities = new ArrayList<>();
    ArrayList<Integer> terminations = new ArrayList<>();

    ArrayList<Double> values = new ArrayList<>();
    values.add(10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new DataTableLight("Name", TableConvention.MONTHS, maturities, terminations, values));
  }

  /**
   * Test {@link DataTableLight#DataTableLight(String, TableConvention, List, List, List)}.
   *
   * <ul>
   *   <li>Given two.
   *   <li>When {@link ArrayList#ArrayList()} add two.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#DataTableLight(String, TableConvention, List, List,
   * List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataTableLight.<init>(String, TableConvention, List, List, List)"})
  public void testNewDataTableLight_givenTwo_whenArrayListAddTwo() {
    // Arrange
    ArrayList<Integer> maturities = new ArrayList<>();
    maturities.add(2);
    ArrayList<Integer> terminations = new ArrayList<>();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new DataTableLight(
                "Name", TableConvention.MONTHS, maturities, terminations, new ArrayList<>()));
  }

  /**
   * Test {@link DataTableLight#DataTableLight(String, TableConvention, List, List, List)}.
   *
   * <ul>
   *   <li>Given two.
   *   <li>When {@link ArrayList#ArrayList()} add two.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#DataTableLight(String, TableConvention, List, List,
   * List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataTableLight.<init>(String, TableConvention, List, List, List)"})
  public void testNewDataTableLight_givenTwo_whenArrayListAddTwo2() {
    // Arrange
    ArrayList<Integer> maturities = new ArrayList<>();

    ArrayList<Integer> terminations = new ArrayList<>();
    terminations.add(2);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new DataTableLight(
                "Name", TableConvention.MONTHS, maturities, terminations, new ArrayList<>()));
  }

  /**
   * Test {@link DataTableLight#DataTableLight(String, TableConvention, int[], int[], double[])}.
   *
   * <ul>
   *   <li>Then return Terminations size is two.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#DataTableLight(String, TableConvention, int[],
   * int[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataTableLight.<init>(String, TableConvention, int[], int[], double[])"})
  public void testNewDataTableLight_thenReturnTerminationsSizeIsTwo() {
    // Arrange and Act
    DataTableLight actualDataTableLight =
        new DataTableLight(
            "Name",
            TableConvention.MONTHS,
            new int[] {1, 4, 1, 0},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    TreeSet<Integer> terminations = actualDataTableLight.getTerminations();
    assertEquals(2, terminations.size());
    TreeSet<Integer> maturities = actualDataTableLight.getMaturities();
    assertEquals(3, maturities.size());
    assertTrue(maturities.contains(4));
    assertTrue(terminations.contains(0));
    assertTrue(terminations.contains(1));
  }

  /**
   * Test {@link DataTableLight#DataTableLight(String, TableConvention, List, List, List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return size is zero.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#DataTableLight(String, TableConvention, List, List,
   * List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataTableLight.<init>(String, TableConvention, List, List, List)"})
  public void testNewDataTableLight_whenArrayList_thenReturnSizeIsZero() {
    // Arrange
    ArrayList<Integer> maturities = new ArrayList<>();
    ArrayList<Integer> terminations = new ArrayList<>();

    // Act
    DataTableLight actualDataTableLight =
        new DataTableLight(
            "Name", TableConvention.MONTHS, maturities, terminations, new ArrayList<>());

    // Assert
    assertEquals("Name", actualDataTableLight.getName());
    assertNull(actualDataTableLight.getReferenceDate());
    assertNull(actualDataTableLight.getScheduleMetaData());
    assertEquals(0, actualDataTableLight.size());
    assertEquals(TableConvention.MONTHS, actualDataTableLight.getConvention());
    TreeSet<Integer> maturities2 = actualDataTableLight.getMaturities();
    assertTrue(maturities2.isEmpty());
    assertEquals(maturities2, actualDataTableLight.getTerminations());
  }

  /**
   * Test {@link DataTableLight#DataTableLight(String, TableConvention, int[], int[], double[])}.
   *
   * <ul>
   *   <li>When array of {@code double} with ten and {@code 0.5}.
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#DataTableLight(String, TableConvention, int[],
   * int[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataTableLight.<init>(String, TableConvention, int[], int[], double[])"})
  public void testNewDataTableLight_whenArrayOfDoubleWithTenAnd05_thenReturnSizeIsTwo() {
    // Arrange and Act
    DataTableLight actualDataTableLight =
        new DataTableLight(
            "Name",
            TableConvention.MONTHS,
            new int[] {1, 0, 1, 0},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertEquals("Name", actualDataTableLight.getName());
    assertNull(actualDataTableLight.getReferenceDate());
    assertNull(actualDataTableLight.getScheduleMetaData());
    TreeSet<Integer> maturities = actualDataTableLight.getMaturities();
    assertEquals(2, maturities.size());
    assertEquals(2, actualDataTableLight.size());
    assertEquals(TableConvention.MONTHS, actualDataTableLight.getConvention());
    assertTrue(maturities.contains(0));
    assertTrue(maturities.contains(1));
    assertEquals(maturities, actualDataTableLight.getTerminations());
  }

  /**
   * Test {@link DataTableLight#DataTableLight(String, TableConvention, int[], int[], double[])}.
   *
   * <ul>
   *   <li>When array of {@code int} with zero and zero.
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#DataTableLight(String, TableConvention, int[],
   * int[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataTableLight.<init>(String, TableConvention, int[], int[], double[])"})
  public void testNewDataTableLight_whenArrayOfIntWithZeroAndZero_thenReturnSizeIsThree() {
    // Arrange and Act
    DataTableLight actualDataTableLight =
        new DataTableLight(
            "Name",
            TableConvention.MONTHS,
            new int[] {0, 0, 1, 0},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertEquals("Name", actualDataTableLight.getName());
    assertNull(actualDataTableLight.getReferenceDate());
    assertNull(actualDataTableLight.getScheduleMetaData());
    TreeSet<Integer> maturities = actualDataTableLight.getMaturities();
    assertEquals(2, maturities.size());
    assertEquals(3, actualDataTableLight.size());
    assertEquals(TableConvention.MONTHS, actualDataTableLight.getConvention());
    assertTrue(maturities.contains(0));
    assertTrue(maturities.contains(1));
    assertEquals(maturities, actualDataTableLight.getTerminations());
  }

  /**
   * Test {@link DataTableLight#DataTableLight(String, TableConvention, int[], int[], double[])}.
   *
   * <ul>
   *   <li>When empty array of {@code double}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#DataTableLight(String, TableConvention, int[],
   * int[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataTableLight.<init>(String, TableConvention, int[], int[], double[])"})
  public void testNewDataTableLight_whenEmptyArrayOfDouble_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new DataTableLight(
                "Name",
                TableConvention.MONTHS,
                new int[] {1, 0, 1, 0},
                new int[] {1, 0, 1, 0},
                new double[] {}));
  }

  /**
   * Test {@link DataTableLight#DataTableLight(String, TableConvention, int[], int[], double[])}.
   *
   * <ul>
   *   <li>When empty array of {@code int}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#DataTableLight(String, TableConvention, int[],
   * int[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataTableLight.<init>(String, TableConvention, int[], int[], double[])"})
  public void testNewDataTableLight_whenEmptyArrayOfInt_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new DataTableLight(
                "Name",
                TableConvention.MONTHS,
                new int[] {},
                new int[] {1, 0, 1, 0},
                new double[] {10.0d, 0.5d, 10.0d, 0.5d}));
  }

  /**
   * Test {@link DataTableLight#addPoint(int, int, double)}.
   *
   * <p>Method under test: {@link DataTableLight#addPoint(int, int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DataTableLight DataTableLight.addPoint(int, int, double)"})
  public void testAddPoint() {
    // Arrange
    DataTableLight dataTableLight =
        new DataTableLight(
            "Name",
            TableConvention.MONTHS,
            new int[] {0, 0, 1, 0},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    DataTableLight actualAddPointResult = dataTableLight.addPoint(2, 1, 10.0d);

    // Assert
    TreeSet<Integer> terminations = actualAddPointResult.getTerminations();
    assertEquals(2, terminations.size());
    TreeSet<Integer> maturities = actualAddPointResult.getMaturities();
    assertEquals(3, maturities.size());
    assertEquals(4, actualAddPointResult.size());
    assertTrue(maturities.contains(2));
    assertTrue(terminations.contains(0));
    assertTrue(terminations.contains(1));
  }

  /**
   * Test {@link DataTableLight#addPoint(int, int, double)}.
   *
   * <p>Method under test: {@link DataTableLight#addPoint(int, int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DataTableLight DataTableLight.addPoint(int, int, double)"})
  public void testAddPoint2() {
    // Arrange
    DataTableLight dataTableLight =
        new DataTableLight(
            "Name",
            TableConvention.MONTHS,
            new int[] {1, 1, 1, 0},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    DataTableLight actualAddPointResult = dataTableLight.addPoint(2, 1, 10.0d);

    // Assert
    TreeSet<Integer> terminations = actualAddPointResult.getTerminations();
    assertEquals(2, terminations.size());
    TreeSet<Integer> maturities = actualAddPointResult.getMaturities();
    assertEquals(3, maturities.size());
    assertEquals(4, actualAddPointResult.size());
    assertTrue(maturities.contains(2));
    assertTrue(terminations.contains(0));
    assertTrue(terminations.contains(1));
  }

  /**
   * Test {@link DataTableLight#addPoint(int, int, double)}.
   *
   * <ul>
   *   <li>Then return Maturities size is one.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#addPoint(int, int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DataTableLight DataTableLight.addPoint(int, int, double)"})
  public void testAddPoint_thenReturnMaturitiesSizeIsOne() {
    // Arrange and Act
    DataTableLight actualAddPointResult =
        new DataTableLight("Name", TableConvention.MONTHS).addPoint(2, 1, 10.0d);

    // Assert
    TreeSet<Integer> maturities = actualAddPointResult.getMaturities();
    assertEquals(1, maturities.size());
    TreeSet<Integer> terminations = actualAddPointResult.getTerminations();
    assertEquals(1, terminations.size());
    assertEquals(1, actualAddPointResult.size());
    assertTrue(maturities.contains(2));
    assertTrue(terminations.contains(1));
  }

  /**
   * Test {@link DataTableLight#addPoint(int, int, double)}.
   *
   * <ul>
   *   <li>Then return Maturities size is two.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#addPoint(int, int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DataTableLight DataTableLight.addPoint(int, int, double)"})
  public void testAddPoint_thenReturnMaturitiesSizeIsTwo() {
    // Arrange
    DataTableLight dataTableLight =
        new DataTableLight(
            "Name",
            TableConvention.MONTHS,
            new int[] {1, 0, 1, 0},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    DataTableLight actualAddPointResult = dataTableLight.addPoint(1, 1, 10.0d);

    // Assert
    TreeSet<Integer> maturities = actualAddPointResult.getMaturities();
    assertEquals(2, maturities.size());
    assertEquals(2, actualAddPointResult.size());
    assertTrue(maturities.contains(0));
    assertTrue(maturities.contains(1));
    assertEquals(maturities, actualAddPointResult.getTerminations());
  }

  /**
   * Test {@link DataTableLight#addPoint(int, int, double)}.
   *
   * <ul>
   *   <li>Then return size is three.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#addPoint(int, int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DataTableLight DataTableLight.addPoint(int, int, double)"})
  public void testAddPoint_thenReturnSizeIsThree() {
    // Arrange
    DataTableLight dataTableLight =
        new DataTableLight(
            "Name",
            TableConvention.MONTHS,
            new int[] {1, 0, 1, 0},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    DataTableLight actualAddPointResult = dataTableLight.addPoint(2, 1, 10.0d);

    // Assert
    TreeSet<Integer> terminations = actualAddPointResult.getTerminations();
    assertEquals(2, terminations.size());
    TreeSet<Integer> maturities = actualAddPointResult.getMaturities();
    assertEquals(3, maturities.size());
    assertEquals(3, actualAddPointResult.size());
    assertTrue(maturities.contains(2));
    assertTrue(terminations.contains(0));
    assertTrue(terminations.contains(1));
  }

  /**
   * Test {@link DataTableLight#addPoints(int[], int[], double[])}.
   *
   * <ul>
   *   <li>Then return Maturities size is three.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#addPoints(int[], int[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DataTableLight DataTableLight.addPoints(int[], int[], double[])"})
  public void testAddPoints_thenReturnMaturitiesSizeIsThree() {
    // Arrange
    DataTableLight dataTableLight =
        new DataTableLight(
            "Name",
            TableConvention.MONTHS,
            new int[] {4, 1, 4, 1},
            new int[] {1, 4, 1, 4},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    DataTableLight actualAddPointsResult =
        dataTableLight.addPoints(
            new int[] {2, 1, 2, 1},
            new int[] {1, 2, 1, 2},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    TreeSet<Integer> maturities = actualAddPointsResult.getMaturities();
    assertEquals(3, maturities.size());
    assertEquals(4, actualAddPointsResult.size());
    assertTrue(maturities.contains(4));
    assertEquals(maturities, actualAddPointsResult.getTerminations());
  }

  /**
   * Test {@link DataTableLight#addPoints(int[], int[], double[])}.
   *
   * <ul>
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#addPoints(int[], int[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DataTableLight DataTableLight.addPoints(int[], int[], double[])"})
  public void testAddPoints_thenReturnName() {
    // Arrange and Act
    DataTableLight actualAddPointsResult =
        new DataTableLight("Name", TableConvention.MONTHS)
            .addPoints(
                new int[] {2, 1, 2, 1},
                new int[] {1, 2, 1, 2},
                new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertEquals("Name", actualAddPointsResult.getName());
    assertNull(actualAddPointsResult.getReferenceDate());
    assertNull(actualAddPointsResult.getScheduleMetaData());
    TreeSet<Integer> maturities = actualAddPointsResult.getMaturities();
    assertEquals(2, maturities.size());
    assertEquals(2, actualAddPointsResult.size());
    assertEquals(TableConvention.MONTHS, actualAddPointsResult.getConvention());
    assertTrue(maturities.contains(1));
    assertTrue(maturities.contains(2));
    assertEquals(maturities, actualAddPointsResult.getTerminations());
  }

  /**
   * Test {@link DataTableLight#getValue(double, double)} with {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#getValue(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DataTableLight.getValue(double, double)"})
  public void testGetValueWithDoubleDouble_when05() {
    // Arrange, Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> new DataTableLight("Name", TableConvention.MONTHS).getValue(0.5d, 10.0d));
  }

  /**
   * Test {@link DataTableLight#getValue(double, double)} with {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#getValue(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DataTableLight.getValue(double, double)"})
  public void testGetValueWithDoubleDouble_when052() {
    // Arrange, Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> new DataTableLight("Name", TableConvention.MONTHS).getValue(-0.5d, 10.0d));
  }

  /**
   * Test {@link DataTableLight#getValue(double, double)} with {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#getValue(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DataTableLight.getValue(double, double)"})
  public void testGetValueWithDoubleDouble_whenNaN() {
    // Arrange, Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> new DataTableLight("Name", TableConvention.MONTHS).getValue(Double.NaN, 10.0d));
  }

  /**
   * Test {@link DataTableLight#getValue(double, double)} with {@code double}, {@code double}.
   *
   * <ul>
   *   <li>When ten.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#getValue(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DataTableLight.getValue(double, double)"})
  public void testGetValueWithDoubleDouble_whenTen() {
    // Arrange, Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> new DataTableLight("Name", TableConvention.MONTHS).getValue(10.0d, 10.0d));
  }

  /**
   * Test {@link DataTableLight#getValue(int, int)} with {@code int}, {@code int}.
   *
   * <p>Method under test: {@link DataTableLight#getValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DataTableLight.getValue(int, int)"})
  public void testGetValueWithIntInt() {
    // Arrange
    DataTableLight dataTableLight =
        new DataTableLight(
            "Name",
            TableConvention.MONTHS,
            new int[] {1, 1, 3, 1},
            new int[] {1, 3, 1, 3},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertEquals(10.0d, dataTableLight.getValue(3, 1), 0.0);
  }

  /**
   * Test {@link DataTableLight#getValue(int, int)} with {@code int}, {@code int}.
   *
   * <p>Method under test: {@link DataTableLight#getValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DataTableLight.getValue(int, int)"})
  public void testGetValueWithIntInt2() {
    // Arrange
    DataTableLight dataTableLight =
        new DataTableLight(
            "Name",
            TableConvention.MONTHS,
            new int[] {1, 1, 3, 1},
            new int[] {1, 3, 1, 3},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertEquals(10.0d, dataTableLight.getValue(1, 1), 0.0);
  }

  /**
   * Test {@link DataTableLight#getValue(int, int)} with {@code int}, {@code int}.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#getValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DataTableLight.getValue(int, int)"})
  public void testGetValueWithIntInt_whenThree_thenReturnTen() {
    // Arrange
    DataTableLight dataTableLight =
        new DataTableLight(
            "Name",
            TableConvention.MONTHS,
            new int[] {3, 1, 3, 1},
            new int[] {1, 3, 1, 3},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertEquals(10.0d, dataTableLight.getValue(3, 1), 0.0);
  }

  /**
   * Test {@link DataTableLight#containsEntryFor(double, double)} with {@code double}, {@code
   * double}.
   *
   * <p>Method under test: {@link DataTableLight#containsEntryFor(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DataTableLight.containsEntryFor(double, double)"})
  public void testContainsEntryForWithDoubleDouble() {
    // Arrange, Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> new DataTableLight("Name", TableConvention.MONTHS).containsEntryFor(10.0d, 10.0d));
  }

  /**
   * Test {@link DataTableLight#containsEntryFor(int, int)} with {@code int}, {@code int}.
   *
   * <p>Method under test: {@link DataTableLight#containsEntryFor(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DataTableLight.containsEntryFor(int, int)"})
  public void testContainsEntryForWithIntInt() {
    // Arrange, Act and Assert
    assertFalse(new DataTableLight("Name", TableConvention.MONTHS).containsEntryFor(1, 1));
  }

  /**
   * Test {@link DataTableLight#containsEntryFor(int, int)} with {@code int}, {@code int}.
   *
   * <p>Method under test: {@link DataTableLight#containsEntryFor(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DataTableLight.containsEntryFor(int, int)"})
  public void testContainsEntryForWithIntInt2() {
    // Arrange
    DataTableLight dataTableLight =
        new DataTableLight(
            "Name",
            TableConvention.MONTHS,
            new int[] {1, 1, 9, 1},
            new int[] {1, 9, 1, 9},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertTrue(dataTableLight.containsEntryFor(1, 1));
  }

  /**
   * Test {@link DataTableLight#containsEntryFor(int, int)} with {@code int}, {@code int}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#containsEntryFor(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DataTableLight.containsEntryFor(int, int)"})
  public void testContainsEntryForWithIntInt_thenReturnFalse() {
    // Arrange
    DataTableLight dataTableLight =
        new DataTableLight(
            "Name",
            TableConvention.MONTHS,
            new int[] {9, 1, 9, 1},
            new int[] {1, 9, 1, 9},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertFalse(dataTableLight.containsEntryFor(1, 1));
  }

  /**
   * Test {@link DataTableLight#containsEntryFor(int, int)} with {@code int}, {@code int}.
   *
   * <ul>
   *   <li>When nine.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#containsEntryFor(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DataTableLight.containsEntryFor(int, int)"})
  public void testContainsEntryForWithIntInt_whenNine_thenReturnTrue() {
    // Arrange
    DataTableLight dataTableLight =
        new DataTableLight(
            "Name",
            TableConvention.MONTHS,
            new int[] {9, 1, 9, 1},
            new int[] {1, 9, 1, 9},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertTrue(dataTableLight.containsEntryFor(1, 9));
  }

  /**
   * Test {@link DataTableLight#getMaturities()}.
   *
   * <p>Method under test: {@link DataTableLight#getMaturities()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TreeSet DataTableLight.getMaturities()"})
  public void testGetMaturities() {
    // Arrange, Act and Assert
    assertTrue(new DataTableLight("Name", TableConvention.MONTHS).getMaturities().isEmpty());
  }

  /**
   * Test {@link DataTableLight#getTerminations()}.
   *
   * <p>Method under test: {@link DataTableLight#getTerminations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TreeSet DataTableLight.getTerminations()"})
  public void testGetTerminations() {
    // Arrange, Act and Assert
    assertTrue(new DataTableLight("Name", TableConvention.MONTHS).getTerminations().isEmpty());
  }

  /**
   * Test {@link DataTableLight#getTerminationsForMaturity(int)}.
   *
   * <ul>
   *   <li>Then return contains one.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#getTerminationsForMaturity(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TreeSet DataTableLight.getTerminationsForMaturity(int)"})
  public void testGetTerminationsForMaturity_thenReturnContainsOne() {
    // Arrange
    DataTableLight dataTableLight =
        new DataTableLight(
            "Name",
            TableConvention.MONTHS,
            new int[] {1, 0, 1, 0},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    TreeSet<Integer> actualTerminationsForMaturity = dataTableLight.getTerminationsForMaturity(1);

    // Assert
    assertEquals(1, actualTerminationsForMaturity.size());
    assertTrue(actualTerminationsForMaturity.contains(1));
  }

  /**
   * Test {@link DataTableLight#getTerminationsForMaturity(int)}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#getTerminationsForMaturity(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TreeSet DataTableLight.getTerminationsForMaturity(int)"})
  public void testGetTerminationsForMaturity_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue(
        new DataTableLight("Name", TableConvention.MONTHS).getTerminationsForMaturity(1).isEmpty());
  }

  /**
   * Test {@link DataTableLight#getTerminationsForMaturity(int)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return contains zero.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#getTerminationsForMaturity(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TreeSet DataTableLight.getTerminationsForMaturity(int)"})
  public void testGetTerminationsForMaturity_whenZero_thenReturnContainsZero() {
    // Arrange
    DataTableLight dataTableLight =
        new DataTableLight(
            "Name",
            TableConvention.MONTHS,
            new int[] {1, 0, 1, 0},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    TreeSet<Integer> actualTerminationsForMaturity = dataTableLight.getTerminationsForMaturity(0);

    // Assert
    assertEquals(1, actualTerminationsForMaturity.size());
    assertTrue(actualTerminationsForMaturity.contains(0));
  }

  /**
   * Test {@link DataTableLight#getMaturitiesForTermination(int)}.
   *
   * <p>Method under test: {@link DataTableLight#getMaturitiesForTermination(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TreeSet DataTableLight.getMaturitiesForTermination(int)"})
  public void testGetMaturitiesForTermination() {
    // Arrange
    DataTableLight dataTableLight =
        new DataTableLight(
            "Name",
            TableConvention.MONTHS,
            new int[] {1, 0, 1, 0},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    TreeSet<Integer> actualMaturitiesForTermination = dataTableLight.getMaturitiesForTermination(1);

    // Assert
    assertEquals(1, actualMaturitiesForTermination.size());
    assertTrue(actualMaturitiesForTermination.contains(1));
  }

  /**
   * Test {@link DataTableLight#getMaturitiesForTermination(int)}.
   *
   * <p>Method under test: {@link DataTableLight#getMaturitiesForTermination(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TreeSet DataTableLight.getMaturitiesForTermination(int)"})
  public void testGetMaturitiesForTermination2() {
    // Arrange
    DataTableLight dataTableLight =
        new DataTableLight(
            "Name",
            TableConvention.MONTHS,
            new int[] {1, 1, 1, 0},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    TreeSet<Integer> actualMaturitiesForTermination = dataTableLight.getMaturitiesForTermination(1);

    // Assert
    assertEquals(1, actualMaturitiesForTermination.size());
    assertTrue(actualMaturitiesForTermination.contains(1));
  }

  /**
   * Test {@link DataTableLight#getMaturitiesForTermination(int)}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#getMaturitiesForTermination(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TreeSet DataTableLight.getMaturitiesForTermination(int)"})
  public void testGetMaturitiesForTermination_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue(
        new DataTableLight("Name", TableConvention.MONTHS)
            .getMaturitiesForTermination(1)
            .isEmpty());
  }

  /**
   * Test {@link DataTableLight#clone()}.
   *
   * <p>Method under test: {@link DataTableLight#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DataTableLight DataTableLight.clone()"})
  public void testClone() {
    // Arrange
    DataTableLight dataTableLight =
        new DataTableLight(
            "Name",
            TableConvention.MONTHS,
            new int[] {0, 0, 1, 0},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    DataTableLight actualCloneResult = dataTableLight.clone();

    // Assert
    TreeSet<Integer> maturities = actualCloneResult.getMaturities();
    assertEquals(2, maturities.size());
    assertEquals(3, actualCloneResult.size());
    assertTrue(maturities.contains(0));
    assertTrue(maturities.contains(1));
    assertEquals(maturities, actualCloneResult.getTerminations());
  }

  /**
   * Test {@link DataTableLight#clone()}.
   *
   * <p>Method under test: {@link DataTableLight#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DataTableLight DataTableLight.clone()"})
  public void testClone2() {
    // Arrange
    DataTableLight dataTableLight =
        new DataTableLight(
            "Name",
            TableConvention.MONTHS,
            new int[] {1, 1, 1, 0},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    DataTableLight actualCloneResult = dataTableLight.clone();

    // Assert
    TreeSet<Integer> maturities = actualCloneResult.getMaturities();
    assertEquals(2, maturities.size());
    assertEquals(3, actualCloneResult.size());
    assertTrue(maturities.contains(0));
    assertTrue(maturities.contains(1));
    assertEquals(maturities, actualCloneResult.getTerminations());
  }

  /**
   * Test {@link DataTableLight#clone()}.
   *
   * <ul>
   *   <li>Given {@link DataTableLight#DataTableLight(String, TableConvention)} with {@code Name}
   *       and tableConvention is {@code MONTHS}.
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DataTableLight DataTableLight.clone()"})
  public void testClone_givenDataTableLightWithNameAndTableConventionIsMonths_thenReturnName() {
    // Arrange and Act
    DataTableLight actualCloneResult = new DataTableLight("Name", TableConvention.MONTHS).clone();

    // Assert
    assertEquals("Name", actualCloneResult.getName());
    assertNull(actualCloneResult.getReferenceDate());
    assertNull(actualCloneResult.getScheduleMetaData());
    assertEquals(0, actualCloneResult.size());
    assertEquals(TableConvention.MONTHS, actualCloneResult.getConvention());
    TreeSet<Integer> maturities = actualCloneResult.getMaturities();
    assertTrue(maturities.isEmpty());
    assertEquals(maturities, actualCloneResult.getTerminations());
  }

  /**
   * Test {@link DataTableLight#clone()}.
   *
   * <ul>
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DataTableLight DataTableLight.clone()"})
  public void testClone_thenReturnSizeIsTwo() {
    // Arrange
    DataTableLight dataTableLight =
        new DataTableLight(
            "Name",
            TableConvention.MONTHS,
            new int[] {1, 0, 1, 0},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    DataTableLight actualCloneResult = dataTableLight.clone();

    // Assert
    TreeSet<Integer> maturities = actualCloneResult.getMaturities();
    assertEquals(2, maturities.size());
    assertEquals(2, actualCloneResult.size());
    assertTrue(maturities.contains(0));
    assertTrue(maturities.contains(1));
    assertEquals(maturities, actualCloneResult.getTerminations());
  }

  /**
   * Test {@link DataTableLight#toString(double)} with {@code double}.
   *
   * <ul>
   *   <li>Then return {@code Name: Name, TableConvention: MONTHS,}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#toString(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataTableLight.toString(double)"})
  public void testToStringWithDouble_thenReturnNameNameTableConventionMonths() {
    // Arrange, Act and Assert
    assertEquals(
        "Name: Name, TableConvention: MONTHS,\n",
        new DataTableLight("Name", TableConvention.MONTHS).toString(10.0d));
  }

  /**
   * Test {@link DataTableLight#toString(double)} with {@code double}.
   *
   * <ul>
   *   <li>Then return {@code Name: Name, TableConvention: MONTHS, 0 1 0 5.0 1 100.0}.
   * </ul>
   *
   * <p>Method under test: {@link DataTableLight#toString(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DataTableLight.toString(double)"})
  public void testToStringWithDouble_thenReturnNameNameTableConventionMonths0105011000() {
    // Arrange
    DataTableLight dataTableLight =
        new DataTableLight(
            "Name",
            TableConvention.MONTHS,
            new int[] {1, 0, 1, 0},
            new int[] {1, 0, 1, 0},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertEquals(
        "Name: Name, TableConvention: MONTHS,\n\t0\t1\n0\t5.0\t\n1\t\t100.0",
        dataTableLight.toString(10.0d));
  }

  /**
   * Test {@link DataTableLight#size()}.
   *
   * <p>Method under test: {@link DataTableLight#size()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DataTableLight.size()"})
  public void testSize() {
    // Arrange, Act and Assert
    assertEquals(0, new DataTableLight("Name", TableConvention.MONTHS).size());
  }
}
