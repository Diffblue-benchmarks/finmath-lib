package net.finmath.montecarlo.interestrate.products.indices;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Set;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.interestrate.LIBORMonteCarloSimulationFromLIBORModel;
import net.finmath.montecarlo.interestrate.TermStructureMonteCarloSimulationModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class UnsupportedIndexDiffblueTest {
  /**
   * Test {@link UnsupportedIndex#UnsupportedIndex(Exception)}.
   *
   * <ul>
   *   <li>When {@link Exception#Exception()}.
   *   <li>Then return Name is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link UnsupportedIndex#UnsupportedIndex(Exception)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void UnsupportedIndex.<init>(Exception)",
    "void UnsupportedIndex.<init>(String, Exception)"
  })
  public void testNewUnsupportedIndex_whenException_thenReturnNameIsNull() {
    // Arrange and Act
    UnsupportedIndex actualUnsupportedIndex = new UnsupportedIndex(new Exception());

    // Assert
    assertNull(actualUnsupportedIndex.getCurrency());
    assertNull(actualUnsupportedIndex.getName());
  }

  /**
   * Test {@link UnsupportedIndex#UnsupportedIndex(String, Exception)}.
   *
   * <ul>
   *   <li>When {@code Name}.
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link UnsupportedIndex#UnsupportedIndex(String, Exception)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void UnsupportedIndex.<init>(Exception)",
    "void UnsupportedIndex.<init>(String, Exception)"
  })
  public void testNewUnsupportedIndex_whenName_thenReturnName() {
    // Arrange and Act
    UnsupportedIndex actualUnsupportedIndex = new UnsupportedIndex("Name", new Exception());

    // Assert
    assertEquals("Name", actualUnsupportedIndex.getName());
    assertNull(actualUnsupportedIndex.getCurrency());
  }

  /**
   * Test {@link UnsupportedIndex#getValue(double, TermStructureMonteCarloSimulationModel)} with
   * {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link UnsupportedIndex#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.stochastic.RandomVariable UnsupportedIndex.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    UnsupportedIndex unsupportedIndex = new UnsupportedIndex(new Exception());

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act and Assert
    assertThrows(
        CalculationException.class,
        () ->
            unsupportedIndex.getValue(10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process)));
    verify(brownianMotion).getTimeDiscretization();
  }

  /**
   * Test {@link UnsupportedIndex#queryUnderlyings()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link UnsupportedIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set UnsupportedIndex.queryUnderlyings()"})
  public void testQueryUnderlyings_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new UnsupportedIndex(new Exception()).queryUnderlyings());
  }

  /**
   * Test {@link UnsupportedIndex#queryUnderlyings()}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link UnsupportedIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set UnsupportedIndex.queryUnderlyings()"})
  public void testQueryUnderlyings_thenReturnSizeIsOne() {
    // Arrange
    UnsupportedIndex unsupportedIndex = new UnsupportedIndex("Name", new Exception());

    // Act
    Set<String> actualQueryUnderlyingsResult = unsupportedIndex.queryUnderlyings();

    // Assert
    assertEquals(1, actualQueryUnderlyingsResult.size());
    assertTrue(actualQueryUnderlyingsResult.contains("Name"));
  }
}
