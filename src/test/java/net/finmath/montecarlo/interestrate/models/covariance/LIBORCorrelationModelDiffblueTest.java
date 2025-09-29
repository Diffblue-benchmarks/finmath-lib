package net.finmath.montecarlo.interestrate.models.covariance;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LIBORCorrelationModelDiffblueTest {
  /**
   * Test {@link LIBORCorrelationModel#getParameterAsDouble()}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with ten.
   * </ul>
   *
   * <p>Method under test: {@link LIBORCorrelationModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] LIBORCorrelationModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble_thenReturnArrayOfDoubleWithTen() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay liborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d, true);

    // Act and Assert
    assertArrayEquals(
        new double[] {10.0d}, liborCorrelationModelExponentialDecay.getParameterAsDouble(), 0.0);
  }

  /**
   * Test {@link LIBORCorrelationModel#getLiborPeriodDiscretization()}.
   *
   * <p>Method under test: {@link LIBORCorrelationModel#getLiborPeriodDiscretization()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.time.TimeDiscretization LIBORCorrelationModel.getLiborPeriodDiscretization()"
  })
  public void testGetLiborPeriodDiscretization() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORCorrelationModelExponentialDecay liborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, liborPeriodDiscretization, 3, 10.0d);

    // Act and Assert
    assertSame(
        liborPeriodDiscretization,
        liborCorrelationModelExponentialDecay.getLiborPeriodDiscretization());
  }

  /**
   * Test {@link LIBORCorrelationModel#getTimeDiscretization()}.
   *
   * <p>Method under test: {@link LIBORCorrelationModel#getTimeDiscretization()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.time.TimeDiscretization LIBORCorrelationModel.getTimeDiscretization()"
  })
  public void testGetTimeDiscretization() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORCorrelationModelExponentialDecay liborCorrelationModelExponentialDecay =
        new LIBORCorrelationModelExponentialDecay(
            timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 3, 10.0d);

    // Act and Assert
    assertSame(timeDiscretization, liborCorrelationModelExponentialDecay.getTimeDiscretization());
  }
}
