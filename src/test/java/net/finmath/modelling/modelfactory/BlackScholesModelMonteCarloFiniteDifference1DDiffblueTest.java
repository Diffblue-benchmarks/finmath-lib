package net.finmath.modelling.modelfactory;

import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.DiscountCurveInterpolation;
import net.finmath.modelling.descriptor.BlackScholesModelDescriptor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BlackScholesModelMonteCarloFiniteDifference1DDiffblueTest {
  /**
   * Test {@link
   * BlackScholesModelMonteCarloFiniteDifference1D#getModelFromDescriptor(BlackScholesModelDescriptor)}
   * with {@code BlackScholesModelDescriptor}.
   *
   * <p>Method under test: {@link
   * BlackScholesModelMonteCarloFiniteDifference1D#getModelFromDescriptor(BlackScholesModelDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.modelling.DescribedModel BlackScholesModelMonteCarloFiniteDifference1D.getModelFromDescriptor(BlackScholesModelDescriptor)"
  })
  public void testGetModelFromDescriptorWithBlackScholesModelDescriptor() {
    // Arrange
    BlackScholesModelMonteCarloFiniteDifference1D blackScholesModelMonteCarloFiniteDifference1D =
        new BlackScholesModelMonteCarloFiniteDifference1D(10.0d);

    DiscountCurveInterpolation discountCurveForForwardRate = mock(DiscountCurveInterpolation.class);
    when(discountCurveForForwardRate.getDiscountFactor(anyDouble())).thenReturn(10.0d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    blackScholesModelMonteCarloFiniteDifference1D.getModelFromDescriptor(
        new BlackScholesModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d));

    // Assert
    verify(discountCurveForForwardRate).getDiscountFactor(1.0d);
  }
}
