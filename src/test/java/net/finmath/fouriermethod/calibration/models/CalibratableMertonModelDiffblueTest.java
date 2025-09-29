package net.finmath.fouriermethod.calibration.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
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
import net.finmath.fouriermethod.models.MertonModel;
import net.finmath.marketdata.model.curves.DiscountCurve;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.modelling.ModelDescriptor;
import net.finmath.modelling.descriptor.MertonModelDescriptor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CalibratableMertonModelDiffblueTest {
  /**
   * Test {@link CalibratableMertonModel#CalibratableMertonModel(MertonModelDescriptor)}.
   *
   * <p>Method under test: {@link
   * CalibratableMertonModel#CalibratableMertonModel(MertonModelDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratableMertonModel.<init>(MertonModelDescriptor)"})
  public void testNewCalibratableMertonModel() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    MertonModelDescriptor descriptor =
        new MertonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d);

    // Act
    CalibratableMertonModel actualCalibratableMertonModel = new CalibratableMertonModel(descriptor);

    // Assert
    ModelDescriptor modelDescriptor = actualCalibratableMertonModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof MertonModelDescriptor);
    assertSame(descriptor, modelDescriptor);
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCalibratableMertonModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCalibratableMertonModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableMertonModel#CalibratableMertonModel(MertonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation)}.
   *
   * <p>Method under test: {@link
   * CalibratableMertonModel#CalibratableMertonModel(MertonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratableMertonModel.<init>(MertonModelDescriptor, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)"
  })
  public void testNewCalibratableMertonModel2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    MertonModelDescriptor descriptor =
        new MertonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityInfo =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation jumpIntensityInfo =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation jumpSizeMeanInfo =
        new ScalarParameterInformationImplementation(true);

    // Act
    CalibratableMertonModel actualCalibratableMertonModel =
        new CalibratableMertonModel(
            descriptor,
            volatilityInfo,
            jumpIntensityInfo,
            jumpSizeMeanInfo,
            new ScalarParameterInformationImplementation(true));

    // Assert
    DiscountCurve discountCurveForDiscountRate =
        actualCalibratableMertonModel
            .getCharacteristicFunctionModel()
            .getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCalibratableMertonModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof MertonModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((MertonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCalibratableMertonModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCalibratableMertonModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableMertonModel#CalibratableMertonModel(MertonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation)}.
   *
   * <p>Method under test: {@link
   * CalibratableMertonModel#CalibratableMertonModel(MertonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratableMertonModel.<init>(MertonModelDescriptor, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)"
  })
  public void testNewCalibratableMertonModel3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    MertonModelDescriptor descriptor =
        new MertonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityInfo =
        new ScalarParameterInformationImplementation(new NegativityConstraint());
    ScalarParameterInformationImplementation jumpIntensityInfo =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation jumpSizeMeanInfo =
        new ScalarParameterInformationImplementation(true);

    // Act
    CalibratableMertonModel actualCalibratableMertonModel =
        new CalibratableMertonModel(
            descriptor,
            volatilityInfo,
            jumpIntensityInfo,
            jumpSizeMeanInfo,
            new ScalarParameterInformationImplementation(true));

    // Assert
    DiscountCurve discountCurveForDiscountRate =
        actualCalibratableMertonModel
            .getCharacteristicFunctionModel()
            .getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCalibratableMertonModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof MertonModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((MertonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCalibratableMertonModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {0.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCalibratableMertonModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableMertonModel#CalibratableMertonModel(MertonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation)}.
   *
   * <p>Method under test: {@link
   * CalibratableMertonModel#CalibratableMertonModel(MertonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratableMertonModel.<init>(MertonModelDescriptor, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)"
  })
  public void testNewCalibratableMertonModel4() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    MertonModelDescriptor descriptor =
        new MertonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityInfo =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation jumpIntensityInfo =
        new ScalarParameterInformationImplementation(new NegativityConstraint());
    ScalarParameterInformationImplementation jumpSizeMeanInfo =
        new ScalarParameterInformationImplementation(true);

    // Act
    CalibratableMertonModel actualCalibratableMertonModel =
        new CalibratableMertonModel(
            descriptor,
            volatilityInfo,
            jumpIntensityInfo,
            jumpSizeMeanInfo,
            new ScalarParameterInformationImplementation(true));

    // Assert
    DiscountCurve discountCurveForDiscountRate =
        actualCalibratableMertonModel
            .getCharacteristicFunctionModel()
            .getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCalibratableMertonModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof MertonModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((MertonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCalibratableMertonModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 0.0d, 1000000.0d, 1000000.0d},
        actualCalibratableMertonModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableMertonModel#CalibratableMertonModel(MertonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation)}.
   *
   * <p>Method under test: {@link
   * CalibratableMertonModel#CalibratableMertonModel(MertonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratableMertonModel.<init>(MertonModelDescriptor, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)"
  })
  public void testNewCalibratableMertonModel5() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    MertonModelDescriptor descriptor =
        new MertonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityInfo =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation jumpIntensityInfo =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation jumpSizeMeanInfo =
        new ScalarParameterInformationImplementation(new NegativityConstraint());

    // Act
    CalibratableMertonModel actualCalibratableMertonModel =
        new CalibratableMertonModel(
            descriptor,
            volatilityInfo,
            jumpIntensityInfo,
            jumpSizeMeanInfo,
            new ScalarParameterInformationImplementation(true));

    // Assert
    DiscountCurve discountCurveForDiscountRate =
        actualCalibratableMertonModel
            .getCharacteristicFunctionModel()
            .getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCalibratableMertonModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof MertonModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((MertonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCalibratableMertonModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 0.0d, 1000000.0d},
        actualCalibratableMertonModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableMertonModel#CalibratableMertonModel(MertonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation)}.
   *
   * <p>Method under test: {@link
   * CalibratableMertonModel#CalibratableMertonModel(MertonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratableMertonModel.<init>(MertonModelDescriptor, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)"
  })
  public void testNewCalibratableMertonModel6() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    MertonModelDescriptor descriptor =
        new MertonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityInfo =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation jumpIntensityInfo =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation jumpSizeMeanInfo =
        new ScalarParameterInformationImplementation(true);

    // Act
    CalibratableMertonModel actualCalibratableMertonModel =
        new CalibratableMertonModel(
            descriptor,
            volatilityInfo,
            jumpIntensityInfo,
            jumpSizeMeanInfo,
            new ScalarParameterInformationImplementation(new NegativityConstraint()));

    // Assert
    DiscountCurve discountCurveForDiscountRate =
        actualCalibratableMertonModel
            .getCharacteristicFunctionModel()
            .getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCalibratableMertonModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof MertonModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((MertonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCalibratableMertonModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 0.0d},
        actualCalibratableMertonModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableMertonModel#CalibratableMertonModel(MertonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation)}.
   *
   * <p>Method under test: {@link
   * CalibratableMertonModel#CalibratableMertonModel(MertonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratableMertonModel.<init>(MertonModelDescriptor, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)"
  })
  public void testNewCalibratableMertonModel7() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    MertonModelDescriptor descriptor =
        new MertonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityInfo =
        new ScalarParameterInformationImplementation(new BoundConstraint(1000000.0d, 1000000.0d));
    ScalarParameterInformationImplementation jumpIntensityInfo =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation jumpSizeMeanInfo =
        new ScalarParameterInformationImplementation(true);

    // Act
    CalibratableMertonModel actualCalibratableMertonModel =
        new CalibratableMertonModel(
            descriptor,
            volatilityInfo,
            jumpIntensityInfo,
            jumpSizeMeanInfo,
            new ScalarParameterInformationImplementation(true));

    // Assert
    DiscountCurve discountCurveForDiscountRate =
        actualCalibratableMertonModel
            .getCharacteristicFunctionModel()
            .getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCalibratableMertonModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof MertonModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((MertonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCalibratableMertonModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCalibratableMertonModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableMertonModel#CalibratableMertonModel(MertonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation)}.
   *
   * <p>Method under test: {@link
   * CalibratableMertonModel#CalibratableMertonModel(MertonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratableMertonModel.<init>(MertonModelDescriptor, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)"
  })
  public void testNewCalibratableMertonModel8() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    MertonModelDescriptor descriptor =
        new MertonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityInfo =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation jumpIntensityInfo =
        new ScalarParameterInformationImplementation(new BoundConstraint(1000000.0d, 1000000.0d));
    ScalarParameterInformationImplementation jumpSizeMeanInfo =
        new ScalarParameterInformationImplementation(true);

    // Act
    CalibratableMertonModel actualCalibratableMertonModel =
        new CalibratableMertonModel(
            descriptor,
            volatilityInfo,
            jumpIntensityInfo,
            jumpSizeMeanInfo,
            new ScalarParameterInformationImplementation(true));

    // Assert
    DiscountCurve discountCurveForDiscountRate =
        actualCalibratableMertonModel
            .getCharacteristicFunctionModel()
            .getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCalibratableMertonModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof MertonModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((MertonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, 1000000.0d, -1000000.0d, -1000000.0d},
        actualCalibratableMertonModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCalibratableMertonModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableMertonModel#CalibratableMertonModel(MertonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation)}.
   *
   * <p>Method under test: {@link
   * CalibratableMertonModel#CalibratableMertonModel(MertonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratableMertonModel.<init>(MertonModelDescriptor, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)"
  })
  public void testNewCalibratableMertonModel9() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    MertonModelDescriptor descriptor =
        new MertonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityInfo =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation jumpIntensityInfo =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation jumpSizeMeanInfo =
        new ScalarParameterInformationImplementation(new BoundConstraint(1000000.0d, 1000000.0d));

    // Act
    CalibratableMertonModel actualCalibratableMertonModel =
        new CalibratableMertonModel(
            descriptor,
            volatilityInfo,
            jumpIntensityInfo,
            jumpSizeMeanInfo,
            new ScalarParameterInformationImplementation(true));

    // Assert
    DiscountCurve discountCurveForDiscountRate =
        actualCalibratableMertonModel
            .getCharacteristicFunctionModel()
            .getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCalibratableMertonModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof MertonModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((MertonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, 1000000.0d, -1000000.0d},
        actualCalibratableMertonModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCalibratableMertonModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableMertonModel#CalibratableMertonModel(MertonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation)}.
   *
   * <p>Method under test: {@link
   * CalibratableMertonModel#CalibratableMertonModel(MertonModelDescriptor,
   * ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation,
   * ScalarParameterInformation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratableMertonModel.<init>(MertonModelDescriptor, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation, ScalarParameterInformation)"
  })
  public void testNewCalibratableMertonModel10() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    MertonModelDescriptor descriptor =
        new MertonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    ScalarParameterInformationImplementation volatilityInfo =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation jumpIntensityInfo =
        new ScalarParameterInformationImplementation(true);
    ScalarParameterInformationImplementation jumpSizeMeanInfo =
        new ScalarParameterInformationImplementation(true);

    // Act
    CalibratableMertonModel actualCalibratableMertonModel =
        new CalibratableMertonModel(
            descriptor,
            volatilityInfo,
            jumpIntensityInfo,
            jumpSizeMeanInfo,
            new ScalarParameterInformationImplementation(
                new BoundConstraint(1000000.0d, 1000000.0d)));

    // Assert
    DiscountCurve discountCurveForDiscountRate =
        actualCalibratableMertonModel
            .getCharacteristicFunctionModel()
            .getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate instanceof DiscountCurveFromForwardCurve);
    ModelDescriptor modelDescriptor = actualCalibratableMertonModel.getModelDescriptor();
    assertTrue(modelDescriptor instanceof MertonModelDescriptor);
    assertSame(
        discountCurveForDiscountRate,
        ((MertonModelDescriptor) modelDescriptor).getDiscountCurveForDiscountRate());
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, 1000000.0d},
        actualCalibratableMertonModel.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCalibratableMertonModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableMertonModel#getCloneForModifiedParameters(double[])}.
   *
   * <ul>
   *   <li>Then return {@link CalibratableMertonModel}.
   * </ul>
   *
   * <p>Method under test: {@link CalibratableMertonModel#getCloneForModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CalibratableProcess CalibratableMertonModel.getCloneForModifiedParameters(double[])"
  })
  public void testGetCloneForModifiedParameters_thenReturnCalibratableMertonModel() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    CalibratableProcess actualCloneForModifiedParameters =
        new CalibratableMertonModel(
                new MertonModelDescriptor(
                    referenceDate,
                    10.0d,
                    discountCurveForForwardRate,
                    new DiscountCurveFromForwardCurve("Forward Curve Name"),
                    10.0d,
                    10.0d,
                    10.0d,
                    10.0d))
            .getCloneForModifiedParameters(new double[] {10.0d, -1000000.0d, 10.0d, -1000000.0d});

    // Assert
    assertTrue(actualCloneForModifiedParameters instanceof CalibratableMertonModel);
    assertTrue(
        actualCloneForModifiedParameters.getModelDescriptor() instanceof MertonModelDescriptor);
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualCloneForModifiedParameters.getParameterLowerBounds(),
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        actualCloneForModifiedParameters.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CalibratableMertonModel#toString()}
   *   <li>{@link CalibratableMertonModel#getModelDescriptor()}
   *   <li>{@link CalibratableMertonModel#getParameterLowerBounds()}
   *   <li>{@link CalibratableMertonModel#getParameterUpperBounds()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ModelDescriptor CalibratableMertonModel.getModelDescriptor()",
    "double[] CalibratableMertonModel.getParameterLowerBounds()",
    "double[] CalibratableMertonModel.getParameterUpperBounds()",
    "java.lang.String CalibratableMertonModel.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    MertonModelDescriptor descriptor =
        new MertonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    CalibratableMertonModel calibratableMertonModel = new CalibratableMertonModel(descriptor);

    // Act
    calibratableMertonModel.toString();
    ModelDescriptor actualModelDescriptor = calibratableMertonModel.getModelDescriptor();
    double[] actualParameterLowerBounds = calibratableMertonModel.getParameterLowerBounds();

    // Assert
    assertSame(descriptor, actualModelDescriptor);
    assertArrayEquals(
        new double[] {-1000000.0d, -1000000.0d, -1000000.0d, -1000000.0d},
        actualParameterLowerBounds,
        0.0);
    assertArrayEquals(
        new double[] {1000000.0d, 1000000.0d, 1000000.0d, 1000000.0d},
        calibratableMertonModel.getParameterUpperBounds(),
        0.0);
  }

  /**
   * Test {@link CalibratableMertonModel#getCharacteristicFunctionModel()}.
   *
   * <p>Method under test: {@link CalibratableMertonModel#getCharacteristicFunctionModel()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MertonModel CalibratableMertonModel.getCharacteristicFunctionModel()"})
  public void testGetCharacteristicFunctionModel() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    MertonModel actualCharacteristicFunctionModel =
        new CalibratableMertonModel(
                new MertonModelDescriptor(
                    referenceDate,
                    10.0d,
                    discountCurveForForwardRate,
                    discountCurveForDiscountRate,
                    10.0d,
                    10.0d,
                    10.0d,
                    10.0d))
            .getCharacteristicFunctionModel();

    // Assert
    DiscountCurve discountCurveForForwardRate2 =
        actualCharacteristicFunctionModel.getDiscountCurveForForwardRate();
    assertTrue(discountCurveForForwardRate2 instanceof DiscountCurveFromForwardCurve);
    assertEquals(10.0d, actualCharacteristicFunctionModel.getInitialValue(), 0.0);
    assertEquals(10.0d, actualCharacteristicFunctionModel.getJumpIntensity(), 0.0);
    assertEquals(10.0d, actualCharacteristicFunctionModel.getJumpSizeMean(), 0.0);
    assertEquals(10.0d, actualCharacteristicFunctionModel.getJumpSizeStdDev(), 0.0);
    assertEquals(10.0d, actualCharacteristicFunctionModel.getVolatility(), 0.0);
    assertEquals(Double.NaN, actualCharacteristicFunctionModel.getDiscountRate(), 0.0);
    assertEquals(Double.NaN, actualCharacteristicFunctionModel.getRiskFreeRate(), 0.0);
    assertSame(
        discountCurveForDiscountRate,
        actualCharacteristicFunctionModel.getDiscountCurveForDiscountRate());
    assertSame(discountCurveForForwardRate, discountCurveForForwardRate2);
    assertSame(referenceDate, actualCharacteristicFunctionModel.getReferenceDate());
  }
}
