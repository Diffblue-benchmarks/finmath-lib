package net.finmath.montecarlo.interestrate.products.indices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.montecarlo.interestrate.products.indices.DateIndex.DateIndexType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DateIndexDiffblueTest {
  /**
   * Test {@link DateIndex#DateIndex(String, String, DateIndexType)}.
   *
   * <ul>
   *   <li>When {@code GBP}.
   *   <li>Then return Currency is {@code GBP}.
   * </ul>
   *
   * <p>Method under test: {@link DateIndex#DateIndex(String, String, DateIndexType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DateIndex.<init>(String, String, DateIndexType)",
    "void DateIndex.<init>(String, DateIndexType)"
  })
  public void testNewDateIndex_whenGbp_thenReturnCurrencyIsGbp() {
    // Arrange and Act
    DateIndex actualDateIndex = new DateIndex("Name", "GBP", DateIndexType.DAY);

    // Assert
    assertEquals("GBP", actualDateIndex.getCurrency());
    assertEquals("Name", actualDateIndex.getName());
  }

  /**
   * Test {@link DateIndex#DateIndex(String, DateIndexType)}.
   *
   * <ul>
   *   <li>When {@code Name}.
   *   <li>Then return Currency is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DateIndex#DateIndex(String, DateIndexType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DateIndex.<init>(String, String, DateIndexType)",
    "void DateIndex.<init>(String, DateIndexType)"
  })
  public void testNewDateIndex_whenName_thenReturnCurrencyIsNull() {
    // Arrange and Act
    DateIndex actualDateIndex = new DateIndex("Name", DateIndexType.DAY);

    // Assert
    assertEquals("Name", actualDateIndex.getName());
    assertNull(actualDateIndex.getCurrency());
  }

  /**
   * Test {@link DateIndex#queryUnderlyings()}.
   *
   * <p>Method under test: {@link DateIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Set DateIndex.queryUnderlyings()"})
  public void testQueryUnderlyings() {
    // Arrange, Act and Assert
    assertNull(new DateIndex("Name", DateIndexType.DAY).queryUnderlyings());
  }
}
