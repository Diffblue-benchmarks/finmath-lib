package net.finmath.equities.marketdata;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class VolatilityPointDiffblueTest {
  /**
   * Test {@link VolatilityPoint#VolatilityPoint(LocalDate, double, double)}.
   *
   * <p>Method under test: {@link VolatilityPoint#VolatilityPoint(LocalDate, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void VolatilityPoint.<init>(LocalDate, double, double)"})
  public void testNewVolatilityPoint() {
    // Arrange
    LocalDate date = LocalDate.of(1970, 1, 1);

    // Act
    VolatilityPoint actualVolatilityPoint = new VolatilityPoint(date, 10.0d, 10.0d);

    // Assert
    assertEquals(10.0d, actualVolatilityPoint.getStrike(), 0.0);
    assertEquals(10.0d, actualVolatilityPoint.getVolatility(), 0.0);
    assertSame(date, actualVolatilityPoint.getDate());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link VolatilityPoint#getDate()}
   *   <li>{@link VolatilityPoint#getStrike()}
   *   <li>{@link VolatilityPoint#getVolatility()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LocalDate VolatilityPoint.getDate()",
    "double VolatilityPoint.getStrike()",
    "double VolatilityPoint.getVolatility()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDate date = LocalDate.of(1970, 1, 1);
    VolatilityPoint volatilityPoint = new VolatilityPoint(date, 10.0d, 10.0d);

    // Act
    LocalDate actualDate = volatilityPoint.getDate();
    double actualStrike = volatilityPoint.getStrike();

    // Assert
    assertEquals("1970-01-01", actualDate.toString());
    assertEquals(10.0d, actualStrike, 0.0);
    assertEquals(10.0d, volatilityPoint.getVolatility(), 0.0);
    assertSame(date, actualDate);
  }
}
