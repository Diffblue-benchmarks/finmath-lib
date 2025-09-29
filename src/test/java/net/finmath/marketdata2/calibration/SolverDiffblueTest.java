package net.finmath.marketdata2.calibration;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import net.finmath.marketdata2.model.AnalyticModel;
import net.finmath.marketdata2.model.AnalyticModelFromCurvesAndVols;
import net.finmath.marketdata2.products.AnalyticProduct;
import net.finmath.marketdata2.products.Cashflow;
import net.finmath.optimizer.StochasticOptimizerFactory;
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
   * StochasticOptimizerFactory)}.
   *
   * <p>Method under test: {@link Solver#Solver(AnalyticModel, Vector, List,
   * ParameterTransformation, double, StochasticOptimizerFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Solver.<init>(AnalyticModel, Vector, List, ParameterTransformation, double, StochasticOptimizerFactory)"
  })
  public void testNewSolver9() {
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
            mock(StochasticOptimizerFactory.class));

    // Assert
    assertEquals(0, actualSolver.getIterations());
    assertEquals(Double.POSITIVE_INFINITY, actualSolver.getAccuracy(), 0.0);
  }

  /**
   * Test {@link Solver#Solver(AnalyticModel, Vector, List, ParameterTransformation, double,
   * StochasticOptimizerFactory)}.
   *
   * <p>Method under test: {@link Solver#Solver(AnalyticModel, Vector, List,
   * ParameterTransformation, double, StochasticOptimizerFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Solver.<init>(AnalyticModel, Vector, List, ParameterTransformation, double, StochasticOptimizerFactory)"
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

    // Act
    Solver actualSolver =
        new Solver(
            model,
            calibrationProducts,
            new ArrayList<>(),
            mock(ParameterTransformation.class),
            10.0d,
            mock(StochasticOptimizerFactory.class));

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
   * StochasticOptimizerFactory)}.
   *
   * <ul>
   *   <li>Given {@link Double#POSITIVE_INFINITY}.
   *   <li>When {@link ArrayList#ArrayList()} add {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link Solver#Solver(AnalyticModel, Vector, List,
   * ParameterTransformation, double, StochasticOptimizerFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Solver.<init>(AnalyticModel, Vector, List, ParameterTransformation, double, StochasticOptimizerFactory)"
  })
  public void testNewSolver_givenPositive_infinity_whenArrayListAddPositive_infinity3() {
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
            mock(StochasticOptimizerFactory.class));

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
   * StochasticOptimizerFactory)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>When {@link ArrayList#ArrayList()} add ten.
   * </ul>
   *
   * <p>Method under test: {@link Solver#Solver(AnalyticModel, Vector, List,
   * ParameterTransformation, double, StochasticOptimizerFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Solver.<init>(AnalyticModel, Vector, List, ParameterTransformation, double, StochasticOptimizerFactory)"
  })
  public void testNewSolver_givenTen_whenArrayListAddTen3() {
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
            mock(StochasticOptimizerFactory.class));

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
   * StochasticOptimizerFactory)}.
   *
   * <ul>
   *   <li>When {@link Vector#Vector()}.
   * </ul>
   *
   * <p>Method under test: {@link Solver#Solver(AnalyticModel, Vector, List,
   * ParameterTransformation, double, StochasticOptimizerFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Solver.<init>(AnalyticModel, Vector, List, ParameterTransformation, double, StochasticOptimizerFactory)"
  })
  public void testNewSolver_whenVector5() {
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
            mock(StochasticOptimizerFactory.class));

    // Assert
    assertEquals(0, actualSolver.getIterations());
    assertEquals(Double.POSITIVE_INFINITY, actualSolver.getAccuracy(), 0.0);
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
