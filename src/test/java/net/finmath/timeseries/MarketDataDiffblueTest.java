package net.finmath.timeseries;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MarketDataDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link MarketData#MarketData(Calendar, Map)}
   *   <li>{@link MarketData#getDate()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MarketData.<init>(Calendar, Map)", "Calendar MarketData.getDate()"})
  public void testGettersAndSetters() {
    // Arrange
    GregorianCalendar date = new GregorianCalendar(1, 1, 1);

    // Act
    MarketData actualMarketData = new MarketData(date, new HashMap<>());

    // Assert
    assertSame(date, actualMarketData.getDate());
  }

  /**
   * Test {@link MarketData#getValue(String)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code 42} is {@code 0.5}.
   *   <li>When {@code 42}.
   *   <li>Then return {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link MarketData#getValue(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double MarketData.getValue(String)"})
  public void testGetValue_givenHashMap42Is05_when42_thenReturn05() {
    // Arrange
    HashMap<String, Double> valuesForSymbols = new HashMap<>();
    valuesForSymbols.put("42", 0.5d);
    MarketData marketData = new MarketData(new GregorianCalendar(1, 1, 1), valuesForSymbols);

    // Act and Assert
    assertEquals(0.5d, marketData.getValue("42"), 0.0);
  }
}
