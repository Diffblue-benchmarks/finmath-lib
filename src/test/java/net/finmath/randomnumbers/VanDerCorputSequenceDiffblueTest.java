package net.finmath.randomnumbers;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class VanDerCorputSequenceDiffblueTest {
  /**
   * Test {@link VanDerCorputSequence#VanDerCorputSequence(int)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return nextDouble is {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link VanDerCorputSequence#VanDerCorputSequence(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void VanDerCorputSequence.<init>(int)"})
  public void testNewVanDerCorputSequence_whenTwo_thenReturnNextDoubleIs05() {
    // Arrange and Act
    VanDerCorputSequence actualVanDerCorputSequence = new VanDerCorputSequence(2);

    // Assert
    assertEquals(0.5d, actualVanDerCorputSequence.nextDouble(), 0.0);
    assertEquals(1, actualVanDerCorputSequence.getDimension());
  }

  /**
   * Test {@link VanDerCorputSequence#VanDerCorputSequence(int, int)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return nextDouble is {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link VanDerCorputSequence#VanDerCorputSequence(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void VanDerCorputSequence.<init>(int, int)"})
  public void testNewVanDerCorputSequence_whenZero_thenReturnNextDoubleIs05() {
    // Arrange and Act
    VanDerCorputSequence actualVanDerCorputSequence = new VanDerCorputSequence(0, 2);

    // Assert
    assertEquals(0.5d, actualVanDerCorputSequence.nextDouble(), 0.0);
    assertEquals(1, actualVanDerCorputSequence.getDimension());
  }

  /**
   * Test {@link VanDerCorputSequence#nextDouble()}.
   *
   * <ul>
   *   <li>Given {@link VanDerCorputSequence#VanDerCorputSequence(int)} with base is two.
   *   <li>Then return {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link VanDerCorputSequence#nextDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double VanDerCorputSequence.nextDouble()"})
  public void testNextDouble_givenVanDerCorputSequenceWithBaseIsTwo_thenReturn05() {
    // Arrange, Act and Assert
    assertEquals(0.5d, new VanDerCorputSequence(2).nextDouble(), 0.0);
  }

  /**
   * Test {@link VanDerCorputSequence#getVanDerCorputNumber(long, int)}.
   *
   * <ul>
   *   <li>When five.
   *   <li>Then return {@code 0.2222222222222222}.
   * </ul>
   *
   * <p>Method under test: {@link VanDerCorputSequence#getVanDerCorputNumber(long, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double VanDerCorputSequence.getVanDerCorputNumber(long, int)"})
  public void testGetVanDerCorputNumber_whenFive_thenReturn02222222222222222() {
    // Arrange, Act and Assert
    assertEquals(0.2222222222222222d, VanDerCorputSequence.getVanDerCorputNumber(5L, 3), 0.0);
  }

  /**
   * Test {@link VanDerCorputSequence#getVanDerCorputNumber(long, int)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 0.6666666666666666}.
   * </ul>
   *
   * <p>Method under test: {@link VanDerCorputSequence#getVanDerCorputNumber(long, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double VanDerCorputSequence.getVanDerCorputNumber(long, int)"})
  public void testGetVanDerCorputNumber_whenOne_thenReturn06666666666666666() {
    // Arrange, Act and Assert
    assertEquals(0.6666666666666666d, VanDerCorputSequence.getVanDerCorputNumber(1L, 3), 0.0);
  }

  /**
   * Test {@link VanDerCorputSequence#getVanDerCorputNumber(long, int)}.
   *
   * <ul>
   *   <li>When six.
   *   <li>Then return {@code 0.5555555555555556}.
   * </ul>
   *
   * <p>Method under test: {@link VanDerCorputSequence#getVanDerCorputNumber(long, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double VanDerCorputSequence.getVanDerCorputNumber(long, int)"})
  public void testGetVanDerCorputNumber_whenSix_thenReturn05555555555555556() {
    // Arrange, Act and Assert
    assertEquals(0.5555555555555556d, VanDerCorputSequence.getVanDerCorputNumber(6L, 3), 0.0);
  }

  /**
   * Test {@link VanDerCorputSequence#getVanDerCorputNumber(long, int)}.
   *
   * <ul>
   *   <li>When two.
   *   <li>Then return {@code 0.1111111111111111}.
   * </ul>
   *
   * <p>Method under test: {@link VanDerCorputSequence#getVanDerCorputNumber(long, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double VanDerCorputSequence.getVanDerCorputNumber(long, int)"})
  public void testGetVanDerCorputNumber_whenTwo_thenReturn01111111111111111() {
    // Arrange, Act and Assert
    assertEquals(0.1111111111111111d, VanDerCorputSequence.getVanDerCorputNumber(2L, 3), 0.0);
  }
}
