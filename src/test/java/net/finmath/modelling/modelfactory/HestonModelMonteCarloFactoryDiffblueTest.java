package net.finmath.modelling.modelfactory;

import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.modelling.descriptor.HestonModelDescriptor;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.assetderivativevaluation.models.HestonModel;
import net.finmath.montecarlo.assetderivativevaluation.models.HestonModel.Scheme;
import net.finmath.stochastic.Scalar;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class HestonModelMonteCarloFactoryDiffblueTest {
  /**
   * Test {@link HestonModelMonteCarloFactory#getModelFromDescriptor(HestonModelDescriptor)} with
   * {@code HestonModelDescriptor}.
   *
   * <p>Method under test: {@link
   * HestonModelMonteCarloFactory#getModelFromDescriptor(HestonModelDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.modelling.DescribedModel HestonModelMonteCarloFactory.getModelFromDescriptor(HestonModelDescriptor)"
  })
  public void testGetModelFromDescriptorWithHestonModelDescriptor() {
    // Arrange
    Scalar scalar = mock(Scalar.class);
    when(scalar.mult(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    Scalar scalar2 = mock(Scalar.class);
    when(scalar2.sub(anyDouble())).thenReturn(scalar);

    Scalar scalar3 = mock(Scalar.class);
    when(scalar3.squared()).thenReturn(scalar2);

    RandomVariableFloatFactory randomVariableFactory = mock(RandomVariableFloatFactory.class);
    when(randomVariableFactory.createRandomVariable(anyDouble())).thenReturn(scalar3);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(null, 3, 10, 42);
    HestonModelMonteCarloFactory hestonModelMonteCarloFactory =
        new HestonModelMonteCarloFactory(
            Scheme.REFLECTION,
            randomVariableFactory,
            new BrownianMotionWithControlVariate(brownianMotion));

    HestonModelDescriptor modelDescriptor = mock(HestonModelDescriptor.class);
    when(modelDescriptor.getInitialValue()).thenReturn(10.0d);
    when(modelDescriptor.getKappa()).thenReturn(10.0d);
    when(modelDescriptor.getRho()).thenReturn(10.0d);
    when(modelDescriptor.getTheta()).thenReturn(10.0d);
    when(modelDescriptor.getVolatility()).thenReturn(10.0d);
    when(modelDescriptor.getXi()).thenReturn(10.0d);
    when(modelDescriptor.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    when(modelDescriptor.getDiscountCurveForDiscountRate())
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));
    when(modelDescriptor.getDiscountCurveForForwardRate())
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act
    hestonModelMonteCarloFactory.getModelFromDescriptor(modelDescriptor);

    // Assert
    verify(modelDescriptor).getDiscountCurveForDiscountRate();
    verify(modelDescriptor).getDiscountCurveForForwardRate();
    verify(modelDescriptor).getInitialValue();
    verify(modelDescriptor).getKappa();
    verify(modelDescriptor).getReferenceDate();
    verify(modelDescriptor).getRho();
    verify(modelDescriptor).getTheta();
    verify(modelDescriptor).getVolatility();
    verify(modelDescriptor).getXi();
    verify(randomVariableFactory, atLeast(1)).createRandomVariable(10.0d);
    verify(scalar).mult(-1.0d);
    verify(scalar3).squared();
    verify(scalar2).sub(1.0d);
  }
}
