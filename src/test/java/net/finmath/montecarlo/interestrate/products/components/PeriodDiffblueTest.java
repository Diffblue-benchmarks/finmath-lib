package net.finmath.montecarlo.interestrate.products.components;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionWithControlVariate;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.interestrate.LIBORMonteCarloSimulationFromLIBORModel;
import net.finmath.montecarlo.interestrate.TermStructureMonteCarloSimulationModel;
import net.finmath.montecarlo.interestrate.models.covariance.AbstractLIBORCovarianceModelParametric;
import net.finmath.montecarlo.interestrate.models.covariance.BlendedLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.models.covariance.HullWhiteLocalVolatilityModel;
import net.finmath.montecarlo.interestrate.products.ForwardRateVolatilitySurfaceCurvature;
import net.finmath.montecarlo.interestrate.simple.SimpleLIBORMarketModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PeriodDiffblueTest {
  /**
   * Test {@link Period#Period(double, double, double, double, Notional, AbstractProductComponent,
   * double, boolean, boolean, boolean)}.
   *
   * <p>Method under test: {@link Period#Period(double, double, double, double, Notional,
   * AbstractProductComponent, double, boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Period.<init>(double, double, double, double, Notional, AbstractProductComponent, double, boolean, boolean, boolean)"
  })
  public void testNewPeriod() {
    // Arrange
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Numeraire index = new Numeraire();

    // Act
    Period actualPeriod =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, index, 10.0d, true, true, true);

    // Assert
    Notional notional2 = actualPeriod.getNotional();
    assertTrue(notional2 instanceof NotionalFromConstant);
    AbstractProductComponent index2 = actualPeriod.getIndex();
    assertTrue(index2 instanceof Numeraire);
    assertNull(index2.getCurrency());
    assertNull(actualPeriod.getCurrency());
    assertNull(notional2.getCurrency());
    assertNull(actualPeriod.getReferenceDate());
    assertEquals(10.0d, actualPeriod.getDaycountFraction(), 0.0);
    assertEquals(10.0d, actualPeriod.getFixingDate(), 0.0);
    assertEquals(10.0d, actualPeriod.getPaymentDate(), 0.0);
    assertEquals(10.0d, actualPeriod.getPeriodEnd(), 0.0);
    assertEquals(10.0d, actualPeriod.getPeriodStart(), 0.0);
    assertSame(notional, notional2);
    assertSame(index, index2);
  }

  /**
   * Test {@link Period#Period(double, double, double, double, Notional, AbstractProductComponent,
   * double, boolean, boolean, boolean, boolean)}.
   *
   * <p>Method under test: {@link Period#Period(double, double, double, double, Notional,
   * AbstractProductComponent, double, boolean, boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Period.<init>(double, double, double, double, Notional, AbstractProductComponent, double, boolean, boolean, boolean, boolean)"
  })
  public void testNewPeriod2() {
    // Arrange
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Numeraire index = new Numeraire();

    // Act
    Period actualPeriod =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, index, 10.0d, true, true, true, true);

    // Assert
    Notional notional2 = actualPeriod.getNotional();
    assertTrue(notional2 instanceof NotionalFromConstant);
    AbstractProductComponent index2 = actualPeriod.getIndex();
    assertTrue(index2 instanceof Numeraire);
    assertNull(index2.getCurrency());
    assertNull(actualPeriod.getCurrency());
    assertNull(notional2.getCurrency());
    assertNull(actualPeriod.getReferenceDate());
    assertEquals(10.0d, actualPeriod.getDaycountFraction(), 0.0);
    assertEquals(10.0d, actualPeriod.getFixingDate(), 0.0);
    assertEquals(10.0d, actualPeriod.getPaymentDate(), 0.0);
    assertEquals(10.0d, actualPeriod.getPeriodEnd(), 0.0);
    assertEquals(10.0d, actualPeriod.getPeriodStart(), 0.0);
    assertSame(notional, notional2);
    assertSame(index, index2);
  }

  /**
   * Test {@link Period#Period(double, double, double, double, Notional, AbstractProductComponent,
   * boolean, boolean, boolean)}.
   *
   * <p>Method under test: {@link Period#Period(double, double, double, double, Notional,
   * AbstractProductComponent, boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Period.<init>(double, double, double, double, Notional, AbstractProductComponent, boolean, boolean, boolean)"
  })
  public void testNewPeriod3() {
    // Arrange
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Numeraire index = new Numeraire();

    // Act
    Period actualPeriod = new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, index, true, true, true);

    // Assert
    Notional notional2 = actualPeriod.getNotional();
    assertTrue(notional2 instanceof NotionalFromConstant);
    AbstractProductComponent index2 = actualPeriod.getIndex();
    assertTrue(index2 instanceof Numeraire);
    assertNull(index2.getCurrency());
    assertNull(actualPeriod.getCurrency());
    assertNull(notional2.getCurrency());
    assertNull(actualPeriod.getReferenceDate());
    assertEquals(0.0d, actualPeriod.getDaycountFraction(), 0.0);
    assertEquals(10.0d, actualPeriod.getFixingDate(), 0.0);
    assertEquals(10.0d, actualPeriod.getPaymentDate(), 0.0);
    assertEquals(10.0d, actualPeriod.getPeriodEnd(), 0.0);
    assertEquals(10.0d, actualPeriod.getPeriodStart(), 0.0);
    assertSame(notional, notional2);
    assertSame(index, index2);
  }

  /**
   * Test {@link Period#Period(LocalDateTime, double, double, double, double, Notional,
   * AbstractProductComponent, double, boolean, boolean, boolean, boolean)}.
   *
   * <p>Method under test: {@link Period#Period(LocalDateTime, double, double, double, double,
   * Notional, AbstractProductComponent, double, boolean, boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Period.<init>(LocalDateTime, double, double, double, double, Notional, AbstractProductComponent, double, boolean, boolean, boolean, boolean)"
  })
  public void testNewPeriod4() {
    // Arrange
    LocalDate ofResult = LocalDate.of(1970, 1, 1);
    LocalDateTime referenceDate = ofResult.atStartOfDay();
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Numeraire index = new Numeraire();

    // Act
    Period actualPeriod =
        new Period(
            referenceDate,
            10.0d,
            10.0d,
            10.0d,
            10.0d,
            notional,
            index,
            10.0d,
            true,
            true,
            true,
            true);

    // Assert
    Notional notional2 = actualPeriod.getNotional();
    assertTrue(notional2 instanceof NotionalFromConstant);
    AbstractProductComponent index2 = actualPeriod.getIndex();
    assertTrue(index2 instanceof Numeraire);
    assertNull(index2.getCurrency());
    assertNull(actualPeriod.getCurrency());
    assertNull(notional2.getCurrency());
    assertEquals(10.0d, actualPeriod.getDaycountFraction(), 0.0);
    assertEquals(10.0d, actualPeriod.getFixingDate(), 0.0);
    assertEquals(10.0d, actualPeriod.getPaymentDate(), 0.0);
    assertEquals(10.0d, actualPeriod.getPeriodEnd(), 0.0);
    assertEquals(10.0d, actualPeriod.getPeriodStart(), 0.0);
    assertSame(notional, notional2);
    assertSame(index, index2);
    LocalDateTime referenceDate2 = actualPeriod.getReferenceDate();
    assertSame(referenceDate, referenceDate2);
    assertSame(ofResult, referenceDate2.toLocalDate());
  }

  /**
   * Test {@link Period#getValue(double, TermStructureMonteCarloSimulationModel)} with {@code
   * double}, {@code TermStructureMonteCarloSimulationModel}.
   *
   * <p>Method under test: {@link Period#getValue(double, TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Period.getValue(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetValueWithDoubleTermStructureMonteCarloSimulationModel()
      throws CalculationException {
    // Arrange
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Period period =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, new Numeraire(), true, true, true);

    BrownianMotion brownianMotion = mock(BrownianMotion.class);
    when(brownianMotion.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    BrownianMotionWithControlVariate stochasticDriver =
        new BrownianMotionWithControlVariate(brownianMotion);
    EulerSchemeFromProcessModel process = new EulerSchemeFromProcessModel(null, stochasticDriver);

    // Act
    RandomVariable actualValue =
        period.getValue(10.0d, new LIBORMonteCarloSimulationFromLIBORModel(process));

    // Assert
    verify(brownianMotion).getTimeDiscretization();
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualValue.getAverage(), 0.0);
    assertEquals(0.0d, actualValue.getMax(), 0.0);
    assertEquals(0.0d, actualValue.getMin(), 0.0);
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, actualValue.getTypePriority());
    assertEquals(1, actualValue.size());
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link Period#getCoupon(double, TermStructureMonteCarloSimulationModel)}.
   *
   * <p>Method under test: {@link Period#getCoupon(double, TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Period.getCoupon(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetCoupon() throws CalculationException {
    // Arrange
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Period period =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, new Numeraire(), true, true, true);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization =
        new TenorFromArray(new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualCoupon = period.getCoupon(10.0d, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCoupon instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCoupon.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCoupon.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCoupon.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCoupon.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCoupon.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCoupon.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCoupon.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCoupon.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCoupon.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCoupon.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualCoupon.getRealizations(),
        0.0);
  }

  /**
   * Test {@link Period#getCoupon(double, TermStructureMonteCarloSimulationModel)}.
   *
   * <ul>
   *   <li>Then abs return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link Period#getCoupon(double, TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Period.getCoupon(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetCoupon_thenAbsReturnRandomVariableFromDoubleArray()
      throws CalculationException {
    // Arrange
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Period period =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, new Numeraire(), true, true, true);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualCoupon = period.getCoupon(10.0d, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCoupon instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCoupon.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCoupon.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCoupon.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCoupon.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCoupon.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCoupon.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCoupon.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCoupon.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCoupon.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCoupon.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualCoupon.getRealizations(),
        0.0);
  }

  /**
   * Test {@link Period#getCoupon(double, TermStructureMonteCarloSimulationModel)}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with zero.
   * </ul>
   *
   * <p>Method under test: {@link Period#getCoupon(double, TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Period.getCoupon(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetCoupon_thenReturnRealizationsIsArrayOfDoubleWithZero()
      throws CalculationException {
    // Arrange
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    ForwardRateVolatilitySurfaceCurvature underlying1 =
        new ForwardRateVolatilitySurfaceCurvature(1.0d);
    Choice index = new Choice(1.0d, underlying1, new ForwardRateVolatilitySurfaceCurvature(1.0d));

    Period period = new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, index, true, true, true);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualCoupon = period.getCoupon(10.0d, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCoupon instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualCoupon.size());
    assertTrue(actualCoupon.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualCoupon.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualCoupon.getRealizations(), 0.0);
  }

  /**
   * Test {@link Period#getCoupon(double, TermStructureMonteCarloSimulationModel)}.
   *
   * <ul>
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link Period#getCoupon(double, TermStructureMonteCarloSimulationModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable Period.getCoupon(double, TermStructureMonteCarloSimulationModel)"
  })
  public void testGetCoupon_thenReturnScalar() throws CalculationException {
    // Arrange
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Period period =
        new Period(
            10.0d, 10.0d, 10.0d, 10.0d, notional, new Cashflow(1.0d, 1.0d, true), true, true, true);

    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    BrownianMotionFromMersenneRandomNumbers brownianMotion =
        new BrownianMotionFromMersenneRandomNumbers(new TenorFromArray(10.0d, 10, 0.5d), 3, 10, 42);

    SimpleLIBORMarketModel model =
        new SimpleLIBORMarketModel(
            liborPeriodDiscretization,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            covarianceModel2,
            new BrownianMotionWithControlVariate(brownianMotion));

    // Act
    RandomVariable actualCoupon = period.getCoupon(10.0d, model);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertTrue(actualCoupon instanceof Scalar);
    assertTrue(actualCoupon.abs() instanceof Scalar);
    assertTrue(actualCoupon.cos() instanceof Scalar);
    assertTrue(actualCoupon.exp() instanceof Scalar);
    assertTrue(actualCoupon.expm1() instanceof Scalar);
    assertTrue(actualCoupon.invert() instanceof Scalar);
    assertTrue(actualCoupon.isNaN() instanceof Scalar);
    assertTrue(actualCoupon.sin() instanceof Scalar);
    assertTrue(actualCoupon.sqrt() instanceof Scalar);
    assertTrue(actualCoupon.squared() instanceof Scalar);
    assertTrue(actualCoupon.variance() instanceof Scalar);
    assertNull(actualCoupon.getRealizations());
    assertNull(actualCoupon.getOperator());
    assertNull(actualCoupon.getRealizationsStream());
    assertEquals(0, actualCoupon.getTypePriority());
    assertEquals(0.0d, actualCoupon.getAverage(), 0.0);
    assertEquals(0.0d, actualCoupon.getMax(), 0.0);
    assertEquals(0.0d, actualCoupon.getMin(), 0.0);
    assertEquals(0.0d, actualCoupon.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualCoupon.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualCoupon.getStandardError(), 0.0);
    assertEquals(0.0d, actualCoupon.getVariance(), 0.0);
    assertEquals(1, actualCoupon.size());
    assertTrue(actualCoupon.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualCoupon.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualCoupon.expectation();
    assertSame(actualCoupon, actualExpectationResult);
  }

  /**
   * Test {@link Period#toString()}.
   *
   * <p>Method under test: {@link Period#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String Period.toString()"})
  public void testToString() {
    // Arrange
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Period period =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, new Numeraire(), true, true, true);

    // Act and Assert
    assertEquals(
        "Period [couponFlow=true, notionalFlow=true, payer=true, toString()=AbstractPeriod [periodStart=10.0,"
            + " periodEnd=10.0, fixingDate=10.0, paymentDate=10.0, notional=Notional [currency=null, notional"
            + "=RandomVariableFromDoubleArray[ realizations=10.0, isDeterministic()=true, filtrationTime=0.0,"
            + " typePriority=1]], index=AbstractMonteCarloProduct [currency=null], daycountFraction=0.0]]",
        period.toString());
  }
}
