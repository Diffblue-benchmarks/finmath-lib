package net.finmath.marketdata2.calibration;

import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import net.finmath.marketdata2.calibration.CalibratedCurves.CalibrationSpec;
import net.finmath.marketdata2.model.AnalyticModel;
import net.finmath.marketdata2.model.AnalyticModelFromCurvesAndVols;
import net.finmath.optimizer.SolverException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CalibratedCurvesDiffblueTest {
  /**
   * Test {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double, double)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(List, AnalyticModel, double, double)"})
  public void testNewCalibratedCurves() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new CalibratedCurves(calibrationSpecs, null, 10.0d, 10.0d));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double, double)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(List, AnalyticModel, double, double)"})
  public void testNewCalibratedCurves2() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            null,
            "Calibration Curve Name",
            10.0d);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new CalibratedCurves(calibrationSpecs, null, 10.0d, 10.0d));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double, double)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(List, AnalyticModel, double, double)"})
  public void testNewCalibratedCurves3() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            "",
            "Calibration Curve Name",
            10.0d);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new CalibratedCurves(calibrationSpecs, null, 10.0d, 10.0d));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double, double)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(List, AnalyticModel, double, double)"})
  public void testNewCalibratedCurves4() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
            10.0d,
            null,
            "Calibration Curve Name",
            10.0d);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(
        RuntimeException.class, () -> new CalibratedCurves(calibrationSpecs, null, 10.0d, 10.0d));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double, double)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(List, AnalyticModel, double, double)"})
  public void testNewCalibratedCurves5() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "",
            10.0d,
            null,
            "Calibration Curve Name",
            10.0d);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(
        RuntimeException.class, () -> new CalibratedCurves(calibrationSpecs, null, 10.0d, 10.0d));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double, double)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(List, AnalyticModel, double, double)"})
  public void testNewCalibratedCurves6() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "deposit",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
            10.0d,
            null,
            "Calibration Curve Name",
            10.0d);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new CalibratedCurves(calibrationSpecs, null, 10.0d, 10.0d));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double, double)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(List, AnalyticModel, double, double)"})
  public void testNewCalibratedCurves7() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "fra",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
            10.0d,
            null,
            "Calibration Curve Name",
            10.0d);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new CalibratedCurves(calibrationSpecs, null, 10.0d, 10.0d));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double, double)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(List, AnalyticModel, double, double)"})
  public void testNewCalibratedCurves8() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "future",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
            10.0d,
            null,
            "Calibration Curve Name",
            10.0d);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new CalibratedCurves(calibrationSpecs, null, 10.0d, 10.0d));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double, double)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(List, AnalyticModel, double, double)"})
  public void testNewCalibratedCurves9() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "swapleg",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
            10.0d,
            null,
            "Calibration Curve Name",
            10.0d);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new CalibratedCurves(calibrationSpecs, null, 10.0d, 10.0d));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double, double)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(List, AnalyticModel, double, double)"})
  public void testNewCalibratedCurves10() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "swap",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
            10.0d,
            null,
            "Calibration Curve Name",
            10.0d);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new CalibratedCurves(calibrationSpecs, null, 10.0d, 10.0d));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double, double)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(List, AnalyticModel, double, double)"})
  public void testNewCalibratedCurves11() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "swapwithresetonreceiver",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
            10.0d,
            null,
            "Calibration Curve Name",
            10.0d);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new CalibratedCurves(calibrationSpecs, null, 10.0d, 10.0d));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double, double)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(List, AnalyticModel, double, double)"})
  public void testNewCalibratedCurves12() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "swapwithresetonpayer",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
            10.0d,
            null,
            "Calibration Curve Name",
            10.0d);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new CalibratedCurves(calibrationSpecs, null, 10.0d, 10.0d));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double, double)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(List, AnalyticModel, double, double)"})
  public void testNewCalibratedCurves13() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "swapleg",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
            10.0d,
            "",
            "Calibration Curve Name",
            10.0d);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new CalibratedCurves(calibrationSpecs, null, 10.0d, 10.0d));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[])}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(CalibrationSpec[])"})
  public void testNewCalibratedCurves14() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);
    CalibrationSpec[] calibrationSpecs = new CalibrationSpec[] {calibrationSpec};

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new CalibratedCurves(calibrationSpecs));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[])}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(CalibrationSpec[])"})
  public void testNewCalibratedCurves15() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            null,
            "Calibration Curve Name",
            10.0d);
    CalibrationSpec[] calibrationSpecs = new CalibrationSpec[] {calibrationSpec};

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new CalibratedCurves(calibrationSpecs));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[])}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(CalibrationSpec[])"})
  public void testNewCalibratedCurves16() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            "",
            "Calibration Curve Name",
            10.0d);
    CalibrationSpec[] calibrationSpecs = new CalibrationSpec[] {calibrationSpec};

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new CalibratedCurves(calibrationSpecs));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratedCurves.<init>(CalibrationSpec[], AnalyticModelFromCurvesAndVols)"
  })
  public void testNewCalibratedCurves17() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);
    CalibrationSpec[] calibrationSpecs = new CalibrationSpec[] {calibrationSpec};

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new CalibratedCurves(calibrationSpecs, new AnalyticModelFromCurvesAndVols()));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratedCurves.<init>(CalibrationSpec[], AnalyticModelFromCurvesAndVols)"
  })
  public void testNewCalibratedCurves18() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);
    CalibrationSpec[] calibrationSpecs = new CalibrationSpec[] {calibrationSpec};

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> new CalibratedCurves(calibrationSpecs, null));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratedCurves.<init>(CalibrationSpec[], AnalyticModelFromCurvesAndVols)"
  })
  public void testNewCalibratedCurves19() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            null,
            "Calibration Curve Name",
            10.0d);
    CalibrationSpec[] calibrationSpecs = new CalibrationSpec[] {calibrationSpec};

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new CalibratedCurves(calibrationSpecs, new AnalyticModelFromCurvesAndVols()));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratedCurves.<init>(CalibrationSpec[], AnalyticModelFromCurvesAndVols)"
  })
  public void testNewCalibratedCurves20() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            "",
            "Calibration Curve Name",
            10.0d);
    CalibrationSpec[] calibrationSpecs = new CalibrationSpec[] {calibrationSpec};

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new CalibratedCurves(calibrationSpecs, new AnalyticModelFromCurvesAndVols()));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols, double)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratedCurves.<init>(CalibrationSpec[], AnalyticModelFromCurvesAndVols, double)"
  })
  public void testNewCalibratedCurves21() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);
    CalibrationSpec[] calibrationSpecs = new CalibrationSpec[] {calibrationSpec};

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new CalibratedCurves(calibrationSpecs, new AnalyticModelFromCurvesAndVols(), 10.0d));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols, double)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratedCurves.<init>(CalibrationSpec[], AnalyticModelFromCurvesAndVols, double)"
  })
  public void testNewCalibratedCurves22() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);
    CalibrationSpec[] calibrationSpecs = new CalibrationSpec[] {calibrationSpec};

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> new CalibratedCurves(calibrationSpecs, null, 10.0d));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols, double)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratedCurves.<init>(CalibrationSpec[], AnalyticModelFromCurvesAndVols, double)"
  })
  public void testNewCalibratedCurves23() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            null,
            "Calibration Curve Name",
            10.0d);
    CalibrationSpec[] calibrationSpecs = new CalibrationSpec[] {calibrationSpec};

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new CalibratedCurves(calibrationSpecs, new AnalyticModelFromCurvesAndVols(), 10.0d));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols, double)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratedCurves.<init>(CalibrationSpec[], AnalyticModelFromCurvesAndVols, double)"
  })
  public void testNewCalibratedCurves24() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            "",
            "Calibration Curve Name",
            10.0d);
    CalibrationSpec[] calibrationSpecs = new CalibrationSpec[] {calibrationSpec};

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new CalibratedCurves(calibrationSpecs, new AnalyticModelFromCurvesAndVols(), 10.0d));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols, double, double)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratedCurves.<init>(CalibrationSpec[], AnalyticModelFromCurvesAndVols, double, double)"
  })
  public void testNewCalibratedCurves25() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);
    CalibrationSpec[] calibrationSpecs = new CalibrationSpec[] {calibrationSpec};

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new CalibratedCurves(
                calibrationSpecs, new AnalyticModelFromCurvesAndVols(), 10.0d, 10.0d));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols, double, double)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratedCurves.<init>(CalibrationSpec[], AnalyticModelFromCurvesAndVols, double, double)"
  })
  public void testNewCalibratedCurves26() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);
    CalibrationSpec[] calibrationSpecs = new CalibrationSpec[] {calibrationSpec};

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new CalibratedCurves(calibrationSpecs, null, 10.0d, 10.0d));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols, double, double)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratedCurves.<init>(CalibrationSpec[], AnalyticModelFromCurvesAndVols, double, double)"
  })
  public void testNewCalibratedCurves27() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            null,
            "Calibration Curve Name",
            10.0d);
    CalibrationSpec[] calibrationSpecs = new CalibrationSpec[] {calibrationSpec};

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new CalibratedCurves(
                calibrationSpecs, new AnalyticModelFromCurvesAndVols(), 10.0d, 10.0d));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols, double, double)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibratedCurves.<init>(CalibrationSpec[], AnalyticModelFromCurvesAndVols, double, double)"
  })
  public void testNewCalibratedCurves28() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            "",
            "Calibration Curve Name",
            10.0d);
    CalibrationSpec[] calibrationSpecs = new CalibrationSpec[] {calibrationSpec};

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new CalibratedCurves(
                calibrationSpecs, new AnalyticModelFromCurvesAndVols(), 10.0d, 10.0d));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double, double)}.
   *
   * <ul>
   *   <li>When {@link AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols()}.
   * </ul>
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(List, AnalyticModel, double, double)"})
  public void testNewCalibratedCurves_whenAnalyticModelFromCurvesAndVols()
      throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new CalibratedCurves(
                calibrationSpecs, new AnalyticModelFromCurvesAndVols(), 10.0d, 10.0d));
  }
}
