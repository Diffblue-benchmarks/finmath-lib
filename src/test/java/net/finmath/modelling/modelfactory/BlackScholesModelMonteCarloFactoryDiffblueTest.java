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
import net.finmath.modelling.descriptor.BlackScholesModelDescriptor;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.stochastic.Scalar;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BlackScholesModelMonteCarloFactoryDiffblueTest {
  /**
   * Test {@link
   * BlackScholesModelMonteCarloFactory#getModelFromDescriptor(BlackScholesModelDescriptor)} with
   * {@code BlackScholesModelDescriptor}.
   *
   * <p>Method under test: {@link
   * BlackScholesModelMonteCarloFactory#getModelFromDescriptor(BlackScholesModelDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.modelling.DescribedModel BlackScholesModelMonteCarloFactory.getModelFromDescriptor(BlackScholesModelDescriptor)"
  })
  public void testGetModelFromDescriptorWithBlackScholesModelDescriptor() {
    // Arrange
    Scalar scalar = mock(Scalar.class);
    when(scalar.log()).thenReturn(new RandomVariableFromDoubleArray(10.0d));
    when(scalar.squared()).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    RandomVariableFloatFactory randomVariableFactory = mock(RandomVariableFloatFactory.class);
    when(randomVariableFactory.createRandomVariable(anyDouble())).thenReturn(scalar);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(null, 3, 10, 42);
    BlackScholesModelMonteCarloFactory blackScholesModelMonteCarloFactory =
        new BlackScholesModelMonteCarloFactory(
            randomVariableFactory, new BrownianMotionWithControlVariate(brownianMotion));

    BlackScholesModelDescriptor modelDescriptor = mock(BlackScholesModelDescriptor.class);
    when(modelDescriptor.getInitialValue()).thenReturn(10.0d);
    when(modelDescriptor.getVolatility()).thenReturn(10.0d);
    when(modelDescriptor.getReferenceDate()).thenReturn(LocalDate.of(1970, 1, 1));
    when(modelDescriptor.getDiscountCurveForDiscountRate())
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));
    when(modelDescriptor.getDiscountCurveForForwardRate())
        .thenReturn(new DiscountCurveFromForwardCurve("Forward Curve Name"));

    // Act
    blackScholesModelMonteCarloFactory.getModelFromDescriptor(modelDescriptor);

    // Assert
    verify(modelDescriptor).getDiscountCurveForDiscountRate();
    verify(modelDescriptor).getDiscountCurveForForwardRate();
    verify(modelDescriptor).getInitialValue();
    verify(modelDescriptor).getReferenceDate();
    verify(modelDescriptor).getVolatility();
    verify(randomVariableFactory, atLeast(1)).createRandomVariable(10.0d);
    verify(scalar).log();
    verify(scalar).squared();
  }
}
