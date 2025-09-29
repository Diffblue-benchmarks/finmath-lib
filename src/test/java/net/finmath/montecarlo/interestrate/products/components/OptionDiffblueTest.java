package net.finmath.montecarlo.interestrate.products.components;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
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
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.MonteCarloSimulationModel;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.assetderivativevaluation.MonteCarloAssetModel;
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.conditionalexpectation.RegressionBasisFunctionsProvider;
import net.finmath.montecarlo.interestrate.LIBORModelMonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.TermStructureMonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.products.AbstractTermStructureMonteCarloProduct;
import net.finmath.montecarlo.interestrate.products.ForwardRateVolatilitySurfaceCurvature;
import net.finmath.montecarlo.interestrate.products.MoneyMarketAccount;
import net.finmath.montecarlo.interestrate.products.TermStructureMonteCarloProduct;
import net.finmath.montecarlo.interestrate.products.indices.AnalyticModelIndex;
import net.finmath.montecarlo.interestrate.products.indices.ConstantMaturitySwaprate;
import net.finmath.montecarlo.interestrate.products.indices.FixedCoupon;
import net.finmath.montecarlo.interestrate.simple.SimpleLIBORMarketModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OptionDiffblueTest {
  /**
   * Test {@link Option#Option(double, double, AbstractTermStructureMonteCarloProduct)}.
   *
   * <p>Method under test: {@link Option#Option(double, double,
   * AbstractTermStructureMonteCarloProduct)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Option.<init>(double, double, AbstractTermStructureMonteCarloProduct)"})
  public void testNewOption() {
    // Arrange, Act and Assert
    assertNull(
        new Option(10.0d, 10.0d, new ForwardRateVolatilitySurfaceCurvature(10.0d)).getCurrency());
  }

  /**
   * Test {@link Option#Option(double, double, boolean, AbstractTermStructureMonteCarloProduct)}.
   *
   * <p>Method under test: {@link Option#Option(double, double, boolean,
   * AbstractTermStructureMonteCarloProduct)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Option.<init>(double, double, boolean, AbstractTermStructureMonteCarloProduct)"
  })
  public void testNewOption2() {
    // Arrange, Act and Assert
    assertNull(
        new Option(10.0d, 10.0d, true, new ForwardRateVolatilitySurfaceCurvature(10.0d))
            .getCurrency());
  }

  /**
   * Test {@link Option#Option(double, double, boolean, AbstractTermStructureMonteCarloProduct,
   * RegressionBasisFunctionsProvider)}.
   *
   * <p>Method under test: {@link Option#Option(double, double, boolean,
   * AbstractTermStructureMonteCarloProduct, RegressionBasisFunctionsProvider)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Option.<init>(double, double, boolean, AbstractTermStructureMonteCarloProduct, RegressionBasisFunctionsProvider)"
  })
  public void testNewOption3() {
    // Arrange, Act and Assert
    assertNull(
        new Option(
                10.0d,
                10.0d,
                true,
                new ForwardRateVolatilitySurfaceCurvature(10.0d),
                mock(RegressionBasisFunctionsProvider.class))
            .getCurrency());
  }

  /**
   * Test {@link Option#Option(double, AbstractTermStructureMonteCarloProduct)}.
   *
   * <p>Method under test: {@link Option#Option(double, AbstractTermStructureMonteCarloProduct)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Option.<init>(double, AbstractTermStructureMonteCarloProduct)"})
  public void testNewOption4() {
    // Arrange and Act
    Option actualOption = new Option(10.0d, new ForwardRateVolatilitySurfaceCurvature(10.0d));

    // Assert
    assertNull(actualOption.getCurrency());
  }

  /**
   * Test {@link Option#Option(double, boolean, TermStructureMonteCarloProduct,
   * AbstractTermStructureMonteCarloProduct)}.
   *
   * <p>Method under test: {@link Option#Option(double, boolean, TermStructureMonteCarloProduct,
   * AbstractTermStructureMonteCarloProduct)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Option.<init>(double, boolean, TermStructureMonteCarloProduct, AbstractTermStructureMonteCarloProduct)"
  })
  public void testNewOption5() {
    // Arrange
    ForwardRateVolatilitySurfaceCurvature strikeProduct =
        new ForwardRateVolatilitySurfaceCurvature(10.0d);

    // Act and Assert
    assertNull(
        new Option(10.0d, true, strikeProduct, new ForwardRateVolatilitySurfaceCurvature(10.0d))
            .getCurrency());
  }

  /**
   * Test {@link Option#Option(double, boolean, TermStructureMonteCarloProduct,
   * AbstractTermStructureMonteCarloProduct, RegressionBasisFunctionsProvider)}.
   *
   * <p>Method under test: {@link Option#Option(double, boolean, TermStructureMonteCarloProduct,
   * AbstractTermStructureMonteCarloProduct, RegressionBasisFunctionsProvider)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Option.<init>(double, boolean, TermStructureMonteCarloProduct, AbstractTermStructureMonteCarloProduct, RegressionBasisFunctionsProvider)"
  })
  public void testNewOption6() {
    // Arrange
    ForwardRateVolatilitySurfaceCurvature strikeProduct =
        new ForwardRateVolatilitySurfaceCurvature(10.0d);

    // Act and Assert
    assertNull(
        new Option(
                10.0d,
                true,
                strikeProduct,
                new ForwardRateVolatilitySurfaceCurvature(10.0d),
                mock(RegressionBasisFunctionsProvider.class))
            .getCurrency());
  }

  /**
   * Test {@link Option#getCurrency()}.
   *
   * <ul>
   *   <li>Given {@link Option#Option(double, AbstractTermStructureMonteCarloProduct)} with
   *       exerciseDate is ten and underlying is {@link Option#Option(double,
   *       AbstractTermStructureMonteCarloProduct)}.
   * </ul>
   *
   * <p>Method under test: {@link Option#getCurrency()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Option.getCurrency()"})
  public void testGetCurrency_givenOptionWithExerciseDateIsTenAndUnderlyingIsOption() {
    // Arrange
    Option underlying = new Option(10.0d, new ForwardRateVolatilitySurfaceCurvature(10.0d));
    Option option = new Option(10.0d, underlying);

    // Act and Assert
    assertNull(option.getCurrency());
  }

  /**
   * Test {@link Option#getCurrency()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Option#getCurrency()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Option.getCurrency()"})
  public void testGetCurrency_thenReturnNull() {
    // Arrange
    Option option = new Option(10.0d, new ForwardRateVolatilitySurfaceCurvature(10.0d));

    // Act and Assert
    assertNull(option.getCurrency());
  }

  /**
   * Test {@link Option#queryUnderlyings()}.
   *
   * <ul>
   *   <li>Given {@link FixedCoupon#FixedCoupon(double)} with coupon is ten.
   * </ul>
   *
   * <p>Method under test: {@link Option#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set Option.queryUnderlyings()"})
  public void testQueryUnderlyings_givenFixedCouponWithCouponIsTen() {
    // Arrange
    AnalyticModelIndex pastFixings = new AnalyticModelIndex("Name", "Curve Name", 10.0d);
    AccrualAccount underlying =
        new AccrualAccount("GBP", pastFixings, new FixedCoupon(10.0d), 10.0d);
    Option option = new Option(10.0d, underlying);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> option.queryUnderlyings());
  }

  /**
   * Test {@link Option#queryUnderlyings()}.
   *
   * <ul>
   *   <li>Given {@link Option#Option(double, AbstractTermStructureMonteCarloProduct)} with
   *       exerciseDate is ten and underlying is {@link Option#Option(double,
   *       AbstractTermStructureMonteCarloProduct)}.
   * </ul>
   *
   * <p>Method under test: {@link Option#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set Option.queryUnderlyings()"})
  public void testQueryUnderlyings_givenOptionWithExerciseDateIsTenAndUnderlyingIsOption() {
    // Arrange
    Option underlying = new Option(10.0d, new ForwardRateVolatilitySurfaceCurvature(10.0d));
    Option option = new Option(10.0d, underlying);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> option.queryUnderlyings());
  }

  /**
   * Test {@link Option#queryUnderlyings()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Option#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set Option.queryUnderlyings()"})
  public void testQueryUnderlyings_thenReturnNull() {
    // Arrange
    Option option = new Option(10.0d, new ForwardRateVolatilitySurfaceCurvature(10.0d));

    // Act and Assert
    assertNull(option.queryUnderlyings());
  }

  /**
   * Test {@link Option#queryUnderlyings()}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link Option#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set Option.queryUnderlyings()"})
  public void testQueryUnderlyings_thenReturnSizeIsOne() {
    // Arrange
    AnalyticModelIndex pastFixings = new AnalyticModelIndex("Name", "Curve Name", 10.0d);
    AccrualAccount underlying =
        new AccrualAccount("GBP", pastFixings, new ConstantMaturitySwaprate(10.0d, 10.0d), 10.0d);
    Option option = new Option(10.0d, underlying);

    // Act
    Set<String> actualQueryUnderlyingsResult = option.queryUnderlyings();

    // Assert
    assertEquals(1, actualQueryUnderlyingsResult.size());
    assertTrue(actualQueryUnderlyingsResult.contains(null));
  }

  /**
   * Test {@link Option#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code
   * double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link Option#getValue(double, TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Option.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    Option option = new Option(10.0d, new MoneyMarketAccount());

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
    RandomVariable actualValue = option.getValue(10.0d, model);

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
    assertEquals(1, actualValue.size());
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.MAX_VALUE, actualValue.getAverage(), 0.0);
    assertEquals(Double.MAX_VALUE, actualValue.getMax(), 0.0);
    assertEquals(Double.MAX_VALUE, actualValue.getMin(), 0.0);
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {Double.MAX_VALUE}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link Option#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code
   * double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link Option#getValue(double, TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Option.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel_thenReturnScalar()
      throws CalculationException {
    // Arrange
    Option option = new Option(1.0d, new ForwardRateVolatilitySurfaceCurvature(10.0d));

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
    RandomVariable actualValue = option.getValue(10.0d, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualValue instanceof Scalar);
    assertTrue(actualValue.abs() instanceof Scalar);
    assertTrue(actualValue.cos() instanceof Scalar);
    assertTrue(actualValue.exp() instanceof Scalar);
    assertTrue(actualValue.expm1() instanceof Scalar);
    assertTrue(actualValue.invert() instanceof Scalar);
    assertTrue(actualValue.isNaN() instanceof Scalar);
    assertTrue(actualValue.sin() instanceof Scalar);
    assertTrue(actualValue.sqrt() instanceof Scalar);
    assertTrue(actualValue.squared() instanceof Scalar);
    assertTrue(actualValue.variance() instanceof Scalar);
    assertNull(actualValue.getRealizations());
    assertNull(actualValue.getOperator());
    assertNull(actualValue.getRealizationsStream());
    assertEquals(0, actualValue.getTypePriority());
    assertEquals(0.0d, actualValue.getAverage(), 0.0);
    assertEquals(0.0d, actualValue.getMax(), 0.0);
    assertEquals(0.0d, actualValue.getMin(), 0.0);
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, actualValue.size());
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualValue.expectation();
    assertSame(actualValue, actualExpectationResult);
  }

  /**
   * Test {@link Option#getBasisFunctions(double, MonteCarloSimulationModel)} with {@code
   * evaluationTime}, {@code model}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link Option#getBasisFunctions(double, MonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] Option.getBasisFunctions(double, MonteCarloSimulationModel)"
  })
  public void testGetBasisFunctionsWithEvaluationTimeModel_thenThrowIllegalArgumentException()
      throws CalculationException {
    // Arrange
    Option option = new Option(10.0d, new ForwardRateVolatilitySurfaceCurvature(10.0d));

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> option.getBasisFunctions(10.0d, new MonteCarloAssetModel(process)));
    verify(brownianMotion).getTimeDiscretization();
  }

  /**
   * Test {@link Option#getBasisFunctions(double, LIBORModelMonteCarloSimulationModel)} with {@code
   * exerciseDate}, {@code model}.
   *
   * <p>Method under test: {@link Option#getBasisFunctions(double,
   * LIBORModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] Option.getBasisFunctions(double, LIBORModelMonteCarloSimulationModel)"
  })
  public void testGetBasisFunctionsWithExerciseDateModel() throws CalculationException {
    // Arrange
    Option option = new Option(10.0d, new ForwardRateVolatilitySurfaceCurvature(10.0d));

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
            new double[] {
              1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d
            },
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualBasisFunctions = option.getBasisFunctions(10.0d, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualBasisFunctions[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[3] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[4] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[0] instanceof Scalar);
    assertEquals(5, actualBasisFunctions.length);
  }

  /**
   * Test {@link Option#getBasisFunctions(double, LIBORModelMonteCarloSimulationModel)} with {@code
   * exerciseDate}, {@code model}.
   *
   * <ul>
   *   <li>Then return array length is four.
   * </ul>
   *
   * <p>Method under test: {@link Option#getBasisFunctions(double,
   * LIBORModelMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] Option.getBasisFunctions(double, LIBORModelMonteCarloSimulationModel)"
  })
  public void testGetBasisFunctionsWithExerciseDateModel_thenReturnArrayLengthIsFour()
      throws CalculationException {
    // Arrange
    Option option = new Option(10.0d, new ForwardRateVolatilitySurfaceCurvature(10.0d));

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 1, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualBasisFunctions = option.getBasisFunctions(10.0d, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualBasisFunctions[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[3] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBasisFunctions[0] instanceof Scalar);
    assertEquals(4, actualBasisFunctions.length);
  }

  /**
   * Test {@link Option#toString()}.
   *
   * <p>Method under test: {@link Option#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String Option.toString()"})
  public void testToString() {
    // Arrange
    Option option = new Option(10.0d, new ForwardRateVolatilitySurfaceCurvature(10.0d));

    // Act and Assert
    assertEquals(
        "Option [exerciseDate=10.0, strikePrice=0.0, underlying=AbstractMonteCarloProduct [currency=null],"
            + " isCall=true, toString()=AbstractMonteCarloProduct [currency=null]]",
        option.toString());
  }
}
