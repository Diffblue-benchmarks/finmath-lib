package net.finmath.modelling.modelfactory;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.modelling.descriptor.AssetModelDescriptor;
import net.finmath.modelling.descriptor.HestonModelDescriptor;
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.assetderivativevaluation.models.HestonModel;
import net.finmath.montecarlo.assetderivativevaluation.models.HestonModel.Scheme;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AssetModelMonteCarloFactoryDiffblueTest {
  /**
   * Test {@link AssetModelMonteCarloFactory#getModelFromDescriptor(AssetModelDescriptor)} with
   * {@code AssetModelDescriptor}.
   *
   * <p>Method under test: {@link
   * AssetModelMonteCarloFactory#getModelFromDescriptor(AssetModelDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.modelling.DescribedModel AssetModelMonteCarloFactory.getModelFromDescriptor(AssetModelDescriptor)"
  })
  public void testGetModelFromDescriptorWithAssetModelDescriptor() {
    // Arrange
    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    AssetModelMonteCarloFactory assetModelMonteCarloFactory =
        new AssetModelMonteCarloFactory(
            new RandomVariableFloatFactory(), stochasticDriver, Scheme.REFLECTION);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    assetModelMonteCarloFactory.getModelFromDescriptor(
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d));

    // Assert
    verify(brownianMotion).getTimeDiscretization();
  }

  /**
   * Test {@link AssetModelMonteCarloFactory#getModelFromDescriptor(AssetModelDescriptor)} with
   * {@code AssetModelDescriptor}.
   *
   * <ul>
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AssetModelMonteCarloFactory#getModelFromDescriptor(AssetModelDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.modelling.DescribedModel AssetModelMonteCarloFactory.getModelFromDescriptor(AssetModelDescriptor)"
  })
  public void testGetModelFromDescriptorWithAssetModelDescriptor_thenThrowRuntimeException() {
    // Arrange
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    AssetModelMonteCarloFactory assetModelMonteCarloFactory =
        new AssetModelMonteCarloFactory(new BrownianMotionWithControlVariate(brownianMotion));
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            assetModelMonteCarloFactory.getModelFromDescriptor(
                new HestonModelDescriptor(
                    referenceDate,
                    10.0d,
                    discountCurveForForwardRate,
                    new DiscountCurveFromForwardCurve("Forward Curve Name"),
                    10.0d,
                    10.0d,
                    10.0d,
                    10.0d,
                    10.0d)));
  }
}
