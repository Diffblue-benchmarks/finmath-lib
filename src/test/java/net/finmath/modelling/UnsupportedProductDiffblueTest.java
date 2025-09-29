package net.finmath.modelling;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class UnsupportedProductDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link UnsupportedProduct#UnsupportedProduct(Exception)}
   *   <li>{@link UnsupportedProduct#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void UnsupportedProduct.<init>(Exception)",
    "java.lang.String UnsupportedProduct.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals(
        "UnsupportedProduct [exception=java.lang.Exception]",
        new UnsupportedProduct(new Exception()).toString());
  }

  /**
   * Test {@link UnsupportedProduct#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <ul>
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link UnsupportedProduct#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double UnsupportedProduct.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenThrowRuntimeException() {
    // Arrange
    UnsupportedProduct unsupportedProduct = new UnsupportedProduct(new Exception());

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> unsupportedProduct.getValue(10.0d, new AnalyticModelFromCurvesAndVols()));
  }

  /**
   * Test {@link UnsupportedProduct#getValues(double, Model)}.
   *
   * <ul>
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link UnsupportedProduct#getValues(double, Model)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Map UnsupportedProduct.getValues(double, Model)"})
  public void testGetValues_thenThrowRuntimeException() {
    // Arrange
    UnsupportedProduct unsupportedProduct = new UnsupportedProduct(new Exception());

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> unsupportedProduct.getValues(10.0d, new AnalyticModelFromCurvesAndVols()));
  }
}
