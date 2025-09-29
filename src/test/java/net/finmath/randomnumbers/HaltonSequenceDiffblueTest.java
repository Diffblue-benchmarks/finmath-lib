package net.finmath.randomnumbers;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class HaltonSequenceDiffblueTest {
  /**
   * Test {@link HaltonSequence#HaltonSequence(int[])}.
   *
   * <ul>
   *   <li>When array of {@code int} with four and one.
   * </ul>
   *
   * <p>Method under test: {@link HaltonSequence#HaltonSequence(int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void HaltonSequence.<init>(int[])"})
  public void testNewHaltonSequence_whenArrayOfIntWithFourAndOne() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new HaltonSequence(new int[] {4, 1, 4, 1, 4, 1, 4, 1}));
  }

  /**
   * Test {@link HaltonSequence#HaltonSequence(int[])}.
   *
   * <ul>
   *   <li>When array of {@code int} with one and zero.
   * </ul>
   *
   * <p>Method under test: {@link HaltonSequence#HaltonSequence(int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void HaltonSequence.<init>(int[])"})
  public void testNewHaltonSequence_whenArrayOfIntWithOneAndZero() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new HaltonSequence(new int[] {1, 0, 1, 0}));
  }

  /**
   * Test {@link HaltonSequence#HaltonSequence(int[])}.
   *
   * <ul>
   *   <li>When empty array of {@code int}.
   *   <li>Then return Dimension is zero.
   * </ul>
   *
   * <p>Method under test: {@link HaltonSequence#HaltonSequence(int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void HaltonSequence.<init>(int[])"})
  public void testNewHaltonSequence_whenEmptyArrayOfInt_thenReturnDimensionIsZero() {
    // Arrange and Act
    HaltonSequence actualHaltonSequence = new HaltonSequence(new int[] {});

    // Assert
    assertEquals(0, actualHaltonSequence.getDimension());
    assertArrayEquals(new double[] {}, actualHaltonSequence.getNext(), 0.0);
  }

  /**
   * Test {@link HaltonSequence#getNext()}.
   *
   * <ul>
   *   <li>Then return empty array of {@code double}.
   * </ul>
   *
   * <p>Method under test: {@link HaltonSequence#getNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] HaltonSequence.getNext()"})
  public void testGetNext_thenReturnEmptyArrayOfDouble() {
    // Arrange
    HaltonSequence haltonSequence = new HaltonSequence(new int[] {});

    // Act and Assert
    assertArrayEquals(new double[] {}, haltonSequence.getNext(), 0.0);
  }

  /**
   * Test {@link HaltonSequence#getDimension()}.
   *
   * <ul>
   *   <li>Given {@link HaltonSequence#HaltonSequence(int[])} with base is empty array of {@code
   *       int}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link HaltonSequence#getDimension()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int HaltonSequence.getDimension()"})
  public void testGetDimension_givenHaltonSequenceWithBaseIsEmptyArrayOfInt_thenReturnZero() {
    // Arrange
    HaltonSequence haltonSequence = new HaltonSequence(new int[] {});

    // Act and Assert
    assertEquals(0, haltonSequence.getDimension());
  }

  /**
   * Test {@link HaltonSequence#getHaltonNumber(long, int)} with {@code index}, {@code dimension}.
   *
   * <ul>
   *   <li>When five.
   *   <li>Then return {@code 0.2222222222222222}.
   * </ul>
   *
   * <p>Method under test: {@link HaltonSequence#getHaltonNumber(long, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double HaltonSequence.getHaltonNumber(long, int)"})
  public void testGetHaltonNumberWithIndexDimension_whenFive_thenReturn02222222222222222() {
    // Arrange
    HaltonSequence haltonSequence = new HaltonSequence(new int[] {3, 3, 3, 3});

    // Act and Assert
    assertEquals(0.2222222222222222d, haltonSequence.getHaltonNumber(5L, 3), 0.0);
  }

  /**
   * Test {@link HaltonSequence#getHaltonNumber(long, int)} with {@code index}, {@code dimension}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 0.6666666666666666}.
   * </ul>
   *
   * <p>Method under test: {@link HaltonSequence#getHaltonNumber(long, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double HaltonSequence.getHaltonNumber(long, int)"})
  public void testGetHaltonNumberWithIndexDimension_whenOne_thenReturn06666666666666666() {
    // Arrange
    HaltonSequence haltonSequence = new HaltonSequence(new int[] {3, 3, 3, 3});

    // Act and Assert
    assertEquals(0.6666666666666666d, haltonSequence.getHaltonNumber(1L, 3), 0.0);
  }

  /**
   * Test {@link HaltonSequence#getHaltonNumber(long, int)} with {@code index}, {@code dimension}.
   *
   * <ul>
   *   <li>When six.
   *   <li>Then return {@code 0.5555555555555556}.
   * </ul>
   *
   * <p>Method under test: {@link HaltonSequence#getHaltonNumber(long, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double HaltonSequence.getHaltonNumber(long, int)"})
  public void testGetHaltonNumberWithIndexDimension_whenSix_thenReturn05555555555555556() {
    // Arrange
    HaltonSequence haltonSequence = new HaltonSequence(new int[] {3, 3, 3, 3});

    // Act and Assert
    assertEquals(0.5555555555555556d, haltonSequence.getHaltonNumber(6L, 3), 0.0);
  }

  /**
   * Test {@link HaltonSequence#getHaltonNumber(long, int)} with {@code index}, {@code dimension}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@code 0.1111111111111111}.
   * </ul>
   *
   * <p>Method under test: {@link HaltonSequence#getHaltonNumber(long, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double HaltonSequence.getHaltonNumber(long, int)"})
  public void testGetHaltonNumberWithIndexDimension_whenTwo_thenReturn01111111111111111() {
    // Arrange
    HaltonSequence haltonSequence = new HaltonSequence(new int[] {3, 3, 3, 3});

    // Act and Assert
    assertEquals(0.1111111111111111d, haltonSequence.getHaltonNumber(2L, 3), 0.0);
  }

  /**
   * Test {@link HaltonSequence#getHaltonNumber(long)} with {@code index}.
   *
   * <ul>
   *   <li>Then return empty array of {@code double}.
   * </ul>
   *
   * <p>Method under test: {@link HaltonSequence#getHaltonNumber(long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] HaltonSequence.getHaltonNumber(long)"})
  public void testGetHaltonNumberWithIndex_thenReturnEmptyArrayOfDouble() {
    // Arrange
    HaltonSequence haltonSequence = new HaltonSequence(new int[] {});

    // Act and Assert
    assertArrayEquals(new double[] {}, haltonSequence.getHaltonNumber(1L), 0.0);
  }

  /**
   * Test {@link HaltonSequence#getHaltonNumberForGivenBase(long, int)}.
   *
   * <ul>
   *   <li>When five.
   *   <li>Then return {@code 0.2222222222222222}.
   * </ul>
   *
   * <p>Method under test: {@link HaltonSequence#getHaltonNumberForGivenBase(long, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double HaltonSequence.getHaltonNumberForGivenBase(long, int)"})
  public void testGetHaltonNumberForGivenBase_whenFive_thenReturn02222222222222222() {
    // Arrange, Act and Assert
    assertEquals(0.2222222222222222d, HaltonSequence.getHaltonNumberForGivenBase(5L, 3), 0.0);
  }

  /**
   * Test {@link HaltonSequence#getHaltonNumberForGivenBase(long, int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 0.6666666666666666}.
   * </ul>
   *
   * <p>Method under test: {@link HaltonSequence#getHaltonNumberForGivenBase(long, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double HaltonSequence.getHaltonNumberForGivenBase(long, int)"})
  public void testGetHaltonNumberForGivenBase_whenOne_thenReturn06666666666666666() {
    // Arrange, Act and Assert
    assertEquals(0.6666666666666666d, HaltonSequence.getHaltonNumberForGivenBase(1L, 3), 0.0);
  }

  /**
   * Test {@link HaltonSequence#getHaltonNumberForGivenBase(long, int)}.
   *
   * <ul>
   *   <li>When six.
   *   <li>Then return {@code 0.5555555555555556}.
   * </ul>
   *
   * <p>Method under test: {@link HaltonSequence#getHaltonNumberForGivenBase(long, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double HaltonSequence.getHaltonNumberForGivenBase(long, int)"})
  public void testGetHaltonNumberForGivenBase_whenSix_thenReturn05555555555555556() {
    // Arrange, Act and Assert
    assertEquals(0.5555555555555556d, HaltonSequence.getHaltonNumberForGivenBase(6L, 3), 0.0);
  }

  /**
   * Test {@link HaltonSequence#getHaltonNumberForGivenBase(long, int)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@code 0.1111111111111111}.
   * </ul>
   *
   * <p>Method under test: {@link HaltonSequence#getHaltonNumberForGivenBase(long, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double HaltonSequence.getHaltonNumberForGivenBase(long, int)"})
  public void testGetHaltonNumberForGivenBase_whenTwo_thenReturn01111111111111111() {
    // Arrange, Act and Assert
    assertEquals(0.1111111111111111d, HaltonSequence.getHaltonNumberForGivenBase(2L, 3), 0.0);
  }
}
