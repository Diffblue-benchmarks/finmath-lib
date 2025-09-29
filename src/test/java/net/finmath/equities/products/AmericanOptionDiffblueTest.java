package net.finmath.equities.products;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AmericanOptionDiffblueTest {
  /**
   * Test {@link AmericanOption#AmericanOption(LocalDate, double, boolean)}.
   *
   * <p>Method under test: {@link AmericanOption#AmericanOption(LocalDate, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AmericanOption.<init>(LocalDate, double, boolean)"})
  public void testNewAmericanOption() {
    // Arrange
    LocalDate expiryDate = LocalDate.of(1970, 1, 1);

    // Act
    AmericanOption actualAmericanOption = new AmericanOption(expiryDate, 10.0d, true);

    // Assert
    assertEquals(10.0d, actualAmericanOption.getStrike(), 0.0);
    assertTrue(actualAmericanOption.isCallOption());
    assertTrue(actualAmericanOption.isAmericanOption());
    assertSame(expiryDate, actualAmericanOption.getExpiryDate());
  }

  /**
   * Test {@link AmericanOption#isAmericanOption()}.
   *
   * <p>Method under test: {@link AmericanOption#isAmericanOption()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AmericanOption.isAmericanOption()"})
  public void testIsAmericanOption() {
    // Arrange, Act and Assert
    assertTrue(new AmericanOption(LocalDate.of(1970, 1, 1), 10.0d, true).isAmericanOption());
  }
}
