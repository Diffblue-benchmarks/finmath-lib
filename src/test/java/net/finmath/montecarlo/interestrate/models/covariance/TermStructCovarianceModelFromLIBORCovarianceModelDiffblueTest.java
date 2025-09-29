package net.finmath.montecarlo.interestrate.models.covariance;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TermStructCovarianceModelFromLIBORCovarianceModelDiffblueTest {
  /**
   * Test {@link TermStructCovarianceModelFromLIBORCovarianceModel#getNumberOfFactors()}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link
   * TermStructCovarianceModelFromLIBORCovarianceModel#getNumberOfFactors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TermStructCovarianceModelFromLIBORCovarianceModel.getNumberOfFactors()"})
  public void testGetNumberOfFactors_thenReturnThree() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    int actualNumberOfFactors =
        new TermStructCovarianceModelFromLIBORCovarianceModel(
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d))
            .getNumberOfFactors();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals(3, actualNumberOfFactors);
  }
}
