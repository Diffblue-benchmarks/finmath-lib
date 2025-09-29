package net.finmath.equities.pricer;

import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.ArrayList;
import net.finmath.equities.pricer.EquityValuationRequest.CalculationRequestType;
import net.finmath.equities.products.AmericanOption;
import net.finmath.equities.products.Option;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class EquityValuationRequestDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link EquityValuationRequest#EquityValuationRequest(Option, ArrayList)}
   *   <li>{@link EquityValuationRequest#getCalcsRequested()}
   *   <li>{@link EquityValuationRequest#getOption()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void EquityValuationRequest.<init>(Option, ArrayList)",
    "ArrayList EquityValuationRequest.getCalcsRequested()",
    "Option EquityValuationRequest.getOption()"
  })
  public void testGettersAndSetters() {
    // Arrange
    AmericanOption option = new AmericanOption(LocalDate.of(1970, 1, 1), 10.0d, true);
    ArrayList<CalculationRequestType> calcsRequested = new ArrayList<>();

    // Act
    EquityValuationRequest actualEquityValuationRequest =
        new EquityValuationRequest(option, calcsRequested);
    ArrayList<CalculationRequestType> actualCalcsRequested =
        actualEquityValuationRequest.getCalcsRequested();

    // Assert
    assertSame(calcsRequested, actualCalcsRequested);
    assertSame(option, actualEquityValuationRequest.getOption());
  }
}
