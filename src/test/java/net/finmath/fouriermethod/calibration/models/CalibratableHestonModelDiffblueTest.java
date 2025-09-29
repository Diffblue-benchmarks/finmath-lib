package net.finmath.fouriermethod.calibration.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.fouriermethod.calibration.BoundConstraint;
import net.finmath.fouriermethod.calibration.NegativityConstraint;
import net.finmath.fouriermethod.calibration.ScalarParameterInformation;
import net.finmath.fouriermethod.calibration.ScalarParameterInformationImplementation;
import net.finmath.fouriermethod.models.HestonModel;
import net.finmath.marketdata.model.curves.DiscountCurve;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.modelling.ModelDescriptor;
import net.finmath.modelling.descriptor.HestonModelDescriptor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CalibratableHestonModelDiffblueTest {
  /**
   * Test {@link CalibratableHestonModel#CalibratableHestonModel(HestonModelDescriptor)}.
   *
   * <p>Method under test: {@link
   * CalibratableHestonModel#CalibratableHestonModel(HestonModelDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratableHestonModel.<init>(HestonModelDescriptor)"})
  public void testNewCalibratableHestonModel() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);

    // Act
    CalibratableHestonModel actualCalibratableHestonModel = new CalibratableHestonModel(descriptor);

    // Assert
    ModelDescriptor modelDescriptor = actualCalibratableHestonModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertSame(descriptor, modelDescriptor);
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCalibratableHestonModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCalibratableHestonModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#CalibratableHestonModel(HestonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation, ScalarParameterInformation, boolean)}.
   *
   * <p>Method under test: {@link
   * CalibratableHestonModel#CalibratableHestonModel(HestonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation, ScalarParameterInformation, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratableHestonModel.<init>(HestonModelDescriptor, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, boolean)"
  })
  public void testNewCalibratableHestonModel2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation thetaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation kappaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation xiConstraint =
        new ScalarParameterInformationImplementation(true);

    // Act
    CalibratableHestonModel actualCalibratableHestonModel =
        new CalibratableHestonModel(
            descriptor,
            volatilityConstraint,
            thetaConstraint,
            kappaConstraint,
            xiConstraint,
            new ScalarParameterInformationImplementation(true),
            true);

    // Assert
    DiscountCurve discountCurveForDiscountRate =
        actualCalibratableHestonModel
            .getCharacteristicFunctionModel()
            .getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCalibratableHestonModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((HestonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCalibratableHestonModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCalibratableHestonModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#CalibratableHestonModel(HestonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation, ScalarParameterInformation, boolean)}.
   *
   * <p>Method under test: {@link
   * CalibratableHestonModel#CalibratableHestonModel(HestonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation, ScalarParameterInformation, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratableHestonModel.<init>(HestonModelDescriptor, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, boolean)"
  })
  public void testNewCalibratableHestonModel3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityConstraint =
        new ScalarParameterInformationImplementation(new NegativityConstraint());
    ScalarParameterInformationImplementation thetaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation kappaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation xiConstraint =
        new ScalarParameterInformationImplementation(true);

    // Act
    CalibratableHestonModel actualCalibratableHestonModel =
        new CalibratableHestonModel(
            descriptor,
            volatilityConstraint,
            thetaConstraint,
            kappaConstraint,
            xiConstraint,
            new ScalarParameterInformationImplementation(true),
            true);

    // Assert
    DiscountCurve discountCurveForDiscountRate =
        actualCalibratableHestonModel
            .getCharacteristicFunctionModel()
            .getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCalibratableHestonModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((HestonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCalibratableHestonModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {0.0d, 1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCalibratableHestonModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#CalibratableHestonModel(HestonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation, ScalarParameterInformation, boolean)}.
   *
   * <p>Method under test: {@link
   * CalibratableHestonModel#CalibratableHestonModel(HestonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation, ScalarParameterInformation, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratableHestonModel.<init>(HestonModelDescriptor, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, boolean)"
  })
  public void testNewCalibratableHestonModel4() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation thetaConstraint =
        new ScalarParameterInformationImplementation(new NegativityConstraint());
    ScalarParameterInformationImplementation kappaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation xiConstraint =
        new ScalarParameterInformationImplementation(true);

    // Act
    CalibratableHestonModel actualCalibratableHestonModel =
        new CalibratableHestonModel(
            descriptor,
            volatilityConstraint,
            thetaConstraint,
            kappaConstraint,
            xiConstraint,
            new ScalarParameterInformationImplementation(true),
            true);

    // Assert
    DiscountCurve discountCurveForDiscountRate =
        actualCalibratableHestonModel
            .getCharacteristicFunctionModel()
            .getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCalibratableHestonModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((HestonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCalibratableHestonModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 0.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCalibratableHestonModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#CalibratableHestonModel(HestonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation, ScalarParameterInformation, boolean)}.
   *
   * <p>Method under test: {@link
   * CalibratableHestonModel#CalibratableHestonModel(HestonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation, ScalarParameterInformation, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratableHestonModel.<init>(HestonModelDescriptor, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, boolean)"
  })
  public void testNewCalibratableHestonModel5() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation thetaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation kappaConstraint =
        new ScalarParameterInformationImplementation(new NegativityConstraint());
    ScalarParameterInformationImplementation xiConstraint =
        new ScalarParameterInformationImplementation(true);

    // Act
    CalibratableHestonModel actualCalibratableHestonModel =
        new CalibratableHestonModel(
            descriptor,
            volatilityConstraint,
            thetaConstraint,
            kappaConstraint,
            xiConstraint,
            new ScalarParameterInformationImplementation(true),
            true);

    // Assert
    DiscountCurve discountCurveForDiscountRate =
        actualCalibratableHestonModel
            .getCharacteristicFunctionModel()
            .getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCalibratableHestonModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((HestonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCalibratableHestonModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 0.0d, 1000000.0d, 1000000.0d},
        actualCalibratableHestonModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#CalibratableHestonModel(HestonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation, ScalarParameterInformation, boolean)}.
   *
   * <p>Method under test: {@link
   * CalibratableHestonModel#CalibratableHestonModel(HestonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation, ScalarParameterInformation, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratableHestonModel.<init>(HestonModelDescriptor, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, boolean)"
  })
  public void testNewCalibratableHestonModel6() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation thetaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation kappaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation xiConstraint =
        new ScalarParameterInformationImplementation(new NegativityConstraint());

    // Act
    CalibratableHestonModel actualCalibratableHestonModel =
        new CalibratableHestonModel(
            descriptor,
            volatilityConstraint,
            thetaConstraint,
            kappaConstraint,
            xiConstraint,
            new ScalarParameterInformationImplementation(true),
            true);

    // Assert
    DiscountCurve discountCurveForDiscountRate =
        actualCalibratableHestonModel
            .getCharacteristicFunctionModel()
            .getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCalibratableHestonModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((HestonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCalibratableHestonModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 0.0d, 1000000.0d},
        actualCalibratableHestonModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#CalibratableHestonModel(HestonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation, ScalarParameterInformation, boolean)}.
   *
   * <p>Method under test: {@link
   * CalibratableHestonModel#CalibratableHestonModel(HestonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation, ScalarParameterInformation, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratableHestonModel.<init>(HestonModelDescriptor, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, boolean)"
  })
  public void testNewCalibratableHestonModel7() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation thetaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation kappaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation xiConstraint =
        new ScalarParameterInformationImplementation(true);

    // Act
    CalibratableHestonModel actualCalibratableHestonModel =
        new CalibratableHestonModel(
            descriptor,
            volatilityConstraint,
            thetaConstraint,
            kappaConstraint,
            xiConstraint,
            new ScalarParameterInformationImplementation(new NegativityConstraint()),
            true);

    // Assert
    DiscountCurve discountCurveForDiscountRate =
        actualCalibratableHestonModel
            .getCharacteristicFunctionModel()
            .getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCalibratableHestonModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((HestonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCalibratableHestonModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d, 0.0d},
        actualCalibratableHestonModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#CalibratableHestonModel(HestonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation, ScalarParameterInformation, boolean)}.
   *
   * <p>Method under test: {@link
   * CalibratableHestonModel#CalibratableHestonModel(HestonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation, ScalarParameterInformation, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratableHestonModel.<init>(HestonModelDescriptor, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, boolean)"
  })
  public void testNewCalibratableHestonModel8() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityConstraint =
        new ScalarParameterInformationImplementation(new BoundConstraint(1000000.0d, 1000000.0d));
    ScalarParameterInformationImplementation thetaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation kappaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation xiConstraint =
        new ScalarParameterInformationImplementation(true);

    // Act
    CalibratableHestonModel actualCalibratableHestonModel =
        new CalibratableHestonModel(
            descriptor,
            volatilityConstraint,
            thetaConstraint,
            kappaConstraint,
            xiConstraint,
            new ScalarParameterInformationImplementation(true),
            true);

    // Assert
    DiscountCurve discountCurveForDiscountRate =
        actualCalibratableHestonModel
            .getCharacteristicFunctionModel()
            .getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCalibratableHestonModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((HestonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCalibratableHestonModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCalibratableHestonModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#CalibratableHestonModel(HestonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation, ScalarParameterInformation, boolean)}.
   *
   * <p>Method under test: {@link
   * CalibratableHestonModel#CalibratableHestonModel(HestonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation, ScalarParameterInformation, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratableHestonModel.<init>(HestonModelDescriptor, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, boolean)"
  })
  public void testNewCalibratableHestonModel9() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation thetaConstraint =
        new ScalarParameterInformationImplementation(new BoundConstraint(1000000.0d, 1000000.0d));
    ScalarParameterInformationImplementation kappaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation xiConstraint =
        new ScalarParameterInformationImplementation(true);

    // Act
    CalibratableHestonModel actualCalibratableHestonModel =
        new CalibratableHestonModel(
            descriptor,
            volatilityConstraint,
            thetaConstraint,
            kappaConstraint,
            xiConstraint,
            new ScalarParameterInformationImplementation(true),
            true);

    // Assert
    DiscountCurve discountCurveForDiscountRate =
        actualCalibratableHestonModel
            .getCharacteristicFunctionModel()
            .getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCalibratableHestonModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((HestonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, 1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCalibratableHestonModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCalibratableHestonModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#CalibratableHestonModel(HestonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation, ScalarParameterInformation, boolean)}.
   *
   * <p>Method under test: {@link
   * CalibratableHestonModel#CalibratableHestonModel(HestonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation, ScalarParameterInformation, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratableHestonModel.<init>(HestonModelDescriptor, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, boolean)"
  })
  public void testNewCalibratableHestonModel10() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation thetaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation kappaConstraint =
        new ScalarParameterInformationImplementation(new BoundConstraint(1000000.0d, 1000000.0d));
    ScalarParameterInformationImplementation xiConstraint =
        new ScalarParameterInformationImplementation(true);

    // Act
    CalibratableHestonModel actualCalibratableHestonModel =
        new CalibratableHestonModel(
            descriptor,
            volatilityConstraint,
            thetaConstraint,
            kappaConstraint,
            xiConstraint,
            new ScalarParameterInformationImplementation(true),
            true);

    // Assert
    DiscountCurve discountCurveForDiscountRate =
        actualCalibratableHestonModel
            .getCharacteristicFunctionModel()
            .getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCalibratableHestonModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((HestonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, 1000000.0d, -1000000.0d, -1000000.0d},
        actualCalibratableHestonModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCalibratableHestonModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#CalibratableHestonModel(HestonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation, ScalarParameterInformation, boolean)}.
   *
   * <p>Method under test: {@link
   * CalibratableHestonModel#CalibratableHestonModel(HestonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation, ScalarParameterInformation, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratableHestonModel.<init>(HestonModelDescriptor, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, boolean)"
  })
  public void testNewCalibratableHestonModel11() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation thetaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation kappaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation xiConstraint =
        new ScalarParameterInformationImplementation(new BoundConstraint(1000000.0d, 1000000.0d));

    // Act
    CalibratableHestonModel actualCalibratableHestonModel =
        new CalibratableHestonModel(
            descriptor,
            volatilityConstraint,
            thetaConstraint,
            kappaConstraint,
            xiConstraint,
            new ScalarParameterInformationImplementation(true),
            true);

    // Assert
    DiscountCurve discountCurveForDiscountRate =
        actualCalibratableHestonModel
            .getCharacteristicFunctionModel()
            .getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCalibratableHestonModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((HestonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, 1000000.0d, -1000000.0d},
        actualCalibratableHestonModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCalibratableHestonModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#CalibratableHestonModel(HestonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation, ScalarParameterInformation, boolean)}.
   *
   * <p>Method under test: {@link
   * CalibratableHestonModel#CalibratableHestonModel(HestonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation, ScalarParameterInformation, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratableHestonModel.<init>(HestonModelDescriptor, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, boolean)"
  })
  public void testNewCalibratableHestonModel12() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation thetaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation kappaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation xiConstraint =
        new ScalarParameterInformationImplementation(true);

    // Act
    CalibratableHestonModel actualCalibratableHestonModel =
        new CalibratableHestonModel(
            descriptor,
            volatilityConstraint,
            thetaConstraint,
            kappaConstraint,
            xiConstraint,
            new ScalarParameterInformationImplementation(
                new BoundConstraint(1000000.0d, 1000000.0d)),
            true);

    // Assert
    DiscountCurve discountCurveForDiscountRate =
        actualCalibratableHestonModel
            .getCharacteristicFunctionModel()
            .getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCalibratableHestonModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((HestonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d, 1000000.0d},
        actualCalibratableHestonModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCalibratableHestonModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}.
   *
   * <p>Method under test: {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CalibratableHestonModel CalibratableHestonModel.getCloneForModifiedParameters(double[])"
  })
  public void testGetCloneForModifiedParameters() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    CalibratableHestonModel actualCloneForModifiedParameters =
        new CalibratableHestonModel(
                new HestonModelDescriptor(
                    referenceDate,
                    10.0d,
                    discountCurveForForwardRate,
                    new DiscountCurveFromForwardCurve("Forward Curve Name"),
                    10.0d,
                    10.0d,
                    10.0d,
                    10.0d,
                    10.0d))
            .getCloneForModifiedParameters(
                new double[] {
                  10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d
                });

    // Assert
    HestonModel characteristicFunctionModel =
        actualCloneForModifiedParameters.getCharacteristicFunctionModel();
    DiscountCurve discountCurveForDiscountRate =
        characteristicFunctionModel.getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCloneForModifiedParameters.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertEquals(
        -1000000.0d, ((HestonModelDescriptor) modelDescriptor).getTheta().doubleValue(), 0.0);
    assertEquals(-1000000.0d, characteristicFunctionModel.getTheta(), 0.0);
    assertSame(
        discountCurveForDiscountRate,
        ((HestonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCloneForModifiedParameters.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCloneForModifiedParameters.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}.
   *
   * <p>Method under test: {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CalibratableHestonModel CalibratableHestonModel.getCloneForModifiedParameters(double[])"
  })
  public void testGetCloneForModifiedParameters2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation thetaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation kappaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation xiConstraint =
        new ScalarParameterInformationImplementation(true);

    CalibratableHestonModel calibratableHestonModel =
        new CalibratableHestonModel(
            descriptor,
            volatilityConstraint,
            thetaConstraint,
            kappaConstraint,
            xiConstraint,
            new ScalarParameterInformationImplementation(true),
            true);

    // Act
    CalibratableHestonModel actualCloneForModifiedParameters =
        calibratableHestonModel.getCloneForModifiedParameters(
            new double[] {
              10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d
            });

    // Assert
    DiscountCurve discountCurveForDiscountRate =
        actualCloneForModifiedParameters
            .getCharacteristicFunctionModel()
            .getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCloneForModifiedParameters.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((HestonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCloneForModifiedParameters.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCloneForModifiedParameters.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}.
   *
   * <p>Method under test: {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CalibratableHestonModel CalibratableHestonModel.getCloneForModifiedParameters(double[])"
  })
  public void testGetCloneForModifiedParameters3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityConstraint =
        new ScalarParameterInformationImplementation(false);
    ScalarParameterInformationImplementation thetaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation kappaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation xiConstraint =
        new ScalarParameterInformationImplementation(true);

    CalibratableHestonModel calibratableHestonModel =
        new CalibratableHestonModel(
            descriptor,
            volatilityConstraint,
            thetaConstraint,
            kappaConstraint,
            xiConstraint,
            new ScalarParameterInformationImplementation(true),
            true);

    // Act
    CalibratableHestonModel actualCloneForModifiedParameters =
        calibratableHestonModel.getCloneForModifiedParameters(
            new double[] {
              10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d
            });

    // Assert
    DiscountCurve discountCurveForDiscountRate =
        actualCloneForModifiedParameters
            .getCharacteristicFunctionModel()
            .getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCloneForModifiedParameters.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((HestonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCloneForModifiedParameters.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCloneForModifiedParameters.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}.
   *
   * <p>Method under test: {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CalibratableHestonModel CalibratableHestonModel.getCloneForModifiedParameters(double[])"
  })
  public void testGetCloneForModifiedParameters4() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityConstraint =
        new ScalarParameterInformationImplementation(new NegativityConstraint());
    ScalarParameterInformationImplementation thetaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation kappaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation xiConstraint =
        new ScalarParameterInformationImplementation(true);

    CalibratableHestonModel calibratableHestonModel =
        new CalibratableHestonModel(
            descriptor,
            volatilityConstraint,
            thetaConstraint,
            kappaConstraint,
            xiConstraint,
            new ScalarParameterInformationImplementation(true),
            true);

    // Act
    CalibratableHestonModel actualCloneForModifiedParameters =
        calibratableHestonModel.getCloneForModifiedParameters(
            new double[] {
              10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d
            });

    // Assert
    HestonModel characteristicFunctionModel =
        actualCloneForModifiedParameters.getCharacteristicFunctionModel();
    DiscountCurve discountCurveForDiscountRate =
        characteristicFunctionModel.getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCloneForModifiedParameters.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertEquals(
        -10.0d, ((HestonModelDescriptor) modelDescriptor).getVolatility().doubleValue(), 0.0);
    assertEquals(-10.0d, characteristicFunctionModel.getVolatility(), 0.0);
    assertSame(
        discountCurveForDiscountRate,
        ((HestonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCloneForModifiedParameters.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {0.0d, 1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCloneForModifiedParameters.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}.
   *
   * <p>Method under test: {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CalibratableHestonModel CalibratableHestonModel.getCloneForModifiedParameters(double[])"
  })
  public void testGetCloneForModifiedParameters5() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation thetaConstraint =
        new ScalarParameterInformationImplementation(false);
    ScalarParameterInformationImplementation kappaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation xiConstraint =
        new ScalarParameterInformationImplementation(true);

    CalibratableHestonModel calibratableHestonModel =
        new CalibratableHestonModel(
            descriptor,
            volatilityConstraint,
            thetaConstraint,
            kappaConstraint,
            xiConstraint,
            new ScalarParameterInformationImplementation(true),
            true);

    // Act
    CalibratableHestonModel actualCloneForModifiedParameters =
        calibratableHestonModel.getCloneForModifiedParameters(
            new double[] {
              10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d
            });

    // Assert
    DiscountCurve discountCurveForDiscountRate =
        actualCloneForModifiedParameters
            .getCharacteristicFunctionModel()
            .getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCloneForModifiedParameters.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((HestonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCloneForModifiedParameters.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCloneForModifiedParameters.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}.
   *
   * <p>Method under test: {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CalibratableHestonModel CalibratableHestonModel.getCloneForModifiedParameters(double[])"
  })
  public void testGetCloneForModifiedParameters6() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation thetaConstraint =
        new ScalarParameterInformationImplementation(new NegativityConstraint());
    ScalarParameterInformationImplementation kappaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation xiConstraint =
        new ScalarParameterInformationImplementation(true);

    CalibratableHestonModel calibratableHestonModel =
        new CalibratableHestonModel(
            descriptor,
            volatilityConstraint,
            thetaConstraint,
            kappaConstraint,
            xiConstraint,
            new ScalarParameterInformationImplementation(true),
            true);

    // Act
    CalibratableHestonModel actualCloneForModifiedParameters =
        calibratableHestonModel.getCloneForModifiedParameters(
            new double[] {
              10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d
            });

    // Assert
    DiscountCurve discountCurveForDiscountRate =
        actualCloneForModifiedParameters
            .getCharacteristicFunctionModel()
            .getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCloneForModifiedParameters.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((HestonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCloneForModifiedParameters.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 0.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCloneForModifiedParameters.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}.
   *
   * <p>Method under test: {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CalibratableHestonModel CalibratableHestonModel.getCloneForModifiedParameters(double[])"
  })
  public void testGetCloneForModifiedParameters7() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation thetaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation kappaConstraint =
        new ScalarParameterInformationImplementation(false);
    ScalarParameterInformationImplementation xiConstraint =
        new ScalarParameterInformationImplementation(true);

    CalibratableHestonModel calibratableHestonModel =
        new CalibratableHestonModel(
            descriptor,
            volatilityConstraint,
            thetaConstraint,
            kappaConstraint,
            xiConstraint,
            new ScalarParameterInformationImplementation(true),
            true);

    // Act
    CalibratableHestonModel actualCloneForModifiedParameters =
        calibratableHestonModel.getCloneForModifiedParameters(
            new double[] {
              10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d
            });

    // Assert
    DiscountCurve discountCurveForDiscountRate =
        actualCloneForModifiedParameters
            .getCharacteristicFunctionModel()
            .getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCloneForModifiedParameters.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((HestonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCloneForModifiedParameters.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCloneForModifiedParameters.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}.
   *
   * <p>Method under test: {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CalibratableHestonModel CalibratableHestonModel.getCloneForModifiedParameters(double[])"
  })
  public void testGetCloneForModifiedParameters8() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation thetaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation kappaConstraint =
        new ScalarParameterInformationImplementation(new NegativityConstraint());
    ScalarParameterInformationImplementation xiConstraint =
        new ScalarParameterInformationImplementation(true);

    CalibratableHestonModel calibratableHestonModel =
        new CalibratableHestonModel(
            descriptor,
            volatilityConstraint,
            thetaConstraint,
            kappaConstraint,
            xiConstraint,
            new ScalarParameterInformationImplementation(true),
            true);

    // Act
    CalibratableHestonModel actualCloneForModifiedParameters =
        calibratableHestonModel.getCloneForModifiedParameters(
            new double[] {
              10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d
            });

    // Assert
    HestonModel characteristicFunctionModel =
        actualCloneForModifiedParameters.getCharacteristicFunctionModel();
    DiscountCurve discountCurveForDiscountRate =
        characteristicFunctionModel.getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCloneForModifiedParameters.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertEquals(-10.0d, ((HestonModelDescriptor) modelDescriptor).getKappa().doubleValue(), 0.0);
    assertEquals(-10.0d, characteristicFunctionModel.getKappa(), 0.0);
    assertEquals(-5.0E10d, ((HestonModelDescriptor) modelDescriptor).getTheta().doubleValue(), 0.0);
    assertEquals(-5.0E10d, characteristicFunctionModel.getTheta(), 0.0);
    assertSame(
        discountCurveForDiscountRate,
        ((HestonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCloneForModifiedParameters.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 0.0d, 1000000.0d, 1000000.0d},
        actualCloneForModifiedParameters.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}.
   *
   * <p>Method under test: {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CalibratableHestonModel CalibratableHestonModel.getCloneForModifiedParameters(double[])"
  })
  public void testGetCloneForModifiedParameters9() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation thetaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation kappaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation xiConstraint =
        new ScalarParameterInformationImplementation(new NegativityConstraint());

    CalibratableHestonModel calibratableHestonModel =
        new CalibratableHestonModel(
            descriptor,
            volatilityConstraint,
            thetaConstraint,
            kappaConstraint,
            xiConstraint,
            new ScalarParameterInformationImplementation(true),
            true);

    // Act
    CalibratableHestonModel actualCloneForModifiedParameters =
        calibratableHestonModel.getCloneForModifiedParameters(
            new double[] {
              10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d
            });

    // Assert
    DiscountCurve discountCurveForDiscountRate =
        actualCloneForModifiedParameters
            .getCharacteristicFunctionModel()
            .getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCloneForModifiedParameters.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((HestonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCloneForModifiedParameters.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 0.0d, 1000000.0d},
        actualCloneForModifiedParameters.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}.
   *
   * <p>Method under test: {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CalibratableHestonModel CalibratableHestonModel.getCloneForModifiedParameters(double[])"
  })
  public void testGetCloneForModifiedParameters10() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation thetaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation kappaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation xiConstraint =
        new ScalarParameterInformationImplementation(true);

    CalibratableHestonModel calibratableHestonModel =
        new CalibratableHestonModel(
            descriptor,
            volatilityConstraint,
            thetaConstraint,
            kappaConstraint,
            xiConstraint,
            new ScalarParameterInformationImplementation(false),
            true);

    // Act
    CalibratableHestonModel actualCloneForModifiedParameters =
        calibratableHestonModel.getCloneForModifiedParameters(
            new double[] {
              10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d
            });

    // Assert
    DiscountCurve discountCurveForDiscountRate =
        actualCloneForModifiedParameters
            .getCharacteristicFunctionModel()
            .getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCloneForModifiedParameters.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((HestonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCloneForModifiedParameters.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCloneForModifiedParameters.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}.
   *
   * <p>Method under test: {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CalibratableHestonModel CalibratableHestonModel.getCloneForModifiedParameters(double[])"
  })
  public void testGetCloneForModifiedParameters11() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation thetaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation kappaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation xiConstraint =
        new ScalarParameterInformationImplementation(true);

    CalibratableHestonModel calibratableHestonModel =
        new CalibratableHestonModel(
            descriptor,
            volatilityConstraint,
            thetaConstraint,
            kappaConstraint,
            xiConstraint,
            new ScalarParameterInformationImplementation(new NegativityConstraint()),
            true);

    // Act
    CalibratableHestonModel actualCloneForModifiedParameters =
        calibratableHestonModel.getCloneForModifiedParameters(
            new double[] {
              10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d
            });

    // Assert
    HestonModel characteristicFunctionModel =
        actualCloneForModifiedParameters.getCharacteristicFunctionModel();
    DiscountCurve discountCurveForDiscountRate =
        characteristicFunctionModel.getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCloneForModifiedParameters.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertEquals(-10.0d, ((HestonModelDescriptor) modelDescriptor).getRho().doubleValue(), 0.0);
    assertEquals(-10.0d, characteristicFunctionModel.getRho(), 0.0);
    assertSame(
        discountCurveForDiscountRate,
        ((HestonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCloneForModifiedParameters.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d, 0.0d},
        actualCloneForModifiedParameters.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}.
   *
   * <p>Method under test: {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CalibratableHestonModel CalibratableHestonModel.getCloneForModifiedParameters(double[])"
  })
  public void testGetCloneForModifiedParameters12() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityConstraint =
        new ScalarParameterInformationImplementation(new BoundConstraint(2.0d, 2.0d));
    ScalarParameterInformationImplementation thetaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation kappaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation xiConstraint =
        new ScalarParameterInformationImplementation(true);

    CalibratableHestonModel calibratableHestonModel =
        new CalibratableHestonModel(
            descriptor,
            volatilityConstraint,
            thetaConstraint,
            kappaConstraint,
            xiConstraint,
            new ScalarParameterInformationImplementation(true),
            true);

    // Act
    CalibratableHestonModel actualCloneForModifiedParameters =
        calibratableHestonModel.getCloneForModifiedParameters(
            new double[] {
              10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d
            });

    // Assert
    HestonModel characteristicFunctionModel =
        actualCloneForModifiedParameters.getCharacteristicFunctionModel();
    DiscountCurve discountCurveForDiscountRate =
        characteristicFunctionModel.getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCloneForModifiedParameters.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertEquals(
        2.0d, ((HestonModelDescriptor) modelDescriptor).getVolatility().doubleValue(), 0.0);
    assertEquals(2.0d, characteristicFunctionModel.getVolatility(), 0.0);
    assertSame(
        discountCurveForDiscountRate,
        ((HestonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {2.0d, -1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCloneForModifiedParameters.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {2.0d, 1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCloneForModifiedParameters.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}.
   *
   * <p>Method under test: {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CalibratableHestonModel CalibratableHestonModel.getCloneForModifiedParameters(double[])"
  })
  public void testGetCloneForModifiedParameters13() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            1.0E12d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation thetaConstraint =
        new ScalarParameterInformationImplementation(false);
    ScalarParameterInformationImplementation kappaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation xiConstraint =
        new ScalarParameterInformationImplementation(true);

    CalibratableHestonModel calibratableHestonModel =
        new CalibratableHestonModel(
            descriptor,
            volatilityConstraint,
            thetaConstraint,
            kappaConstraint,
            xiConstraint,
            new ScalarParameterInformationImplementation(true),
            true);

    // Act
    CalibratableHestonModel actualCloneForModifiedParameters =
        calibratableHestonModel.getCloneForModifiedParameters(
            new double[] {
              10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d
            });

    // Assert
    HestonModel characteristicFunctionModel =
        actualCloneForModifiedParameters.getCharacteristicFunctionModel();
    DiscountCurve discountCurveForDiscountRate =
        characteristicFunctionModel.getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCloneForModifiedParameters.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertEquals(1.0E12d, ((HestonModelDescriptor) modelDescriptor).getTheta().doubleValue(), 0.0);
    assertEquals(1.0E12d, characteristicFunctionModel.getTheta(), 0.0);
    assertSame(
        discountCurveForDiscountRate,
        ((HestonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCloneForModifiedParameters.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCloneForModifiedParameters.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}.
   *
   * <p>Method under test: {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CalibratableHestonModel CalibratableHestonModel.getCloneForModifiedParameters(double[])"
  })
  public void testGetCloneForModifiedParameters14() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation thetaConstraint =
        new ScalarParameterInformationImplementation(new BoundConstraint(2.0d, 2.0d));
    ScalarParameterInformationImplementation kappaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation xiConstraint =
        new ScalarParameterInformationImplementation(true);

    CalibratableHestonModel calibratableHestonModel =
        new CalibratableHestonModel(
            descriptor,
            volatilityConstraint,
            thetaConstraint,
            kappaConstraint,
            xiConstraint,
            new ScalarParameterInformationImplementation(true),
            true);

    // Act
    CalibratableHestonModel actualCloneForModifiedParameters =
        calibratableHestonModel.getCloneForModifiedParameters(
            new double[] {
              10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d
            });

    // Assert
    DiscountCurve discountCurveForDiscountRate =
        actualCloneForModifiedParameters
            .getCharacteristicFunctionModel()
            .getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCloneForModifiedParameters.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((HestonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, 2.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCloneForModifiedParameters.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 2.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCloneForModifiedParameters.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}.
   *
   * <p>Method under test: {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CalibratableHestonModel CalibratableHestonModel.getCloneForModifiedParameters(double[])"
  })
  public void testGetCloneForModifiedParameters15() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation thetaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation kappaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation xiConstraint =
        new ScalarParameterInformationImplementation(new BoundConstraint(2.0d, 2.0d));

    CalibratableHestonModel calibratableHestonModel =
        new CalibratableHestonModel(
            descriptor,
            volatilityConstraint,
            thetaConstraint,
            kappaConstraint,
            xiConstraint,
            new ScalarParameterInformationImplementation(true),
            true);

    // Act
    CalibratableHestonModel actualCloneForModifiedParameters =
        calibratableHestonModel.getCloneForModifiedParameters(
            new double[] {
              10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d
            });

    // Assert
    HestonModel characteristicFunctionModel =
        actualCloneForModifiedParameters.getCharacteristicFunctionModel();
    DiscountCurve discountCurveForDiscountRate =
        characteristicFunctionModel.getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCloneForModifiedParameters.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertEquals(
        0.200000001d, ((HestonModelDescriptor) modelDescriptor).getTheta().doubleValue(), 0.0);
    assertEquals(0.200000001d, characteristicFunctionModel.getTheta(), 0.0);
    assertEquals(2.0d, ((HestonModelDescriptor) modelDescriptor).getXi().doubleValue(), 0.0);
    assertEquals(2.0d, characteristicFunctionModel.getXi(), 0.0);
    assertSame(
        discountCurveForDiscountRate,
        ((HestonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, 2.0d, -1000000.0d},
        actualCloneForModifiedParameters.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 2.0d, 1000000.0d},
        actualCloneForModifiedParameters.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}.
   *
   * <ul>
   *   <li>Then return ModelDescriptor Kappa doubleValue is two.
   * </ul>
   *
   * <p>Method under test: {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CalibratableHestonModel CalibratableHestonModel.getCloneForModifiedParameters(double[])"
  })
  public void testGetCloneForModifiedParameters_thenReturnModelDescriptorKappaDoubleValueIsTwo() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation thetaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation kappaConstraint =
        new ScalarParameterInformationImplementation(new BoundConstraint(2.0d, 2.0d));
    ScalarParameterInformationImplementation xiConstraint =
        new ScalarParameterInformationImplementation(true);

    CalibratableHestonModel calibratableHestonModel =
        new CalibratableHestonModel(
            descriptor,
            volatilityConstraint,
            thetaConstraint,
            kappaConstraint,
            xiConstraint,
            new ScalarParameterInformationImplementation(true),
            true);

    // Act
    CalibratableHestonModel actualCloneForModifiedParameters =
        calibratableHestonModel.getCloneForModifiedParameters(
            new double[] {
              10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d
            });

    // Assert
    HestonModel characteristicFunctionModel =
        actualCloneForModifiedParameters.getCharacteristicFunctionModel();
    DiscountCurve discountCurveForDiscountRate =
        characteristicFunctionModel.getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCloneForModifiedParameters.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertEquals(2.0d, ((HestonModelDescriptor) modelDescriptor).getKappa().doubleValue(), 0.0);
    assertEquals(2.0d, characteristicFunctionModel.getKappa(), 0.0);
    assertEquals(2.5E11d, ((HestonModelDescriptor) modelDescriptor).getTheta().doubleValue(), 0.0);
    assertEquals(2.5E11d, characteristicFunctionModel.getTheta(), 0.0);
    assertSame(
        discountCurveForDiscountRate,
        ((HestonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, 2.0d, -1000000.0d, -1000000.0d},
        actualCloneForModifiedParameters.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 2.0d, 1000000.0d, 1000000.0d},
        actualCloneForModifiedParameters.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}.
   *
   * <ul>
   *   <li>Then return ModelDescriptor Xi doubleValue is ten.
   * </ul>
   *
   * <p>Method under test: {@link CalibratableHestonModel#getCloneForModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CalibratableHestonModel CalibratableHestonModel.getCloneForModifiedParameters(double[])"
  })
  public void testGetCloneForModifiedParameters_thenReturnModelDescriptorXiDoubleValueIsTen() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation thetaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation kappaConstraint =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation xiConstraint =
        new ScalarParameterInformationImplementation(false);

    CalibratableHestonModel calibratableHestonModel =
        new CalibratableHestonModel(
            descriptor,
            volatilityConstraint,
            thetaConstraint,
            kappaConstraint,
            xiConstraint,
            new ScalarParameterInformationImplementation(true),
            true);

    // Act
    CalibratableHestonModel actualCloneForModifiedParameters =
        calibratableHestonModel.getCloneForModifiedParameters(
            new double[] {
              10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d, 10.0d, -1000000.0d
            });

    // Assert
    HestonModel characteristicFunctionModel =
        actualCloneForModifiedParameters.getCharacteristicFunctionModel();
    DiscountCurve discountCurveForDiscountRate =
        characteristicFunctionModel.getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCloneForModifiedParameters.getModelDescriptor();
    assertTrue(modelDescriptor instanceof HestonModelDescriptor);
    assertEquals(10.0d, ((HestonModelDescriptor) modelDescriptor).getXi().doubleValue(), 0.0);
    assertEquals(10.0d, characteristicFunctionModel.getXi(), 0.0);
    assertEquals(
        5.000000001d, ((HestonModelDescriptor) modelDescriptor).getTheta().doubleValue(), 0.0);
    assertEquals(5.000000001d, characteristicFunctionModel.getTheta(), 0.0);
    assertSame(
        discountCurveForDiscountRate,
        ((HestonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCloneForModifiedParameters.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCloneForModifiedParameters.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CalibratableHestonModel#toString()}
   *   <li>{@link CalibratableHestonModel#getModelDescriptor()}
   *   <li>{@link CalibratableHestonModel#getParameterLowerBounds()}
   *   <li>{@link CalibratableHestonModel#getParameterUpperBounds()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ModelDescriptor CalibratableHestonModel.getModelDescriptor()",
    "double[] CalibratableHestonModel.getParameterLowerBounds()",
    "double[] CalibratableHestonModel.getParameterUpperBounds()",
    "java.lang.String CalibratableHestonModel.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    CalibratableHestonModel calibratableHestonModel = new CalibratableHestonModel(descriptor);

    // Act
    calibratableHestonModel.toString();
    ModelDescriptor actualModelDescriptor = calibratableHestonModel.getModelDescriptor();
    double[] actualParameterLowerBounds = calibratableHestonModel.getParameterLowerBounds();

    // Assert
    assertSame(descriptor, actualModelDescriptor);
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualParameterLowerBounds,
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        calibratableHestonModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableHestonModel#getCharacteristicFunctionModel()}.
   *
   * <p>Method under test: {@link CalibratableHestonModel#getCharacteristicFunctionModel()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"HestonModel CalibratableHestonModel.getCharacteristicFunctionModel()"})
  public void testGetCharacteristicFunctionModel() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    HestonModel actualCharacteristicFunctionModel =
        new CalibratableHestonModel(
                new HestonModelDescriptor(
                    referenceDate,
                    10.0d,
                    discountCurveForForwardRate,
                    discountCurveForDiscountRate,
                    10.0d,
                    10.0d,
                    10.0d,
                    10.0d,
                    10.0d))
            .getCharacteristicFunctionModel();

    // Assert
    DiscountCurve discountCurveForForwardRate2 =
        actualCharacteristicFunctionModel.getDiscountCurveForForwardRate();
    assertTrue(discountCurveForForwardRate2 instanceof DiscountCurveFromForwardCurve);
    assertNull(actualCharacteristicFunctionModel.getReferenceDate());
    assertEquals(10.0d, actualCharacteristicFunctionModel.getInitialValue(), 0.0);
    assertEquals(10.0d, actualCharacteristicFunctionModel.getKappa(), 0.0);
    assertEquals(10.0d, actualCharacteristicFunctionModel.getRho(), 0.0);
    assertEquals(10.0d, actualCharacteristicFunctionModel.getTheta(), 0.0);
    assertEquals(10.0d, actualCharacteristicFunctionModel.getVolatility(), 0.0);
    assertEquals(10.0d, actualCharacteristicFunctionModel.getXi(), 0.0);
    assertEquals(Double.NaN, actualCharacteristicFunctionModel.getDiscountRate(), 0.0);
    assertEquals(Double.NaN, actualCharacteristicFunctionModel.getRiskFreeRate(), 0.0);
    assertSame(
        discountCurveForDiscountRate,
        actualCharacteristicFunctionModel.getDiscountCurveForDiscountRate());
    assertSame(discountCurveForForwardRate, discountCurveForForwardRate2);
  }
}
