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

public class EuropeanOptionDiffblueTest {
  /**
   * Test {@link EuropeanOption#EuropeanOption(LocalDate, double, boolean)}.
   *
   * <p>Method under test: {@link EuropeanOption#EuropeanOption(LocalDate, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EuropeanOption.<init>(LocalDate, double, boolean)"})
  public void testNewEuropeanOption() {
    // Arrange
    LocalDate expiryDate = LocalDate.of(1970, 1, 1);

    // Act
    EuropeanOption actualEuropeanOption = new EuropeanOption(expiryDate, 10.0d, true);

    // Assert
    assertEquals(10.0d, actualEuropeanOption.getStrike(), 0.0);
    assertFalse(actualEuropeanOption.isAmericanOption());
    assertTrue(actualEuropeanOption.isCallOption());
    assertSame(expiryDate, actualEuropeanOption.getExpiryDate());
  }

  /**
   * Test {@link EuropeanOption#isAmericanOption()}.
   *
   * <p>Method under test: {@link EuropeanOption#isAmericanOption()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean EuropeanOption.isAmericanOption()"})
  public void testIsAmericanOption() {
    // Arrange, Act and Assert
    assertFalse(new EuropeanOption(LocalDate.of(1970, 1, 1), 10.0d, true).isAmericanOption());
  }
}
