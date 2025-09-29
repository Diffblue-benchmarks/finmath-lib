package net.finmath.singleswaprate.calibration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.marketdata.model.volatilities.SwaptionDataLattice;
import net.finmath.marketdata.model.volatilities.SwaptionDataLattice.QuotingConvention;
import net.finmath.singleswaprate.annuitymapping.AnnuityMapping;
import net.finmath.singleswaprate.annuitymapping.AnnuityMapping.AnnuityMappingType;
import net.finmath.singleswaprate.model.VolatilityCubeModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SABRCubeParallelCalibrationDiffblueTest {
  /**
   * Test {@link SABRCubeParallelCalibration#SABRCubeParallelCalibration(LocalDate,
   * SwaptionDataLattice, SwaptionDataLattice, SwaptionDataLattice, VolatilityCubeModel,
   * AnnuityMappingType)}.
   *
   * <ul>
   *   <li>Given {@code 3}.
   *   <li>Then return {@code Forward Curve Name}.
   * </ul>
   *
   * <p>Method under test: {@link SABRCubeParallelCalibration#SABRCubeParallelCalibration(LocalDate,
   * SwaptionDataLattice, SwaptionDataLattice, SwaptionDataLattice, VolatilityCubeModel,
   * AnnuityMapping.AnnuityMappingType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SABRCubeParallelCalibration.<init>(LocalDate, SwaptionDataLattice, SwaptionDataLattice, SwaptionDataLattice, VolatilityCubeModel, AnnuityMapping.AnnuityMappingType)"
  })
  public void testNewSABRCubeParallelCalibration_given3_thenReturnForwardCurveName() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    SwaptionDataLattice cashPayerPremiums = mock(SwaptionDataLattice.class);
    when(cashPayerPremiums.getDiscountCurveName()).thenReturn("3");
    when(cashPayerPremiums.getForwardCurveName()).thenReturn("Forward Curve Name");
    when(cashPayerPremiums.getQuotingConvention()).thenReturn(QuotingConvention.PAYERPRICE);

    SwaptionDataLattice cashReceiverPremiums = mock(SwaptionDataLattice.class);
    when(cashReceiverPremiums.getQuotingConvention()).thenReturn(QuotingConvention.RECEIVERPRICE);
    VolatilityCubeModel model = mock(VolatilityCubeModel.class);

    // Act
    SABRCubeParallelCalibration actualSabrCubeParallelCalibration =
        new SABRCubeParallelCalibration(
            referenceDate,
            cashPayerPremiums,
            cashReceiverPremiums,
            mock(SwaptionDataLattice.class),
            model,
            AnnuityMappingType.BASICPITERBARG);

    // Assert
    verify(cashPayerPremiums).getDiscountCurveName();
    verify(cashPayerPremiums).getForwardCurveName();
    verify(cashPayerPremiums).getQuotingConvention();
    verify(cashReceiverPremiums).getQuotingConvention();
    assertEquals("Forward Curve Name", actualSabrCubeParallelCalibration.getForwardCurveName());
    assertNull(actualSabrCubeParallelCalibration.getInitialParameters());
    assertEquals(-0.15d, actualSabrCubeParallelCalibration.getReplicationLowerBound(), 0.0);
    assertEquals(0.0d, actualSabrCubeParallelCalibration.getInitialCorrelationDecay(), 0.0);
    assertEquals(0.15d, actualSabrCubeParallelCalibration.getReplicationUpperBound(), 0.0);
    assertEquals(0.1d, actualSabrCubeParallelCalibration.getInitialRho(), 0.0);
    assertEquals(0.25d, actualSabrCubeParallelCalibration.getInitialDisplacement(), 0.0);
    assertEquals(0.5d, actualSabrCubeParallelCalibration.getInitialBeta(), 0.0);
    assertEquals(1.0E-4d, actualSabrCubeParallelCalibration.getInitialVolvol(), 0.0);
    assertEquals(1.0d, actualSabrCubeParallelCalibration.getInitialIborOisDecorrelation(), 0.0);
    assertEquals(250, actualSabrCubeParallelCalibration.getMaxIterations());
    assertEquals(500, actualSabrCubeParallelCalibration.getReplicationNumberOfEvaluationPoints());
    assertEquals(8, actualSabrCubeParallelCalibration.getNumberOfThreads());
    assertTrue(actualSabrCubeParallelCalibration.isReplicationUseAsOffset());
    assertSame(referenceDate, actualSabrCubeParallelCalibration.getReferenceDate());
    assertSame(model, actualSabrCubeParallelCalibration.getModel());
  }

  /**
   * Test {@link SABRCubeParallelCalibration#SABRCubeParallelCalibration(LocalDate,
   * SwaptionDataLattice, SwaptionDataLattice, SwaptionDataLattice, VolatilityCubeModel,
   * AnnuityMappingType)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link SABRCubeParallelCalibration#SABRCubeParallelCalibration(LocalDate,
   * SwaptionDataLattice, SwaptionDataLattice, SwaptionDataLattice, VolatilityCubeModel,
   * AnnuityMapping.AnnuityMappingType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SABRCubeParallelCalibration.<init>(LocalDate, SwaptionDataLattice, SwaptionDataLattice, SwaptionDataLattice, VolatilityCubeModel, AnnuityMapping.AnnuityMappingType)"
  })
  public void testNewSABRCubeParallelCalibration_thenThrowIllegalArgumentException() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    SwaptionDataLattice cashPayerPremiums = mock(SwaptionDataLattice.class);
    when(cashPayerPremiums.getQuotingConvention())
        .thenReturn(QuotingConvention.PAYERVOLATILITYLOGNORMAL);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new SABRCubeParallelCalibration(
                referenceDate,
                cashPayerPremiums,
                mock(SwaptionDataLattice.class),
                mock(SwaptionDataLattice.class),
                mock(VolatilityCubeModel.class),
                AnnuityMappingType.BASICPITERBARG));
    verify(cashPayerPremiums).getQuotingConvention();
  }

  /**
   * Test {@link SABRCubeParallelCalibration#SABRCubeParallelCalibration(LocalDate,
   * SwaptionDataLattice, SwaptionDataLattice, SwaptionDataLattice, VolatilityCubeModel,
   * AnnuityMappingType)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link SABRCubeParallelCalibration#SABRCubeParallelCalibration(LocalDate,
   * SwaptionDataLattice, SwaptionDataLattice, SwaptionDataLattice, VolatilityCubeModel,
   * AnnuityMapping.AnnuityMappingType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SABRCubeParallelCalibration.<init>(LocalDate, SwaptionDataLattice, SwaptionDataLattice, SwaptionDataLattice, VolatilityCubeModel, AnnuityMapping.AnnuityMappingType)"
  })
  public void testNewSABRCubeParallelCalibration_thenThrowIllegalArgumentException2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    SwaptionDataLattice cashPayerPremiums = mock(SwaptionDataLattice.class);
    when(cashPayerPremiums.getQuotingConvention()).thenReturn(QuotingConvention.PAYERPRICE);

    SwaptionDataLattice cashReceiverPremiums = mock(SwaptionDataLattice.class);
    when(cashReceiverPremiums.getQuotingConvention())
        .thenReturn(QuotingConvention.PAYERVOLATILITYLOGNORMAL);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new SABRCubeParallelCalibration(
                referenceDate,
                cashPayerPremiums,
                cashReceiverPremiums,
                mock(SwaptionDataLattice.class),
                mock(VolatilityCubeModel.class),
                AnnuityMappingType.BASICPITERBARG));
    verify(cashPayerPremiums).getQuotingConvention();
    verify(cashReceiverPremiums).getQuotingConvention();
  }
}
