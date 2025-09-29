package net.finmath.montecarlo.products;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Optional;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.MonteCarloProduct;
import net.finmath.montecarlo.MonteCarloSimulationModel;
import net.finmath.montecarlo.assetderivativevaluation.MonteCarloAssetModel;
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.interestrate.products.ForwardRateVolatilitySurfaceCurvature;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PortfolioMonteCarloProductDiffblueTest {
  /**
   * Test {@link PortfolioMonteCarloProduct#PortfolioMonteCarloProduct(MonteCarloProduct[])}.
   *
   * <p>Method under test: {@link
   * PortfolioMonteCarloProduct#PortfolioMonteCarloProduct(MonteCarloProduct[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PortfolioMonteCarloProduct.<init>(MonteCarloProduct[])"})
  public void testNewPortfolioMonteCarloProduct() {
    // Arrange
    MonteCarloProduct[] products =
        new MonteCarloProduct[] {new ForwardRateVolatilitySurfaceCurvature(10.0d)};

    // Act
    PortfolioMonteCarloProduct actualPortfolioMonteCarloProduct =
        new PortfolioMonteCarloProduct(products);

    // Assert
    assertNull(actualPortfolioMonteCarloProduct.getCurrency());
  }

  /**
   * Test {@link PortfolioMonteCarloProduct#PortfolioMonteCarloProduct(MonteCarloProduct[],
   * double[])}.
   *
   * <p>Method under test: {@link
   * PortfolioMonteCarloProduct#PortfolioMonteCarloProduct(MonteCarloProduct[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PortfolioMonteCarloProduct.<init>(MonteCarloProduct[], double[])"})
  public void testNewPortfolioMonteCarloProduct2() {
    // Arrange
    MonteCarloProduct[] products =
        new MonteCarloProduct[] {new ForwardRateVolatilitySurfaceCurvature(10.0d)};

    // Act
    PortfolioMonteCarloProduct actualPortfolioMonteCarloProduct =
        new PortfolioMonteCarloProduct(products, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertNull(actualPortfolioMonteCarloProduct.getCurrency());
  }

  /**
   * Test {@link PortfolioMonteCarloProduct#PortfolioMonteCarloProduct(MonteCarloProduct[],
   * double[], Optional)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * PortfolioMonteCarloProduct#PortfolioMonteCarloProduct(MonteCarloProduct[], double[], Optional)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PortfolioMonteCarloProduct.<init>(MonteCarloProduct[], double[], Optional)"
  })
  public void testNewPortfolioMonteCarloProduct_thenThrowIllegalArgumentException() {
    // Arrange
    MonteCarloProduct[] products =
        new MonteCarloProduct[] {new ForwardRateVolatilitySurfaceCurvature(10.0d)};
    Optional<Integer> numberOfThreads = Optional.of(0);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new PortfolioMonteCarloProduct(
                products, new double[] {10.0d, 0.5d, 10.0d, 0.5d}, numberOfThreads));
  }

  /**
   * Test {@link PortfolioMonteCarloProduct#PortfolioMonteCarloProduct(MonteCarloProduct[],
   * double[], Optional)}.
   *
   * <ul>
   *   <li>When empty.
   *   <li>Then return Currency is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * PortfolioMonteCarloProduct#PortfolioMonteCarloProduct(MonteCarloProduct[], double[], Optional)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PortfolioMonteCarloProduct.<init>(MonteCarloProduct[], double[], Optional)"
  })
  public void testNewPortfolioMonteCarloProduct_whenEmpty_thenReturnCurrencyIsNull() {
    // Arrange
    MonteCarloProduct[] products =
        new MonteCarloProduct[] {new ForwardRateVolatilitySurfaceCurvature(10.0d)};
    Optional<Integer> numberOfThreads = Optional.empty();

    // Act
    PortfolioMonteCarloProduct actualPortfolioMonteCarloProduct =
        new PortfolioMonteCarloProduct(
            products, new double[] {10.0d, 0.5d, 10.0d, 0.5d}, numberOfThreads);

    // Assert
    assertNull(actualPortfolioMonteCarloProduct.getCurrency());
  }

  /**
   * Test {@link PortfolioMonteCarloProduct#PortfolioMonteCarloProduct(MonteCarloProduct[],
   * double[], Optional)}.
   *
   * <ul>
   *   <li>When of one.
   *   <li>Then return Currency is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * PortfolioMonteCarloProduct#PortfolioMonteCarloProduct(MonteCarloProduct[], double[], Optional)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PortfolioMonteCarloProduct.<init>(MonteCarloProduct[], double[], Optional)"
  })
  public void testNewPortfolioMonteCarloProduct_whenOfOne_thenReturnCurrencyIsNull() {
    // Arrange
    MonteCarloProduct[] products =
        new MonteCarloProduct[] {new ForwardRateVolatilitySurfaceCurvature(10.0d)};
    Optional<Integer> numberOfThreads = Optional.of(1);

    // Act
    PortfolioMonteCarloProduct actualPortfolioMonteCarloProduct =
        new PortfolioMonteCarloProduct(
            products, new double[] {10.0d, 0.5d, 10.0d, 0.5d}, numberOfThreads);

    // Assert
    assertNull(actualPortfolioMonteCarloProduct.getCurrency());
  }

  /**
   * Test {@link PortfolioMonteCarloProduct#getValue(double, MonteCarloSimulationModel)} with {@code
   * double}, {@code MonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link PortfolioMonteCarloProduct#getValue(double,
   * MonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable PortfolioMonteCarloProduct.getValue(double, MonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleMonteCarloSimulationModel() throws CalculationException {
    // Arrange
    MonteCarloProduct[] products =
        new MonteCarloProduct[] {new ForwardRateVolatilitySurfaceCurvature(10.0d)};
    PortfolioMonteCarloProduct portfolioMonteCarloProduct =
        new PortfolioMonteCarloProduct(products);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act and Assert
    assertThrows(
        CalculationException.class,
        () -> portfolioMonteCarloProduct.getValue(10.0d, new MonteCarloAssetModel(process)));
    verify(brownianMotion).getTimeDiscretization();
  }

  /**
   * Test {@link PortfolioMonteCarloProduct#getValue(double, MonteCarloSimulationModel)} with {@code
   * double}, {@code MonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link PortfolioMonteCarloProduct#getValue(double,
   * MonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable PortfolioMonteCarloProduct.getValue(double, MonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleMonteCarloSimulationModel2() throws CalculationException {
    // Arrange
    PortfolioMonteCarloProduct portfolioMonteCarloProduct =
        new PortfolioMonteCarloProduct(new MonteCarloProduct[] {});

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable actualValue =
        portfolioMonteCarloProduct.getValue(10.0d, new MonteCarloAssetModel(process));

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertNull(actualValue);
  }

  /**
   * Test {@link PortfolioMonteCarloProduct#getValue(double, MonteCarloSimulationModel)} with {@code
   * double}, {@code MonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link PortfolioMonteCarloProduct#getValue(double,
   * MonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable PortfolioMonteCarloProduct.getValue(double, MonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleMonteCarloSimulationModel3() throws CalculationException {
    // Arrange
    PortfolioMonteCarloProduct portfolioMonteCarloProduct =
        new PortfolioMonteCarloProduct(null, new double[] {});

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable actualValue =
        portfolioMonteCarloProduct.getValue(10.0d, new MonteCarloAssetModel(process));

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertNull(actualValue);
  }

  /**
   * Test {@link PortfolioMonteCarloProduct#getValue(double, MonteCarloSimulationModel)} with {@code
   * double}, {@code MonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link PortfolioMonteCarloProduct#getValue(double,
   * MonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable PortfolioMonteCarloProduct.getValue(double, MonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleMonteCarloSimulationModel4() throws CalculationException {
    // Arrange
    ForwardRateVolatilitySurfaceCurvature forwardRateVolatilitySurfaceCurvature =
        new ForwardRateVolatilitySurfaceCurvature(10.0d);
    Optional<Integer> numberOfThreads = Optional.of(1);
    PortfolioMonteCarloProduct portfolioMonteCarloProduct =
        new PortfolioMonteCarloProduct(
            new MonteCarloProduct[] {
              forwardRateVolatilitySurfaceCurvature,
              new ForwardRateVolatilitySurfaceCurvature(10.0d)
            },
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            numberOfThreads);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act and Assert
    assertThrows(
        CalculationException.class,
        () -> portfolioMonteCarloProduct.getValue(10.0d, new MonteCarloAssetModel(process)));
    verify(brownianMotion).getTimeDiscretization();
  }
}
