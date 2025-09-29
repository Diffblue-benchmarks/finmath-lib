package net.finmath.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.marketdata.model.curves.Curve;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CSVCurveParserDiffblueTest {
  /**
   * Test {@link CSVCurveParser#getReferenceDates(Curve[])}.
   *
   * <ul>
   *   <li>Then return first element is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CSVCurveParser#getReferenceDates(Curve[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"LocalDate[] CSVCurveParser.getReferenceDates(Curve[])"})
  public void testGetReferenceDates_thenReturnFirstElementIsNull() {
    // Arrange and Act
    LocalDate[] actualReferenceDates =
        CSVCurveParser.getReferenceDates(
            new Curve[] {new DiscountCurveFromForwardCurve("Forward Curve Name")});

    // Assert
    assertNull(actualReferenceDates[0]);
    assertEquals(1, actualReferenceDates.length);
  }
}
