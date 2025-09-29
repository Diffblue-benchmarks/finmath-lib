package net.finmath.montecarlo.interestrate.models.covariance;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LIBORVolatilityModelDiffblueTest {
  /**
   * Test {@link LIBORVolatilityModel#getParameterAsDouble()}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with ten and ten.
   * </ul>
   *
   * <p>Method under test: {@link LIBORVolatilityModel#getParameterAsDouble()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] LIBORVolatilityModel.getParameterAsDouble()"})
  public void testGetParameterAsDouble_thenReturnArrayOfDoubleWithTenAndTen() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm
        liborVolatilityModelTwoParameterExponentialForm =
            new LIBORVolatilityModelTwoParameterExponentialForm(
                timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);

    // Act and Assert
    assertArrayEquals(
        new double[] {10.0d, 10.0d},
        liborVolatilityModelTwoParameterExponentialForm.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORVolatilityModel#getLiborPeriodDiscretization()}.
   *
   * <p>Method under test: {@link LIBORVolatilityModel#getLiborPeriodDiscretization()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.time.TimeDiscretization LIBORVolatilityModel.getLiborPeriodDiscretization()"
  })
  public void testGetLiborPeriodDiscretization() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelTwoParameterExponentialForm
        liborVolatilityModelTwoParameterExponentialForm =
            new LIBORVolatilityModelTwoParameterExponentialForm(
                timeDiscretization, liborPeriodDiscretization, 10.0d, 10.0d);

    // Act and Assert
    assertSame(
        liborPeriodDiscretization,
        liborVolatilityModelTwoParameterExponentialForm.getLiborPeriodDiscretization());
  }

  /**
   * Test {@link LIBORVolatilityModel#getTimeDiscretization()}.
   *
   * <p>Method under test: {@link LIBORVolatilityModel#getTimeDiscretization()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.time.TimeDiscretization LIBORVolatilityModel.getTimeDiscretization()"
  })
  public void testGetTimeDiscretization() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm
        liborVolatilityModelTwoParameterExponentialForm =
            new LIBORVolatilityModelTwoParameterExponentialForm(
                timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);

    // Act and Assert
    assertSame(
        timeDiscretization,
        liborVolatilityModelTwoParameterExponentialForm.getTimeDiscretization());
  }
}
