package net.finmath.marketdata.model.volatilities;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.HashMap;
import net.finmath.marketdata.model.volatilities.VolatilitySurface.QuotingConvention;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OptionSmileDataDiffblueTest {
  /**
   * Test {@link OptionSmileData#OptionSmileData(String, LocalDate, double[], double, double[],
   * QuotingConvention)}.
   *
   * <ul>
   *   <li>Then return Smile size is two.
   * </ul>
   *
   * <p>Method under test: {@link OptionSmileData#OptionSmileData(String, LocalDate, double[],
   * double, double[], QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptionSmileData.<init>(String, LocalDate, double[], double, double[], QuotingConvention)"
  })
  public void testNewOptionSmileData_thenReturnSmileSizeIsTwo() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    OptionSmileData actualOptionSmileData =
        new OptionSmileData(
            "Underlying",
            referenceDate,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.VOLATILITYLOGNORMAL);

    // Assert
    HashMap<Double, OptionData> smile = actualOptionSmileData.getSmile();
    assertEquals(2, smile.size());
    OptionData getResult = smile.get(0.5d);
    assertEquals("Underlying", getResult.getUnderlying());
    OptionData getResult2 = smile.get(10.0d);
    assertEquals("Underlying", getResult2.getUnderlying());
    assertEquals("Underlying", actualOptionSmileData.getUnderlying());
    assertEquals(0.5d, getResult.getStrike(), 0.0);
    assertEquals(0.5d, getResult.getValue(), 0.0);
    assertEquals(10.0d, getResult.getMaturity(), 0.0);
    assertEquals(10.0d, getResult2.getMaturity(), 0.0);
    assertEquals(10.0d, getResult2.getStrike(), 0.0);
    assertEquals(10.0d, getResult2.getValue(), 0.0);
    assertEquals(10.0d, actualOptionSmileData.getMaturity(), 0.0);
    assertEquals(QuotingConvention.VOLATILITYLOGNORMAL, getResult.getConvention());
    assertEquals(QuotingConvention.VOLATILITYLOGNORMAL, getResult2.getConvention());
    assertSame(referenceDate, getResult.getReferenceDate());
    assertSame(referenceDate, getResult2.getReferenceDate());
    assertSame(referenceDate, actualOptionSmileData.getReferenceDate());
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d, 0.5d}, actualOptionSmileData.getStrikes(), 0.0);
  }

  /**
   * Test {@link OptionSmileData#OptionSmileData(String, LocalDate, double[], double, double[],
   * QuotingConvention)}.
   *
   * <ul>
   *   <li>When empty array of {@code double}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link OptionSmileData#OptionSmileData(String, LocalDate, double[],
   * double, double[], QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OptionSmileData.<init>(String, LocalDate, double[], double, double[], QuotingConvention)"
  })
  public void testNewOptionSmileData_whenEmptyArrayOfDouble_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new OptionSmileData(
                "Underlying",
                LocalDate.of(1970, 1, 1),
                new double[] {},
                10.0d,
                new double[] {10.0d, 0.5d, 10.0d, 0.5d},
                QuotingConvention.VOLATILITYLOGNORMAL));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link OptionSmileData#toString()}
   *   <li>{@link OptionSmileData#getMaturity()}
   *   <li>{@link OptionSmileData#getReferenceDate()}
   *   <li>{@link OptionSmileData#getSmile()}
   *   <li>{@link OptionSmileData#getStrikes()}
   *   <li>{@link OptionSmileData#getUnderlying()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double OptionSmileData.getMaturity()",
    "LocalDate OptionSmileData.getReferenceDate()",
    "HashMap OptionSmileData.getSmile()",
    "double[] OptionSmileData.getStrikes()",
    "String OptionSmileData.getUnderlying()",
    "String OptionSmileData.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "Underlying",
            referenceDate,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.VOLATILITYLOGNORMAL);

    // Act
    String actualToStringResult = optionSmileData.toString();
    double actualMaturity = optionSmileData.getMaturity();
    LocalDate actualReferenceDate = optionSmileData.getReferenceDate();
    HashMap<Double, OptionData> actualSmile = optionSmileData.getSmile();
    double[] actualStrikes = optionSmileData.getStrikes();

    // Assert
    assertEquals("1970-01-01", actualReferenceDate.toString());
    assertEquals(
        "EquityOptionSmile [underlying=Underlying, strikes=[10.0, 0.5, 10.0, 0.5], maturity=10.0, smile={0.5"
            + "=EquityOptionQuote [underlying=Underlying, referenceDate=1970-01-01, strike=0.5, maturity=10.0,"
            + " value=0.5, convention=VOLATILITYLOGNORMAL], 10.0=EquityOptionQuote [underlying=Underlying,"
            + " referenceDate=1970-01-01, strike=10.0, maturity=10.0, value=10.0, convention=VOLATILITYLOGNORMAL]}]",
        actualToStringResult);
    assertEquals(2, actualSmile.size());
    OptionData getResult = actualSmile.get(0.5d);
    assertEquals("Underlying", getResult.getUnderlying());
    OptionData getResult2 = actualSmile.get(10.0d);
    assertEquals("Underlying", getResult2.getUnderlying());
    assertEquals("Underlying", optionSmileData.getUnderlying());
    assertEquals(0.5d, getResult.getStrike(), 0.0);
    assertEquals(0.5d, getResult.getValue(), 0.0);
    assertEquals(10.0d, getResult.getMaturity(), 0.0);
    assertEquals(10.0d, getResult2.getMaturity(), 0.0);
    assertEquals(10.0d, getResult2.getStrike(), 0.0);
    assertEquals(10.0d, getResult2.getValue(), 0.0);
    assertEquals(10.0d, actualMaturity, 0.0);
    assertEquals(QuotingConvention.VOLATILITYLOGNORMAL, getResult.getConvention());
    assertEquals(QuotingConvention.VOLATILITYLOGNORMAL, getResult2.getConvention());
    assertSame(referenceDate, getResult.getReferenceDate());
    assertSame(referenceDate, getResult2.getReferenceDate());
    assertSame(referenceDate, actualReferenceDate);
    assertArrayEquals(new double[] {10.0d, 0.5d, 10.0d, 0.5d}, actualStrikes, 0.0);
  }

  /**
   * Test {@link OptionSmileData#getOption(double)}.
   *
   * <p>Method under test: {@link OptionSmileData#getOption(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OptionData OptionSmileData.getOption(double)"})
  public void testGetOption() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    OptionSmileData optionSmileData =
        new OptionSmileData(
            "Underlying",
            referenceDate,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            10.0d,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            QuotingConvention.VOLATILITYLOGNORMAL);

    // Act
    OptionData actualOption = optionSmileData.getOption(10.0d);

    // Assert
    LocalDate referenceDate2 = actualOption.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Underlying", actualOption.getUnderlying());
    assertEquals(10.0d, actualOption.getMaturity(), 0.0);
    assertEquals(10.0d, actualOption.getStrike(), 0.0);
    assertEquals(10.0d, actualOption.getValue(), 0.0);
    assertEquals(QuotingConvention.VOLATILITYLOGNORMAL, actualOption.getConvention());
    assertSame(referenceDate, referenceDate2);
  }
}
