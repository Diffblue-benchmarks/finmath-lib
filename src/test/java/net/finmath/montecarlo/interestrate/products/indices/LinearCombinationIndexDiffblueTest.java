package net.finmath.montecarlo.interestrate.products.indices;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Set;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.interestrate.TermStructureMonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.products.components.AbstractProductComponent;
import net.finmath.montecarlo.interestrate.products.components.Numeraire;
import net.finmath.montecarlo.interestrate.simple.SimpleLIBORMarketModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LinearCombinationIndexDiffblueTest {
  /**
   * Test {@link LinearCombinationIndex#LinearCombinationIndex(double, AbstractProductComponent,
   * double, AbstractProductComponent)}.
   *
   * <p>Method under test: {@link LinearCombinationIndex#LinearCombinationIndex(double,
   * AbstractProductComponent, double, AbstractProductComponent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LinearCombinationIndex.<init>(double, AbstractProductComponent, double, AbstractProductComponent)"
  })
  public void testNewLinearCombinationIndex() {
    // Arrange
    Numeraire index1 = new Numeraire();
    Numeraire index2 = new Numeraire();

    // Act
    LinearCombinationIndex actualLinearCombinationIndex =
        new LinearCombinationIndex(10.0d, index1, 10.0d, index2);

    // Assert
    AbstractProductComponent index12 = actualLinearCombinationIndex.getIndex1();
    assertTrue(index12 instanceof Numeraire);
    AbstractProductComponent index22 = actualLinearCombinationIndex.getIndex2();
    assertTrue(index22 instanceof Numeraire);
    assertNull(actualLinearCombinationIndex.getCurrency());
    assertNull(index12.getCurrency());
    assertNull(index22.getCurrency());
    assertNull(actualLinearCombinationIndex.getName());
    assertEquals(10.0d, actualLinearCombinationIndex.getScaling1(), 0.0);
    assertEquals(10.0d, actualLinearCombinationIndex.getScaling2(), 0.0);
    assertSame(index1, index12);
    assertSame(index2, index22);
  }

  /**
   * Test {@link LinearCombinationIndex#getValue(double, TermStructureMonteCarloSimulationModel)}
   * with {@code double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link LinearCombinationIndex#getValue(double,
   * TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LinearCombinationIndex.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    Numeraire index1 = new Numeraire();
    LinearCombinationIndex linearCombinationIndex =
        new LinearCombinationIndex(10.0d, index1, 10.0d, new Numeraire());

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualValue = linearCombinationIndex.getValue(10.0d, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, actualValue.getTypePriority());
    assertEquals(10, actualValue.size());
    assertEquals(10.0d, actualValue.getFiltrationTime(), 0.0);
    assertEquals(20.0d, actualValue.getAverage(), 0.0);
    assertEquals(20.0d, actualValue.getMax(), 0.0);
    assertEquals(20.0d, actualValue.getMin(), 0.0);
    assertFalse(actualValue.isDeterministic());
    assertArrayEquals(
        new double[] {20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d, 20.0d},
        actualValue.getRealizations(),
        0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link LinearCombinationIndex#toString()}
   *   <li>{@link LinearCombinationIndex#getIndex1()}
   *   <li>{@link LinearCombinationIndex#getIndex2()}
   *   <li>{@link LinearCombinationIndex#getScaling1()}
   *   <li>{@link LinearCombinationIndex#getScaling2()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractProductComponent LinearCombinationIndex.getIndex1()",
    "AbstractProductComponent LinearCombinationIndex.getIndex2()",
    "double LinearCombinationIndex.getScaling1()",
    "double LinearCombinationIndex.getScaling2()",
    "String LinearCombinationIndex.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    Numeraire index1 = new Numeraire();
    Numeraire index2 = new Numeraire();

    LinearCombinationIndex linearCombinationIndex =
        new LinearCombinationIndex(10.0d, index1, 10.0d, index2);

    // Act
    String actualToStringResult = linearCombinationIndex.toString();
    AbstractProductComponent actualIndex1 = linearCombinationIndex.getIndex1();
    AbstractProductComponent actualIndex2 = linearCombinationIndex.getIndex2();
    double actualScaling1 = linearCombinationIndex.getScaling1();

    // Assert
    assertTrue(actualIndex1 instanceof Numeraire);
    assertTrue(actualIndex2 instanceof Numeraire);
    assertEquals(
        "LinearCombinationIndex [index1=AbstractMonteCarloProduct [currency=null], index2=AbstractMonteCarloProduct"
            + " [currency=null], scaling1=10.0, scaling2=10.0, toString()=AbstractMonteCarloProduct [currency=null"
            + "]]",
        actualToStringResult);
    assertEquals(10.0d, actualScaling1, 0.0);
    assertEquals(10.0d, linearCombinationIndex.getScaling2(), 0.0);
    assertSame(index1, actualIndex1);
    assertSame(index2, actualIndex2);
  }

  /**
   * Test {@link LinearCombinationIndex#queryUnderlyings()}.
   *
   * <p>Method under test: {@link LinearCombinationIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set LinearCombinationIndex.queryUnderlyings()"})
  public void testQueryUnderlyings() {
    // Arrange
    Numeraire index1 = new Numeraire();
    LinearCombinationIndex linearCombinationIndex =
        new LinearCombinationIndex(
            10.0d, index1, 10.0d, new ConstantMaturitySwaprate(10.0d, 10.0d));

    // Act
    Set<String> actualQueryUnderlyingsResult = linearCombinationIndex.queryUnderlyings();

    // Assert
    assertEquals(1, actualQueryUnderlyingsResult.size());
    assertTrue(actualQueryUnderlyingsResult.contains(null));
  }

  /**
   * Test {@link LinearCombinationIndex#queryUnderlyings()}.
   *
   * <p>Method under test: {@link LinearCombinationIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set LinearCombinationIndex.queryUnderlyings()"})
  public void testQueryUnderlyings2() {
    // Arrange
    ConstantMaturitySwaprate index1 = new ConstantMaturitySwaprate(10.0d, 10.0d);
    LinearCombinationIndex linearCombinationIndex =
        new LinearCombinationIndex(
            10.0d, index1, 10.0d, new ConstantMaturitySwaprate(10.0d, 10.0d));

    // Act
    Set<String> actualQueryUnderlyingsResult = linearCombinationIndex.queryUnderlyings();

    // Assert
    assertEquals(1, actualQueryUnderlyingsResult.size());
    assertTrue(actualQueryUnderlyingsResult.contains(null));
  }

  /**
   * Test {@link LinearCombinationIndex#queryUnderlyings()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinearCombinationIndex#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set LinearCombinationIndex.queryUnderlyings()"})
  public void testQueryUnderlyings_thenReturnNull() {
    // Arrange
    Numeraire index1 = new Numeraire();
    LinearCombinationIndex linearCombinationIndex =
        new LinearCombinationIndex(10.0d, index1, 10.0d, new Numeraire());

    // Act and Assert
    assertNull(linearCombinationIndex.queryUnderlyings());
  }
}
