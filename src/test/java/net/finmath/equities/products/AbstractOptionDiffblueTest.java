package net.finmath.equities.products;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AbstractOptionDiffblueTest {
  /**
   * Test {@link AbstractOption#getExpiryDate()}.
   *
   * <p>Method under test: {@link AbstractOption#getExpiryDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"LocalDate AbstractOption.getExpiryDate()"})
  public void testGetExpiryDate() {
    // Arrange
    LocalDate expiryDate = LocalDate.of(1970, 1, 1);

    // Act
    LocalDate actualExpiryDate = new AmericanOption(expiryDate, 10.0d, true).getExpiryDate();

    // Assert
    assertEquals("1970-01-01", actualExpiryDate.toString());
    assertSame(expiryDate, actualExpiryDate);
  }

  /**
   * Test {@link AbstractOption#getStrike()}.
   *
   * <p>Method under test: {@link AbstractOption#getStrike()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AbstractOption.getStrike()"})
  public void testGetStrike() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new AmericanOption(LocalDate.of(1970, 1, 1), 10.0d, true).getStrike(), 0.0);
  }

  /**
   * Test {@link AbstractOption#isCallOption()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractOption#isCallOption()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractOption.isCallOption()"})
  public void testIsCallOption_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new AmericanOption(LocalDate.of(1970, 1, 1), 10.0d, false).isCallOption());
  }

  /**
   * Test {@link AbstractOption#isCallOption()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractOption#isCallOption()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractOption.isCallOption()"})
  public void testIsCallOption_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new AmericanOption(LocalDate.of(1970, 1, 1), 10.0d, true).isCallOption());
  }

  /**
   * Test {@link AbstractOption#callPutFactor()}.
   *
   * <ul>
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link AbstractOption#callPutFactor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AbstractOption.callPutFactor()"})
  public void testCallPutFactor_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(
        -1.0d, new AmericanOption(LocalDate.of(1970, 1, 1), 10.0d, false).callPutFactor(), 0.0);
  }

  /**
   * Test {@link AbstractOption#callPutFactor()}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link AbstractOption#callPutFactor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AbstractOption.callPutFactor()"})
  public void testCallPutFactor_thenReturnOne() {
    // Arrange, Act and Assert
    assertEquals(
        1.0d, new AmericanOption(LocalDate.of(1970, 1, 1), 10.0d, true).callPutFactor(), 0.0);
  }

  /**
   * Test {@link AbstractOption#getPayoff(double)}.
   *
   * <p>Method under test: {@link AbstractOption#getPayoff(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AbstractOption.getPayoff(double)"})
  public void testGetPayoff() {
    // Arrange, Act and Assert
    assertEquals(
        0.0d, new AmericanOption(LocalDate.of(1970, 1, 1), 10.0d, true).getPayoff(10.0d), 0.0);
  }

  /**
   * Test {@link AbstractOption#getPayoff(double)}.
   *
   * <p>Method under test: {@link AbstractOption#getPayoff(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AbstractOption.getPayoff(double)"})
  public void testGetPayoff2() {
    // Arrange, Act and Assert
    assertEquals(
        0.0d, new AmericanOption(LocalDate.of(1970, 1, 1), 10.0d, false).getPayoff(10.0d), 0.0);
  }
}
