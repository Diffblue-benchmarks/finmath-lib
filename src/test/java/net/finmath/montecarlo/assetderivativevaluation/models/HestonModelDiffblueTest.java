package net.finmath.montecarlo.assetderivativevaluation.models;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import net.finmath.marketdata.model.curves.DiscountCurve;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.DiscountCurveInterpolation;
import net.finmath.modelling.descriptor.HestonModelDescriptor;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFactory;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.assetderivativevaluation.models.HestonModel.Scheme;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.montecarlo.process.MonteCarloProcess;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class HestonModelDiffblueTest {
  /**
   * Test {@link HestonModel#HestonModel(double, double, double, double, double, double, double,
   * double, Scheme)}.
   *
   * <p>Method under test: {@link HestonModel#HestonModel(double, double, double, double, double,
   * double, double, double, Scheme)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HestonModel.<init>(double, double, double, double, double, double, double, double, Scheme)"
  })
  public void testNewHestonModel() {
    // Arrange and Act
    HestonModel actualHestonModel =
        new HestonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, Scheme.REFLECTION);

    // Assert
    assertTrue(actualHestonModel.getInitialValue() instanceof Scalar);
    assertTrue(actualHestonModel.getKappa() instanceof Scalar);
    assertTrue(actualHestonModel.getRho() instanceof Scalar);
    assertTrue(actualHestonModel.getRiskFreeRate() instanceof Scalar);
    assertTrue(actualHestonModel.getTheta() instanceof Scalar);
    assertTrue(actualHestonModel.getVolatility() instanceof Scalar);
    assertTrue(actualHestonModel.getXi() instanceof Scalar);
    assertNull(actualHestonModel.getDiscountCurveForDiscountRate());
    assertNull(actualHestonModel.getDiscountCurveForForwardRate());
    assertEquals(1, actualHestonModel.getNumberOfFactors());
    assertEquals(2, actualHestonModel.getNumberOfComponents());
    assertEquals(Scheme.REFLECTION, actualHestonModel.getScheme());
  }

  /**
   * Test {@link HestonModel#HestonModel(double, double, double, double, double, double, double,
   * Scheme)}.
   *
   * <p>Method under test: {@link HestonModel#HestonModel(double, double, double, double, double,
   * double, double, Scheme)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HestonModel.<init>(double, double, double, double, double, double, double, Scheme)"
  })
  public void testNewHestonModel2() {
    // Arrange and Act
    HestonModel actualHestonModel =
        new HestonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, Scheme.REFLECTION);

    // Assert
    assertTrue(actualHestonModel.getInitialValue() instanceof Scalar);
    assertTrue(actualHestonModel.getKappa() instanceof Scalar);
    assertTrue(actualHestonModel.getRho() instanceof Scalar);
    assertTrue(actualHestonModel.getRiskFreeRate() instanceof Scalar);
    assertTrue(actualHestonModel.getTheta() instanceof Scalar);
    assertTrue(actualHestonModel.getVolatility() instanceof Scalar);
    assertTrue(actualHestonModel.getXi() instanceof Scalar);
    assertNull(actualHestonModel.getDiscountCurveForDiscountRate());
    assertNull(actualHestonModel.getDiscountCurveForForwardRate());
    assertEquals(1, actualHestonModel.getNumberOfFactors());
    assertEquals(2, actualHestonModel.getNumberOfComponents());
    assertEquals(Scheme.REFLECTION, actualHestonModel.getScheme());
  }

  /**
   * Test {@link HestonModel#HestonModel(HestonModelDescriptor, Scheme, RandomVariableFactory)}.
   *
   * <p>Method under test: {@link HestonModel#HestonModel(HestonModelDescriptor, Scheme,
   * RandomVariableFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HestonModel.<init>(HestonModelDescriptor, Scheme, RandomVariableFactory)"
  })
  public void testNewHestonModel3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            discountCurveForForwardRate,
            discountCurveForDiscountRate,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);

    // Act
    HestonModel actualHestonModel =
        new HestonModel(descriptor, Scheme.REFLECTION, new RandomVariableFloatFactory());

    // Assert
    DiscountCurve discountCurveForForwardRate2 = actualHestonModel.getDiscountCurveForForwardRate();
    assertTrue(discountCurveForForwardRate2 instanceof DiscountCurveFromForwardCurve);
    assertTrue(actualHestonModel.getInitialValue() instanceof RandomVariableFromFloatArray);
    assertTrue(actualHestonModel.getKappa() instanceof RandomVariableFromFloatArray);
    assertTrue(actualHestonModel.getRho() instanceof RandomVariableFromFloatArray);
    assertTrue(actualHestonModel.getTheta() instanceof RandomVariableFromFloatArray);
    assertTrue(actualHestonModel.getVolatility() instanceof RandomVariableFromFloatArray);
    assertTrue(actualHestonModel.getXi() instanceof RandomVariableFromFloatArray);
    assertNull(actualHestonModel.getRiskFreeRate());
    assertEquals(1, actualHestonModel.getNumberOfFactors());
    assertEquals(2, actualHestonModel.getNumberOfComponents());
    assertEquals(Scheme.REFLECTION, actualHestonModel.getScheme());
    assertSame(discountCurveForDiscountRate, actualHestonModel.getDiscountCurveForDiscountRate());
    assertSame(discountCurveForForwardRate, discountCurveForForwardRate2);
  }

  /**
   * Test {@link HestonModel#HestonModel(RandomVariable, DiscountCurve, RandomVariable,
   * DiscountCurve, RandomVariable, RandomVariable, RandomVariable, RandomVariable, Scheme,
   * RandomVariableFactory)}.
   *
   * <p>Method under test: {@link HestonModel#HestonModel(RandomVariable, DiscountCurve,
   * RandomVariable, DiscountCurve, RandomVariable, RandomVariable, RandomVariable, RandomVariable,
   * Scheme, RandomVariableFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HestonModel.<init>(RandomVariable, DiscountCurve, RandomVariable, DiscountCurve, RandomVariable, RandomVariable, RandomVariable, RandomVariable, Scheme, RandomVariableFactory)"
  })
  public void testNewHestonModel4() {
    // Arrange
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(10.0d);
    DiscountCurveFromForwardCurve discountCurveForForwardRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);
    DiscountCurveFromForwardCurve discountCurveForDiscountRate =
        new DiscountCurveFromForwardCurve("Forward Curve Name");
    RandomVariableFromDoubleArray theta = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray kappa = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray xi = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray rho = new RandomVariableFromDoubleArray(10.0d);

    // Act
    HestonModel actualHestonModel =
        new HestonModel(
            initialValue,
            discountCurveForForwardRate,
            volatility,
            discountCurveForDiscountRate,
            theta,
            kappa,
            xi,
            rho,
            Scheme.REFLECTION,
            new RandomVariableFloatFactory());

    // Assert
    DiscountCurve discountCurveForForwardRate2 = actualHestonModel.getDiscountCurveForForwardRate();
    assertTrue(discountCurveForForwardRate2 instanceof DiscountCurveFromForwardCurve);
    RandomVariable initialValue2 = actualHestonModel.getInitialValue();
    assertTrue(initialValue2 instanceof RandomVariableFromDoubleArray);
    RandomVariable kappa2 = actualHestonModel.getKappa();
    assertTrue(kappa2 instanceof RandomVariableFromDoubleArray);
    RandomVariable rho2 = actualHestonModel.getRho();
    assertTrue(rho2 instanceof RandomVariableFromDoubleArray);
    RandomVariable theta2 = actualHestonModel.getTheta();
    assertTrue(theta2 instanceof RandomVariableFromDoubleArray);
    RandomVariable volatility2 = actualHestonModel.getVolatility();
    assertTrue(volatility2 instanceof RandomVariableFromDoubleArray);
    RandomVariable xi2 = actualHestonModel.getXi();
    assertTrue(xi2 instanceof RandomVariableFromDoubleArray);
    assertNull(actualHestonModel.getRiskFreeRate());
    assertEquals(1, actualHestonModel.getNumberOfFactors());
    assertEquals(2, actualHestonModel.getNumberOfComponents());
    assertEquals(Scheme.REFLECTION, actualHestonModel.getScheme());
    assertSame(discountCurveForDiscountRate, actualHestonModel.getDiscountCurveForDiscountRate());
    assertSame(discountCurveForForwardRate, discountCurveForForwardRate2);
    assertSame(initialValue, initialValue2);
    assertSame(kappa, kappa2);
    assertSame(rho, rho2);
    assertSame(theta, theta2);
    assertSame(volatility, volatility2);
    assertSame(xi, xi2);
  }

  /**
   * Test {@link HestonModel#HestonModel(RandomVariable, RandomVariable, RandomVariable,
   * RandomVariable, RandomVariable, RandomVariable, RandomVariable, RandomVariable, Scheme,
   * RandomVariableFactory)}.
   *
   * <ul>
   *   <li>Then InitialValue return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link HestonModel#HestonModel(RandomVariable, RandomVariable,
   * RandomVariable, RandomVariable, RandomVariable, RandomVariable, RandomVariable, RandomVariable,
   * Scheme, RandomVariableFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HestonModel.<init>(RandomVariable, RandomVariable, RandomVariable, RandomVariable, RandomVariable, RandomVariable, RandomVariable, RandomVariable, Scheme, RandomVariableFactory)"
  })
  public void testNewHestonModel_thenInitialValueReturnRandomVariableFromDoubleArray() {
    // Arrange
    RandomVariableFromDoubleArray initialValue = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray riskFreeRate = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray volatility = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray discountRate = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray theta = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray kappa = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray xi = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray rho = new RandomVariableFromDoubleArray(10.0d);

    // Act
    HestonModel actualHestonModel =
        new HestonModel(
            initialValue,
            riskFreeRate,
            volatility,
            discountRate,
            theta,
            kappa,
            xi,
            rho,
            Scheme.REFLECTION,
            new RandomVariableFloatFactory());

    // Assert
    RandomVariable initialValue2 = actualHestonModel.getInitialValue();
    assertTrue(initialValue2 instanceof RandomVariableFromDoubleArray);
    RandomVariable kappa2 = actualHestonModel.getKappa();
    assertTrue(kappa2 instanceof RandomVariableFromDoubleArray);
    RandomVariable rho2 = actualHestonModel.getRho();
    assertTrue(rho2 instanceof RandomVariableFromDoubleArray);
    RandomVariable riskFreeRate2 = actualHestonModel.getRiskFreeRate();
    assertTrue(riskFreeRate2 instanceof RandomVariableFromDoubleArray);
    RandomVariable theta2 = actualHestonModel.getTheta();
    assertTrue(theta2 instanceof RandomVariableFromDoubleArray);
    RandomVariable volatility2 = actualHestonModel.getVolatility();
    assertTrue(volatility2 instanceof RandomVariableFromDoubleArray);
    RandomVariable xi2 = actualHestonModel.getXi();
    assertTrue(xi2 instanceof RandomVariableFromDoubleArray);
    assertNull(actualHestonModel.getDiscountCurveForDiscountRate());
    assertNull(actualHestonModel.getDiscountCurveForForwardRate());
    assertEquals(1, actualHestonModel.getNumberOfFactors());
    assertEquals(2, actualHestonModel.getNumberOfComponents());
    assertEquals(Scheme.REFLECTION, actualHestonModel.getScheme());
    assertSame(initialValue, initialValue2);
    assertSame(kappa, kappa2);
    assertSame(rho, rho2);
    assertSame(riskFreeRate, riskFreeRate2);
    assertSame(theta, theta2);
    assertSame(volatility, volatility2);
    assertSame(xi, xi2);
  }

  /**
   * Test {@link HestonModel#HestonModel(double, double, double, double, double, double, double,
   * double, Scheme, RandomVariableFactory)}.
   *
   * <ul>
   *   <li>Then InitialValue return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link HestonModel#HestonModel(double, double, double, double, double,
   * double, double, double, Scheme, RandomVariableFactory)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HestonModel.<init>(double, double, double, double, double, double, double, double, Scheme, RandomVariableFactory)"
  })
  public void testNewHestonModel_thenInitialValueReturnRandomVariableFromFloatArray() {
    // Arrange and Act
    HestonModel actualHestonModel =
        new HestonModel(
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            Scheme.REFLECTION,
            new RandomVariableFloatFactory());

    // Assert
    assertTrue(actualHestonModel.getInitialValue() instanceof RandomVariableFromFloatArray);
    assertTrue(actualHestonModel.getKappa() instanceof RandomVariableFromFloatArray);
    assertTrue(actualHestonModel.getRho() instanceof RandomVariableFromFloatArray);
    assertTrue(actualHestonModel.getRiskFreeRate() instanceof RandomVariableFromFloatArray);
    assertTrue(actualHestonModel.getTheta() instanceof RandomVariableFromFloatArray);
    assertTrue(actualHestonModel.getVolatility() instanceof RandomVariableFromFloatArray);
    assertTrue(actualHestonModel.getXi() instanceof RandomVariableFromFloatArray);
    assertNull(actualHestonModel.getDiscountCurveForDiscountRate());
    assertNull(actualHestonModel.getDiscountCurveForForwardRate());
    assertEquals(1, actualHestonModel.getNumberOfFactors());
    assertEquals(2, actualHestonModel.getNumberOfComponents());
    assertEquals(Scheme.REFLECTION, actualHestonModel.getScheme());
  }

  /**
   * Test {@link HestonModel#getInitialState(MonteCarloProcess)}.
   *
   * <p>Method under test: {@link HestonModel#getInitialState(MonteCarloProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] HestonModel.getInitialState(MonteCarloProcess)"})
  public void testGetInitialState() {
    // Arrange
    HestonModel hestonModel =
        new HestonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, Scheme.REFLECTION);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable[] actualInitialState = hestonModel.getInitialState(process);

    // Assert
    assertTrue(actualInitialState[0] instanceof Scalar);
    assertTrue(actualInitialState[1] instanceof Scalar);
    assertEquals(2, actualInitialState.length);
  }

  /**
   * Test {@link HestonModel#getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])}.
   *
   * <ul>
   *   <li>Then first element return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link HestonModel#getDrift(MonteCarloProcess, int, RandomVariable[],
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HestonModel.getDrift(MonteCarloProcess, int, RandomVariable[], RandomVariable[])"
  })
  public void testGetDrift_thenFirstElementReturnRandomVariableFromDoubleArray() {
    // Arrange
    HestonModel hestonModel =
        new HestonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, Scheme.REFLECTION);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable[] actualDrift =
        hestonModel.getDrift(
            process,
            1,
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(10.0d)
            },
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualDrift[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDrift[1] instanceof RandomVariableFromDoubleArray);
    assertEquals(2, actualDrift.length);
  }

  /**
   * Test {@link HestonModel#getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])}.
   *
   * <ul>
   *   <li>Then first element return {@link RandomVariableDifferentiableAAD}.
   * </ul>
   *
   * <p>Method under test: {@link HestonModel#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HestonModel.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading_thenFirstElementReturnRandomVariableDifferentiableAAD() {
    // Arrange
    HestonModel hestonModel =
        new HestonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, Scheme.REFLECTION);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.mult(Mockito.<RandomVariable>any()))
        .thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    RandomVariableAAD randomVariableAAD2 = mock(RandomVariableAAD.class);
    when(randomVariableAAD2.sqrt()).thenReturn(randomVariableAAD);

    RandomVariableAAD randomVariableAAD3 = mock(RandomVariableAAD.class);
    when(randomVariableAAD3.abs()).thenReturn(randomVariableAAD2);

    // Act
    RandomVariable[] actualFactorLoading =
        hestonModel.getFactorLoading(
            process,
            1,
            1,
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d), randomVariableAAD3});

    // Assert
    verify(randomVariableAAD3).abs();
    verify(randomVariableAAD).mult(isA(RandomVariable.class));
    verify(randomVariableAAD2).sqrt();
    assertTrue(actualFactorLoading[0] instanceof RandomVariableDifferentiableAAD);
    assertTrue(actualFactorLoading[1] instanceof RandomVariableDifferentiableAAD);
    assertEquals(2, actualFactorLoading.length);
  }

  /**
   * Test {@link HestonModel#getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])}.
   *
   * <ul>
   *   <li>Then first element return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link HestonModel#getFactorLoading(MonteCarloProcess, int, int,
   * RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] HestonModel.getFactorLoading(MonteCarloProcess, int, int, RandomVariable[])"
  })
  public void testGetFactorLoading_thenFirstElementReturnRandomVariableFromDoubleArray() {
    // Arrange
    HestonModel hestonModel =
        new HestonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, Scheme.REFLECTION);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable[] actualFactorLoading =
        hestonModel.getFactorLoading(
            process,
            1,
            1,
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    assertTrue(actualFactorLoading[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualFactorLoading[1] instanceof RandomVariableFromDoubleArray);
    assertEquals(2, actualFactorLoading.length);
  }

  /**
   * Test {@link HestonModel#applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)}.
   *
   * <p>Method under test: {@link HestonModel#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HestonModel.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform() {
    // Arrange
    HestonModel hestonModel =
        new HestonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, Scheme.REFLECTION);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));
    RandomVariableFromDoubleArray randomVariable = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualApplyStateSpaceTransformResult =
        hestonModel.applyStateSpaceTransform(process, 1, 1, randomVariable);

    // Assert
    assertSame(randomVariable, actualApplyStateSpaceTransformResult);
  }

  /**
   * Test {@link HestonModel#applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link HestonModel#applyStateSpaceTransform(MonteCarloProcess, int, int,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HestonModel.applyStateSpaceTransform(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransform_whenZero_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    HestonModel hestonModel =
        new HestonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, Scheme.REFLECTION);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualApplyStateSpaceTransformResult =
        hestonModel.applyStateSpaceTransform(
            process, 1, 0, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualApplyStateSpaceTransformResult instanceof RandomVariableFromDoubleArray);
    assertEquals(22026.465794806718d, actualApplyStateSpaceTransformResult.getAverage(), 0.0);
    assertEquals(22026.465794806718d, actualApplyStateSpaceTransformResult.getMax(), 0.0);
    assertEquals(22026.465794806718d, actualApplyStateSpaceTransformResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {22026.465794806718d},
        actualApplyStateSpaceTransformResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link HestonModel#applyStateSpaceTransformInverse(MonteCarloProcess, int, int,
   * RandomVariable)}.
   *
   * <p>Method under test: {@link HestonModel#applyStateSpaceTransformInverse(MonteCarloProcess,
   * int, int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HestonModel.applyStateSpaceTransformInverse(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse() {
    // Arrange
    HestonModel hestonModel =
        new HestonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, Scheme.REFLECTION);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));
    RandomVariableFromDoubleArray randomVariable = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariable actualApplyStateSpaceTransformInverseResult =
        hestonModel.applyStateSpaceTransformInverse(process, 1, 1, randomVariable);

    // Assert
    assertSame(randomVariable, actualApplyStateSpaceTransformInverseResult);
  }

  /**
   * Test {@link HestonModel#applyStateSpaceTransformInverse(MonteCarloProcess, int, int,
   * RandomVariable)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link HestonModel#applyStateSpaceTransformInverse(MonteCarloProcess,
   * int, int, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable HestonModel.applyStateSpaceTransformInverse(MonteCarloProcess, int, int, RandomVariable)"
  })
  public void testApplyStateSpaceTransformInverse_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    HestonModel hestonModel =
        new HestonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, Scheme.REFLECTION);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualApplyStateSpaceTransformInverseResult =
        hestonModel.applyStateSpaceTransformInverse(
            process, 1, 0, new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(
        actualApplyStateSpaceTransformInverseResult instanceof RandomVariableFromDoubleArray);
    assertEquals(2.302585092994046d, actualApplyStateSpaceTransformInverseResult.getAverage(), 0.0);
    assertEquals(2.302585092994046d, actualApplyStateSpaceTransformInverseResult.getMax(), 0.0);
    assertEquals(2.302585092994046d, actualApplyStateSpaceTransformInverseResult.getMin(), 0.0);
    assertArrayEquals(
        new double[] {2.302585092994046d},
        actualApplyStateSpaceTransformInverseResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link HestonModel#getNumeraire(MonteCarloProcess, double)}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link HestonModel#getNumeraire(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable HestonModel.getNumeraire(MonteCarloProcess, double)"})
  public void testGetNumeraire_thenReturnRandomVariableFromFloatArray() {
    // Arrange
    DiscountCurveInterpolation discountCurveForDiscountRate =
        mock(DiscountCurveInterpolation.class);
    when(discountCurveForDiscountRate.getDiscountFactor(anyDouble())).thenReturn(10.0d);
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    HestonModelDescriptor descriptor =
        new HestonModelDescriptor(
            referenceDate,
            10.0d,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            discountCurveForDiscountRate,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            10.0d);
    HestonModel hestonModel =
        new HestonModel(descriptor, Scheme.REFLECTION, new RandomVariableFloatFactory());
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualNumeraire = hestonModel.getNumeraire(process, 10.0d);

    // Assert
    verify(discountCurveForDiscountRate).getDiscountFactor(10.0d);
    assertTrue(actualNumeraire instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.abs() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.average() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.cos() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.expectation() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.expm1() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.invert() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.sin() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.sqrt() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.squared() instanceof RandomVariableFromFloatArray);
    assertTrue(actualNumeraire.variance() instanceof RandomVariableFromFloatArray);
    assertEquals(0.0d, actualNumeraire.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualNumeraire.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualNumeraire.getStandardError(), 0.0);
    assertEquals(0.0d, actualNumeraire.getVariance(), 0.0);
    assertEquals(0.1d, actualNumeraire.getAverage(), 0.0);
    assertEquals(0.1d, actualNumeraire.getMax(), 0.0);
    assertEquals(0.1d, actualNumeraire.getMin(), 0.0);
    assertEquals(1, actualNumeraire.getTypePriority());
    assertEquals(1, actualNumeraire.size());
    assertTrue(actualNumeraire.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualNumeraire.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {0.1d}, actualNumeraire.getRealizations(), 0.0);
  }

  /**
   * Test {@link HestonModel#getNumeraire(MonteCarloProcess, double)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link HestonModel#getNumeraire(MonteCarloProcess, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable HestonModel.getNumeraire(MonteCarloProcess, double)"})
  public void testGetNumeraire_thenReturnScalar() {
    // Arrange
    HestonModel hestonModel =
        new HestonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, Scheme.REFLECTION);
    BachelierModel model = new BachelierModel(10.0d, 10.0d, 10.0d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(mock(TimeDiscretization.class), 3, 10, 42);
    EulerSchemeFromProcessModel process =
        new EulerSchemeFromProcessModel(
            model, new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualNumeraire = hestonModel.getNumeraire(process, 10.0d);

    // Assert
    assertTrue(actualNumeraire instanceof Scalar);
    assertTrue(actualNumeraire.abs() instanceof Scalar);
    assertTrue(actualNumeraire.cos() instanceof Scalar);
    assertTrue(actualNumeraire.exp() instanceof Scalar);
    assertTrue(actualNumeraire.expm1() instanceof Scalar);
    assertTrue(actualNumeraire.invert() instanceof Scalar);
    assertTrue(actualNumeraire.isNaN() instanceof Scalar);
    assertTrue(actualNumeraire.sin() instanceof Scalar);
    assertTrue(actualNumeraire.sqrt() instanceof Scalar);
    assertTrue(actualNumeraire.squared() instanceof Scalar);
    assertTrue(actualNumeraire.variance() instanceof Scalar);
    assertNull(actualNumeraire.getRealizations());
    assertNull(actualNumeraire.getOperator());
    assertNull(actualNumeraire.getRealizationsStream());
    assertEquals(0, actualNumeraire.getTypePriority());
    assertEquals(0.0d, actualNumeraire.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualNumeraire.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualNumeraire.getStandardError(), 0.0);
    assertEquals(0.0d, actualNumeraire.getVariance(), 0.0);
    assertEquals(1, actualNumeraire.size());
    assertEquals(2.6881171418161356E43d, actualNumeraire.getAverage(), 0.0);
    assertEquals(2.6881171418161356E43d, actualNumeraire.getMax(), 0.0);
    assertEquals(2.6881171418161356E43d, actualNumeraire.getMin(), 0.0);
    assertTrue(actualNumeraire.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualNumeraire.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualNumeraire.expectation();
    assertSame(actualNumeraire, actualExpectationResult);
  }

  /**
   * Test {@link HestonModel#getRandomVariableForConstant(double)}.
   *
   * <p>Method under test: {@link HestonModel#getRandomVariableForConstant(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable HestonModel.getRandomVariableForConstant(double)"})
  public void testGetRandomVariableForConstant() {
    // Arrange
    HestonModel hestonModel =
        new HestonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, Scheme.REFLECTION);

    // Act
    RandomVariable actualRandomVariableForConstant =
        hestonModel.getRandomVariableForConstant(10.0d);

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
   * Test {@link HestonModel#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then InitialValue return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link HestonModel#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"HestonModel HestonModel.getCloneWithModifiedData(Map)"})
  public void testGetCloneWithModifiedData_whenHashMap_thenInitialValueReturnScalar() {
    // Arrange
    HestonModel hestonModel =
        new HestonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, Scheme.REFLECTION);

    // Act
    HestonModel actualCloneWithModifiedData = hestonModel.getCloneWithModifiedData(new HashMap<>());

    // Assert
    assertTrue(actualCloneWithModifiedData.getInitialValue() instanceof Scalar);
    assertTrue(actualCloneWithModifiedData.getKappa() instanceof Scalar);
    assertTrue(actualCloneWithModifiedData.getRho() instanceof Scalar);
    assertTrue(actualCloneWithModifiedData.getRiskFreeRate() instanceof Scalar);
    assertTrue(actualCloneWithModifiedData.getTheta() instanceof Scalar);
    assertTrue(actualCloneWithModifiedData.getVolatility() instanceof Scalar);
    assertTrue(actualCloneWithModifiedData.getXi() instanceof Scalar);
    assertNull(actualCloneWithModifiedData.getDiscountCurveForDiscountRate());
    assertNull(actualCloneWithModifiedData.getDiscountCurveForForwardRate());
    assertEquals(1, actualCloneWithModifiedData.getNumberOfFactors());
    assertEquals(2, actualCloneWithModifiedData.getNumberOfComponents());
    assertEquals(Scheme.REFLECTION, actualCloneWithModifiedData.getScheme());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link HestonModel#toString()}
   *   <li>{@link HestonModel#getDiscountCurveForDiscountRate()}
   *   <li>{@link HestonModel#getDiscountCurveForForwardRate()}
   *   <li>{@link HestonModel#getInitialValue()}
   *   <li>{@link HestonModel#getKappa()}
   *   <li>{@link HestonModel#getNumberOfComponents()}
   *   <li>{@link HestonModel#getNumberOfFactors()}
   *   <li>{@link HestonModel#getRho()}
   *   <li>{@link HestonModel#getRiskFreeRate()}
   *   <li>{@link HestonModel#getScheme()}
   *   <li>{@link HestonModel#getTheta()}
   *   <li>{@link HestonModel#getVolatility()}
   *   <li>{@link HestonModel#getXi()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DiscountCurve HestonModel.getDiscountCurveForDiscountRate()",
    "DiscountCurve HestonModel.getDiscountCurveForForwardRate()",
    "RandomVariable HestonModel.getInitialValue()",
    "RandomVariable HestonModel.getKappa()",
    "int HestonModel.getNumberOfComponents()",
    "int HestonModel.getNumberOfFactors()",
    "RandomVariable HestonModel.getRho()",
    "RandomVariable HestonModel.getRiskFreeRate()",
    "Scheme HestonModel.getScheme()",
    "RandomVariable HestonModel.getTheta()",
    "RandomVariable HestonModel.getVolatility()",
    "RandomVariable HestonModel.getXi()",
    "String HestonModel.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    HestonModel hestonModel =
        new HestonModel(10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, Scheme.REFLECTION);

    // Act
    String actualToStringResult = hestonModel.toString();
    DiscountCurve actualDiscountCurveForDiscountRate =
        hestonModel.getDiscountCurveForDiscountRate();
    DiscountCurve actualDiscountCurveForForwardRate = hestonModel.getDiscountCurveForForwardRate();
    RandomVariable actualInitialValue = hestonModel.getInitialValue();
    RandomVariable actualKappa = hestonModel.getKappa();
    int actualNumberOfComponents = hestonModel.getNumberOfComponents();
    int actualNumberOfFactors = hestonModel.getNumberOfFactors();
    RandomVariable actualRho = hestonModel.getRho();
    RandomVariable actualRiskFreeRate = hestonModel.getRiskFreeRate();
    Scheme actualScheme = hestonModel.getScheme();
    RandomVariable actualTheta = hestonModel.getTheta();
    RandomVariable actualVolatility = hestonModel.getVolatility();

    // Assert
    assertTrue(actualInitialValue instanceof Scalar);
    assertTrue(actualKappa instanceof Scalar);
    assertTrue(actualRho instanceof Scalar);
    assertTrue(actualRiskFreeRate instanceof Scalar);
    assertTrue(actualTheta instanceof Scalar);
    assertTrue(actualVolatility instanceof Scalar);
    assertTrue(hestonModel.getXi() instanceof Scalar);
    assertEquals(
        "HestonModel [initialValue=Scalar [value=10.0, filtrationTime=-Infinity, typePriority()=0], riskFreeRate"
            + "=Scalar [value=10.0, filtrationTime=-Infinity, typePriority()=0], volatility=Scalar [value=10.0,"
            + " filtrationTime=-Infinity, typePriority()=0], theta=Scalar [value=10.0, filtrationTime=-Infinity,"
            + " typePriority()=0], kappa=Scalar [value=10.0, filtrationTime=-Infinity, typePriority()=0], xi=Scalar"
            + " [value=10.0, filtrationTime=-Infinity, typePriority()=0], rho=Scalar [value=10.0, filtrationTime=-Infinity,"
            + " typePriority()=0], scheme=REFLECTION]",
        actualToStringResult);
    assertNull(actualDiscountCurveForDiscountRate);
    assertNull(actualDiscountCurveForForwardRate);
    assertEquals(1, actualNumberOfFactors);
    assertEquals(2, actualNumberOfComponents);
    assertEquals(Scheme.REFLECTION, actualScheme);
  }
}
