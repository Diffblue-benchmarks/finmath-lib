package net.finmath.montecarlo.conditionalexpectation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import net.finmath.montecarlo.AbstractMonteCarloProduct;
import net.finmath.montecarlo.BrownianBridge;
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.MonteCarloSimulationModel;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.assetderivativevaluation.MonteCarloAssetModel;
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.assetderivativevaluation.products.AsianOption;
import net.finmath.montecarlo.assetderivativevaluation.products.DigitalOption;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RegressionBasisFunctionsFromProductsDiffblueTest {
  /**
   * Test {@link RegressionBasisFunctionsFromProducts#getBasisFunctions(double,
   * MonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link RegressionBasisFunctionsFromProducts#getBasisFunctions(double,
   * MonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] RegressionBasisFunctionsFromProducts.getBasisFunctions(double, MonteCarloSimulationModel)"
  })
  public void testGetBasisFunctions() {
    // Arrange
    ArrayList<AbstractMonteCarloProduct> products = new ArrayList<>();
    products.add(new DigitalOption(10.0d, 10.0d));
    RegressionBasisFunctionsFromProducts regressionBasisFunctionsFromProducts =
        new RegressionBasisFunctionsFromProducts(products);

    RandomVariableDifferentiableAAD ofResult = RandomVariableDifferentiableAAD.of(1.0d);
    ofResult.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(1.0d)});
    RandomVariable[] start = new RandomVariable[] {ofResult};
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge stochasticDriver = new BrownianBridge(generator, start, end);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable[] actualBasisFunctions =
        regressionBasisFunctionsFromProducts.getBasisFunctions(
            10.0d, new MonteCarloAssetModel(process));

    // Assert
    assertTrue(actualBasisFunctions[0] instanceof Scalar);
    assertEquals(1, actualBasisFunctions.length);
  }

  /**
   * Test {@link RegressionBasisFunctionsFromProducts#getBasisFunctions(double,
   * MonteCarloSimulationModel)}.
   *
   * <ul>
   *   <li>Then first element return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RegressionBasisFunctionsFromProducts#getBasisFunctions(double,
   * MonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] RegressionBasisFunctionsFromProducts.getBasisFunctions(double, MonteCarloSimulationModel)"
  })
  public void testGetBasisFunctions_thenFirstElementReturnRandomVariableFromDoubleArray() {
    // Arrange
    ArrayList<AbstractMonteCarloProduct> products = new ArrayList<>();
    products.add(new AsianOption(10.0d, 10.0d, new TenorFromArray(10.0d, 10, 0.5d)));
    RegressionBasisFunctionsFromProducts regressionBasisFunctionsFromProducts =
        new RegressionBasisFunctionsFromProducts(products);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getBrownianIncrement(anyInt(), anyInt()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getNumberOfFactors()).thenReturn(3);
    when(brownianMotion.getNumberOfPaths()).thenReturn(10);
    when(brownianMotion.getRandomVariableForConstant(anyDouble()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable[] actualBasisFunctions =
        regressionBasisFunctionsFromProducts.getBasisFunctions(
            10.0d, new MonteCarloAssetModel(process));

    // Assert
    verify(brownianMotion, atLeast(1)).getBrownianIncrement(anyInt(), anyInt());
    verify(brownianMotion, atLeast(1)).getNumberOfFactors();
    verify(brownianMotion).getNumberOfPaths();
    verify(brownianMotion).getRandomVariableForConstant(0.1d);
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
    assertTrue(actualBasisFunctions[0] instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualBasisFunctions.length);
  }

  /**
   * Test {@link RegressionBasisFunctionsFromProducts#getBasisFunctions(double,
   * MonteCarloSimulationModel)}.
   *
   * <ul>
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RegressionBasisFunctionsFromProducts#getBasisFunctions(double,
   * MonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] RegressionBasisFunctionsFromProducts.getBasisFunctions(double, MonteCarloSimulationModel)"
  })
  public void testGetBasisFunctions_thenFirstElementReturnScalar() {
    // Arrange
    ArrayList<AbstractMonteCarloProduct> products = new ArrayList<>();
    products.add(new DigitalOption(10.0d, 10.0d));
    RegressionBasisFunctionsFromProducts regressionBasisFunctionsFromProducts =
        new RegressionBasisFunctionsFromProducts(products);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    BrownianMotionWithControlVariate generator =
        new BrownianMotionWithControlVariate(brownianMotion);
    RandomVariable[] start = new RandomVariable[] {RandomVariableDifferentiableAAD.of(1.0d)};
    RandomVariable[] end = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    BrownianBridge stochasticDriver = new BrownianBridge(generator, start, end);

    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(model, stochasticDriver);

    // Act
    RandomVariable[] actualBasisFunctions =
        regressionBasisFunctionsFromProducts.getBasisFunctions(
            10.0d, new MonteCarloAssetModel(process));

    // Assert
    assertTrue(actualBasisFunctions[0] instanceof Scalar);
    assertEquals(1, actualBasisFunctions.length);
  }

  /**
   * Test {@link RegressionBasisFunctionsFromProducts#getBasisFunctions(double,
   * MonteCarloSimulationModel)}.
   *
   * <ul>
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link RegressionBasisFunctionsFromProducts#getBasisFunctions(double,
   * MonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] RegressionBasisFunctionsFromProducts.getBasisFunctions(double, MonteCarloSimulationModel)"
  })
  public void testGetBasisFunctions_thenReturnArrayLengthIsZero() {
    // Arrange
    RegressionBasisFunctionsFromProducts regressionBasisFunctionsFromProducts =
        new RegressionBasisFunctionsFromProducts(new ArrayList<>());

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act
    RandomVariable[] actualBasisFunctions =
        regressionBasisFunctionsFromProducts.getBasisFunctions(
            10.0d, new MonteCarloAssetModel(process));

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertEquals(0, actualBasisFunctions.length);
  }
}
