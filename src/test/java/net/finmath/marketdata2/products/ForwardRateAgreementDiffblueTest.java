package net.finmath.marketdata2.products;

import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.marketdata2.model.AnalyticModel;
import net.finmath.marketdata2.model.AnalyticModelFromCurvesAndVols;
import net.finmath.time.RegularSchedule;
import net.finmath.time.Schedule;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ForwardRateAgreementDiffblueTest {
  /**
   * Test {@link ForwardRateAgreement#ForwardRateAgreement(Schedule, double, String, String)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link ForwardRateAgreement#ForwardRateAgreement(Schedule, double,
   * String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ForwardRateAgreement.<init>(Schedule, double, String, String)"})
  public void testNewForwardRateAgreement_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new ForwardRateAgreement(
                new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
                10.0d,
                "Forward Curve Name",
                "3"));
  }

  /**
   * Test {@link ForwardRateAgreement#ForwardRateAgreement(Schedule, double, String, String,
   * boolean)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link ForwardRateAgreement#ForwardRateAgreement(Schedule, double,
   * String, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ForwardRateAgreement.<init>(Schedule, double, String, String, boolean)"})
  public void testNewForwardRateAgreement_thenThrowIllegalArgumentException2() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new ForwardRateAgreement(
                new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
                10.0d,
                "Forward Curve Name",
                "3",
                true));
  }

  /**
   * Test {@link ForwardRateAgreement#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link ForwardRateAgreement#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.stochastic.RandomVariable ForwardRateAgreement.getValue(double, AnalyticModel)"
  })
  public void testGetValueWithDoubleAnalyticModel_thenThrowIllegalArgumentException() {
    // Arrange
    ForwardRateAgreement forwardRateAgreement =
        new ForwardRateAgreement(
            new RegularSchedule(new TenorFromArray(10.0d, 1, 0.5d)),
            10.0d,
            "Forward Curve Name",
            "3");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> forwardRateAgreement.getValue(10.0d, new AnalyticModelFromCurvesAndVols()));
  }

  /**
   * Test {@link ForwardRateAgreement#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link ForwardRateAgreement#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.stochastic.RandomVariable ForwardRateAgreement.getValue(double, AnalyticModel)"
  })
  public void testGetValueWithDoubleAnalyticModel_thenThrowIllegalArgumentException2() {
    // Arrange
    ForwardRateAgreement forwardRateAgreement =
        new ForwardRateAgreement(
            new RegularSchedule(new TenorFromArray(10.0d, 1, 0.5d)),
            10.0d,
            "Forward Curve Name",
            "3");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> forwardRateAgreement.getValue(10.0d, (AnalyticModel) null));
  }

  /**
   * Test {@link ForwardRateAgreement#getRate(AnalyticModel)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link ForwardRateAgreement#getRate(AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.stochastic.RandomVariable ForwardRateAgreement.getRate(AnalyticModel)"
  })
  public void testGetRate_thenThrowIllegalArgumentException() {
    // Arrange
    ForwardRateAgreement forwardRateAgreement =
        new ForwardRateAgreement(
            new RegularSchedule(new TenorFromArray(10.0d, 1, 0.5d)),
            10.0d,
            "Forward Curve Name",
            "3");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> forwardRateAgreement.getRate(new AnalyticModelFromCurvesAndVols()));
  }

  /**
   * Test {@link ForwardRateAgreement#getRate(AnalyticModel)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link ForwardRateAgreement#getRate(AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.stochastic.RandomVariable ForwardRateAgreement.getRate(AnalyticModel)"
  })
  public void testGetRate_thenThrowIllegalArgumentException2() {
    // Arrange
    ForwardRateAgreement forwardRateAgreement =
        new ForwardRateAgreement(
            new RegularSchedule(new TenorFromArray(10.0d, 1, 0.5d)),
            10.0d,
            "Forward Curve Name",
            "3");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> forwardRateAgreement.getRate(null));
  }
}
