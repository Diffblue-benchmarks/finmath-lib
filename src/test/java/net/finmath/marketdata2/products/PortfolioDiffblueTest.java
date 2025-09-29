package net.finmath.marketdata2.products;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import net.finmath.marketdata2.model.AnalyticModel;
import net.finmath.marketdata2.model.AnalyticModelFromCurvesAndVols;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PortfolioDiffblueTest {
  /**
   * Test {@link Portfolio#Portfolio(AnalyticProduct, double)}.
   *
   * <p>Method under test: {@link Portfolio#Portfolio(AnalyticProduct, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Portfolio.<init>(AnalyticProduct, double)"})
  public void testNewPortfolio() {
    // Arrange
    Cashflow product = new Cashflow("GBP", 10.0d, 10.0d, true, "3");

    // Act
    Portfolio actualPortfolio = new Portfolio(product, 10.0d);

    // Assert
    List<AnalyticProduct> products = actualPortfolio.getProducts();
    assertEquals(1, products.size());
    AnalyticProduct getResult = products.get(0);
    assertTrue(getResult instanceof Cashflow);
    List<Double> weights = actualPortfolio.getWeights();
    assertEquals(1, weights.size());
    assertEquals(10.0d, weights.get(0).doubleValue(), 0.0);
    assertSame(product, getResult);
  }

  /**
   * Test {@link Portfolio#Portfolio(List, List)}.
   *
   * <ul>
   *   <li>Given {@code 0.5}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 0.5}.
   *   <li>Then return Weights is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link Portfolio#Portfolio(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Portfolio.<init>(List, List)"})
  public void testNewPortfolio_given05_whenArrayListAdd05_thenReturnWeightsIsArrayList() {
    // Arrange
    ArrayList<AnalyticProduct> products = new ArrayList<>();

    ArrayList<Double> weights = new ArrayList<>();
    weights.add(0.5d);
    weights.add(10.0d);

    // Act
    Portfolio actualPortfolio = new Portfolio(products, weights);

    // Assert
    assertTrue(actualPortfolio.getProducts().isEmpty());
    assertEquals(weights, actualPortfolio.getWeights());
  }

  /**
   * Test {@link Portfolio#Portfolio(Portfolio, List, List)}.
   *
   * <ul>
   *   <li>Given {@code 0.5}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 0.5}.
   *   <li>Then return Weights is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link Portfolio#Portfolio(Portfolio, List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Portfolio.<init>(Portfolio, List, List)"})
  public void testNewPortfolio_given05_whenArrayListAdd05_thenReturnWeightsIsArrayList2() {
    // Arrange
    Portfolio portfolio = new Portfolio(new ArrayList<>());
    ArrayList<AnalyticProduct> products = new ArrayList<>();

    ArrayList<Double> weights = new ArrayList<>();
    weights.add(0.5d);
    weights.add(10.0d);

    // Act
    Portfolio actualPortfolio = new Portfolio(portfolio, products, weights);

    // Assert
    assertTrue(actualPortfolio.getProducts().isEmpty());
    assertEquals(weights, actualPortfolio.getWeights());
  }

  /**
   * Test {@link Portfolio#Portfolio(List, List)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>When {@link ArrayList#ArrayList()} add ten.
   *   <li>Then return Weights is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link Portfolio#Portfolio(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Portfolio.<init>(List, List)"})
  public void testNewPortfolio_givenTen_whenArrayListAddTen_thenReturnWeightsIsArrayList() {
    // Arrange
    ArrayList<AnalyticProduct> products = new ArrayList<>();

    ArrayList<Double> weights = new ArrayList<>();
    weights.add(10.0d);

    // Act
    Portfolio actualPortfolio = new Portfolio(products, weights);

    // Assert
    assertTrue(actualPortfolio.getProducts().isEmpty());
    assertEquals(weights, actualPortfolio.getWeights());
  }

  /**
   * Test {@link Portfolio#Portfolio(Portfolio, List, List)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>When {@link ArrayList#ArrayList()} add ten.
   *   <li>Then return Weights is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link Portfolio#Portfolio(Portfolio, List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Portfolio.<init>(Portfolio, List, List)"})
  public void testNewPortfolio_givenTen_whenArrayListAddTen_thenReturnWeightsIsArrayList2() {
    // Arrange
    Portfolio portfolio = new Portfolio(new ArrayList<>());
    ArrayList<AnalyticProduct> products = new ArrayList<>();

    ArrayList<Double> weights = new ArrayList<>();
    weights.add(10.0d);

    // Act
    Portfolio actualPortfolio = new Portfolio(portfolio, products, weights);

    // Assert
    assertTrue(actualPortfolio.getProducts().isEmpty());
    assertEquals(weights, actualPortfolio.getWeights());
  }

  /**
   * Test {@link Portfolio#Portfolio(List, List)}.
   *
   * <ul>
   *   <li>Then return Products is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link Portfolio#Portfolio(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Portfolio.<init>(List, List)"})
  public void testNewPortfolio_thenReturnProductsIsArrayList() {
    // Arrange
    ArrayList<AnalyticProduct> products = new ArrayList<>();
    Cashflow cashflow = new Cashflow("GBP", 10.0d, 10.0d, true, "3");
    products.add(cashflow);

    // Act
    Portfolio actualPortfolio = new Portfolio(products, new ArrayList<>());

    // Assert
    assertEquals(products, actualPortfolio.getProducts());
  }

  /**
   * Test {@link Portfolio#Portfolio(List, List)}.
   *
   * <ul>
   *   <li>Then return Products is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link Portfolio#Portfolio(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Portfolio.<init>(List, List)"})
  public void testNewPortfolio_thenReturnProductsIsArrayList2() {
    // Arrange
    ArrayList<AnalyticProduct> products = new ArrayList<>();
    Cashflow cashflow = new Cashflow("GBP", 10.0d, 10.0d, true, "3");
    products.add(cashflow);
    Cashflow cashflow2 = new Cashflow("GBP", 10.0d, 10.0d, true, "3");
    products.add(cashflow2);

    // Act
    Portfolio actualPortfolio = new Portfolio(products, new ArrayList<>());

    // Assert
    assertEquals(products, actualPortfolio.getProducts());
  }

  /**
   * Test {@link Portfolio#Portfolio(Portfolio, List, List)}.
   *
   * <ul>
   *   <li>Then return Products is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link Portfolio#Portfolio(Portfolio, List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Portfolio.<init>(Portfolio, List, List)"})
  public void testNewPortfolio_thenReturnProductsIsArrayList3() {
    // Arrange
    Portfolio portfolio = new Portfolio(new ArrayList<>());

    ArrayList<AnalyticProduct> products = new ArrayList<>();
    Cashflow cashflow = new Cashflow("GBP", 10.0d, 10.0d, true, "3");
    products.add(cashflow);

    // Act
    Portfolio actualPortfolio = new Portfolio(portfolio, products, new ArrayList<>());

    // Assert
    assertEquals(products, actualPortfolio.getProducts());
  }

  /**
   * Test {@link Portfolio#Portfolio(Portfolio, List, List)}.
   *
   * <ul>
   *   <li>Then return Products is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link Portfolio#Portfolio(Portfolio, List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Portfolio.<init>(Portfolio, List, List)"})
  public void testNewPortfolio_thenReturnProductsIsArrayList4() {
    // Arrange
    Portfolio portfolio = new Portfolio(new ArrayList<>());

    ArrayList<AnalyticProduct> products = new ArrayList<>();
    Cashflow cashflow = new Cashflow("GBP", 10.0d, 10.0d, true, "3");
    products.add(cashflow);
    Cashflow cashflow2 = new Cashflow("GBP", 10.0d, 10.0d, true, "3");
    products.add(cashflow2);

    // Act
    Portfolio actualPortfolio = new Portfolio(portfolio, products, new ArrayList<>());

    // Assert
    assertEquals(products, actualPortfolio.getProducts());
  }

  /**
   * Test {@link Portfolio#Portfolio(List)}.
   *
   * <ul>
   *   <li>Then return Products size is two.
   * </ul>
   *
   * <p>Method under test: {@link Portfolio#Portfolio(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Portfolio.<init>(List)"})
  public void testNewPortfolio_thenReturnProductsSizeIsTwo() {
    // Arrange
    ArrayList<AnalyticProduct> products = new ArrayList<>();
    Cashflow cashflow = new Cashflow("GBP", 1.0d, 1.0d, true, "3");
    products.add(cashflow);
    Cashflow cashflow2 = new Cashflow("GBP", 1.0d, 1.0d, true, "3");
    products.add(cashflow2);

    // Act
    Portfolio actualPortfolio = new Portfolio(products);

    // Assert
    List<AnalyticProduct> products2 = actualPortfolio.getProducts();
    assertEquals(2, products2.size());
    AnalyticProduct getResult = products2.get(1);
    assertTrue(getResult instanceof Cashflow);
    List<Double> weights = actualPortfolio.getWeights();
    assertEquals(2, weights.size());
    assertEquals(1.0d, weights.get(1).doubleValue(), 0.0);
    assertSame(cashflow2, getResult);
  }

  /**
   * Test {@link Portfolio#Portfolio(List)}.
   *
   * <ul>
   *   <li>Then return Weights size is one.
   * </ul>
   *
   * <p>Method under test: {@link Portfolio#Portfolio(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Portfolio.<init>(List)"})
  public void testNewPortfolio_thenReturnWeightsSizeIsOne() {
    // Arrange
    ArrayList<AnalyticProduct> products = new ArrayList<>();
    Cashflow cashflow = new Cashflow("GBP", 1.0d, 1.0d, true, "3");
    products.add(cashflow);

    // Act
    Portfolio actualPortfolio = new Portfolio(products);

    // Assert
    List<Double> weights = actualPortfolio.getWeights();
    assertEquals(1, weights.size());
    assertEquals(1.0d, weights.get(0).doubleValue(), 0.0);
    assertEquals(products, actualPortfolio.getProducts());
  }

  /**
   * Test {@link Portfolio#Portfolio(List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return Products Empty.
   * </ul>
   *
   * <p>Method under test: {@link Portfolio#Portfolio(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Portfolio.<init>(List)"})
  public void testNewPortfolio_whenArrayList_thenReturnProductsEmpty() {
    // Arrange and Act
    Portfolio actualPortfolio = new Portfolio(new ArrayList<>());

    // Assert
    assertTrue(actualPortfolio.getProducts().isEmpty());
    assertTrue(actualPortfolio.getWeights().isEmpty());
  }

  /**
   * Test {@link Portfolio#Portfolio(List, List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return Weights Empty.
   * </ul>
   *
   * <p>Method under test: {@link Portfolio#Portfolio(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Portfolio.<init>(List, List)"})
  public void testNewPortfolio_whenArrayList_thenReturnWeightsEmpty() {
    // Arrange
    ArrayList<AnalyticProduct> products = new ArrayList<>();

    // Act
    Portfolio actualPortfolio = new Portfolio(products, new ArrayList<>());

    // Assert
    assertTrue(actualPortfolio.getProducts().isEmpty());
    assertTrue(actualPortfolio.getWeights().isEmpty());
  }

  /**
   * Test {@link Portfolio#Portfolio(Portfolio, List, List)}.
   *
   * <ul>
   *   <li>When {@link Portfolio#Portfolio(List)} with products is {@link ArrayList#ArrayList()}.
   *   <li>Then return Weights Empty.
   * </ul>
   *
   * <p>Method under test: {@link Portfolio#Portfolio(Portfolio, List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Portfolio.<init>(Portfolio, List, List)"})
  public void testNewPortfolio_whenPortfolioWithProductsIsArrayList_thenReturnWeightsEmpty() {
    // Arrange
    Portfolio portfolio = new Portfolio(new ArrayList<>());
    ArrayList<AnalyticProduct> products = new ArrayList<>();

    // Act
    Portfolio actualPortfolio = new Portfolio(portfolio, products, new ArrayList<>());

    // Assert
    assertTrue(actualPortfolio.getProducts().isEmpty());
    assertTrue(actualPortfolio.getWeights().isEmpty());
  }

  /**
   * Test {@link Portfolio#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <p>Method under test: {@link Portfolio#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Portfolio.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel() {
    // Arrange
    Portfolio portfolio = new Portfolio(new Portfolio(new ArrayList<>()), 10.0d);

    // Act
    RandomVariable actualValue = portfolio.getValue(10.0d, new AnalyticModelFromCurvesAndVols());

    // Assert
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
    RandomVariable actualExpectationResult = actualValue.expectation();
    assertSame(actualValue, actualExpectationResult);
  }

  /**
   * Test {@link Portfolio#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link Portfolio#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable Portfolio.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_thenReturnScalar() {
    // Arrange
    Portfolio portfolio = new Portfolio(new ArrayList<>());

    // Act
    RandomVariable actualValue = portfolio.getValue(10.0d, new AnalyticModelFromCurvesAndVols());

    // Assert
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
    RandomVariable actualExpectationResult = actualValue.expectation();
    assertSame(actualValue, actualExpectationResult);
  }

  /**
   * Test {@link Portfolio#getProducts()}.
   *
   * <p>Method under test: {@link Portfolio#getProducts()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List Portfolio.getProducts()"})
  public void testGetProducts() {
    // Arrange, Act and Assert
    assertTrue(new Portfolio(new ArrayList<>()).getProducts().isEmpty());
  }

  /**
   * Test {@link Portfolio#getWeights()}.
   *
   * <p>Method under test: {@link Portfolio#getWeights()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List Portfolio.getWeights()"})
  public void testGetWeights() {
    // Arrange, Act and Assert
    assertTrue(new Portfolio(new ArrayList<>()).getWeights().isEmpty());
  }
}
