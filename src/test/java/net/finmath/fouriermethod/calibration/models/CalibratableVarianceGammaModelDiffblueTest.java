package net.finmath.fouriermethod.calibration.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.fouriermethod.calibration.BoundConstraint;
import net.finmath.fouriermethod.calibration.NegativityConstraint;
import net.finmath.fouriermethod.calibration.ScalarParameterInformation;
import net.finmath.fouriermethod.calibration.ScalarParameterInformationImplementation;
import net.finmath.fouriermethod.models.CharacteristicFunctionModel;
import net.finmath.fouriermethod.models.VarianceGammaModel;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.curves.DiscountCurve;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.DiscountCurveInterpolation;
import net.finmath.modelling.ModelDescriptor;
import net.finmath.modelling.descriptor.VarianceGammaModelDescriptor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class CalibratableVarianceGammaModelDiffblueTest {
  /**
   * Test {@link
   * CalibratableVarianceGammaModel#CalibratableVarianceGammaModel(VarianceGammaModelDescriptor)}.
   *
   * <p>Method under test: {@link
   * CalibratableVarianceGammaModel#CalibratableVarianceGammaModel(VarianceGammaModelDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratableVarianceGammaModel.<init>(VarianceGammaModelDescriptor)"})
  public void testNewCalibratableVarianceGammaModel() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    VarianceGammaModelDescriptor descriptor =
        new VarianceGammaModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d);

    // Act
    CalibratableVarianceGammaModel actualCalibratableVarianceGammaModel =
        new CalibratableVarianceGammaModel(descriptor);

    // Assert
    assertTrue(
        actualCalibratableVarianceGammaModel.getCharacteristicFunctionModel()
            instanceof VarianceGammaModel);
    ModelDescriptor modelDescriptor = actualCalibratableVarianceGammaModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof VarianceGammaModelDescriptor);
    assertSame(descriptor, modelDescriptor);
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d},
        actualCalibratableVarianceGammaModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d},
        actualCalibratableVarianceGammaModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link
   * CalibratableVarianceGammaModel#CalibratableVarianceGammaModel(VarianceGammaModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)}.
   *
   * <p>Method under test: {@link
   * CalibratableVarianceGammaModel#CalibratableVarianceGammaModel(VarianceGammaModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratableVarianceGammaModel.<init>(VarianceGammaModelDescriptor, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)"
  })
  public void testNewCalibratableVarianceGammaModel2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    VarianceGammaModelDescriptor descriptor =
        new VarianceGammaModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation sigmaInfo =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation thetaInfo =
        new ScalarParameterInformationImplementation(true);

    // Act
    CalibratableVarianceGammaModel actualCalibratableVarianceGammaModel =
        new CalibratableVarianceGammaModel(
            descriptor, sigmaInfo, thetaInfo, new ScalarParameterInformationImplementation(true));

    // Assert
    CharacteristicFunctionModel characteristicFunctionModel =
        actualCalibratableVarianceGammaModel.getCharacteristicFunctionModel();
    assertTrue(characteristicFunctionModel instanceof VarianceGammaModel);
    DiscountCurve discountCurveForDiscountRate =
        ((VarianceGammaModel) characteristicFunctionModel).getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCalibratableVarianceGammaModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof VarianceGammaModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((VarianceGammaModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d},
        actualCalibratableVarianceGammaModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d},
        actualCalibratableVarianceGammaModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link
   * CalibratableVarianceGammaModel#CalibratableVarianceGammaModel(VarianceGammaModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)}.
   *
   * <p>Method under test: {@link
   * CalibratableVarianceGammaModel#CalibratableVarianceGammaModel(VarianceGammaModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratableVarianceGammaModel.<init>(VarianceGammaModelDescriptor, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)"
  })
  public void testNewCalibratableVarianceGammaModel3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    VarianceGammaModelDescriptor descriptor =
        new VarianceGammaModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation sigmaInfo =
        new ScalarParameterInformationImplementation(new NegativityConstraint());
    ScalarParameterInformationImplementation thetaInfo =
        new ScalarParameterInformationImplementation(true);

    // Act
    CalibratableVarianceGammaModel actualCalibratableVarianceGammaModel =
        new CalibratableVarianceGammaModel(
            descriptor, sigmaInfo, thetaInfo, new ScalarParameterInformationImplementation(true));

    // Assert
    CharacteristicFunctionModel characteristicFunctionModel =
        actualCalibratableVarianceGammaModel.getCharacteristicFunctionModel();
    assertTrue(characteristicFunctionModel instanceof VarianceGammaModel);
    DiscountCurve discountCurveForDiscountRate =
        ((VarianceGammaModel) characteristicFunctionModel).getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCalibratableVarianceGammaModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof VarianceGammaModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((VarianceGammaModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d},
        actualCalibratableVarianceGammaModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {0.0d, 1000000.0d, 1000000.0d},
        actualCalibratableVarianceGammaModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link
   * CalibratableVarianceGammaModel#CalibratableVarianceGammaModel(VarianceGammaModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)}.
   *
   * <p>Method under test: {@link
   * CalibratableVarianceGammaModel#CalibratableVarianceGammaModel(VarianceGammaModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratableVarianceGammaModel.<init>(VarianceGammaModelDescriptor, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)"
  })
  public void testNewCalibratableVarianceGammaModel4() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    VarianceGammaModelDescriptor descriptor =
        new VarianceGammaModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation sigmaInfo =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation thetaInfo =
        new ScalarParameterInformationImplementation(new NegativityConstraint());

    // Act
    CalibratableVarianceGammaModel actualCalibratableVarianceGammaModel =
        new CalibratableVarianceGammaModel(
            descriptor, sigmaInfo, thetaInfo, new ScalarParameterInformationImplementation(true));

    // Assert
    CharacteristicFunctionModel characteristicFunctionModel =
        actualCalibratableVarianceGammaModel.getCharacteristicFunctionModel();
    assertTrue(characteristicFunctionModel instanceof VarianceGammaModel);
    DiscountCurve discountCurveForDiscountRate =
        ((VarianceGammaModel) characteristicFunctionModel).getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCalibratableVarianceGammaModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof VarianceGammaModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((VarianceGammaModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d},
        actualCalibratableVarianceGammaModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 0.0d, 1000000.0d},
        actualCalibratableVarianceGammaModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link
   * CalibratableVarianceGammaModel#CalibratableVarianceGammaModel(VarianceGammaModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)}.
   *
   * <p>Method under test: {@link
   * CalibratableVarianceGammaModel#CalibratableVarianceGammaModel(VarianceGammaModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratableVarianceGammaModel.<init>(VarianceGammaModelDescriptor, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)"
  })
  public void testNewCalibratableVarianceGammaModel5() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    VarianceGammaModelDescriptor descriptor =
        new VarianceGammaModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation sigmaInfo =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation thetaInfo =
        new ScalarParameterInformationImplementation(true);

    // Act
    CalibratableVarianceGammaModel actualCalibratableVarianceGammaModel =
        new CalibratableVarianceGammaModel(
            descriptor,
            sigmaInfo,
            thetaInfo,
            new ScalarParameterInformationImplementation(new NegativityConstraint()));

    // Assert
    CharacteristicFunctionModel characteristicFunctionModel =
        actualCalibratableVarianceGammaModel.getCharacteristicFunctionModel();
    assertTrue(characteristicFunctionModel instanceof VarianceGammaModel);
    DiscountCurve discountCurveForDiscountRate =
        ((VarianceGammaModel) characteristicFunctionModel).getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCalibratableVarianceGammaModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof VarianceGammaModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((VarianceGammaModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d},
        actualCalibratableVarianceGammaModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 0.0d},
        actualCalibratableVarianceGammaModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link
   * CalibratableVarianceGammaModel#CalibratableVarianceGammaModel(VarianceGammaModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)}.
   *
   * <p>Method under test: {@link
   * CalibratableVarianceGammaModel#CalibratableVarianceGammaModel(VarianceGammaModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratableVarianceGammaModel.<init>(VarianceGammaModelDescriptor, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)"
  })
  public void testNewCalibratableVarianceGammaModel6() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    VarianceGammaModelDescriptor descriptor =
        new VarianceGammaModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation sigmaInfo =
        new ScalarParameterInformationImplementation(new BoundConstraint(1000000.0d, 1000000.0d));
    ScalarParameterInformationImplementation thetaInfo =
        new ScalarParameterInformationImplementation(true);

    // Act
    CalibratableVarianceGammaModel actualCalibratableVarianceGammaModel =
        new CalibratableVarianceGammaModel(
            descriptor, sigmaInfo, thetaInfo, new ScalarParameterInformationImplementation(true));

    // Assert
    CharacteristicFunctionModel characteristicFunctionModel =
        actualCalibratableVarianceGammaModel.getCharacteristicFunctionModel();
    assertTrue(characteristicFunctionModel instanceof VarianceGammaModel);
    DiscountCurve discountCurveForDiscountRate =
        ((VarianceGammaModel) characteristicFunctionModel).getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCalibratableVarianceGammaModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof VarianceGammaModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((VarianceGammaModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {1000000.0d, -1000000.0d, -1000000.0d},
        actualCalibratableVarianceGammaModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d},
        actualCalibratableVarianceGammaModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link
   * CalibratableVarianceGammaModel#CalibratableVarianceGammaModel(VarianceGammaModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)}.
   *
   * <p>Method under test: {@link
   * CalibratableVarianceGammaModel#CalibratableVarianceGammaModel(VarianceGammaModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratableVarianceGammaModel.<init>(VarianceGammaModelDescriptor, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)"
  })
  public void testNewCalibratableVarianceGammaModel7() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    VarianceGammaModelDescriptor descriptor =
        new VarianceGammaModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation sigmaInfo =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation thetaInfo =
        new ScalarParameterInformationImplementation(new BoundConstraint(1000000.0d, 1000000.0d));

    // Act
    CalibratableVarianceGammaModel actualCalibratableVarianceGammaModel =
        new CalibratableVarianceGammaModel(
            descriptor, sigmaInfo, thetaInfo, new ScalarParameterInformationImplementation(true));

    // Assert
    CharacteristicFunctionModel characteristicFunctionModel =
        actualCalibratableVarianceGammaModel.getCharacteristicFunctionModel();
    assertTrue(characteristicFunctionModel instanceof VarianceGammaModel);
    DiscountCurve discountCurveForDiscountRate =
        ((VarianceGammaModel) characteristicFunctionModel).getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCalibratableVarianceGammaModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof VarianceGammaModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((VarianceGammaModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, 1000000.0d, -1000000.0d},
        actualCalibratableVarianceGammaModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d},
        actualCalibratableVarianceGammaModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link
   * CalibratableVarianceGammaModel#CalibratableVarianceGammaModel(VarianceGammaModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)}.
   *
   * <p>Method under test: {@link
   * CalibratableVarianceGammaModel#CalibratableVarianceGammaModel(VarianceGammaModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratableVarianceGammaModel.<init>(VarianceGammaModelDescriptor, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)"
  })
  public void testNewCalibratableVarianceGammaModel8() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    VarianceGammaModelDescriptor descriptor =
        new VarianceGammaModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation sigmaInfo =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation thetaInfo =
        new ScalarParameterInformationImplementation(true);

    // Act
    CalibratableVarianceGammaModel actualCalibratableVarianceGammaModel =
        new CalibratableVarianceGammaModel(
            descriptor,
            sigmaInfo,
            thetaInfo,
            new ScalarParameterInformationImplementation(
                new BoundConstraint(1000000.0d, 1000000.0d)));

    // Assert
    CharacteristicFunctionModel characteristicFunctionModel =
        actualCalibratableVarianceGammaModel.getCharacteristicFunctionModel();
    assertTrue(characteristicFunctionModel instanceof VarianceGammaModel);
    DiscountCurve discountCurveForDiscountRate =
        ((VarianceGammaModel) characteristicFunctionModel).getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCalibratableVarianceGammaModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof VarianceGammaModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((VarianceGammaModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, 1000000.0d},
        actualCalibratableVarianceGammaModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d},
        actualCalibratableVarianceGammaModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableVarianceGammaModel#getCloneForModifiedParameters(double[])}.
   *
   * <ul>
   *   <li>Then return {@link CalibratableVarianceGammaModel}.
   * </ul>
   *
   * <p>Method under test: {@link
   * CalibratableVarianceGammaModel#getCloneForModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CalibratableProcess CalibratableVarianceGammaModel.getCloneForModifiedParameters(double[])"
  })
  public void testGetCloneForModifiedParameters_thenReturnCalibratableVarianceGammaModel() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    CalibratableProcess actualCloneForModifiedParameters =
        new CalibratableVarianceGammaModel(
                new VarianceGammaModelDescriptor(
                    referenceDate,
                    10.0d,
                    discountCurveForForwardRate,
                    new DiscountCurveFromForwardCurve("Forward Curve Name"),
                    10.0d,
                    10.0d,
                    10.0d))
            .getCloneForModifiedParameters(new double[] {10.0d, -1000000.0d, 10.0d, -1000000.0d});

    // Assert
    assertTrue(actualCloneForModifiedParameters instanceof CalibratableVarianceGammaModel);
    assertTrue(
        actualCloneForModifiedParameters.getCharacteristicFunctionModel()
            instanceof VarianceGammaModel);
    assertTrue(
        actualCloneForModifiedParameters.getModelDescriptor()
            instanceof VarianceGammaModelDescriptor);
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d},
        actualCloneForModifiedParameters.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d},
        actualCloneForModifiedParameters.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CalibratableVarianceGammaModel#getModelDescriptor()}
   *   <li>{@link CalibratableVarianceGammaModel#getParameterLowerBounds()}
   *   <li>{@link CalibratableVarianceGammaModel#getParameterUpperBounds()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ModelDescriptor CalibratableVarianceGammaModel.getModelDescriptor()",
    "double[] CalibratableVarianceGammaModel.getParameterLowerBounds()",
    "double[] CalibratableVarianceGammaModel.getParameterUpperBounds()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    VarianceGammaModelDescriptor descriptor =
        new VarianceGammaModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d);
    CalibratableVarianceGammaModel calibratableVarianceGammaModel =
        new CalibratableVarianceGammaModel(descriptor);

    // Act
    ModelDescriptor actualModelDescriptor = calibratableVarianceGammaModel.getModelDescriptor();
    double[] actualParameterLowerBounds = calibratableVarianceGammaModel.getParameterLowerBounds();

    // Assert
    assertSame(descriptor, actualModelDescriptor);
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d}, actualParameterLowerBounds, 0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d},
        calibratableVarianceGammaModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableVarianceGammaModel#getCharacteristicFunctionModel()}.
   *
   * <p>Method under test: {@link CalibratableVarianceGammaModel#getCharacteristicFunctionModel()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CharacteristicFunctionModel CalibratableVarianceGammaModel.getCharacteristicFunctionModel()"
  })
  public void testGetCharacteristicFunctionModel() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    VarianceGammaModelDescriptor descriptor =
        new VarianceGammaModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            discountCurveForDiscountRate,
            10.0d,
            10.0d,
            10.0d);
    CalibratableVarianceGammaModel calibratableVarianceGammaModel =
        new CalibratableVarianceGammaModel(descriptor);

    // Act
    CharacteristicFunctionModel actualCharacteristicFunctionModel =
        calibratableVarianceGammaModel.getCharacteristicFunctionModel();

    // Assert
    assertTrue(actualCharacteristicFunctionModel instanceof VarianceGammaModel);
    DiscountCurve discountCurveForForwardRate2 =
        ((VarianceGammaModel) actualCharacteristicFunctionModel).getDiscountCurveForForwardRate();
    assertTrue(discountCurveForForwardRate2 instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = calibratableVarianceGammaModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof VarianceGammaModelDescriptor);
    assertNull(((VarianceGammaModel) actualCharacteristicFunctionModel).getReferenceDate());
    assertEquals(
        10.0d, ((VarianceGammaModel) actualCharacteristicFunctionModel).getInitialValue(), 0.0);
    assertEquals(10.0d, ((VarianceGammaModel) actualCharacteristicFunctionModel).getNu(), 0.0);
    assertEquals(10.0d, ((VarianceGammaModel) actualCharacteristicFunctionModel).getSigma(), 0.0);
    assertEquals(10.0d, ((VarianceGammaModel) actualCharacteristicFunctionModel).getTheta(), 0.0);
    assertEquals(
        Double.NaN,
        ((VarianceGammaModel) actualCharacteristicFunctionModel).getDiscountRate(),
        0.0);
    assertEquals(
        Double.NaN,
        ((VarianceGammaModel) actualCharacteristicFunctionModel).getRiskFreeRate(),
        0.0);
    assertSame(
        discountCurveForDiscountRate,
        ((VarianceGammaModel) actualCharacteristicFunctionModel).getDiscountCurveForDiscountRate());
    assertSame(discountCurveForForwardRate, discountCurveForForwardRate2);
    assertSame(descriptor, modelDescriptor);
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d},
        calibratableVarianceGammaModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d},
        calibratableVarianceGammaModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableVarianceGammaModel#getCharacteristicFunctionModel()}.
   *
   * <p>Method under test: {@link CalibratableVarianceGammaModel#getCharacteristicFunctionModel()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CharacteristicFunctionModel CalibratableVarianceGammaModel.getCharacteristicFunctionModel()"
  })
  public void testGetCharacteristicFunctionModel2() {
    // Arrange
    DiscountCurveInterpolation discountCurveForForwardRate = mock(DiscountCurveInterpolation.class);
    when(discountCurveForForwardRate.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    DiscountCurveInterpolation discountCurveForDiscountRate =
        mock(DiscountCurveInterpolation.class);
    when(discountCurveForDiscountRate.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);

    VarianceGammaModelDescriptor descriptor =
        new VarianceGammaModelDescriptor(
            LocalDate.of(1970, 1, 1),
            10.0d,
            discountCurveForForwardRate,
            discountCurveForDiscountRate,
            10.0d,
            10.0d,
            10.0d);
    CalibratableVarianceGammaModel calibratableVarianceGammaModel =
        new CalibratableVarianceGammaModel(descriptor);

    // Act
    CharacteristicFunctionModel actualCharacteristicFunctionModel =
        calibratableVarianceGammaModel.getCharacteristicFunctionModel();
    actualCharacteristicFunctionModel.apply(10.0d);

    // Assert
    verify(discountCurveForForwardRate).getDiscountFactor(isNull(), eq(10.0d));
    verify(discountCurveForDiscountRate).getDiscountFactor(isNull(), eq(10.0d));
    assertTrue(actualCharacteristicFunctionModel instanceof VarianceGammaModel);
    ModelDescriptor modelDescriptor = calibratableVarianceGammaModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof VarianceGammaModelDescriptor);
    assertNull(((VarianceGammaModel) actualCharacteristicFunctionModel).getReferenceDate());
    assertEquals(
        10.0d, ((VarianceGammaModel) actualCharacteristicFunctionModel).getInitialValue(), 0.0);
    assertEquals(10.0d, ((VarianceGammaModel) actualCharacteristicFunctionModel).getNu(), 0.0);
    assertEquals(10.0d, ((VarianceGammaModel) actualCharacteristicFunctionModel).getSigma(), 0.0);
    assertEquals(10.0d, ((VarianceGammaModel) actualCharacteristicFunctionModel).getTheta(), 0.0);
    assertEquals(
        Double.NaN,
        ((VarianceGammaModel) actualCharacteristicFunctionModel).getDiscountRate(),
        0.0);
    assertEquals(
        Double.NaN,
        ((VarianceGammaModel) actualCharacteristicFunctionModel).getRiskFreeRate(),
        0.0);
    assertSame(descriptor, modelDescriptor);
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d},
        calibratableVarianceGammaModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d},
        calibratableVarianceGammaModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableVarianceGammaModel#getCharacteristicFunctionModel()}.
   *
   * <p>Method under test: {@link CalibratableVarianceGammaModel#getCharacteristicFunctionModel()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CharacteristicFunctionModel CalibratableVarianceGammaModel.getCharacteristicFunctionModel()"
  })
  public void testGetCharacteristicFunctionModel3() {
    // Arrange
    DiscountCurveInterpolation discountCurveForDiscountRate =
        mock(DiscountCurveInterpolation.class);
    when(discountCurveForDiscountRate.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);
    VarianceGammaModelDescriptor descriptor =
        new VarianceGammaModelDescriptor(
            LocalDate.of(1970, 1, 1),
            Double.NaN,
            null,
            discountCurveForDiscountRate,
            Double.NaN,
            Double.NaN,
            Double.NaN);
    ScalarParameterInformationImplementation sigmaInfo =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation thetaInfo =
        new ScalarParameterInformationImplementation(true);

    CalibratableVarianceGammaModel calibratableVarianceGammaModel =
        new CalibratableVarianceGammaModel(
            descriptor, sigmaInfo, thetaInfo, new ScalarParameterInformationImplementation(true));

    // Act
    CharacteristicFunctionModel actualCharacteristicFunctionModel =
        calibratableVarianceGammaModel.getCharacteristicFunctionModel();
    actualCharacteristicFunctionModel.apply(10.0d);

    // Assert
    verify(discountCurveForDiscountRate).getDiscountFactor(isNull(), eq(10.0d));
    assertTrue(actualCharacteristicFunctionModel instanceof VarianceGammaModel);
    ModelDescriptor modelDescriptor = calibratableVarianceGammaModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof VarianceGammaModelDescriptor);
    assertNull(((VarianceGammaModel) actualCharacteristicFunctionModel).getReferenceDate());
    assertNull(
        ((VarianceGammaModel) actualCharacteristicFunctionModel).getDiscountCurveForForwardRate());
    assertEquals(
        Double.NaN,
        ((VarianceGammaModel) actualCharacteristicFunctionModel).getDiscountRate(),
        0.0);
    assertEquals(
        Double.NaN,
        ((VarianceGammaModel) actualCharacteristicFunctionModel).getInitialValue(),
        0.0);
    assertEquals(Double.NaN, ((VarianceGammaModel) actualCharacteristicFunctionModel).getNu(), 0.0);
    assertEquals(
        Double.NaN,
        ((VarianceGammaModel) actualCharacteristicFunctionModel).getRiskFreeRate(),
        0.0);
    assertEquals(
        Double.NaN, ((VarianceGammaModel) actualCharacteristicFunctionModel).getSigma(), 0.0);
    assertEquals(
        Double.NaN, ((VarianceGammaModel) actualCharacteristicFunctionModel).getTheta(), 0.0);
    assertSame(descriptor, modelDescriptor);
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d},
        calibratableVarianceGammaModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d},
        calibratableVarianceGammaModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableVarianceGammaModel#getCharacteristicFunctionModel()}.
   *
   * <ul>
   *   <li>Then return DiscountCurveForDiscountRate is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CalibratableVarianceGammaModel#getCharacteristicFunctionModel()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CharacteristicFunctionModel CalibratableVarianceGammaModel.getCharacteristicFunctionModel()"
  })
  public void testGetCharacteristicFunctionModel_thenReturnDiscountCurveForDiscountRateIsNull() {
    // Arrange
    DiscountCurveInterpolation discountCurveForForwardRate = mock(DiscountCurveInterpolation.class);
    when(discountCurveForForwardRate.getDiscountFactor(Mockito.<AnalyticModel>any(), anyDouble()))
        .thenReturn(10.0d);
    VarianceGammaModelDescriptor descriptor =
        new VarianceGammaModelDescriptor(
            LocalDate.of(1970, 1, 1),
            10.0d,
            discountCurveForForwardRate,
            null,
            10.0d,
            10.0d,
            10.0d);
    CalibratableVarianceGammaModel calibratableVarianceGammaModel =
        new CalibratableVarianceGammaModel(descriptor);

    // Act
    CharacteristicFunctionModel actualCharacteristicFunctionModel =
        calibratableVarianceGammaModel.getCharacteristicFunctionModel();
    actualCharacteristicFunctionModel.apply(10.0d);

    // Assert
    verify(discountCurveForForwardRate).getDiscountFactor(isNull(), eq(10.0d));
    assertTrue(actualCharacteristicFunctionModel instanceof VarianceGammaModel);
    ModelDescriptor modelDescriptor = calibratableVarianceGammaModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof VarianceGammaModelDescriptor);
    assertNull(((VarianceGammaModel) actualCharacteristicFunctionModel).getReferenceDate());
    assertNull(
        ((VarianceGammaModel) actualCharacteristicFunctionModel).getDiscountCurveForDiscountRate());
    assertEquals(
        10.0d, ((VarianceGammaModel) actualCharacteristicFunctionModel).getInitialValue(), 0.0);
    assertEquals(10.0d, ((VarianceGammaModel) actualCharacteristicFunctionModel).getNu(), 0.0);
    assertEquals(10.0d, ((VarianceGammaModel) actualCharacteristicFunctionModel).getSigma(), 0.0);
    assertEquals(10.0d, ((VarianceGammaModel) actualCharacteristicFunctionModel).getTheta(), 0.0);
    assertEquals(
        Double.NaN,
        ((VarianceGammaModel) actualCharacteristicFunctionModel).getDiscountRate(),
        0.0);
    assertEquals(
        Double.NaN,
        ((VarianceGammaModel) actualCharacteristicFunctionModel).getRiskFreeRate(),
        0.0);
    assertSame(descriptor, modelDescriptor);
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d},
        calibratableVarianceGammaModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d},
        calibratableVarianceGammaModel.getParameterUpperBounds(),
        0.0);
  }
}
