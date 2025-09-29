package net.finmath.montecarlo.assetderivativevaluation;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFactory;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MonteCarloMultiAssetBlackScholesModelDiffblueTest {
  /**
   * Test {@link
   * MonteCarloMultiAssetBlackScholesModel#MonteCarloMultiAssetBlackScholesModel(RandomVariableFactory,
   * BrownianMotion, double[], double, double[][])}.
   *
   * <p>Method under test: {@link
   * MonteCarloMultiAssetBlackScholesModel#MonteCarloMultiAssetBlackScholesModel(RandomVariableFactory,
   * BrownianMotion, double[], double, double[][])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MonteCarloMultiAssetBlackScholesModel.<init>(RandomVariableFactory, BrownianMotion, double[], double, double[][])"
  })
  public void testNewMonteCarloMultiAssetBlackScholesModel() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(timeDiscretization, 3, 10, 42);
    double[][] factorLoadings = new double[][] {new double[] {10.0d, 1.0d, 10.0d, 1.0d}};

    // Act
    MonteCarloMultiAssetBlackScholesModel actualMonteCarloMultiAssetBlackScholesModel =
        new MonteCarloMultiAssetBlackScholesModel(
            randomVariableFactory,
            new BrownianMotionWithControlVariate(brownianMotion),
            new double[] {},
            10.0d,
            factorLoadings);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualMonteCarloMultiAssetBlackScholesModel.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(0, actualMonteCarloMultiAssetBlackScholesModel.getNumberOfAssets());
    assertEquals(0, actualMonteCarloMultiAssetBlackScholesModel.getNumberOfComponents());
    assertEquals(1, actualMonteCarloMultiAssetBlackScholesModel.getCorrelations().length);
    double[][] factorLoadings2 = actualMonteCarloMultiAssetBlackScholesModel.getFactorLoadings();
    assertEquals(1, factorLoadings2.length);
    assertEquals(10, actualMonteCarloMultiAssetBlackScholesModel.getNumberOfPaths());
    assertEquals(10.0d, actualMonteCarloMultiAssetBlackScholesModel.getRiskFreeRate(), 0.0);
    assertEquals(3, actualMonteCarloMultiAssetBlackScholesModel.getNumberOfFactors());
    assertSame(timeDiscretization, timeDiscretization2);
    assertSame(factorLoadings, factorLoadings2);
    assertArrayEquals(
        new double[] {14.212670403551895d},
        actualMonteCarloMultiAssetBlackScholesModel.getVolatilities(),
        0.0);
  }
}
