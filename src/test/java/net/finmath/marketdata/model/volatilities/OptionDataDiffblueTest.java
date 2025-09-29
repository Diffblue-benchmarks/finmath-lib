package net.finmath.marketdata.model.volatilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.marketdata.model.volatilities.VolatilitySurface.QuotingConvention;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OptionDataDiffblueTest {
  /**
   * Test {@link OptionData#OptionData(String, LocalDate, double, double, double,
   * QuotingConvention)}.
   *
   * <p>Method under test: {@link OptionData#OptionData(String, LocalDate, double, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptionData.<init>(String, LocalDate, double, double, double, QuotingConvention)"
  })
  public void testNewOptionData() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    OptionData actualOptionData =
        new OptionData(
            "Underlying",
            referenceDate,
            10.0d,
            10.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL);

    // Assert
    assertEquals("Underlying", actualOptionData.getUnderlying());
    assertEquals(10.0d, actualOptionData.getMaturity(), 0.0);
    assertEquals(10.0d, actualOptionData.getStrike(), 0.0);
    assertEquals(10.0d, actualOptionData.getValue(), 0.0);
    assertEquals(QuotingConvention.VOLATILITYLOGNORMAL, actualOptionData.getConvention());
    assertSame(referenceDate, actualOptionData.getReferenceDate());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link OptionData#toString()}
   *   <li>{@link OptionData#getConvention()}
   *   <li>{@link OptionData#getMaturity()}
   *   <li>{@link OptionData#getReferenceDate()}
   *   <li>{@link OptionData#getStrike()}
   *   <li>{@link OptionData#getUnderlying()}
   *   <li>{@link OptionData#getValue()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "QuotingConvention OptionData.getConvention()",
    "double OptionData.getMaturity()",
    "LocalDate OptionData.getReferenceDate()",
    "double OptionData.getStrike()",
    "String OptionData.getUnderlying()",
    "double OptionData.getValue()",
    "String OptionData.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    OptionData optionData =
        new OptionData(
            "Underlying",
            referenceDate,
            10.0d,
            10.0d,
            10.0d,
            QuotingConvention.VOLATILITYLOGNORMAL);

    // Act
    String actualToStringResult = optionData.toString();
    QuotingConvention actualConvention = optionData.getConvention();
    double actualMaturity = optionData.getMaturity();
    LocalDate actualReferenceDate = optionData.getReferenceDate();
    double actualStrike = optionData.getStrike();
    String actualUnderlying = optionData.getUnderlying();

    // Assert
    assertEquals("1970-01-01", actualReferenceDate.toString());
    assertEquals(
        "EquityOptionQuote [underlying=Underlying, referenceDate=1970-01-01, strike=10.0, maturity=10.0,"
            + " value=10.0, convention=VOLATILITYLOGNORMAL]",
        actualToStringResult);
    assertEquals("Underlying", actualUnderlying);
    assertEquals(10.0d, actualMaturity, 0.0);
    assertEquals(10.0d, actualStrike, 0.0);
    assertEquals(10.0d, optionData.getValue(), 0.0);
    assertEquals(QuotingConvention.VOLATILITYLOGNORMAL, actualConvention);
    assertSame(referenceDate, actualReferenceDate);
  }
}
