package net.finmath.montecarlo.assetderivativevaluation.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import net.finmath.exception.CalculationException;
import net.finmath.marketdata.model.curves.DiscountCurve;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.DiscountCurveInterpolation;
import net.finmath.modelling.descriptor.VarianceGammaModelDescriptor;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFactory;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.model.ProcessModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.montecarlo.process.MonteCarloProcess;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class VarianceGammaModelDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link VarianceGammaModel#VarianceGammaModel(RandomVariable, DiscountCurve,
   *       DiscountCurve, RandomVariable, RandomVariable, RandomVariable, RandomVariableFactory)}
   *   <li>{@link VarianceGammaModel#toString()}
   *   <li>{@link VarianceGammaModel#getDiscountCurveForDiscountRate()}
   *   <li>{@link VarianceGammaModel#getDiscountCurveForForwardRate()}
   *   <li>{@link VarianceGammaModel#getDiscountRate()}
   *   <li>{@link VarianceGammaModel#getNu()}
   *   <li>{@link VarianceGammaModel#getNumberOfComponents()}
   *   <li>{@link VarianceGammaModel#getNumberOfFactors()}
   *   <li>{@link VarianceGammaModel#getRiskFreeRate()}
   *   <li>{@link VarianceGammaModel#getSigma()}
   *   <li>{@link VarianceGammaModel#getTheta()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void VarianceGammaModel.<init>(RandomVariable, DiscountCurve, DiscountCurve, RandomVariable, RandomVariable, RandomVariable, RandomVariableFactory)",
    "DiscountCurve VarianceGammaModel.getDiscountCurveForDiscountRate()",
    "DiscountCurve VarianceGammaModel.getDiscountCurveForForwardRate()",
    "RandomVariable VarianceGammaModel.getDiscountRate()",
    "RandomVariable VarianceGammaModel.getNu()",
    "int VarianceGammaModel.getNumberOfComponents()",
    "int VarianceGammaModel.getNumberOfFactors()",
    "RandomVariable VarianceGammaModel.getRiskFreeRate()",
    "RandomVariable VarianceGammaModel.getSigma()",
    "RandomVariable VarianceGammaModel.getTheta()",
    "String VarianceGammaModel.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(10.0d);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFromDoubleArray sigma = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray theta = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray nu = new RandomVariableFromDoubleArray(10.0d);

    // Act
    VarianceGammaModel actualVarianceGammaModel =
        new VarianceGammaModel(
            initialValue,
            discountCurveForForwardRate,
            discountCurveForDiscountRate,
            sigma,
            theta,
            nu,
            new RandomVariableFloatFactory());
    String actualToStringResult = actualVarianceGammaModel.toString();
    DiscountCurve actualDiscountCurveForDiscountRate =
        actualVarianceGammaModel.getDiscountCurveForDiscountRate();
    DiscountCurve actualDiscountCurveForForwardRate =
        actualVarianceGammaModel.getDiscountCurveForForwardRate();
    RandomVariable actualDiscountRate = actualVarianceGammaModel.getDiscountRate();
    RandomVariable actualNu = actualVarianceGammaModel.getNu();
    int actualNumberOfComponents = actualVarianceGammaModel.getNumberOfComponents();
    int actualNumberOfFactors = actualVarianceGammaModel.getNumberOfFactors();
    RandomVariable actualRiskFreeRate = actualVarianceGammaModel.getRiskFreeRate();
    RandomVariable actualSigma = actualVarianceGammaModel.getSigma();
    RandomVariable actualTheta = actualVarianceGammaModel.getTheta();

    // Assert
    assertTrue(actualSigma instanceof RandomVariableFromDoubleArray);
    assertTrue(actualTheta instanceof RandomVariableFromDoubleArray);
    assertEquals(
        "VarianceGammaModel [initialValue=RandomVariableFromDoubleArray[ realizations=10.0, isDeterministic()=true,"
            + " filtrationTime=-Infinity, typePriority=1], discountCurveForForwardRate=AbstractCurve [name"
            + "=DiscountCurveFromForwardCurve(Forward Curve Name), referenceDate=null], riskFreeRate=null,"
            + " discountCurveForDiscountRate=AbstractCurve [name=DiscountCurveFromForwardCurve(Forward Curve Name),"
            + " referenceDate=null], discountRate=null, sigma=RandomVariableFromDoubleArray[ realizations=10.0,"
            + " isDeterministic()=true, filtrationTime=-Infinity, typePriority=1], theta=RandomVariableFromDoubleArray["
            + " realizations=10.0, isDeterministic()=true, filtrationTime=-Infinity, typePriority=1], nu=RandomVari"
            + "ableFromDoubleArray[ realizations=10.0, isDeterministic()=true, filtrationTime=-Infinity, typePriority"
            + "=1]]",
        actualToStringResult);
    assertNull(actualDiscountRate);
    assertNull(actualRiskFreeRate);
    assertEquals(1, actualNumberOfComponents);
    assertEquals(1, actualNumberOfFactors);
    assertSame(discountCurveForDiscountRate, actualDiscountCurveForDiscountRate);
    assertSame(discountCurveForForwardRate, actualDiscountCurveForForwardRate);
    assertSame(nu, actualNu);
    assertSame(sigma, actualSigma);
    assertSame(theta, actualTheta);
  }

  /**
   * Test {@link VarianceGammaModel#VarianceGammaModel(double, double, double, double, double)}.
   *
   * <p>Method under test: {@link VarianceGammaModel#VarianceGammaModel(double, double, double,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void VarianceGammaModel.<init>(double, double, double, double, double)"})
  public void testNewVarianceGammaModel() {
    // Arrange and Act
    VarianceGammaModel actualVarianceGammaModel =
        new VarianceGammaModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertTrue(actualVarianceGammaModel.getDiscountRate() instanceof Scalar);
    assertTrue(actualVarianceGammaModel.getNu() instanceof Scalar);
    assertTrue(actualVarianceGammaModel.getRiskFreeRate() instanceof Scalar);
    assertTrue(actualVarianceGammaModel.getSigma() instanceof Scalar);
    assertTrue(actualVarianceGammaModel.getTheta() instanceof Scalar);
    assertNull(actualVarianceGammaModel.getDiscountCurveForDiscountRate());
    assertNull(actualVarianceGammaModel.getDiscountCurveForForwardRate());
    assertEquals(1, actualVarianceGammaModel.getNumberOfComponents());
    assertEquals(1, actualVarianceGammaModel.getNumberOfFactors());
  }

  /**
   * Test {@link VarianceGammaModel#VarianceGammaModel(double, double, double, double, double,
   * double)}.
   *
   * <p>Method under test: {@link VarianceGammaModel#VarianceGammaModel(double, double, double,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void VarianceGammaModel.<init>(double, double, double, double, double, double)"
  })
  public void testNewVarianceGammaModel2() {
    // Arrange and Act
    VarianceGammaModel actualVarianceGammaModel =
        new VarianceGammaModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    assertTrue(actualVarianceGammaModel.getDiscountRate() instanceof Scalar);
    assertTrue(actualVarianceGammaModel.getNu() instanceof Scalar);
    assertTrue(actualVarianceGammaModel.getRiskFreeRate() instanceof Scalar);
    assertTrue(actualVarianceGammaModel.getSigma() instanceof Scalar);
    assertTrue(actualVarianceGammaModel.getTheta() instanceof Scalar);
    assertNull(actualVarianceGammaModel.getDiscountCurveForDiscountRate());
    assertNull(actualVarianceGammaModel.getDiscountCurveForForwardRate());
    assertEquals(1, actualVarianceGammaModel.getNumberOfComponents());
    assertEquals(1, actualVarianceGammaModel.getNumberOfFactors());
  }

  /**
   * Test {@link VarianceGammaModel#VarianceGammaModel(double, DiscountCurve, DiscountCurve, double,
   * double, double)}.
   *
   * <p>Method under test: {@link VarianceGammaModel#VarianceGammaModel(double, DiscountCurve,
   * DiscountCurve, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void VarianceGammaModel.<init>(double, DiscountCurve, DiscountCurve, double, double, double)"
  })
  public void testNewVarianceGammaModel3() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    VarianceGammaModel actualVarianceGammaModel =
        new VarianceGammaModel(
            10.0d, discountCurveForForwardRate, discountCurveForDiscountRate, 10.0d, 10.0d, 10.0d);

    // Assert
    DiscountCurve discountCurveForDiscountRate2 =
        actualVarianceGammaModel.getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate2 instanceof DiscountCurveFromForwardCurve);
    assertTrue(actualVarianceGammaModel.getNu() instanceof Scalar);
    assertTrue(actualVarianceGammaModel.getSigma() instanceof Scalar);
    assertTrue(actualVarianceGammaModel.getTheta() instanceof Scalar);
    assertNull(actualVarianceGammaModel.getDiscountRate());
    assertNull(actualVarianceGammaModel.getRiskFreeRate());
    assertEquals(1, actualVarianceGammaModel.getNumberOfComponents());
    assertEquals(1, actualVarianceGammaModel.getNumberOfFactors());
    assertEquals(discountCurveForForwardRate, discountCurveForDiscountRate2);
    assertSame(discountCurveForDiscountRate, discountCurveForDiscountRate2);
    assertSame(
        discountCurveForDiscountRate, actualVarianceGammaModel.getDiscountCurveForForwardRate());
  }

  /**
   * Test {@link VarianceGammaModel#VarianceGammaModel(double, DiscountCurve, DiscountCurve, double,
   * double, double, RandomVariableFactory)}.
   *
   * <p>Method under test: {@link VarianceGammaModel#VarianceGammaModel(double, DiscountCurve,
   * DiscountCurve, double, double, double, RandomVariableFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void VarianceGammaModel.<init>(double, DiscountCurve, DiscountCurve, double, double, double, RandomVariableFactory)"
  })
  public void testNewVarianceGammaModel4() {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    VarianceGammaModel actualVarianceGammaModel =
        new VarianceGammaModel(
            10.0d,
            discountCurveForForwardRate,
            discountCurveForDiscountRate,
            10.0d,
            10.0d,
            10.0d,
            new RandomVariableFloatFactory());

    // Assert
    DiscountCurve discountCurveForForwardRate2 =
        actualVarianceGammaModel.getDiscountCurveForForwardRate();
    assertTrue(discountCurveForForwardRate2 instanceof DiscountCurveFromForwardCurve);
    assertTrue(actualVarianceGammaModel.getNu() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVarianceGammaModel.getSigma() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVarianceGammaModel.getTheta() instanceof RandomVariableFromFloatArray);
    assertNull(actualVarianceGammaModel.getDiscountRate());
    assertNull(actualVarianceGammaModel.getRiskFreeRate());
    assertEquals(1, actualVarianceGammaModel.getNumberOfComponents());
    assertEquals(1, actualVarianceGammaModel.getNumberOfFactors());
    assertSame(
        discountCurveForDiscountRate, actualVarianceGammaModel.getDiscountCurveForDiscountRate());
    assertSame(discountCurveForForwardRate, discountCurveForForwardRate2);
  }

  /**
   * Test {@link VarianceGammaModel#VarianceGammaModel(VarianceGammaModelDescriptor)}.
   *
   * <p>Method under test: {@link
   * VarianceGammaModel#VarianceGammaModel(VarianceGammaModelDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void VarianceGammaModel.<init>(VarianceGammaModelDescriptor)"})
  public void testNewVarianceGammaModel5() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    VarianceGammaModel actualVarianceGammaModel =
        new VarianceGammaModel(
            new VarianceGammaModelDescriptor(
                referenceDate,
                10.0d,
                discountCurveForForwardRate,
                discountCurveForDiscountRate,
                10.0d,
                10.0d,
                10.0d));

    // Assert
    DiscountCurve discountCurveForDiscountRate2 =
        actualVarianceGammaModel.getDiscountCurveForDiscountRate();
    assertTrue(discountCurveForDiscountRate2 instanceof DiscountCurveFromForwardCurve);
    assertTrue(actualVarianceGammaModel.getNu() instanceof Scalar);
    assertTrue(actualVarianceGammaModel.getSigma() instanceof Scalar);
    assertTrue(actualVarianceGammaModel.getTheta() instanceof Scalar);
    assertNull(actualVarianceGammaModel.getDiscountRate());
    assertNull(actualVarianceGammaModel.getRiskFreeRate());
    assertEquals(1, actualVarianceGammaModel.getNumberOfComponents());
    assertEquals(1, actualVarianceGammaModel.getNumberOfFactors());
    assertEquals(discountCurveForForwardRate, discountCurveForDiscountRate2);
    assertSame(discountCurveForDiscountRate, discountCurveForDiscountRate2);
    assertSame(
        discountCurveForDiscountRate, actualVarianceGammaModel.getDiscountCurveForForwardRate());
  }

  /**
   * Test {@link VarianceGammaModel#VarianceGammaModel(RandomVariable, RandomVariable,
   * RandomVariable, RandomVariable, RandomVariable, RandomVariable, RandomVariableFactory)}.
   *
   * <p>Method under test: {@link VarianceGammaModel#VarianceGammaModel(RandomVariable,
   * RandomVariable, RandomVariable, RandomVariable, RandomVariable, RandomVariable,
   * RandomVariableFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void VarianceGammaModel.<init>(RandomVariable, RandomVariable, RandomVariable, RandomVariable, RandomVariable, RandomVariable, RandomVariableFactory)"
  })
  public void testNewVarianceGammaModel6() {
    // Arrange
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray discountRate = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray sigma = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray theta = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray nu = new RandomVariableFromDoubleArray(10.0d);

    // Act
    VarianceGammaModel actualVarianceGammaModel =
        new VarianceGammaModel(
            initialValue,
            riskFreeRate,
            discountRate,
            sigma,
            theta,
            nu,
            new RandomVariableFloatFactory());

    // Assert
    RandomVariable discountRate2 = actualVarianceGammaModel.getDiscountRate();
    assertTrue(discountRate2 instanceof RandomVariableFromDoubleArray);
    RandomVariable nu2 = actualVarianceGammaModel.getNu();
    assertTrue(nu2 instanceof RandomVariableFromDoubleArray);
    RandomVariable riskFreeRate2 = actualVarianceGammaModel.getRiskFreeRate();
    assertTrue(riskFreeRate2 instanceof RandomVariableFromDoubleArray);
    RandomVariable sigma2 = actualVarianceGammaModel.getSigma();
    assertTrue(sigma2 instanceof RandomVariableFromDoubleArray);
    RandomVariable theta2 = actualVarianceGammaModel.getTheta();
    assertTrue(theta2 instanceof RandomVariableFromDoubleArray);
    assertNull(actualVarianceGammaModel.getDiscountCurveForDiscountRate());
    assertNull(actualVarianceGammaModel.getDiscountCurveForForwardRate());
    assertEquals(1, actualVarianceGammaModel.getNumberOfComponents());
    assertEquals(1, actualVarianceGammaModel.getNumberOfFactors());
    assertSame(discountRate, discountRate2);
    assertSame(nu, nu2);
    assertSame(riskFreeRate, riskFreeRate2);
    assertSame(sigma, sigma2);
    assertSame(theta, theta2);
  }

  /**
   * Test {@link VarianceGammaModel#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link VarianceGammaModel#applyStateSpaceTransform(MonteCarloProcess,
   * int, int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable VarianceGammaModel.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    VarianceGammaModel varianceGammaModel =
        new VarianceGammaModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualApplyStateSpaceTransformResult =
        varianceGammaModel.applyStateSpaceTransform(
            process, 1, 1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualApplyStateSpaceTransformResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyStateSpaceTransformResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyStateSpaceTransformResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformResult.expectation()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualApplyStateSpaceTransformResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformResult.getVariance(), 0.0);
    assertEquals(1, actualApplyStateSpaceTransformResult.getTypePriority());
    assertEquals(1, actualApplyStateSpaceTransformResult.size());
    assertEquals(22026.465794806718d, actualApplyStateSpaceTransformResult.getAverage(), 0.0);
    assertEquals(22026.465794806718d, actualApplyStateSpaceTransformResult.getMax(), 0.0);
    assertEquals(22026.465794806718d, actualApplyStateSpaceTransformResult.getMin(), 0.0);
    assertTrue(actualApplyStateSpaceTransformResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualApplyStateSpaceTransformResult.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {22026.465794806718d},
        actualApplyStateSpaceTransformResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link VarianceGammaModel#applyStateSpaceTransformInverse(MonteCarloProcess, int, int,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * VarianceGammaModel#applyStateSpaceTransformInverse(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable VarianceGammaModel.applyStateSpaceTransformInverse(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    VarianceGammaModel varianceGammaModel =
        new VarianceGammaModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualApplyStateSpaceTransformInverseResult =
        varianceGammaModel.applyStateSpaceTransformInverse(
            process, 1, 1, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualApplyStateSpaceTransformInverseResult instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.average()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.expectation()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.invert()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.isNaN()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.sqrt()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.squared()
            instanceof RandomVariableFromDoubleArray);
    assertTrue(
        actualApplyStateSpaceTransformInverseResult.variance()
            instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualApplyStateSpaceTransformInverseResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformInverseResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformInverseResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualApplyStateSpaceTransformInverseResult.getVariance(), 0.0);
    assertEquals(1, actualApplyStateSpaceTransformInverseResult.getTypePriority());
    assertEquals(1, actualApplyStateSpaceTransformInverseResult.size());
    assertEquals(2.302585092994046d, actualApplyStateSpaceTransformInverseResult.getAverage(), 0.0);
    assertEquals(2.302585092994046d, actualApplyStateSpaceTransformInverseResult.getMax(), 0.0);
    assertEquals(2.302585092994046d, actualApplyStateSpaceTransformInverseResult.getMin(), 0.0);
    assertTrue(actualApplyStateSpaceTransformInverseResult.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY,
        actualApplyStateSpaceTransformInverseResult.getFiltrationTime(),
        0.0);
    assertArrayEquals(
        new double[] {2.302585092994046d},
        actualApplyStateSpaceTransformInverseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link VarianceGammaModel#getInitialState(MonteCarloProcess)}.
   *
   * <p>Method under test: {@link VarianceGammaModel#getInitialState(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] VarianceGammaModel.getInitialState(MonteCarloProcess)"})
  public void testGetInitialState() {
    // Arrange
    VarianceGammaModel varianceGammaModel =
        new VarianceGammaModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualInitialState = varianceGammaModel.getInitialState(process);

    // Assert
    assertTrue(actualInitialState[0] instanceof Scalar);
    assertEquals(1, actualInitialState.length);
  }

  /**
   * Test {@link VarianceGammaModel#getNumeraire(MonteCarloProcess, double)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 0.1}.
   * </ul>
   *
   * <p>Method under test: {@link VarianceGammaModel#getNumeraire(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable VarianceGammaModel.getNumeraire(MonteCarloProcess, double)"})
  public void testGetNumeraire_thenReturnAverageIs01() {
    // Arrange
    DiscountCurveInterpolation discountCurveForDiscountRate =
        mock(DiscountCurveInterpolation.class);
    when(discountCurveForDiscountRate.getDiscountFactor(anyDouble())).thenReturn(10.0d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    VarianceGammaModel varianceGammaModel =
        new VarianceGammaModel(
            new VarianceGammaModelDescriptor(
                referenceDate,
                10.0d,
                new DiscountCurveFromForwardCurve("Forward Curve Name"),
                discountCurveForDiscountRate,
                10.0d,
                10.0d,
                10.0d));
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualNumeraire = varianceGammaModel.getNumeraire(process, 10.0d);

    // Assert
    verify(discountCurveForDiscountRate).getDiscountFactor(10.0d);
    assertTrue(actualNumeraire instanceof Scalar);
    assertEquals(0.1d, actualNumeraire.getAverage(), 0.0);
    assertEquals(0.1d, actualNumeraire.getMax(), 0.0);
    assertEquals(0.1d, actualNumeraire.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualNumeraire.expectation();
    assertSame(actualNumeraire, actualExpectationResult);
  }

  /**
   * Test {@link VarianceGammaModel#getNumeraire(MonteCarloProcess, double)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 2.6881171418161356E43}.
   * </ul>
   *
   * <p>Method under test: {@link VarianceGammaModel#getNumeraire(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable VarianceGammaModel.getNumeraire(MonteCarloProcess, double)"})
  public void testGetNumeraire_thenReturnAverageIs26881171418161356e43() {
    // Arrange
    VarianceGammaModel varianceGammaModel =
        new VarianceGammaModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualNumeraire = varianceGammaModel.getNumeraire(process, 10.0d);

    // Assert
    assertTrue(actualNumeraire instanceof Scalar);
    assertEquals(2.6881171418161356E43d, actualNumeraire.getAverage(), 0.0);
    assertEquals(2.6881171418161356E43d, actualNumeraire.getMax(), 0.0);
    assertEquals(2.6881171418161356E43d, actualNumeraire.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualNumeraire.expectation();
    assertSame(actualNumeraire, actualExpectationResult);
  }

  /**
   * Test {@link VarianceGammaModel#getDrift(MonteCarloProcess, int, RandomVariable[],
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return first element Average is {@code -0.7493868988828214}.
   * </ul>
   *
   * <p>Method under test: {@link VarianceGammaModel#getDrift(MonteCarloProcess, int,
   * RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] VarianceGammaModel.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift_thenReturnFirstElementAverageIs07493868988828214() {
    // Arrange
    DiscountCurveInterpolation discountCurveForDiscountRate =
        mock(DiscountCurveInterpolation.class);
    when(discountCurveForDiscountRate.getDiscountFactor(anyDouble())).thenReturn(10.0d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    VarianceGammaModel varianceGammaModel =
        new VarianceGammaModel(
            new VarianceGammaModelDescriptor(
                referenceDate,
                0.5d,
                new DiscountCurveFromForwardCurve("Forward Curve Name"),
                discountCurveForDiscountRate,
                0.5d,
                0.5d,
                0.5d));
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualDrift =
        varianceGammaModel.getDrift(
            process,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(discountCurveForDiscountRate, atLeast(1)).getDiscountFactor(anyDouble());
    RandomVariable randomVariable = actualDrift[0];
    assertTrue(randomVariable instanceof Scalar);
    assertEquals(-0.7493868988828214d, randomVariable.getAverage(), 0.0);
    assertEquals(-0.7493868988828214d, randomVariable.getMax(), 0.0);
    assertEquals(-0.7493868988828214d, randomVariable.getMin(), 0.0);
    assertEquals(1, actualDrift.length);
    assertSame(randomVariable, randomVariable.expectation());
  }

  /**
   * Test {@link VarianceGammaModel#getDrift(MonteCarloProcess, int, RandomVariable[],
   * RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return first element Average is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link VarianceGammaModel#getDrift(MonteCarloProcess, int,
   * RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] VarianceGammaModel.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift_thenReturnFirstElementAverageIsNaN() {
    // Arrange
    VarianceGammaModel varianceGammaModel =
        new VarianceGammaModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualDrift =
        varianceGammaModel.getDrift(
            process,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    RandomVariable randomVariable = actualDrift[0];
    assertTrue(randomVariable instanceof Scalar);
    assertEquals(1, actualDrift.length);
    assertEquals(Double.NaN, randomVariable.getAverage(), 0.0);
    assertEquals(Double.NaN, randomVariable.getMax(), 0.0);
    assertEquals(Double.NaN, randomVariable.getMin(), 0.0);
    assertSame(randomVariable, randomVariable.expectation());
  }

  /**
   * Test {@link VarianceGammaModel#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}.
   *
   * <p>Method under test: {@link VarianceGammaModel#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] VarianceGammaModel.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading() {
    // Arrange
    VarianceGammaModel varianceGammaModel =
        new VarianceGammaModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualFactorLoading =
        varianceGammaModel.getFactorLoading(
            process, 1, 1, new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualFactorLoading[0] instanceof Scalar);
    assertEquals(1, actualFactorLoading.length);
  }

  /**
   * Test {@link VarianceGammaModel#getRandomVariableForConstant(double)}.
   *
   * <p>Method under test: {@link VarianceGammaModel#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable VarianceGammaModel.getRandomVariableForConstant(double)"})
  public void testGetRandomVariableForConstant() {
    // Arrange
    VarianceGammaModel varianceGammaModel =
        new VarianceGammaModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    RandomVariable actualRandomVariableForConstant =
        varianceGammaModel.getRandomVariableForConstant(10.0d);

    // Assert
    assertTrue(actualRandomVariableForConstant instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.abs() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.cos() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.exp() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.expm1() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.invert() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.isNaN() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.sin() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.sqrt() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.squared() instanceof Scalar);
    assertTrue(actualRandomVariableForConstant.variance() instanceof Scalar);
    assertNull(actualRandomVariableForConstant.getRealizations());
    assertNull(actualRandomVariableForConstant.getOperator());
    assertNull(actualRandomVariableForConstant.getRealizationsStream());
    assertEquals(0, actualRandomVariableForConstant.getTypePriority());
    assertEquals(0.0d, actualRandomVariableForConstant.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableForConstant.getVariance(), 0.0);
    assertEquals(1, actualRandomVariableForConstant.size());
    assertEquals(10.0d, actualRandomVariableForConstant.getAverage(), 0.0);
    assertEquals(10.0d, actualRandomVariableForConstant.getMax(), 0.0);
    assertEquals(10.0d, actualRandomVariableForConstant.getMin(), 0.0);
    assertTrue(actualRandomVariableForConstant.isDeterministic());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualRandomVariableForConstant.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualRandomVariableForConstant.expectation();
    assertSame(actualRandomVariableForConstant, actualExpectationResult);
  }

  /**
   * Test {@link VarianceGammaModel#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then return {@link VarianceGammaModel}.
   * </ul>
   *
   * <p>Method under test: {@link VarianceGammaModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ProcessModel VarianceGammaModel.getCloneWithModifiedData(Map)"})
  public void testGetCloneWithModifiedData_whenHashMap_thenReturnVarianceGammaModel()
      throws CalculationException {
    // Arrange
    VarianceGammaModel varianceGammaModel =
        new VarianceGammaModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    ProcessModel actualCloneWithModifiedData =
        varianceGammaModel.getCloneWithModifiedData(new HashMap<>());

    // Assert
    assertTrue(actualCloneWithModifiedData instanceof VarianceGammaModel);
    assertTrue(
        ((VarianceGammaModel) actualCloneWithModifiedData).getDiscountRate() instanceof Scalar);
    assertTrue(((VarianceGammaModel) actualCloneWithModifiedData).getNu() instanceof Scalar);
    assertTrue(
        ((VarianceGammaModel) actualCloneWithModifiedData).getRiskFreeRate() instanceof Scalar);
    assertTrue(((VarianceGammaModel) actualCloneWithModifiedData).getSigma() instanceof Scalar);
    assertTrue(((VarianceGammaModel) actualCloneWithModifiedData).getTheta() instanceof Scalar);
    assertNull(
        ((VarianceGammaModel) actualCloneWithModifiedData).getDiscountCurveForDiscountRate());
    assertNull(((VarianceGammaModel) actualCloneWithModifiedData).getDiscountCurveForForwardRate());
    assertEquals(1, actualCloneWithModifiedData.getNumberOfComponents());
    assertEquals(1, actualCloneWithModifiedData.getNumberOfFactors());
  }
}
