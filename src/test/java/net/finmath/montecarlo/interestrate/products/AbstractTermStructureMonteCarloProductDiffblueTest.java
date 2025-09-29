package net.finmath.montecarlo.interestrate.products;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.MonteCarloSimulationModel;
import net.finmath.montecarlo.assetderivativevaluation.MonteCarloAssetModel;
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AbstractTermStructureMonteCarloProductDiffblueTest {
  /**
   * Test {@link AbstractTermStructureMonteCarloProduct#getValue(double, MonteCarloSimulationModel)}
   * with {@code double}, {@code MonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link AbstractTermStructureMonteCarloProduct#getValue(double,
   * MonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.stochastic.RandomVariable AbstractTermStructureMonteCarloProduct.getValue(double, MonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleMonteCarloSimulationModel() throws CalculationException {
    // Arrange
    ForwardRateVolatilitySurfaceCurvature forwardRateVolatilitySurfaceCurvature =
        new ForwardRateVolatilitySurfaceCurvature(10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            forwardRateVolatilitySurfaceCurvature.getValue(
                10.0d, new MonteCarloAssetModel(process)));
    verify(brownianMotion).getTimeDiscretization();
  }

  /**
   * Test {@link AbstractTermStructureMonteCarloProduct#getValueForModifiedData(double,
   * MonteCarloSimulationModel, Map)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AbstractTermStructureMonteCarloProduct#getValueForModifiedData(double,
   * MonteCarloSimulationModel, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.stochastic.RandomVariable AbstractTermStructureMonteCarloProduct.getValueForModifiedData(double, MonteCarloSimulationModel, Map)"
  })
  public void testGetValueForModifiedData_thenThrowIllegalArgumentException()
      throws CalculationException {
    // Arrange
    ForwardRateVolatilitySurfaceCurvature forwardRateVolatilitySurfaceCurvature =
        new ForwardRateVolatilitySurfaceCurvature(10.0d);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(new BachelierModel(10.0d, 10.0d, 10.0d), stochasticDriver);
    MonteCarloAssetModel monteCarloSimulationModel = new MonteCarloAssetModel(process);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            forwardRateVolatilitySurfaceCurvature.getValueForModifiedData(
                10.0d, monteCarloSimulationModel, new HashMap<>()));
    verify(brownianMotion, atLeast(1)).getTimeDiscretization();
  }
}
