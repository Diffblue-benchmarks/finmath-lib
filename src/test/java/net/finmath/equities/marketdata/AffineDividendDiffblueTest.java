package net.finmath.equities.marketdata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AffineDividendDiffblueTest {
  /**
   * Test {@link AffineDividend#AffineDividend(LocalDate, double, double)}.
   *
   * <p>Method under test: {@link AffineDividend#AffineDividend(LocalDate, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AffineDividend.<init>(LocalDate, double, double)"})
  public void testNewAffineDividend() {
    // Arrange
    LocalDate date = LocalDate.of(1970, 1, 1);

    // Act
    AffineDividend actualAffineDividend = new AffineDividend(date, 10.0d, 10.0d);

    // Assert
    assertEquals(10.0d, actualAffineDividend.getCashDividend(), 0.0);
    assertEquals(10.0d, actualAffineDividend.getProportionalDividendFactor(), 0.0);
    assertSame(date, actualAffineDividend.getDate());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AffineDividend#getCashDividend()}
   *   <li>{@link AffineDividend#getDate()}
   *   <li>{@link AffineDividend#getProportionalDividendFactor()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double AffineDividend.getCashDividend()",
    "LocalDate AffineDividend.getDate()",
    "double AffineDividend.getProportionalDividendFactor()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDate date = LocalDate.of(1970, 1, 1);
    AffineDividend affineDividend = new AffineDividend(date, 10.0d, 10.0d);

    // Act
    double actualCashDividend = affineDividend.getCashDividend();
    LocalDate actualDate = affineDividend.getDate();

    // Assert
    assertEquals("1970-01-01", actualDate.toString());
    assertEquals(10.0d, actualCashDividend, 0.0);
    assertEquals(10.0d, affineDividend.getProportionalDividendFactor(), 0.0);
    assertSame(date, actualDate);
  }

  /**
   * Test {@link AffineDividend#getDividend(double)}.
   *
   * <p>Method under test: {@link AffineDividend#getDividend(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AffineDividend.getDividend(double)"})
  public void testGetDividend() {
    // Arrange, Act and Assert
    assertEquals(
        110.0d, new AffineDividend(LocalDate.of(1970, 1, 1), 10.0d, 10.0d).getDividend(10.0d), 0.0);
  }
}
