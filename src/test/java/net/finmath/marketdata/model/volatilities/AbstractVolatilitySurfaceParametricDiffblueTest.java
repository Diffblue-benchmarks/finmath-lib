package net.finmath.marketdata.model.volatilities;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import net.finmath.exception.CalculationException;
import net.finmath.marketdata.calibration.ParameterTransformation;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.volatilities.VolatilitySurface.QuotingConvention;
import net.finmath.marketdata.products.AnalyticProduct;
import net.finmath.optimizer.OptimizerFactory;
import net.finmath.optimizer.SolverException;
import net.finmath.singleswaprate.model.AnalyticModelWithVolatilityCubes;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class AbstractVolatilitySurfaceParametricDiffblueTest {
  /**
   * Test {@link AbstractVolatilitySurfaceParametric#getCloneCalibrated(AnalyticModel, Vector, List,
   * Map)} with {@code calibrationModel}, {@code calibrationProducts}, {@code
   * calibrationTargetValues}, {@code calibrationParameters}.
   *
   * <p>Method under test: {@link
   * AbstractVolatilitySurfaceParametric#getCloneCalibrated(AnalyticModel, Vector, List, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractVolatilitySurfaceParametric AbstractVolatilitySurfaceParametric.getCloneCalibrated(AnalyticModel, Vector, List, Map)"
  })
  public void
      testGetCloneCalibratedWithCalibrationModelCalibrationProductsCalibrationTargetValuesCalibrationParameters()
          throws CalculationException, SolverException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric("Name", referenceDate, 10.0d, 10.0d, 10.0d, 10.0d);
    AnalyticModelFromCurvesAndVols calibrationModel = new AnalyticModelFromCurvesAndVols();
    Vector<AnalyticProduct> calibrationProducts = new Vector<>();
    ArrayList<Double> calibrationTargetValues = new ArrayList<>();

    // Act
    AbstractVolatilitySurfaceParametric actualCloneCalibrated =
        capletVolatilitiesParametric.getCloneCalibrated(
            calibrationModel, calibrationProducts, calibrationTargetValues, new HashMap<>());

    // Assert
    assertTrue(actualCloneCalibrated instanceof CapletVolatilitiesParametric);
    LocalDate referenceDate2 = actualCloneCalibrated.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualCloneCalibrated.getName());
    assertNull(actualCloneCalibrated.getDiscountCurve());
    assertNull(actualCloneCalibrated.getForwardCurve());
    assertNull(actualCloneCalibrated.getDaycountConvention());
    assertEquals(
        QuotingConvention.VOLATILITYLOGNORMAL, actualCloneCalibrated.getQuotingConvention());
    assertSame(referenceDate, referenceDate2);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d}, actualCloneCalibrated.getParameter(), 0.0);
  }

  /**
   * Test {@link AbstractVolatilitySurfaceParametric#getCloneCalibrated(AnalyticModel, Vector, List,
   * Map)} with {@code calibrationModel}, {@code calibrationProducts}, {@code
   * calibrationTargetValues}, {@code calibrationParameters}.
   *
   * <p>Method under test: {@link
   * AbstractVolatilitySurfaceParametric#getCloneCalibrated(AnalyticModel, Vector, List, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractVolatilitySurfaceParametric AbstractVolatilitySurfaceParametric.getCloneCalibrated(AnalyticModel, Vector, List, Map)"
  })
  public void
      testGetCloneCalibratedWithCalibrationModelCalibrationProductsCalibrationTargetValuesCalibrationParameters2()
          throws CalculationException, SolverException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric("Name", referenceDate, 10.0d, 10.0d, 10.0d, 10.0d);
    AnalyticModelFromCurvesAndVols calibrationModel = new AnalyticModelFromCurvesAndVols();
    Vector<AnalyticProduct> calibrationProducts = new Vector<>();

    // Act
    AbstractVolatilitySurfaceParametric actualCloneCalibrated =
        capletVolatilitiesParametric.getCloneCalibrated(
            calibrationModel, calibrationProducts, new ArrayList<>(), null);

    // Assert
    assertTrue(actualCloneCalibrated instanceof CapletVolatilitiesParametric);
    LocalDate referenceDate2 = actualCloneCalibrated.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualCloneCalibrated.getName());
    assertNull(actualCloneCalibrated.getDiscountCurve());
    assertNull(actualCloneCalibrated.getForwardCurve());
    assertNull(actualCloneCalibrated.getDaycountConvention());
    assertEquals(
        QuotingConvention.VOLATILITYLOGNORMAL, actualCloneCalibrated.getQuotingConvention());
    assertSame(referenceDate, referenceDate2);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d}, actualCloneCalibrated.getParameter(), 0.0);
  }

  /**
   * Test {@link AbstractVolatilitySurfaceParametric#getCloneCalibrated(AnalyticModel, Vector, List,
   * Map)} with {@code calibrationModel}, {@code calibrationProducts}, {@code
   * calibrationTargetValues}, {@code calibrationParameters}.
   *
   * <p>Method under test: {@link
   * AbstractVolatilitySurfaceParametric#getCloneCalibrated(AnalyticModel, Vector, List, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractVolatilitySurfaceParametric AbstractVolatilitySurfaceParametric.getCloneCalibrated(AnalyticModel, Vector, List, Map)"
  })
  public void
      testGetCloneCalibratedWithCalibrationModelCalibrationProductsCalibrationTargetValuesCalibrationParameters3()
          throws CalculationException, SolverException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric("Name", referenceDate, 10.0d, 10.0d, 10.0d, 10.0d);
    AnalyticModelFromCurvesAndVols calibrationModel = new AnalyticModelFromCurvesAndVols();
    Vector<AnalyticProduct> calibrationProducts = new Vector<>();
    ArrayList<Double> calibrationTargetValues = new ArrayList<>();

    HashMap<String, Object> calibrationParameters = new HashMap<>();
    calibrationParameters.put("maxIterations", null);
    calibrationParameters.put("accuracy", null);
    calibrationParameters.put("evaluationTime", 10.0d);

    // Act
    AbstractVolatilitySurfaceParametric actualCloneCalibrated =
        capletVolatilitiesParametric.getCloneCalibrated(
            calibrationModel, calibrationProducts, calibrationTargetValues, calibrationParameters);

    // Assert
    assertTrue(actualCloneCalibrated instanceof CapletVolatilitiesParametric);
    LocalDate referenceDate2 = actualCloneCalibrated.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualCloneCalibrated.getName());
    assertNull(actualCloneCalibrated.getDiscountCurve());
    assertNull(actualCloneCalibrated.getForwardCurve());
    assertNull(actualCloneCalibrated.getDaycountConvention());
    assertEquals(
        QuotingConvention.VOLATILITYLOGNORMAL, actualCloneCalibrated.getQuotingConvention());
    assertSame(referenceDate, referenceDate2);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d}, actualCloneCalibrated.getParameter(), 0.0);
  }

  /**
   * Test {@link AbstractVolatilitySurfaceParametric#getCloneCalibrated(AnalyticModel, Vector, List,
   * Map)} with {@code calibrationModel}, {@code calibrationProducts}, {@code
   * calibrationTargetValues}, {@code calibrationParameters}.
   *
   * <p>Method under test: {@link
   * AbstractVolatilitySurfaceParametric#getCloneCalibrated(AnalyticModel, Vector, List, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractVolatilitySurfaceParametric AbstractVolatilitySurfaceParametric.getCloneCalibrated(AnalyticModel, Vector, List, Map)"
  })
  public void
      testGetCloneCalibratedWithCalibrationModelCalibrationProductsCalibrationTargetValuesCalibrationParameters4()
          throws CalculationException, SolverException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric("Name", referenceDate, 10.0d, 10.0d, 10.0d, 10.0d);
    AnalyticModelFromCurvesAndVols calibrationModel = new AnalyticModelFromCurvesAndVols();
    Vector<AnalyticProduct> calibrationProducts = new Vector<>();
    ArrayList<Double> calibrationTargetValues = new ArrayList<>();

    HashMap<String, Object> calibrationParameters = new HashMap<>();
    calibrationParameters.put("maxIterations", null);
    calibrationParameters.put("accuracy", 10.0d);
    calibrationParameters.put("evaluationTime", null);

    // Act
    AbstractVolatilitySurfaceParametric actualCloneCalibrated =
        capletVolatilitiesParametric.getCloneCalibrated(
            calibrationModel, calibrationProducts, calibrationTargetValues, calibrationParameters);

    // Assert
    assertTrue(actualCloneCalibrated instanceof CapletVolatilitiesParametric);
    LocalDate referenceDate2 = actualCloneCalibrated.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualCloneCalibrated.getName());
    assertNull(actualCloneCalibrated.getDiscountCurve());
    assertNull(actualCloneCalibrated.getForwardCurve());
    assertNull(actualCloneCalibrated.getDaycountConvention());
    assertEquals(
        QuotingConvention.VOLATILITYLOGNORMAL, actualCloneCalibrated.getQuotingConvention());
    assertSame(referenceDate, referenceDate2);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d}, actualCloneCalibrated.getParameter(), 0.0);
  }

  /**
   * Test {@link AbstractVolatilitySurfaceParametric#getCloneCalibrated(AnalyticModel, Vector, List,
   * Map)} with {@code calibrationModel}, {@code calibrationProducts}, {@code
   * calibrationTargetValues}, {@code calibrationParameters}.
   *
   * <p>Method under test: {@link
   * AbstractVolatilitySurfaceParametric#getCloneCalibrated(AnalyticModel, Vector, List, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractVolatilitySurfaceParametric AbstractVolatilitySurfaceParametric.getCloneCalibrated(AnalyticModel, Vector, List, Map)"
  })
  public void
      testGetCloneCalibratedWithCalibrationModelCalibrationProductsCalibrationTargetValuesCalibrationParameters5()
          throws CalculationException, SolverException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric("Name", referenceDate, 10.0d, 10.0d, 10.0d, 10.0d);
    AnalyticModelFromCurvesAndVols calibrationModel = new AnalyticModelFromCurvesAndVols();
    Vector<AnalyticProduct> calibrationProducts = new Vector<>();
    ArrayList<Double> calibrationTargetValues = new ArrayList<>();

    HashMap<String, Object> calibrationParameters = new HashMap<>();
    calibrationParameters.put("maxIterations", 1);
    calibrationParameters.put("accuracy", null);
    calibrationParameters.put("evaluationTime", null);

    // Act
    AbstractVolatilitySurfaceParametric actualCloneCalibrated =
        capletVolatilitiesParametric.getCloneCalibrated(
            calibrationModel, calibrationProducts, calibrationTargetValues, calibrationParameters);

    // Assert
    assertTrue(actualCloneCalibrated instanceof CapletVolatilitiesParametric);
    LocalDate referenceDate2 = actualCloneCalibrated.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualCloneCalibrated.getName());
    assertNull(actualCloneCalibrated.getDiscountCurve());
    assertNull(actualCloneCalibrated.getForwardCurve());
    assertNull(actualCloneCalibrated.getDaycountConvention());
    assertEquals(
        QuotingConvention.VOLATILITYLOGNORMAL, actualCloneCalibrated.getQuotingConvention());
    assertSame(referenceDate, referenceDate2);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d}, actualCloneCalibrated.getParameter(), 0.0);
  }

  /**
   * Test {@link AbstractVolatilitySurfaceParametric#getCloneCalibrated(AnalyticModel, Vector, List,
   * Map, ParameterTransformation)} with {@code calibrationModel}, {@code calibrationProducts},
   * {@code calibrationTargetValues}, {@code calibrationParameters}, {@code
   * parameterTransformation}.
   *
   * <p>Method under test: {@link
   * AbstractVolatilitySurfaceParametric#getCloneCalibrated(AnalyticModel, Vector, List, Map,
   * ParameterTransformation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractVolatilitySurfaceParametric AbstractVolatilitySurfaceParametric.getCloneCalibrated(AnalyticModel, Vector, List, Map, ParameterTransformation)"
  })
  public void
      testGetCloneCalibratedWithCalibrationModelCalibrationProductsCalibrationTargetValuesCalibrationParametersParameterTransformation()
          throws CalculationException, SolverException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric("Name", referenceDate, 10.0d, 10.0d, 10.0d, 10.0d);
    AnalyticModelWithVolatilityCubes calibrationModel = new AnalyticModelWithVolatilityCubes();
    Vector<AnalyticProduct> calibrationProducts = new Vector<>();
    ArrayList<Double> calibrationTargetValues = new ArrayList<>();
    HashMap<String, Object> calibrationParameters = new HashMap<>();

    ParameterTransformation parameterTransformation = mock(ParameterTransformation.class);
    when(parameterTransformation.getParameter(Mockito.<double[]>any()))
        .thenReturn(new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    when(parameterTransformation.getSolverParameter(Mockito.<double[]>any()))
        .thenReturn(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    AbstractVolatilitySurfaceParametric actualCloneCalibrated =
        capletVolatilitiesParametric.getCloneCalibrated(
            calibrationModel,
            calibrationProducts,
            calibrationTargetValues,
            calibrationParameters,
            parameterTransformation);

    // Assert
    verify(parameterTransformation, atLeast(1)).getParameter(Mockito.<double[]>any());
    verify(parameterTransformation, atLeast(1)).getSolverParameter(Mockito.<double[]>any());
    assertTrue(actualCloneCalibrated instanceof CapletVolatilitiesParametric);
    LocalDate referenceDate2 = actualCloneCalibrated.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualCloneCalibrated.getName());
    assertNull(actualCloneCalibrated.getDiscountCurve());
    assertNull(actualCloneCalibrated.getForwardCurve());
    assertNull(actualCloneCalibrated.getDaycountConvention());
    assertEquals(
        QuotingConvention.VOLATILITYLOGNORMAL, actualCloneCalibrated.getQuotingConvention());
    assertSame(referenceDate, referenceDate2);
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d, 0.5d}, actualCloneCalibrated.getParameter(), 0.0);
  }

  /**
   * Test {@link AbstractVolatilitySurfaceParametric#getCloneCalibrated(AnalyticModel, Vector, List,
   * Map, ParameterTransformation, OptimizerFactory)} with {@code calibrationModel}, {@code
   * calibrationProducts}, {@code calibrationTargetValues}, {@code calibrationParameters}, {@code
   * parameterTransformation}, {@code optimizerFactory}.
   *
   * <p>Method under test: {@link
   * AbstractVolatilitySurfaceParametric#getCloneCalibrated(AnalyticModel, Vector, List, Map,
   * ParameterTransformation, OptimizerFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AbstractVolatilitySurfaceParametric AbstractVolatilitySurfaceParametric.getCloneCalibrated(AnalyticModel, Vector, List, Map, ParameterTransformation, OptimizerFactory)"
  })
  public void
      testGetCloneCalibratedWithCalibrationModelCalibrationProductsCalibrationTargetValuesCalibrationParametersParameterTransformationOptimizerFactory()
          throws SolverException {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    CapletVolatilitiesParametric capletVolatilitiesParametric =
        new CapletVolatilitiesParametric("Name", referenceDate, 10.0d, 10.0d, 10.0d, 10.0d);
    AnalyticModelWithVolatilityCubes calibrationModel = new AnalyticModelWithVolatilityCubes();
    Vector<AnalyticProduct> calibrationProducts = new Vector<>();
    ArrayList<Double> calibrationTargetValues = new ArrayList<>();
    HashMap<String, Object> calibrationParameters = new HashMap<>();

    ParameterTransformation parameterTransformation = mock(ParameterTransformation.class);
    when(parameterTransformation.getParameter(Mockito.<double[]>any()))
        .thenReturn(new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    when(parameterTransformation.getSolverParameter(Mockito.<double[]>any()))
        .thenReturn(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    AbstractVolatilitySurfaceParametric actualCloneCalibrated =
        capletVolatilitiesParametric.getCloneCalibrated(
            calibrationModel,
            calibrationProducts,
            calibrationTargetValues,
            calibrationParameters,
            parameterTransformation,
            null);

    // Assert
    verify(parameterTransformation, atLeast(1)).getParameter(Mockito.<double[]>any());
    verify(parameterTransformation, atLeast(1)).getSolverParameter(Mockito.<double[]>any());
    assertTrue(actualCloneCalibrated instanceof CapletVolatilitiesParametric);
    LocalDate referenceDate2 = actualCloneCalibrated.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualCloneCalibrated.getName());
    assertNull(actualCloneCalibrated.getDiscountCurve());
    assertNull(actualCloneCalibrated.getForwardCurve());
    assertNull(actualCloneCalibrated.getDaycountConvention());
    assertEquals(
        QuotingConvention.VOLATILITYLOGNORMAL, actualCloneCalibrated.getQuotingConvention());
    assertSame(referenceDate, referenceDate2);
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d, 0.5d}, actualCloneCalibrated.getParameter(), 0.0);
  }
}
