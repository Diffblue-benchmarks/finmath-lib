package net.finmath.montecarlo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.modelling.Model;
import net.finmath.montecarlo.interestrate.products.ForwardRateVolatilitySurfaceCurvature;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AbstractMonteCarloProductDiffblueTest {
  /**
   * Test {@link AbstractMonteCarloProduct#getValue(double, Model)} with {@code evaluationTime},
   * {@code model}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractMonteCarloProduct#getValue(double, Model)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object AbstractMonteCarloProduct.getValue(double, Model)"})
  public void testGetValueWithEvaluationTimeModel_thenThrowIllegalArgumentException() {
    // Arrange
    ForwardRateVolatilitySurfaceCurvature forwardRateVolatilitySurfaceCurvature =
        new ForwardRateVolatilitySurfaceCurvature(10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            forwardRateVolatilitySurfaceCurvature.getValue(
                10.0d, new AnalyticModelFromCurvesAndVols()));
  }

  /**
   * Test {@link AbstractMonteCarloProduct#getValues(double, Model)} with {@code double}, {@code
   * Model}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link AbstractMonteCarloProduct#getValues(double, Model)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map AbstractMonteCarloProduct.getValues(double, Model)"})
  public void testGetValuesWithDoubleModel_thenReturnSizeIsOne() {
    // Arrange
    ForwardRateVolatilitySurfaceCurvature forwardRateVolatilitySurfaceCurvature =
        new ForwardRateVolatilitySurfaceCurvature(10.0d);

    // Act
    Map<String, Object> actualValues =
        forwardRateVolatilitySurfaceCurvature.getValues(
            10.0d, new AnalyticModelFromCurvesAndVols());

    // Assert
    assertEquals(1, actualValues.size());
    Object getResult = actualValues.get("exception");
    assertTrue(getResult instanceof IllegalArgumentException);
    assertEquals(
        "The product class net.finmath.montecarlo.interestrate.products.ForwardRateVolatilitySurfaceCurvature"
            + " cannot be valued against a model class net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols.It"
            + " requires a model of type interface net.finmath.montecarlo.MonteCarloSimulationModel.",
        ((IllegalArgumentException) getResult).getLocalizedMessage());
    assertEquals(
        "The product class net.finmath.montecarlo.interestrate.products.ForwardRateVolatilitySurfaceCurvature"
            + " cannot be valued against a model class net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols.It"
            + " requires a model of type interface net.finmath.montecarlo.MonteCarloSimulationModel.",
        ((IllegalArgumentException) getResult).getMessage());
    assertNull(((IllegalArgumentException) getResult).getCause());
    assertEquals(0, ((IllegalArgumentException) getResult).getSuppressed().length);
  }

  /**
   * Test {@link AbstractMonteCarloProduct#getCurrency()}.
   *
   * <p>Method under test: {@link AbstractMonteCarloProduct#getCurrency()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractMonteCarloProduct.getCurrency()"})
  public void testGetCurrency() {
    // Arrange, Act and Assert
    assertNull(new ForwardRateVolatilitySurfaceCurvature(10.0d).getCurrency());
  }

  /**
   * Test {@link AbstractMonteCarloProduct#toString()}.
   *
   * <p>Method under test: {@link AbstractMonteCarloProduct#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractMonteCarloProduct.toString()"})
  public void testToString() {
    // Arrange, Act and Assert
    assertEquals(
        "AbstractMonteCarloProduct [currency=null]",
        new ForwardRateVolatilitySurfaceCurvature(10.0d).toString());
  }
}
