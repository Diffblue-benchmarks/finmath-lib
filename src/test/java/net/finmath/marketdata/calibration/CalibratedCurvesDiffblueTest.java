package net.finmath.marketdata.calibration;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import net.finmath.marketdata.calibration.CalibratedCurves.CalibrationSpec;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.products.AnalyticProduct;
import net.finmath.marketdata.products.Deposit;
import net.finmath.marketdata.products.ForwardRateAgreement;
import net.finmath.marketdata.products.SwapLeg;
import net.finmath.modelling.descriptor.InterestRateSwapLegProductDescriptor;
import net.finmath.optimizer.SolverException;
import net.finmath.singleswaprate.model.AnalyticModelWithVolatilityCubes;
import net.finmath.time.RegularSchedule;
import net.finmath.time.Schedule;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CalibratedCurvesDiffblueTest {
  /**
   * Test CalibrationSpec {@link CalibrationSpec#getCloneShifted(double)}.
   *
   * <p>Method under test: {@link CalibrationSpec#getCloneShifted(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CalibrationSpec CalibrationSpec.getCloneShifted(double)"})
  public void testCalibrationSpecGetCloneShifted() {
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

    // Act and Assert
    assertNull(calibrationSpec.getCloneShifted(10.0d).getSymbol());
  }

  /**
   * Test CalibrationSpec {@link CalibrationSpec#getCloneShifted(double)}.
   *
   * <p>Method under test: {@link CalibrationSpec#getCloneShifted(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CalibrationSpec CalibrationSpec.getCloneShifted(double)"})
  public void testCalibrationSpecGetCloneShifted2() {
    // Arrange
    RegularSchedule swapTenorDefinitionReceiver =
        new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            swapTenorDefinitionReceiver,
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Payer Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);

    // Act and Assert
    assertNull(calibrationSpec.getCloneShifted(10.0d).getSymbol());
  }

  /**
   * Test CalibrationSpec {@link CalibrationSpec#getCloneShifted(double)}.
   *
   * <p>Method under test: {@link CalibrationSpec#getCloneShifted(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CalibrationSpec CalibrationSpec.getCloneShifted(double)"})
  public void testCalibrationSpecGetCloneShifted3() {
    // Arrange
    RegularSchedule swapTenorDefinitionReceiver =
        new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "swapleg",
            swapTenorDefinitionReceiver,
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Payer Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);

    // Act and Assert
    assertNull(calibrationSpec.getCloneShifted(10.0d).getSymbol());
  }

  /**
   * Test CalibrationSpec {@link CalibrationSpec#getCloneShifted(double)}.
   *
   * <p>Method under test: {@link CalibrationSpec#getCloneShifted(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CalibrationSpec CalibrationSpec.getCloneShifted(double)"})
  public void testCalibrationSpecGetCloneShifted4() {
    // Arrange
    RegularSchedule swapTenorDefinitionReceiver =
        new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "deposit",
            swapTenorDefinitionReceiver,
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Payer Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);

    // Act and Assert
    assertNull(calibrationSpec.getCloneShifted(10.0d).getSymbol());
  }

  /**
   * Test CalibrationSpec {@link CalibrationSpec#getCloneShifted(double)}.
   *
   * <p>Method under test: {@link CalibrationSpec#getCloneShifted(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CalibrationSpec CalibrationSpec.getCloneShifted(double)"})
  public void testCalibrationSpecGetCloneShifted5() {
    // Arrange
    RegularSchedule swapTenorDefinitionReceiver =
        new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "fra",
            swapTenorDefinitionReceiver,
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Payer Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);

    // Act and Assert
    assertNull(calibrationSpec.getCloneShifted(10.0d).getSymbol());
  }

  /**
   * Test CalibrationSpec getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CalibrationSpec#toString()}
   *   <li>{@link CalibrationSpec#getSymbol()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CalibrationSpec.getSymbol()", "String CalibrationSpec.toString()"})
  public void testCalibrationSpecGettersAndSetters() {
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

    // Act
    calibrationSpec.toString();

    // Assert
    assertNull(calibrationSpec.getSymbol());
  }

  /**
   * Test CalibrationSpec {@link CalibrationSpec#CalibrationSpec(String, String, Schedule, String,
   * double, String, Schedule, String, double, String, String, double)}.
   *
   * <p>Method under test: {@link CalibrationSpec#CalibrationSpec(String, String, Schedule, String,
   * double, String, Schedule, String, double, String, String, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibrationSpec.<init>(String, String, Schedule, String, double, String, Schedule, String, double, String, String, double)"
  })
  public void testCalibrationSpecNewCalibrationSpec() {
    // Arrange
    RegularSchedule swapTenorDefinitionReceiver =
        new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    CalibrationSpec actualCalibrationSpec =
        new CalibrationSpec(
            "Symbol",
            "Type",
            swapTenorDefinitionReceiver,
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Payer Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);

    // Assert
    assertEquals("Symbol", actualCalibrationSpec.getSymbol());
  }

  /**
   * Test CalibrationSpec {@link CalibrationSpec#CalibrationSpec(String, Schedule, String, double,
   * String, Schedule, String, double, String, String, double)}.
   *
   * <p>Method under test: {@link CalibrationSpec#CalibrationSpec(String, Schedule, String, double,
   * String, Schedule, String, double, String, String, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibrationSpec.<init>(String, Schedule, String, double, String, Schedule, String, double, String, String, double)"
  })
  public void testCalibrationSpecNewCalibrationSpec2() {
    // Arrange
    RegularSchedule swapTenorDefinitionReceiver =
        new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    CalibrationSpec actualCalibrationSpec =
        new CalibrationSpec(
            "Type",
            swapTenorDefinitionReceiver,
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Payer Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);

    // Assert
    assertNull(actualCalibrationSpec.getSymbol());
  }

  /**
   * Test CalibrationSpec {@link CalibrationSpec#CalibrationSpec(String, double[], String, double,
   * String, double[], String, double, String, String, double)}.
   *
   * <ul>
   *   <li>Then return Symbol is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CalibrationSpec#CalibrationSpec(String, double[], String, double,
   * String, double[], String, double, String, String, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibrationSpec.<init>(String, double[], String, double, String, double[], String, double, String, String, double)"
  })
  public void testCalibrationSpecNewCalibrationSpec_thenReturnSymbolIsNull() {
    // Arrange and Act
    CalibrationSpec actualCalibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Payer Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);

    // Assert
    assertNull(actualCalibrationSpec.getSymbol());
  }

  /**
   * Test CalibrationSpec {@link CalibrationSpec#CalibrationSpec(String, double[], String, double,
   * String, String, double)}.
   *
   * <ul>
   *   <li>When array of {@code double} with {@code -0.5} and {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link CalibrationSpec#CalibrationSpec(String, double[], String, double,
   * String, String, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibrationSpec.<init>(String, double[], String, double, String, String, double)"
  })
  public void testCalibrationSpecNewCalibrationSpec_whenArrayOfDoubleWith05And05() {
    // Arrange and Act
    CalibrationSpec actualCalibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {-0.5d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);

    // Assert
    assertNull(actualCalibrationSpec.getSymbol());
  }

  /**
   * Test CalibrationSpec {@link CalibrationSpec#CalibrationSpec(String, double[], String, double,
   * String, double[], String, double, String, String, double)}.
   *
   * <ul>
   *   <li>When array of {@code double} with {@code -0.5} and {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link CalibrationSpec#CalibrationSpec(String, double[], String, double,
   * String, double[], String, double, String, String, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibrationSpec.<init>(String, double[], String, double, String, double[], String, double, String, String, double)"
  })
  public void testCalibrationSpecNewCalibrationSpec_whenArrayOfDoubleWith05And052() {
    // Arrange and Act
    CalibrationSpec actualCalibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {-0.5d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Payer Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);

    // Assert
    assertNull(actualCalibrationSpec.getSymbol());
  }

  /**
   * Test CalibrationSpec {@link CalibrationSpec#CalibrationSpec(String, double[], String, double,
   * String, String, double)}.
   *
   * <ul>
   *   <li>When array of {@code double} with ten and {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link CalibrationSpec#CalibrationSpec(String, double[], String, double,
   * String, String, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibrationSpec.<init>(String, double[], String, double, String, String, double)"
  })
  public void testCalibrationSpecNewCalibrationSpec_whenArrayOfDoubleWithTenAnd05() {
    // Arrange and Act
    CalibrationSpec actualCalibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);

    // Assert
    assertNull(actualCalibrationSpec.getSymbol());
  }

  /**
   * Test CalibrationSpec {@link CalibrationSpec#CalibrationSpec(String, double[], String, double,
   * String, String, double)}.
   *
   * <ul>
   *   <li>When array of {@code double} with ten and {@code -0.5}.
   * </ul>
   *
   * <p>Method under test: {@link CalibrationSpec#CalibrationSpec(String, double[], String, double,
   * String, String, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibrationSpec.<init>(String, double[], String, double, String, String, double)"
  })
  public void testCalibrationSpecNewCalibrationSpec_whenArrayOfDoubleWithTenAnd052() {
    // Arrange and Act
    CalibrationSpec actualCalibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, -0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);

    // Assert
    assertNull(actualCalibrationSpec.getSymbol());
  }

  /**
   * Test CalibrationSpec {@link CalibrationSpec#CalibrationSpec(String, double[], String, double,
   * String, double[], String, double, String, String, double)}.
   *
   * <ul>
   *   <li>When array of {@code double} with ten and {@code -0.5}.
   * </ul>
   *
   * <p>Method under test: {@link CalibrationSpec#CalibrationSpec(String, double[], String, double,
   * String, double[], String, double, String, String, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibrationSpec.<init>(String, double[], String, double, String, double[], String, double, String, String, double)"
  })
  public void testCalibrationSpecNewCalibrationSpec_whenArrayOfDoubleWithTenAnd053() {
    // Arrange and Act
    CalibrationSpec actualCalibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, -0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Payer Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);

    // Assert
    assertNull(actualCalibrationSpec.getSymbol());
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(Collection)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(Collection)"})
  public void testNewCalibratedCurves() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "net.finmath",
            new double[] {Double.POSITIVE_INFINITY, 1.0d, Double.POSITIVE_INFINITY, 1.0d},
            "net.finmath",
            Double.POSITIVE_INFINITY,
            "3",
            "net.finmath",
            Double.POSITIVE_INFINITY);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new CalibratedCurves(calibrationSpecs));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(Collection)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(Collection)"})
  public void testNewCalibratedCurves2() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {Double.POSITIVE_INFINITY, 1.0d, Double.POSITIVE_INFINITY, 1.0d},
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);
    calibrationSpecs.add(calibrationSpec);
    CalibrationSpec calibrationSpec2 =
        new CalibrationSpec(
            "net.finmath",
            new double[] {Double.POSITIVE_INFINITY, 1.0d, Double.POSITIVE_INFINITY, 1.0d},
            "net.finmath",
            Double.POSITIVE_INFINITY,
            "3",
            "net.finmath",
            Double.POSITIVE_INFINITY);
    calibrationSpecs.add(calibrationSpec2);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new CalibratedCurves(calibrationSpecs));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(Collection)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(Collection)"})
  public void testNewCalibratedCurves3() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "net.finmath",
            new double[] {Double.POSITIVE_INFINITY, 1.0d, Double.POSITIVE_INFINITY, 1.0d},
            "net.finmath",
            Double.POSITIVE_INFINITY,
            null,
            "net.finmath",
            Double.POSITIVE_INFINITY);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new CalibratedCurves(calibrationSpecs));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(Collection)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(Collection)"})
  public void testNewCalibratedCurves4() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "net.finmath",
            new double[] {Double.POSITIVE_INFINITY, 1.0d, Double.POSITIVE_INFINITY, 1.0d},
            "net.finmath",
            Double.POSITIVE_INFINITY,
            "",
            "net.finmath",
            Double.POSITIVE_INFINITY);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new CalibratedCurves(calibrationSpecs));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(Collection)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(Collection)"})
  public void testNewCalibratedCurves5() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "net.finmath",
            new double[] {Double.POSITIVE_INFINITY, 1.0d, Double.POSITIVE_INFINITY, 1.0d},
            null,
            Double.POSITIVE_INFINITY,
            null,
            "net.finmath",
            Double.POSITIVE_INFINITY);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> new CalibratedCurves(calibrationSpecs));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(Collection)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(Collection)"})
  public void testNewCalibratedCurves6() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "net.finmath",
            new double[] {Double.POSITIVE_INFINITY, 1.0d, Double.POSITIVE_INFINITY, 1.0d},
            "",
            Double.POSITIVE_INFINITY,
            null,
            "net.finmath",
            Double.POSITIVE_INFINITY);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(RuntimeException.class, () -> new CalibratedCurves(calibrationSpecs));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(Collection)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(Collection)"})
  public void testNewCalibratedCurves7() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "deposit",
            new double[] {Double.POSITIVE_INFINITY, 1.0d, Double.POSITIVE_INFINITY, 1.0d},
            null,
            Double.POSITIVE_INFINITY,
            null,
            "net.finmath",
            Double.POSITIVE_INFINITY);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new CalibratedCurves(calibrationSpecs));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(Collection)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(Collection)"})
  public void testNewCalibratedCurves8() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "fra",
            new double[] {Double.POSITIVE_INFINITY, 1.0d, Double.POSITIVE_INFINITY, 1.0d},
            null,
            Double.POSITIVE_INFINITY,
            null,
            "net.finmath",
            Double.POSITIVE_INFINITY);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new CalibratedCurves(calibrationSpecs));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(Collection)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(Collection)"})
  public void testNewCalibratedCurves9() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "future",
            new double[] {Double.POSITIVE_INFINITY, 1.0d, Double.POSITIVE_INFINITY, 1.0d},
            null,
            Double.POSITIVE_INFINITY,
            null,
            "net.finmath",
            Double.POSITIVE_INFINITY);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new CalibratedCurves(calibrationSpecs));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(Collection)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(Collection)"})
  public void testNewCalibratedCurves10() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "swapleg",
            new double[] {Double.POSITIVE_INFINITY, 1.0d, Double.POSITIVE_INFINITY, 1.0d},
            null,
            Double.POSITIVE_INFINITY,
            null,
            "net.finmath",
            Double.POSITIVE_INFINITY);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new CalibratedCurves(calibrationSpecs));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(Collection)}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(Collection)"})
  public void testNewCalibratedCurves11() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "swapleg",
            new double[] {Double.POSITIVE_INFINITY, 1.0d, Double.POSITIVE_INFINITY, 1.0d},
            null,
            Double.POSITIVE_INFINITY,
            "",
            "net.finmath",
            Double.POSITIVE_INFINITY);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new CalibratedCurves(calibrationSpecs));
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
  public void testNewCalibratedCurves13() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "net.finmath",
            new double[] {Double.POSITIVE_INFINITY, 1.0d, Double.POSITIVE_INFINITY, 1.0d},
            "net.finmath",
            Double.POSITIVE_INFINITY,
            "",
            "net.finmath",
            Double.POSITIVE_INFINITY);
    calibrationSpecs.add(calibrationSpec);

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
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(List, AnalyticModel, double, double)"})
  public void testNewCalibratedCurves14() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {Double.POSITIVE_INFINITY, 1.0d, Double.POSITIVE_INFINITY, 1.0d},
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);
    calibrationSpecs.add(calibrationSpec);
    CalibrationSpec calibrationSpec2 =
        new CalibrationSpec(
            "net.finmath",
            new double[] {Double.POSITIVE_INFINITY, 1.0d, Double.POSITIVE_INFINITY, 1.0d},
            "net.finmath",
            Double.POSITIVE_INFINITY,
            "",
            "net.finmath",
            Double.POSITIVE_INFINITY);
    calibrationSpecs.add(calibrationSpec2);

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
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(List, AnalyticModel, double, double)"})
  public void testNewCalibratedCurves15() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "net.finmath",
            new double[] {Double.POSITIVE_INFINITY, 1.0d, Double.POSITIVE_INFINITY, 1.0d},
            null,
            Double.POSITIVE_INFINITY,
            "",
            "net.finmath",
            Double.POSITIVE_INFINITY);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            new CalibratedCurves(
                calibrationSpecs, new AnalyticModelFromCurvesAndVols(), 10.0d, 10.0d));
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
  public void testNewCalibratedCurves16() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "net.finmath",
            new double[] {Double.POSITIVE_INFINITY, 1.0d, Double.POSITIVE_INFINITY, 1.0d},
            "",
            Double.POSITIVE_INFINITY,
            "",
            "net.finmath",
            Double.POSITIVE_INFINITY);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            new CalibratedCurves(
                calibrationSpecs, new AnalyticModelFromCurvesAndVols(), 10.0d, 10.0d));
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
  public void testNewCalibratedCurves17() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "net.finmath",
            new double[] {Double.POSITIVE_INFINITY, 1.0d, Double.POSITIVE_INFINITY, 1.0d},
            "net.finmath",
            Double.POSITIVE_INFINITY,
            "",
            "net.finmath",
            Double.POSITIVE_INFINITY);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new CalibratedCurves(
                calibrationSpecs, new AnalyticModelWithVolatilityCubes(), 10.0d, 10.0d));
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
  public void testNewCalibratedCurves18() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "net.finmath",
            new double[] {Double.POSITIVE_INFINITY, 1.0d, Double.POSITIVE_INFINITY, 1.0d},
            "net.finmath",
            Double.POSITIVE_INFINITY,
            "3",
            "net.finmath",
            Double.POSITIVE_INFINITY);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new CalibratedCurves(
                calibrationSpecs, new AnalyticModelWithVolatilityCubes(), 10.0d, 10.0d));
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
  public void testNewCalibratedCurves19() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "deposit",
            new double[] {Double.POSITIVE_INFINITY, 1.0d, Double.POSITIVE_INFINITY, 1.0d},
            null,
            Double.POSITIVE_INFINITY,
            "",
            "net.finmath",
            Double.POSITIVE_INFINITY);
    calibrationSpecs.add(calibrationSpec);

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
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(List, AnalyticModel, double, double)"})
  public void testNewCalibratedCurves20() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "fra",
            new double[] {Double.POSITIVE_INFINITY, 1.0d, Double.POSITIVE_INFINITY, 1.0d},
            null,
            Double.POSITIVE_INFINITY,
            "",
            "net.finmath",
            Double.POSITIVE_INFINITY);
    calibrationSpecs.add(calibrationSpec);

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
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(List, AnalyticModel, double, double)"})
  public void testNewCalibratedCurves21() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "future",
            new double[] {Double.POSITIVE_INFINITY, 1.0d, Double.POSITIVE_INFINITY, 1.0d},
            null,
            Double.POSITIVE_INFINITY,
            "",
            "net.finmath",
            Double.POSITIVE_INFINITY);
    calibrationSpecs.add(calibrationSpec);

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
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(List, AnalyticModel, double, double)"})
  public void testNewCalibratedCurves22() throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "swapleg",
            new double[] {Double.POSITIVE_INFINITY, 1.0d, Double.POSITIVE_INFINITY, 1.0d},
            null,
            Double.POSITIVE_INFINITY,
            "",
            "net.finmath",
            Double.POSITIVE_INFINITY);
    calibrationSpecs.add(calibrationSpec);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new CalibratedCurves(
                calibrationSpecs, new AnalyticModelFromCurvesAndVols(), 10.0d, 10.0d));
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
  public void testNewCalibratedCurves23() throws CloneNotSupportedException, SolverException {
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
  public void testNewCalibratedCurves24() throws CloneNotSupportedException, SolverException {
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
  public void testNewCalibratedCurves25() throws CloneNotSupportedException, SolverException {
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
   * Test {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[])}.
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(CalibrationSpec[])"})
  public void testNewCalibratedCurves26() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
            10.0d,
            null,
            "Calibration Curve Name",
            10.0d);
    CalibrationSpec[] calibrationSpecs = new CalibrationSpec[] {calibrationSpec};

    // Act and Assert
    assertThrows(RuntimeException.class, () -> new CalibratedCurves(calibrationSpecs));
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
  public void testNewCalibratedCurves27() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "",
            10.0d,
            null,
            "Calibration Curve Name",
            10.0d);
    CalibrationSpec[] calibrationSpecs = new CalibrationSpec[] {calibrationSpec};

    // Act and Assert
    assertThrows(RuntimeException.class, () -> new CalibratedCurves(calibrationSpecs));
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
  public void testNewCalibratedCurves28() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "deposit",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
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
  public void testNewCalibratedCurves29() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "fra",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
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
  public void testNewCalibratedCurves30() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "future",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
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
  public void testNewCalibratedCurves31() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "swapleg",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
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
  public void testNewCalibratedCurves32() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "swapleg",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
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
  public void testNewCalibratedCurves33() throws CloneNotSupportedException, SolverException {
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
  public void testNewCalibratedCurves34() throws CloneNotSupportedException, SolverException {
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
  public void testNewCalibratedCurves35() throws CloneNotSupportedException, SolverException {
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
  public void testNewCalibratedCurves36() throws CloneNotSupportedException, SolverException {
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
  public void testNewCalibratedCurves37() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
            10.0d,
            null,
            "Calibration Curve Name",
            10.0d);
    CalibrationSpec[] calibrationSpecs = new CalibrationSpec[] {calibrationSpec};

    // Act and Assert
    assertThrows(
        RuntimeException.class,
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
  public void testNewCalibratedCurves38() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "",
            10.0d,
            null,
            "Calibration Curve Name",
            10.0d);
    CalibrationSpec[] calibrationSpecs = new CalibrationSpec[] {calibrationSpec};

    // Act and Assert
    assertThrows(
        RuntimeException.class,
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
  public void testNewCalibratedCurves39() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "deposit",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
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
  public void testNewCalibratedCurves40() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "fra",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
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
  public void testNewCalibratedCurves41() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "future",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
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
  public void testNewCalibratedCurves42() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "swapleg",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
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
  public void testNewCalibratedCurves43() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.0d, 10.0d, 0.0d},
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);
    CalibrationSpec calibrationSpec2 =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.0d, 10.0d, 0.0d},
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new CalibratedCurves(
                new CalibrationSpec[] {calibrationSpec, calibrationSpec2},
                new AnalyticModelWithVolatilityCubes()));
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
  public void testNewCalibratedCurves44() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "swapleg",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
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
  public void testNewCalibratedCurves45() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.0d, 10.0d, 0.0d},
            "Forward Curve Receiver Name",
            10.0d,
            null,
            "Calibration Curve Name",
            10.0d);
    CalibrationSpec calibrationSpec2 =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.0d, 10.0d, 0.0d},
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new CalibratedCurves(
                new CalibrationSpec[] {calibrationSpec, calibrationSpec2},
                new AnalyticModelWithVolatilityCubes()));
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
  public void testNewCalibratedCurves46() throws CloneNotSupportedException, SolverException {
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
  public void testNewCalibratedCurves47() throws CloneNotSupportedException, SolverException {
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
  public void testNewCalibratedCurves48() throws CloneNotSupportedException, SolverException {
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
  public void testNewCalibratedCurves49() throws CloneNotSupportedException, SolverException {
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
  public void testNewCalibratedCurves50() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
            10.0d,
            null,
            "Calibration Curve Name",
            10.0d);
    CalibrationSpec[] calibrationSpecs = new CalibrationSpec[] {calibrationSpec};

    // Act and Assert
    assertThrows(
        RuntimeException.class,
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
  public void testNewCalibratedCurves51() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "",
            10.0d,
            null,
            "Calibration Curve Name",
            10.0d);
    CalibrationSpec[] calibrationSpecs = new CalibrationSpec[] {calibrationSpec};

    // Act and Assert
    assertThrows(
        RuntimeException.class,
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
  public void testNewCalibratedCurves52() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "deposit",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
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
  public void testNewCalibratedCurves53() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "fra",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
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
  public void testNewCalibratedCurves54() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "future",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
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
  public void testNewCalibratedCurves55() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "swapleg",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
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
  public void testNewCalibratedCurves56() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.0d, 10.0d, 0.0d},
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);
    CalibrationSpec calibrationSpec2 =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.0d, 10.0d, 0.0d},
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new CalibratedCurves(
                new CalibrationSpec[] {calibrationSpec, calibrationSpec2},
                new AnalyticModelWithVolatilityCubes(),
                10.0d));
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
  public void testNewCalibratedCurves57() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "swapleg",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
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
  public void testNewCalibratedCurves58() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.0d, 10.0d, 0.0d},
            "Forward Curve Receiver Name",
            10.0d,
            null,
            "Calibration Curve Name",
            10.0d);
    CalibrationSpec calibrationSpec2 =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.0d, 10.0d, 0.0d},
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new CalibratedCurves(
                new CalibrationSpec[] {calibrationSpec, calibrationSpec2},
                new AnalyticModelWithVolatilityCubes(),
                10.0d));
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
  public void testNewCalibratedCurves59() throws CloneNotSupportedException, SolverException {
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
  public void testNewCalibratedCurves60() throws CloneNotSupportedException, SolverException {
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
  public void testNewCalibratedCurves61() throws CloneNotSupportedException, SolverException {
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
  public void testNewCalibratedCurves62() throws CloneNotSupportedException, SolverException {
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
  public void testNewCalibratedCurves63() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
            10.0d,
            null,
            "Calibration Curve Name",
            10.0d);
    CalibrationSpec[] calibrationSpecs = new CalibrationSpec[] {calibrationSpec};

    // Act and Assert
    assertThrows(
        RuntimeException.class,
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
  public void testNewCalibratedCurves64() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "",
            10.0d,
            null,
            "Calibration Curve Name",
            10.0d);
    CalibrationSpec[] calibrationSpecs = new CalibrationSpec[] {calibrationSpec};

    // Act and Assert
    assertThrows(
        RuntimeException.class,
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
  public void testNewCalibratedCurves65() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "deposit",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
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
  public void testNewCalibratedCurves66() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "fra",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
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
  public void testNewCalibratedCurves67() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "future",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
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
  public void testNewCalibratedCurves68() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "swapleg",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
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
  public void testNewCalibratedCurves69() throws CloneNotSupportedException, SolverException {
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
    CalibrationSpec calibrationSpec2 =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new CalibratedCurves(
                new CalibrationSpec[] {calibrationSpec, calibrationSpec2},
                new AnalyticModelWithVolatilityCubes(),
                10.0d,
                10.0d));
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
  public void testNewCalibratedCurves70() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "swapleg",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            null,
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
  public void testNewCalibratedCurves71() throws CloneNotSupportedException, SolverException {
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
    CalibrationSpec calibrationSpec2 =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new CalibratedCurves(
                new CalibrationSpec[] {calibrationSpec, calibrationSpec2},
                new AnalyticModelWithVolatilityCubes(),
                10.0d,
                10.0d));
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(Collection)}.
   *
   * <ul>
   *   <li>Then Model return {@link AnalyticModelFromCurvesAndVols}.
   * </ul>
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(Collection)"})
  public void testNewCalibratedCurves_thenModelReturnAnalyticModelFromCurvesAndVols()
      throws CloneNotSupportedException, SolverException {
    // Arrange and Act
    CalibratedCurves actualCalibratedCurves = new CalibratedCurves(new ArrayList<>());

    // Assert
    AnalyticModel model = actualCalibratedCurves.getModel();
    assertTrue(model instanceof AnalyticModelFromCurvesAndVols);
    assertNull(((AnalyticModelFromCurvesAndVols) model).getReferenceDate());
    assertEquals(2, actualCalibratedCurves.getLastNumberOfInterations());
    assertTrue(model.getCurves().isEmpty());
    assertTrue(model.getVolatilitySurfaces().isEmpty());
    assertEquals(Double.NaN, actualCalibratedCurves.getLastAccuracy(), 0.0);
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double, double)}.
   *
   * <ul>
   *   <li>Then Model return {@link AnalyticModelFromCurvesAndVols}.
   * </ul>
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(List, AnalyticModel, double, double)"})
  public void testNewCalibratedCurves_thenModelReturnAnalyticModelFromCurvesAndVols2()
      throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();

    // Act
    CalibratedCurves actualCalibratedCurves =
        new CalibratedCurves(calibrationSpecs, new AnalyticModelFromCurvesAndVols(), 10.0d, 10.0d);

    // Assert
    AnalyticModel model = actualCalibratedCurves.getModel();
    assertTrue(model instanceof AnalyticModelFromCurvesAndVols);
    assertNull(((AnalyticModelFromCurvesAndVols) model).getReferenceDate());
    assertEquals(2, actualCalibratedCurves.getLastNumberOfInterations());
    assertTrue(model.getCurves().isEmpty());
    assertTrue(model.getVolatilitySurfaces().isEmpty());
    assertEquals(Double.NaN, actualCalibratedCurves.getLastAccuracy(), 0.0);
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[])}.
   *
   * <ul>
   *   <li>Then Model return {@link AnalyticModelFromCurvesAndVols}.
   * </ul>
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(CalibrationSpec[])"})
  public void testNewCalibratedCurves_thenModelReturnAnalyticModelFromCurvesAndVols3()
      throws CloneNotSupportedException, SolverException {
    // Arrange and Act
    CalibratedCurves actualCalibratedCurves = new CalibratedCurves(new CalibrationSpec[] {});

    // Assert
    AnalyticModel model = actualCalibratedCurves.getModel();
    assertTrue(model instanceof AnalyticModelFromCurvesAndVols);
    assertNull(((AnalyticModelFromCurvesAndVols) model).getReferenceDate());
    assertEquals(2, actualCalibratedCurves.getLastNumberOfInterations());
    assertTrue(model.getCurves().isEmpty());
    assertTrue(model.getVolatilitySurfaces().isEmpty());
    assertEquals(Double.NaN, actualCalibratedCurves.getLastAccuracy(), 0.0);
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols)}.
   *
   * <ul>
   *   <li>Then Model return {@link AnalyticModelFromCurvesAndVols}.
   * </ul>
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
  public void testNewCalibratedCurves_thenModelReturnAnalyticModelFromCurvesAndVols4()
      throws CloneNotSupportedException, SolverException {
    // Arrange and Act
    CalibratedCurves actualCalibratedCurves =
        new CalibratedCurves(new CalibrationSpec[] {}, new AnalyticModelFromCurvesAndVols());

    // Assert
    AnalyticModel model = actualCalibratedCurves.getModel();
    assertTrue(model instanceof AnalyticModelFromCurvesAndVols);
    assertNull(((AnalyticModelFromCurvesAndVols) model).getReferenceDate());
    assertEquals(2, actualCalibratedCurves.getLastNumberOfInterations());
    assertTrue(model.getCurves().isEmpty());
    assertTrue(model.getVolatilitySurfaces().isEmpty());
    assertEquals(Double.NaN, actualCalibratedCurves.getLastAccuracy(), 0.0);
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols, double)}.
   *
   * <ul>
   *   <li>Then Model return {@link AnalyticModelFromCurvesAndVols}.
   * </ul>
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
  public void testNewCalibratedCurves_thenModelReturnAnalyticModelFromCurvesAndVols5()
      throws CloneNotSupportedException, SolverException {
    // Arrange and Act
    CalibratedCurves actualCalibratedCurves =
        new CalibratedCurves(new CalibrationSpec[] {}, new AnalyticModelFromCurvesAndVols(), 10.0d);

    // Assert
    AnalyticModel model = actualCalibratedCurves.getModel();
    assertTrue(model instanceof AnalyticModelFromCurvesAndVols);
    assertNull(((AnalyticModelFromCurvesAndVols) model).getReferenceDate());
    assertEquals(2, actualCalibratedCurves.getLastNumberOfInterations());
    assertTrue(model.getCurves().isEmpty());
    assertTrue(model.getVolatilitySurfaces().isEmpty());
    assertEquals(Double.NaN, actualCalibratedCurves.getLastAccuracy(), 0.0);
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols, double, double)}.
   *
   * <ul>
   *   <li>Then Model return {@link AnalyticModelFromCurvesAndVols}.
   * </ul>
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
  public void testNewCalibratedCurves_thenModelReturnAnalyticModelFromCurvesAndVols6()
      throws CloneNotSupportedException, SolverException {
    // Arrange and Act
    CalibratedCurves actualCalibratedCurves =
        new CalibratedCurves(
            new CalibrationSpec[] {}, new AnalyticModelFromCurvesAndVols(), 10.0d, 10.0d);

    // Assert
    AnalyticModel model = actualCalibratedCurves.getModel();
    assertTrue(model instanceof AnalyticModelFromCurvesAndVols);
    assertNull(((AnalyticModelFromCurvesAndVols) model).getReferenceDate());
    assertEquals(2, actualCalibratedCurves.getLastNumberOfInterations());
    assertTrue(model.getCurves().isEmpty());
    assertTrue(model.getVolatilitySurfaces().isEmpty());
    assertEquals(Double.NaN, actualCalibratedCurves.getLastAccuracy(), 0.0);
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double, double)}.
   *
   * <ul>
   *   <li>Then Model return {@link AnalyticModelWithVolatilityCubes}.
   * </ul>
   *
   * <p>Method under test: {@link CalibratedCurves#CalibratedCurves(List, AnalyticModel, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CalibratedCurves.<init>(List, AnalyticModel, double, double)"})
  public void testNewCalibratedCurves_thenModelReturnAnalyticModelWithVolatilityCubes()
      throws CloneNotSupportedException, SolverException {
    // Arrange
    ArrayList<CalibrationSpec> calibrationSpecs = new ArrayList<>();

    // Act
    CalibratedCurves actualCalibratedCurves =
        new CalibratedCurves(
            calibrationSpecs, new AnalyticModelWithVolatilityCubes(), 10.0d, 10.0d);

    // Assert
    AnalyticModel model = actualCalibratedCurves.getModel();
    assertTrue(model instanceof AnalyticModelWithVolatilityCubes);
    assertNull(((AnalyticModelWithVolatilityCubes) model).getReferenceDate());
    assertEquals(2, actualCalibratedCurves.getLastNumberOfInterations());
    assertTrue(model.getCurves().isEmpty());
    assertTrue(model.getVolatilitySurfaces().isEmpty());
    assertTrue(((AnalyticModelWithVolatilityCubes) model).getVolatilityCubes().isEmpty());
    assertTrue(((AnalyticModelWithVolatilityCubes) model).getVolatilityCubeNames().isEmpty());
    assertEquals(Double.NaN, actualCalibratedCurves.getLastAccuracy(), 0.0);
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols)}.
   *
   * <ul>
   *   <li>Then Model return {@link AnalyticModelWithVolatilityCubes}.
   * </ul>
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
  public void testNewCalibratedCurves_thenModelReturnAnalyticModelWithVolatilityCubes2()
      throws CloneNotSupportedException, SolverException {
    // Arrange and Act
    CalibratedCurves actualCalibratedCurves =
        new CalibratedCurves(new CalibrationSpec[] {}, new AnalyticModelWithVolatilityCubes());

    // Assert
    AnalyticModel model = actualCalibratedCurves.getModel();
    assertTrue(model instanceof AnalyticModelWithVolatilityCubes);
    assertNull(((AnalyticModelWithVolatilityCubes) model).getReferenceDate());
    assertEquals(2, actualCalibratedCurves.getLastNumberOfInterations());
    assertTrue(model.getCurves().isEmpty());
    assertTrue(model.getVolatilitySurfaces().isEmpty());
    assertTrue(((AnalyticModelWithVolatilityCubes) model).getVolatilityCubes().isEmpty());
    assertTrue(((AnalyticModelWithVolatilityCubes) model).getVolatilityCubeNames().isEmpty());
    assertEquals(Double.NaN, actualCalibratedCurves.getLastAccuracy(), 0.0);
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols, double)}.
   *
   * <ul>
   *   <li>Then Model return {@link AnalyticModelWithVolatilityCubes}.
   * </ul>
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
  public void testNewCalibratedCurves_thenModelReturnAnalyticModelWithVolatilityCubes3()
      throws CloneNotSupportedException, SolverException {
    // Arrange and Act
    CalibratedCurves actualCalibratedCurves =
        new CalibratedCurves(
            new CalibrationSpec[] {}, new AnalyticModelWithVolatilityCubes(), 10.0d);

    // Assert
    AnalyticModel model = actualCalibratedCurves.getModel();
    assertTrue(model instanceof AnalyticModelWithVolatilityCubes);
    assertNull(((AnalyticModelWithVolatilityCubes) model).getReferenceDate());
    assertEquals(2, actualCalibratedCurves.getLastNumberOfInterations());
    assertTrue(model.getCurves().isEmpty());
    assertTrue(model.getVolatilitySurfaces().isEmpty());
    assertTrue(((AnalyticModelWithVolatilityCubes) model).getVolatilityCubes().isEmpty());
    assertTrue(((AnalyticModelWithVolatilityCubes) model).getVolatilityCubeNames().isEmpty());
    assertEquals(Double.NaN, actualCalibratedCurves.getLastAccuracy(), 0.0);
  }

  /**
   * Test {@link CalibratedCurves#CalibratedCurves(CalibrationSpec[],
   * AnalyticModelFromCurvesAndVols, double, double)}.
   *
   * <ul>
   *   <li>Then Model return {@link AnalyticModelWithVolatilityCubes}.
   * </ul>
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
  public void testNewCalibratedCurves_thenModelReturnAnalyticModelWithVolatilityCubes4()
      throws CloneNotSupportedException, SolverException {
    // Arrange and Act
    CalibratedCurves actualCalibratedCurves =
        new CalibratedCurves(
            new CalibrationSpec[] {}, new AnalyticModelWithVolatilityCubes(), 10.0d, 10.0d);

    // Assert
    AnalyticModel model = actualCalibratedCurves.getModel();
    assertTrue(model instanceof AnalyticModelWithVolatilityCubes);
    assertNull(((AnalyticModelWithVolatilityCubes) model).getReferenceDate());
    assertEquals(2, actualCalibratedCurves.getLastNumberOfInterations());
    assertTrue(model.getCurves().isEmpty());
    assertTrue(model.getVolatilitySurfaces().isEmpty());
    assertTrue(((AnalyticModelWithVolatilityCubes) model).getVolatilityCubes().isEmpty());
    assertTrue(((AnalyticModelWithVolatilityCubes) model).getVolatilityCubeNames().isEmpty());
    assertEquals(Double.NaN, actualCalibratedCurves.getLastAccuracy(), 0.0);
  }

  /**
   * Test {@link CalibratedCurves#getCalibrationProductForSpec(CalibrationSpec)}.
   *
   * <p>Method under test: {@link CalibratedCurves#getCalibrationProductForSpec(CalibrationSpec)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnalyticProduct CalibratedCurves.getCalibrationProductForSpec(CalibrationSpec)"
  })
  public void testGetCalibrationProductForSpec()
      throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibratedCurves calibratedCurves = new CalibratedCurves(new ArrayList<>());
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            "3",
            "Calibration Curve Name",
            10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> calibratedCurves.getCalibrationProductForSpec(calibrationSpec));
  }

  /**
   * Test {@link CalibratedCurves#getCalibrationProductForSpec(CalibrationSpec)}.
   *
   * <p>Method under test: {@link CalibratedCurves#getCalibrationProductForSpec(CalibrationSpec)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnalyticProduct CalibratedCurves.getCalibrationProductForSpec(CalibrationSpec)"
  })
  public void testGetCalibrationProductForSpec2()
      throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibratedCurves calibratedCurves = new CalibratedCurves(new ArrayList<>());
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "Forward Curve Receiver Name",
            10.0d,
            "",
            "Calibration Curve Name",
            10.0d);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> calibratedCurves.getCalibrationProductForSpec(calibrationSpec));
  }

  /**
   * Test {@link CalibratedCurves#getCalibrationProductForSpec(CalibrationSpec)}.
   *
   * <p>Method under test: {@link CalibratedCurves#getCalibrationProductForSpec(CalibrationSpec)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnalyticProduct CalibratedCurves.getCalibrationProductForSpec(CalibrationSpec)"
  })
  public void testGetCalibrationProductForSpec3()
      throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibratedCurves calibratedCurves = new CalibratedCurves(new ArrayList<>());
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "fra",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "",
            10.0d,
            "",
            "Calibration Curve Name",
            10.0d);

    // Act
    AnalyticProduct actualCalibrationProductForSpec =
        calibratedCurves.getCalibrationProductForSpec(calibrationSpec);

    // Assert
    assertTrue(actualCalibrationProductForSpec instanceof ForwardRateAgreement);
  }

  /**
   * Test {@link CalibratedCurves#getCalibrationProductForSpec(CalibrationSpec)}.
   *
   * <p>Method under test: {@link CalibratedCurves#getCalibrationProductForSpec(CalibrationSpec)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnalyticProduct CalibratedCurves.getCalibrationProductForSpec(CalibrationSpec)"
  })
  public void testGetCalibrationProductForSpec4()
      throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibratedCurves calibratedCurves = new CalibratedCurves(new ArrayList<>());
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "future",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "",
            10.0d,
            "",
            "Calibration Curve Name",
            10.0d);

    // Act
    AnalyticProduct actualCalibrationProductForSpec =
        calibratedCurves.getCalibrationProductForSpec(calibrationSpec);

    // Assert
    assertTrue(actualCalibrationProductForSpec instanceof ForwardRateAgreement);
  }

  /**
   * Test {@link CalibratedCurves#getCalibrationProductForSpec(CalibrationSpec)}.
   *
   * <ul>
   *   <li>Then return {@link Deposit}.
   * </ul>
   *
   * <p>Method under test: {@link CalibratedCurves#getCalibrationProductForSpec(CalibrationSpec)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnalyticProduct CalibratedCurves.getCalibrationProductForSpec(CalibrationSpec)"
  })
  public void testGetCalibrationProductForSpec_thenReturnDeposit()
      throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibratedCurves calibratedCurves = new CalibratedCurves(new ArrayList<>());
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "deposit",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "",
            10.0d,
            "",
            "Calibration Curve Name",
            10.0d);

    // Act
    AnalyticProduct actualCalibrationProductForSpec =
        calibratedCurves.getCalibrationProductForSpec(calibrationSpec);

    // Assert
    assertTrue(actualCalibrationProductForSpec instanceof Deposit);
    Schedule schedule = ((Deposit) actualCalibrationProductForSpec).getSchedule();
    assertTrue(schedule instanceof RegularSchedule);
    assertEquals("", ((Deposit) actualCalibrationProductForSpec).getDiscountCurveName());
    assertNull(schedule.getReferenceDate());
    assertNull(schedule.getPeriods());
    assertNull(schedule.iterator());
    assertNull(schedule.getDaycountconvention());
    assertEquals(0, schedule.getNumberOfPeriods());
    assertEquals(10.0d, ((Deposit) actualCalibrationProductForSpec).getFixingTime(), 0.0);
    assertEquals(10.0d, ((Deposit) actualCalibrationProductForSpec).getRate(), 0.0);
  }

  /**
   * Test {@link CalibratedCurves#getCalibrationProductForSpec(CalibrationSpec)}.
   *
   * <ul>
   *   <li>Then return {@link SwapLeg}.
   * </ul>
   *
   * <p>Method under test: {@link CalibratedCurves#getCalibrationProductForSpec(CalibrationSpec)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnalyticProduct CalibratedCurves.getCalibrationProductForSpec(CalibrationSpec)"
  })
  public void testGetCalibrationProductForSpec_thenReturnSwapLeg()
      throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibratedCurves calibratedCurves = new CalibratedCurves(new ArrayList<>());
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "swapleg",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "",
            10.0d,
            "",
            "Calibration Curve Name",
            10.0d);

    // Act
    AnalyticProduct actualCalibrationProductForSpec =
        calibratedCurves.getCalibrationProductForSpec(calibrationSpec);

    // Assert
    assertTrue(actualCalibrationProductForSpec instanceof SwapLeg);
    Schedule schedule = ((SwapLeg) actualCalibrationProductForSpec).getSchedule();
    assertTrue(schedule instanceof RegularSchedule);
    assertEquals("", ((SwapLeg) actualCalibrationProductForSpec).getDiscountCurveName());
    assertEquals("", ((SwapLeg) actualCalibrationProductForSpec).getForwardCurveName());
    InterestRateSwapLegProductDescriptor descriptor =
        ((SwapLeg) actualCalibrationProductForSpec).getDescriptor();
    assertEquals("", descriptor.getDiscountCurveName());
    assertEquals("", descriptor.getForwardCurveName());
    assertNull(schedule.getReferenceDate());
    assertNull(schedule.getPeriods());
    assertNull(schedule.iterator());
    assertNull(schedule.getDaycountconvention());
    assertEquals(0, schedule.getNumberOfPeriods());
    assertTrue(((SwapLeg) actualCalibrationProductForSpec).isNotionalExchanged());
    assertTrue(descriptor.isNotionalExchanged());
    assertArrayEquals(
        new double[] {}, ((SwapLeg) actualCalibrationProductForSpec).getSpreads(), 0.0);
    assertArrayEquals(new double[] {}, descriptor.getNotionals(), 0.0);
    assertArrayEquals(new double[] {}, descriptor.getSpreads(), 0.0);
  }

  /**
   * Test {@link CalibratedCurves#getCalibrationProductForSpec(CalibrationSpec)}.
   *
   * <ul>
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link CalibratedCurves#getCalibrationProductForSpec(CalibrationSpec)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AnalyticProduct CalibratedCurves.getCalibrationProductForSpec(CalibrationSpec)"
  })
  public void testGetCalibrationProductForSpec_thenThrowRuntimeException()
      throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibratedCurves calibratedCurves = new CalibratedCurves(new ArrayList<>());
    CalibrationSpec calibrationSpec =
        new CalibrationSpec(
            "Type",
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            "",
            10.0d,
            "",
            "Calibration Curve Name",
            10.0d);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> calibratedCurves.getCalibrationProductForSpec(calibrationSpec));
  }

  /**
   * Test {@link CalibratedCurves#getCurve(String)}.
   *
   * <p>Method under test: {@link CalibratedCurves#getCurve(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.marketdata.model.curves.Curve CalibratedCurves.getCurve(String)"})
  public void testGetCurve() throws CloneNotSupportedException, SolverException {
    // Arrange, Act and Assert
    assertNull(new CalibratedCurves(new ArrayList<>()).getCurve("Name"));
  }

  /**
   * Test {@link CalibratedCurves#getCloneShifted(Map)} with {@code shifts}.
   *
   * <p>Method under test: {@link CalibratedCurves#getCloneShifted(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CalibratedCurves CalibratedCurves.getCloneShifted(Map)"})
  public void testGetCloneShiftedWithShifts() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibratedCurves calibratedCurves = new CalibratedCurves(new ArrayList<>());

    // Act
    CalibratedCurves actualCloneShifted = calibratedCurves.getCloneShifted(new HashMap<>());

    // Assert
    AnalyticModel model = actualCloneShifted.getModel();
    assertTrue(model instanceof AnalyticModelFromCurvesAndVols);
    assertNull(((AnalyticModelFromCurvesAndVols) model).getReferenceDate());
    assertEquals(2, actualCloneShifted.getLastNumberOfInterations());
    assertTrue(model.getCurves().isEmpty());
    assertTrue(model.getVolatilitySurfaces().isEmpty());
    assertEquals(Double.NaN, actualCloneShifted.getLastAccuracy(), 0.0);
  }

  /**
   * Test {@link CalibratedCurves#getCloneShifted(Pattern, double)} with {@code symbolRegExp},
   * {@code shift}.
   *
   * <p>Method under test: {@link CalibratedCurves#getCloneShifted(Pattern, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CalibratedCurves CalibratedCurves.getCloneShifted(Pattern, double)"})
  public void testGetCloneShiftedWithSymbolRegExpShift()
      throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibratedCurves calibratedCurves = new CalibratedCurves(new ArrayList<>());

    // Act
    CalibratedCurves actualCloneShifted =
        calibratedCurves.getCloneShifted(Pattern.compile(".*\\.txt"), 10.0d);

    // Assert
    AnalyticModel model = actualCloneShifted.getModel();
    assertTrue(model instanceof AnalyticModelFromCurvesAndVols);
    assertNull(((AnalyticModelFromCurvesAndVols) model).getReferenceDate());
    assertEquals(2, actualCloneShifted.getLastNumberOfInterations());
    assertTrue(model.getCurves().isEmpty());
    assertTrue(model.getVolatilitySurfaces().isEmpty());
    assertEquals(Double.NaN, actualCloneShifted.getLastAccuracy(), 0.0);
  }

  /**
   * Test {@link CalibratedCurves#getCloneShifted(String, double)} with {@code symbol}, {@code
   * shift}.
   *
   * <p>Method under test: {@link CalibratedCurves#getCloneShifted(String, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CalibratedCurves CalibratedCurves.getCloneShifted(String, double)"})
  public void testGetCloneShiftedWithSymbolShift()
      throws CloneNotSupportedException, SolverException {
    // Arrange and Act
    CalibratedCurves actualCloneShifted =
        new CalibratedCurves(new ArrayList<>()).getCloneShifted("Symbol", 10.0d);

    // Assert
    AnalyticModel model = actualCloneShifted.getModel();
    assertTrue(model instanceof AnalyticModelFromCurvesAndVols);
    assertNull(((AnalyticModelFromCurvesAndVols) model).getReferenceDate());
    assertEquals(2, actualCloneShifted.getLastNumberOfInterations());
    assertTrue(model.getCurves().isEmpty());
    assertTrue(model.getVolatilitySurfaces().isEmpty());
    assertEquals(Double.NaN, actualCloneShifted.getLastAccuracy(), 0.0);
  }

  /**
   * Test {@link CalibratedCurves#getCloneShiftedForRegExp(String, double)}.
   *
   * <ul>
   *   <li>Then Model return {@link AnalyticModelFromCurvesAndVols}.
   * </ul>
   *
   * <p>Method under test: {@link CalibratedCurves#getCloneShiftedForRegExp(String, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CalibratedCurves CalibratedCurves.getCloneShiftedForRegExp(String, double)"})
  public void testGetCloneShiftedForRegExp_thenModelReturnAnalyticModelFromCurvesAndVols()
      throws CloneNotSupportedException, SolverException {
    // Arrange and Act
    CalibratedCurves actualCloneShiftedForRegExp =
        new CalibratedCurves(new ArrayList<>()).getCloneShiftedForRegExp(".*", 10.0d);

    // Assert
    AnalyticModel model = actualCloneShiftedForRegExp.getModel();
    assertTrue(model instanceof AnalyticModelFromCurvesAndVols);
    assertNull(((AnalyticModelFromCurvesAndVols) model).getReferenceDate());
    assertEquals(2, actualCloneShiftedForRegExp.getLastNumberOfInterations());
    assertTrue(model.getCurves().isEmpty());
    assertTrue(model.getVolatilitySurfaces().isEmpty());
    assertEquals(Double.NaN, actualCloneShiftedForRegExp.getLastAccuracy(), 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CalibratedCurves#getLastAccuracy()}
   *   <li>{@link CalibratedCurves#getLastNumberOfInterations()}
   *   <li>{@link CalibratedCurves#getModel()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double CalibratedCurves.getLastAccuracy()",
    "int CalibratedCurves.getLastNumberOfInterations()",
    "AnalyticModel CalibratedCurves.getModel()"
  })
  public void testGettersAndSetters() throws CloneNotSupportedException, SolverException {
    // Arrange
    CalibratedCurves calibratedCurves = new CalibratedCurves(new ArrayList<>());

    // Act
    double actualLastAccuracy = calibratedCurves.getLastAccuracy();
    int actualLastNumberOfInterations = calibratedCurves.getLastNumberOfInterations();

    // Assert
    assertTrue(calibratedCurves.getModel() instanceof AnalyticModelFromCurvesAndVols);
    assertEquals(2, actualLastNumberOfInterations);
    assertEquals(Double.NaN, actualLastAccuracy, 0.0);
  }

  /**
   * Test {@link CalibratedCurves#getCalibrationProductForSymbol(String)}.
   *
   * <p>Method under test: {@link CalibratedCurves#getCalibrationProductForSymbol(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticProduct CalibratedCurves.getCalibrationProductForSymbol(String)"})
  public void testGetCalibrationProductForSymbol()
      throws CloneNotSupportedException, SolverException {
    // Arrange, Act and Assert
    assertNull(new CalibratedCurves(new ArrayList<>()).getCalibrationProductForSymbol("Symbol"));
  }
}
