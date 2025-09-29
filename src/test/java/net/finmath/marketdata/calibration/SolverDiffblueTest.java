package net.finmath.marketdata.calibration;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata.model.curves.Curve;
import net.finmath.marketdata.model.curves.CurveInterpolation;
import net.finmath.marketdata.model.curves.CurveInterpolation.ExtrapolationMethod;
import net.finmath.marketdata.model.curves.CurveInterpolation.InterpolationEntity;
import net.finmath.marketdata.model.curves.CurveInterpolation.InterpolationMethod;
import net.finmath.marketdata.model.curves.DiscountCurveNelsonSiegelSvensson;
import net.finmath.marketdata.products.AnalyticProduct;
import net.finmath.marketdata.products.Cashflow;
import net.finmath.marketdata.products.Portfolio;
import net.finmath.optimizer.OptimizerFactory;
import net.finmath.optimizer.OptimizerFactoryCMAES;
import net.finmath.optimizer.SolverException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SolverDiffblueTest {
  /**
   * Test {@link Solver#Solver(AnalyticModel, Vector)}.
   *
   * <p>Method under test: {@link Solver#Solver(AnalyticModel, Vector)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Solver.<init>(AnalyticModel, Vector)"})
  public void testNewSolver() {
    // Arrange
    AnalyticModelFromCurvesAndVols model = new AnalyticModelFromCurvesAndVols();

    Vector<AnalyticProduct> calibrationProducts = new Vector<>();
    Cashflow cashflow =
        new Cashflow("GBP", Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, true, "3");
    calibrationProducts.add(cashflow);

    // Act
    Solver actualSolver = new Solver(model, calibrationProducts);

    // Assert
    assertEquals(0, actualSolver.getIterations());
    assertEquals(Double.POSITIVE_INFINITY, actualSolver.getAccuracy(), 0.0);
  }

  /**
   * Test {@link Solver#Solver(AnalyticModel, Vector)}.
   *
   * <p>Method under test: {@link Solver#Solver(AnalyticModel, Vector)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Solver.<init>(AnalyticModel, Vector)"})
  public void testNewSolver2() {
    // Arrange
    AnalyticModelFromCurvesAndVols model = new AnalyticModelFromCurvesAndVols();

    Vector<AnalyticProduct> calibrationProducts = new Vector<>();
    Cashflow cashflow =
        new Cashflow("GBP", Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, true, "3");
    calibrationProducts.add(cashflow);
    Cashflow cashflow2 =
        new Cashflow("GBP", Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, true, "3");
    calibrationProducts.add(cashflow2);

    // Act
    Solver actualSolver = new Solver(model, calibrationProducts);

    // Assert
    assertEquals(0, actualSolver.getIterations());
    assertEquals(Double.POSITIVE_INFINITY, actualSolver.getAccuracy(), 0.0);
  }

  /**
   * Test {@link Solver#Solver(AnalyticModel, Vector, double, double)}.
   *
   * <p>Method under test: {@link Solver#Solver(AnalyticModel, Vector, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Solver.<init>(AnalyticModel, Vector, double, double)"})
  public void testNewSolver3() {
    // Arrange
    AnalyticModelFromCurvesAndVols model = new AnalyticModelFromCurvesAndVols();

    Vector<AnalyticProduct> calibrationProducts = new Vector<>();
    Cashflow cashflow =
        new Cashflow("GBP", Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, true, "3");
    calibrationProducts.add(cashflow);

    // Act
    Solver actualSolver = new Solver(model, calibrationProducts, 10.0d, 10.0d);

    // Assert
    assertEquals(0, actualSolver.getIterations());
    assertEquals(Double.POSITIVE_INFINITY, actualSolver.getAccuracy(), 0.0);
  }

  /**
   * Test {@link Solver#Solver(AnalyticModel, Vector, double, double)}.
   *
   * <p>Method under test: {@link Solver#Solver(AnalyticModel, Vector, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Solver.<init>(AnalyticModel, Vector, double, double)"})
  public void testNewSolver4() {
    // Arrange
    AnalyticModelFromCurvesAndVols model = new AnalyticModelFromCurvesAndVols();

    Vector<AnalyticProduct> calibrationProducts = new Vector<>();
    Cashflow cashflow =
        new Cashflow("GBP", Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, true, "3");
    calibrationProducts.add(cashflow);
    Cashflow cashflow2 =
        new Cashflow("GBP", Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, true, "3");
    calibrationProducts.add(cashflow2);

    // Act
    Solver actualSolver = new Solver(model, calibrationProducts, 10.0d, 10.0d);

    // Assert
    assertEquals(0, actualSolver.getIterations());
    assertEquals(Double.POSITIVE_INFINITY, actualSolver.getAccuracy(), 0.0);
  }

  /**
   * Test {@link Solver#Solver(AnalyticModel, Vector, List, double, double)}.
   *
   * <p>Method under test: {@link Solver#Solver(AnalyticModel, Vector, List, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Solver.<init>(AnalyticModel, Vector, List, double, double)"})
  public void testNewSolver5() {
    // Arrange
    AnalyticModelFromCurvesAndVols model = new AnalyticModelFromCurvesAndVols();

    Vector<AnalyticProduct> calibrationProducts = new Vector<>();
    Cashflow cashflow =
        new Cashflow("GBP", Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, true, "3");
    calibrationProducts.add(cashflow);

    // Act
    Solver actualSolver = new Solver(model, calibrationProducts, new ArrayList<>(), 10.0d, 10.0d);

    // Assert
    assertEquals(0, actualSolver.getIterations());
    assertEquals(Double.POSITIVE_INFINITY, actualSolver.getAccuracy(), 0.0);
  }

  /**
   * Test {@link Solver#Solver(AnalyticModel, Vector, List, double, double)}.
   *
   * <p>Method under test: {@link Solver#Solver(AnalyticModel, Vector, List, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Solver.<init>(AnalyticModel, Vector, List, double, double)"})
  public void testNewSolver6() {
    // Arrange
    AnalyticModelFromCurvesAndVols model = new AnalyticModelFromCurvesAndVols();

    Vector<AnalyticProduct> calibrationProducts = new Vector<>();
    Cashflow cashflow =
        new Cashflow("GBP", Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, true, "3");
    calibrationProducts.add(cashflow);
    Cashflow cashflow2 =
        new Cashflow("GBP", Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, true, "3");
    calibrationProducts.add(cashflow2);

    // Act
    Solver actualSolver = new Solver(model, calibrationProducts, new ArrayList<>(), 10.0d, 10.0d);

    // Assert
    assertEquals(0, actualSolver.getIterations());
    assertEquals(Double.POSITIVE_INFINITY, actualSolver.getAccuracy(), 0.0);
  }

  /**
   * Test {@link Solver#Solver(AnalyticModel, Vector, List, ParameterTransformation, double,
   * double)}.
   *
   * <p>Method under test: {@link Solver#Solver(AnalyticModel, Vector, List,
   * ParameterTransformation, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Solver.<init>(AnalyticModel, Vector, List, ParameterTransformation, double, double)"
  })
  public void testNewSolver7() {
    // Arrange
    AnalyticModelFromCurvesAndVols model = new AnalyticModelFromCurvesAndVols();

    Vector<AnalyticProduct> calibrationProducts = new Vector<>();
    Cashflow cashflow =
        new Cashflow("GBP", Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, true, "3");
    calibrationProducts.add(cashflow);

    // Act
    Solver actualSolver =
        new Solver(
            model,
            calibrationProducts,
            new ArrayList<>(),
            mock(ParameterTransformation.class),
            10.0d,
            10.0d);

    // Assert
    assertEquals(0, actualSolver.getIterations());
    assertEquals(Double.POSITIVE_INFINITY, actualSolver.getAccuracy(), 0.0);
  }

  /**
   * Test {@link Solver#Solver(AnalyticModel, Vector, List, ParameterTransformation, double,
   * double)}.
   *
   * <p>Method under test: {@link Solver#Solver(AnalyticModel, Vector, List,
   * ParameterTransformation, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Solver.<init>(AnalyticModel, Vector, List, ParameterTransformation, double, double)"
  })
  public void testNewSolver8() {
    // Arrange
    AnalyticModelFromCurvesAndVols model = new AnalyticModelFromCurvesAndVols();

    Vector<AnalyticProduct> calibrationProducts = new Vector<>();
    Cashflow cashflow =
        new Cashflow("GBP", Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, true, "3");
    calibrationProducts.add(cashflow);
    Cashflow cashflow2 =
        new Cashflow("GBP", Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, true, "3");
    calibrationProducts.add(cashflow2);

    // Act
    Solver actualSolver =
        new Solver(
            model,
            calibrationProducts,
            new ArrayList<>(),
            mock(ParameterTransformation.class),
            10.0d,
            10.0d);

    // Assert
    assertEquals(0, actualSolver.getIterations());
    assertEquals(Double.POSITIVE_INFINITY, actualSolver.getAccuracy(), 0.0);
  }

  /**
   * Test {@link Solver#Solver(AnalyticModel, Vector, List, ParameterTransformation, double,
   * OptimizerFactory)}.
   *
   * <p>Method under test: {@link Solver#Solver(AnalyticModel, Vector, List,
   * ParameterTransformation, double, OptimizerFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Solver.<init>(AnalyticModel, Vector, List, ParameterTransformation, double, OptimizerFactory)"
  })
  public void testNewSolver9() {
    // Arrange
    AnalyticModelFromCurvesAndVols model = new AnalyticModelFromCurvesAndVols();

    Vector<AnalyticProduct> calibrationProducts = new Vector<>();
    Cashflow cashflow =
        new Cashflow("GBP", Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, true, "3");
    calibrationProducts.add(cashflow);
    ArrayList<Double> calibrationTargetValues = new ArrayList<>();
    ParameterTransformation parameterTransformation = mock(ParameterTransformation.class);

    // Act
    Solver actualSolver =
        new Solver(
            model,
            calibrationProducts,
            calibrationTargetValues,
            parameterTransformation,
            10.0d,
            new OptimizerFactoryCMAES(10.0d, 3));

    // Assert
    assertEquals(0, actualSolver.getIterations());
    assertEquals(Double.POSITIVE_INFINITY, actualSolver.getAccuracy(), 0.0);
  }

  /**
   * Test {@link Solver#Solver(AnalyticModel, Vector, List, ParameterTransformation, double,
   * OptimizerFactory)}.
   *
   * <p>Method under test: {@link Solver#Solver(AnalyticModel, Vector, List,
   * ParameterTransformation, double, OptimizerFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Solver.<init>(AnalyticModel, Vector, List, ParameterTransformation, double, OptimizerFactory)"
  })
  public void testNewSolver10() {
    // Arrange
    AnalyticModelFromCurvesAndVols model = new AnalyticModelFromCurvesAndVols();

    Vector<AnalyticProduct> calibrationProducts = new Vector<>();
    Cashflow cashflow =
        new Cashflow("GBP", Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, true, "3");
    calibrationProducts.add(cashflow);
    Cashflow cashflow2 =
        new Cashflow("GBP", Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY, true, "3");
    calibrationProducts.add(cashflow2);
    ArrayList<Double> calibrationTargetValues = new ArrayList<>();
    ParameterTransformation parameterTransformation = mock(ParameterTransformation.class);

    // Act
    Solver actualSolver =
        new Solver(
            model,
            calibrationProducts,
            calibrationTargetValues,
            parameterTransformation,
            10.0d,
            new OptimizerFactoryCMAES(10.0d, 3));

    // Assert
    assertEquals(0, actualSolver.getIterations());
    assertEquals(Double.POSITIVE_INFINITY, actualSolver.getAccuracy(), 0.0);
  }

  /**
   * Test {@link Solver#Solver(AnalyticModel, Vector, List, double, double)}.
   *
   * <ul>
   *   <li>Given {@link Double#POSITIVE_INFINITY}.
   *   <li>When {@link ArrayList#ArrayList()} add {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link Solver#Solver(AnalyticModel, Vector, List, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Solver.<init>(AnalyticModel, Vector, List, double, double)"})
  public void testNewSolver_givenPositive_infinity_whenArrayListAddPositive_infinity() {
    // Arrange
    AnalyticModelFromCurvesAndVols model = new AnalyticModelFromCurvesAndVols();
    Vector<AnalyticProduct> calibrationProducts = new Vector<>();

    ArrayList<Double> calibrationTargetValues = new ArrayList<>();
    calibrationTargetValues.add(Double.POSITIVE_INFINITY);

    // Act
    Solver actualSolver =
        new Solver(model, calibrationProducts, calibrationTargetValues, 10.0d, 10.0d);

    // Assert
    assertEquals(0, actualSolver.getIterations());
    assertEquals(Double.POSITIVE_INFINITY, actualSolver.getAccuracy(), 0.0);
  }

  /**
   * Test {@link Solver#Solver(AnalyticModel, Vector, List, ParameterTransformation, double,
   * double)}.
   *
   * <ul>
   *   <li>Given {@link Double#POSITIVE_INFINITY}.
   *   <li>When {@link ArrayList#ArrayList()} add {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link Solver#Solver(AnalyticModel, Vector, List,
   * ParameterTransformation, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Solver.<init>(AnalyticModel, Vector, List, ParameterTransformation, double, double)"
  })
  public void testNewSolver_givenPositive_infinity_whenArrayListAddPositive_infinity2() {
    // Arrange
    AnalyticModelFromCurvesAndVols model = new AnalyticModelFromCurvesAndVols();
    Vector<AnalyticProduct> calibrationProducts = new Vector<>();

    ArrayList<Double> calibrationTargetValues = new ArrayList<>();
    calibrationTargetValues.add(Double.POSITIVE_INFINITY);

    // Act
    Solver actualSolver =
        new Solver(
            model,
            calibrationProducts,
            calibrationTargetValues,
            mock(ParameterTransformation.class),
            10.0d,
            10.0d);

    // Assert
    assertEquals(0, actualSolver.getIterations());
    assertEquals(Double.POSITIVE_INFINITY, actualSolver.getAccuracy(), 0.0);
  }

  /**
   * Test {@link Solver#Solver(AnalyticModel, Vector, List, ParameterTransformation, double,
   * OptimizerFactory)}.
   *
   * <ul>
   *   <li>Given {@link Double#POSITIVE_INFINITY}.
   *   <li>When {@link ArrayList#ArrayList()} add {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link Solver#Solver(AnalyticModel, Vector, List,
   * ParameterTransformation, double, OptimizerFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Solver.<init>(AnalyticModel, Vector, List, ParameterTransformation, double, OptimizerFactory)"
  })
  public void testNewSolver_givenPositive_infinity_whenArrayListAddPositive_infinity3() {
    // Arrange
    AnalyticModelFromCurvesAndVols model = new AnalyticModelFromCurvesAndVols();
    Vector<AnalyticProduct> calibrationProducts = new Vector<>();

    ArrayList<Double> calibrationTargetValues = new ArrayList<>();
    calibrationTargetValues.add(Double.POSITIVE_INFINITY);
    ParameterTransformation parameterTransformation = mock(ParameterTransformation.class);

    // Act
    Solver actualSolver =
        new Solver(
            model,
            calibrationProducts,
            calibrationTargetValues,
            parameterTransformation,
            10.0d,
            new OptimizerFactoryCMAES(10.0d, 3));

    // Assert
    assertEquals(0, actualSolver.getIterations());
    assertEquals(Double.POSITIVE_INFINITY, actualSolver.getAccuracy(), 0.0);
  }

  /**
   * Test {@link Solver#Solver(AnalyticModel, Vector, List, double, double)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>When {@link ArrayList#ArrayList()} add ten.
   * </ul>
   *
   * <p>Method under test: {@link Solver#Solver(AnalyticModel, Vector, List, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Solver.<init>(AnalyticModel, Vector, List, double, double)"})
  public void testNewSolver_givenTen_whenArrayListAddTen() {
    // Arrange
    AnalyticModelFromCurvesAndVols model = new AnalyticModelFromCurvesAndVols();
    Vector<AnalyticProduct> calibrationProducts = new Vector<>();

    ArrayList<Double> calibrationTargetValues = new ArrayList<>();
    calibrationTargetValues.add(10.0d);
    calibrationTargetValues.add(Double.POSITIVE_INFINITY);

    // Act
    Solver actualSolver =
        new Solver(model, calibrationProducts, calibrationTargetValues, 10.0d, 10.0d);

    // Assert
    assertEquals(0, actualSolver.getIterations());
    assertEquals(Double.POSITIVE_INFINITY, actualSolver.getAccuracy(), 0.0);
  }

  /**
   * Test {@link Solver#Solver(AnalyticModel, Vector, List, ParameterTransformation, double,
   * double)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>When {@link ArrayList#ArrayList()} add ten.
   * </ul>
   *
   * <p>Method under test: {@link Solver#Solver(AnalyticModel, Vector, List,
   * ParameterTransformation, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Solver.<init>(AnalyticModel, Vector, List, ParameterTransformation, double, double)"
  })
  public void testNewSolver_givenTen_whenArrayListAddTen2() {
    // Arrange
    AnalyticModelFromCurvesAndVols model = new AnalyticModelFromCurvesAndVols();
    Vector<AnalyticProduct> calibrationProducts = new Vector<>();

    ArrayList<Double> calibrationTargetValues = new ArrayList<>();
    calibrationTargetValues.add(10.0d);
    calibrationTargetValues.add(Double.POSITIVE_INFINITY);

    // Act
    Solver actualSolver =
        new Solver(
            model,
            calibrationProducts,
            calibrationTargetValues,
            mock(ParameterTransformation.class),
            10.0d,
            10.0d);

    // Assert
    assertEquals(0, actualSolver.getIterations());
    assertEquals(Double.POSITIVE_INFINITY, actualSolver.getAccuracy(), 0.0);
  }

  /**
   * Test {@link Solver#Solver(AnalyticModel, Vector, List, ParameterTransformation, double,
   * OptimizerFactory)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>When {@link ArrayList#ArrayList()} add ten.
   * </ul>
   *
   * <p>Method under test: {@link Solver#Solver(AnalyticModel, Vector, List,
   * ParameterTransformation, double, OptimizerFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Solver.<init>(AnalyticModel, Vector, List, ParameterTransformation, double, OptimizerFactory)"
  })
  public void testNewSolver_givenTen_whenArrayListAddTen3() {
    // Arrange
    AnalyticModelFromCurvesAndVols model = new AnalyticModelFromCurvesAndVols();
    Vector<AnalyticProduct> calibrationProducts = new Vector<>();

    ArrayList<Double> calibrationTargetValues = new ArrayList<>();
    calibrationTargetValues.add(10.0d);
    calibrationTargetValues.add(Double.POSITIVE_INFINITY);
    ParameterTransformation parameterTransformation = mock(ParameterTransformation.class);

    // Act
    Solver actualSolver =
        new Solver(
            model,
            calibrationProducts,
            calibrationTargetValues,
            parameterTransformation,
            10.0d,
            new OptimizerFactoryCMAES(10.0d, 3));

    // Assert
    assertEquals(0, actualSolver.getIterations());
    assertEquals(Double.POSITIVE_INFINITY, actualSolver.getAccuracy(), 0.0);
  }

  /**
   * Test {@link Solver#Solver(AnalyticModel, Vector)}.
   *
   * <ul>
   *   <li>When {@link Vector#Vector()}.
   * </ul>
   *
   * <p>Method under test: {@link Solver#Solver(AnalyticModel, Vector)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Solver.<init>(AnalyticModel, Vector)"})
  public void testNewSolver_whenVector() {
    // Arrange
    AnalyticModelFromCurvesAndVols model = new AnalyticModelFromCurvesAndVols();

    // Act
    Solver actualSolver = new Solver(model, new Vector<>());

    // Assert
    assertEquals(0, actualSolver.getIterations());
    assertEquals(Double.POSITIVE_INFINITY, actualSolver.getAccuracy(), 0.0);
  }

  /**
   * Test {@link Solver#Solver(AnalyticModel, Vector, double, double)}.
   *
   * <ul>
   *   <li>When {@link Vector#Vector()}.
   * </ul>
   *
   * <p>Method under test: {@link Solver#Solver(AnalyticModel, Vector, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Solver.<init>(AnalyticModel, Vector, double, double)"})
  public void testNewSolver_whenVector2() {
    // Arrange
    AnalyticModelFromCurvesAndVols model = new AnalyticModelFromCurvesAndVols();

    // Act
    Solver actualSolver = new Solver(model, new Vector<>(), 10.0d, 10.0d);

    // Assert
    assertEquals(0, actualSolver.getIterations());
    assertEquals(Double.POSITIVE_INFINITY, actualSolver.getAccuracy(), 0.0);
  }

  /**
   * Test {@link Solver#Solver(AnalyticModel, Vector, List, double, double)}.
   *
   * <ul>
   *   <li>When {@link Vector#Vector()}.
   * </ul>
   *
   * <p>Method under test: {@link Solver#Solver(AnalyticModel, Vector, List, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Solver.<init>(AnalyticModel, Vector, List, double, double)"})
  public void testNewSolver_whenVector3() {
    // Arrange
    AnalyticModelFromCurvesAndVols model = new AnalyticModelFromCurvesAndVols();
    Vector<AnalyticProduct> calibrationProducts = new Vector<>();

    // Act
    Solver actualSolver = new Solver(model, calibrationProducts, new ArrayList<>(), 10.0d, 10.0d);

    // Assert
    assertEquals(0, actualSolver.getIterations());
    assertEquals(Double.POSITIVE_INFINITY, actualSolver.getAccuracy(), 0.0);
  }

  /**
   * Test {@link Solver#Solver(AnalyticModel, Vector, List, ParameterTransformation, double,
   * double)}.
   *
   * <ul>
   *   <li>When {@link Vector#Vector()}.
   * </ul>
   *
   * <p>Method under test: {@link Solver#Solver(AnalyticModel, Vector, List,
   * ParameterTransformation, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Solver.<init>(AnalyticModel, Vector, List, ParameterTransformation, double, double)"
  })
  public void testNewSolver_whenVector4() {
    // Arrange
    AnalyticModelFromCurvesAndVols model = new AnalyticModelFromCurvesAndVols();
    Vector<AnalyticProduct> calibrationProducts = new Vector<>();

    // Act
    Solver actualSolver =
        new Solver(
            model,
            calibrationProducts,
            new ArrayList<>(),
            mock(ParameterTransformation.class),
            10.0d,
            10.0d);

    // Assert
    assertEquals(0, actualSolver.getIterations());
    assertEquals(Double.POSITIVE_INFINITY, actualSolver.getAccuracy(), 0.0);
  }

  /**
   * Test {@link Solver#Solver(AnalyticModel, Vector, List, ParameterTransformation, double,
   * OptimizerFactory)}.
   *
   * <ul>
   *   <li>When {@link Vector#Vector()}.
   * </ul>
   *
   * <p>Method under test: {@link Solver#Solver(AnalyticModel, Vector, List,
   * ParameterTransformation, double, OptimizerFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Solver.<init>(AnalyticModel, Vector, List, ParameterTransformation, double, OptimizerFactory)"
  })
  public void testNewSolver_whenVector5() {
    // Arrange
    AnalyticModelFromCurvesAndVols model = new AnalyticModelFromCurvesAndVols();
    Vector<AnalyticProduct> calibrationProducts = new Vector<>();
    ArrayList<Double> calibrationTargetValues = new ArrayList<>();
    ParameterTransformation parameterTransformation = mock(ParameterTransformation.class);

    // Act
    Solver actualSolver =
        new Solver(
            model,
            calibrationProducts,
            calibrationTargetValues,
            parameterTransformation,
            10.0d,
            new OptimizerFactoryCMAES(10.0d, 3));

    // Assert
    assertEquals(0, actualSolver.getIterations());
    assertEquals(Double.POSITIVE_INFINITY, actualSolver.getAccuracy(), 0.0);
  }

  /**
   * Test {@link Solver#getCalibratedModel(Set)}.
   *
   * <p>Method under test: {@link Solver#getCalibratedModel(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel Solver.getCalibratedModel(Set)"})
  public void testGetCalibratedModel() throws SolverException {
    // Arrange
    AnalyticModelFromCurvesAndVols model = new AnalyticModelFromCurvesAndVols();
    Solver solver = new Solver(model, new Vector<>());

    // Act
    AnalyticModel actualCalibratedModel = solver.getCalibratedModel(new HashSet<>());

    // Assert
    assertTrue(actualCalibratedModel instanceof AnalyticModelFromCurvesAndVols);
    assertNull(((AnalyticModelFromCurvesAndVols) actualCalibratedModel).getReferenceDate());
    assertEquals(2, solver.getIterations());
    assertTrue(actualCalibratedModel.getCurves().isEmpty());
    assertTrue(actualCalibratedModel.getVolatilitySurfaces().isEmpty());
    assertEquals(Double.NaN, solver.getAccuracy(), 0.0);
  }

  /**
   * Test {@link Solver#getCalibratedModel(Set)}.
   *
   * <p>Method under test: {@link Solver#getCalibratedModel(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel Solver.getCalibratedModel(Set)"})
  public void testGetCalibratedModel2() throws SolverException {
    // Arrange
    AnalyticModelFromCurvesAndVols model = new AnalyticModelFromCurvesAndVols();
    Vector<AnalyticProduct> calibrationProducts = new Vector<>();

    Solver solver = new Solver(model, calibrationProducts, new ArrayList<>(), 1.0d, 1.0d);

    // Act
    AnalyticModel actualCalibratedModel = solver.getCalibratedModel(new HashSet<>());

    // Assert
    assertTrue(actualCalibratedModel instanceof AnalyticModelFromCurvesAndVols);
    assertNull(((AnalyticModelFromCurvesAndVols) actualCalibratedModel).getReferenceDate());
    assertEquals(2, solver.getIterations());
    assertTrue(actualCalibratedModel.getCurves().isEmpty());
    assertTrue(actualCalibratedModel.getVolatilitySurfaces().isEmpty());
    assertEquals(Double.NaN, solver.getAccuracy(), 0.0);
  }

  /**
   * Test {@link Solver#getCalibratedModel(Set)}.
   *
   * <p>Method under test: {@link Solver#getCalibratedModel(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel Solver.getCalibratedModel(Set)"})
  public void testGetCalibratedModel3() throws SolverException {
    // Arrange
    Vector<AnalyticProduct> calibrationProducts = new Vector<>();
    calibrationProducts.add(new Portfolio(new ArrayList<>()));
    Solver solver = new Solver(new AnalyticModelFromCurvesAndVols(), calibrationProducts);

    // Act
    AnalyticModel actualCalibratedModel = solver.getCalibratedModel(new HashSet<>());

    // Assert
    assertTrue(actualCalibratedModel instanceof AnalyticModelFromCurvesAndVols);
    assertNull(((AnalyticModelFromCurvesAndVols) actualCalibratedModel).getReferenceDate());
    assertEquals(0.0d, solver.getAccuracy(), 0.0);
    assertEquals(2, solver.getIterations());
    assertTrue(actualCalibratedModel.getCurves().isEmpty());
    assertTrue(actualCalibratedModel.getVolatilitySurfaces().isEmpty());
  }

  /**
   * Test {@link Solver#getCalibratedModel(Set)}.
   *
   * <p>Method under test: {@link Solver#getCalibratedModel(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel Solver.getCalibratedModel(Set)"})
  public void testGetCalibratedModel4() throws SolverException {
    // Arrange
    AnalyticModelFromCurvesAndVols model = new AnalyticModelFromCurvesAndVols();
    Solver solver = new Solver(model, new Vector<>());

    HashSet<ParameterObject> objectsToCalibrate = new HashSet<>();
    CurveInterpolation curveInterpolation =
        new CurveInterpolation(
            "net.finmath",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {1.0d, Double.NEGATIVE_INFINITY, 1.0d, Double.NEGATIVE_INFINITY},
            new double[] {1.0d, Double.NEGATIVE_INFINITY, 1.0d, Double.NEGATIVE_INFINITY});
    objectsToCalibrate.add(curveInterpolation);

    // Act
    AnalyticModel actualCalibratedModel = solver.getCalibratedModel(objectsToCalibrate);

    // Assert
    assertTrue(actualCalibratedModel instanceof AnalyticModelFromCurvesAndVols);
    Map<String, Curve> curves = actualCalibratedModel.getCurves();
    assertEquals(1, curves.size());
    assertSame(curveInterpolation, curves.get("net.finmath"));
  }

  /**
   * Test {@link Solver#getCalibratedModel(Set)}.
   *
   * <p>Method under test: {@link Solver#getCalibratedModel(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AnalyticModel Solver.getCalibratedModel(Set)"})
  public void testGetCalibratedModel5() throws SolverException {
    // Arrange
    AnalyticModelFromCurvesAndVols model = new AnalyticModelFromCurvesAndVols();
    Solver solver = new Solver(model, new Vector<>());

    HashSet<ParameterObject> objectsToCalibrate = new HashSet<>();
    DiscountCurveNelsonSiegelSvensson discountCurveNelsonSiegelSvensson =
        new DiscountCurveNelsonSiegelSvensson(
            "net.finmath",
            LocalDate.of(1970, 1, 1),
            new double[] {1.0d, Double.NEGATIVE_INFINITY, 1.0d, Double.NEGATIVE_INFINITY},
            1.0d);
    objectsToCalibrate.add(discountCurveNelsonSiegelSvensson);

    // Act
    AnalyticModel actualCalibratedModel = solver.getCalibratedModel(objectsToCalibrate);

    // Assert
    assertTrue(actualCalibratedModel instanceof AnalyticModelFromCurvesAndVols);
    Map<String, Curve> curves = actualCalibratedModel.getCurves();
    assertEquals(1, curves.size());
    Curve getResult = curves.get("net.finmath");
    assertTrue(getResult instanceof DiscountCurveNelsonSiegelSvensson);
    assertEquals(1.0d, ((DiscountCurveNelsonSiegelSvensson) getResult).getTimeScaling(), 0.0);
    assertEquals(1001, solver.getIterations());
    assertArrayEquals(new double[] {0.0d, 0.0d, 0.0d, 0.0d}, getResult.getParameter(), 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Solver#getAccuracy()}
   *   <li>{@link Solver#getIterations()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double Solver.getAccuracy()", "int Solver.getIterations()"})
  public void testGettersAndSetters() {
    // Arrange
    AnalyticModelFromCurvesAndVols model = new AnalyticModelFromCurvesAndVols();
    Solver solver = new Solver(model, new Vector<>());

    // Act
    double actualAccuracy = solver.getAccuracy();

    // Assert
    assertEquals(0, solver.getIterations());
    assertEquals(Double.POSITIVE_INFINITY, actualAccuracy, 0.0);
  }
}
