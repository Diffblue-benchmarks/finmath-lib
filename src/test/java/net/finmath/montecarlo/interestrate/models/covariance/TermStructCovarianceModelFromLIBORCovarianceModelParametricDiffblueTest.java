package net.finmath.montecarlo.interestrate.models.covariance;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class TermStructCovarianceModelFromLIBORCovarianceModelParametricDiffblueTest {
  /**
   * Test {@link
   * TermStructCovarianceModelFromLIBORCovarianceModelParametric#getScaledTenorTime(double,
   * double)}.
   *
   * <p>Method under test: {@link
   * TermStructCovarianceModelFromLIBORCovarianceModelParametric#getScaledTenorTime(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double TermStructCovarianceModelFromLIBORCovarianceModelParametric.getScaledTenorTime(double, double)"
  })
  public void testGetScaledTenorTime() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TermStructureTenorTimeScalingPicewiseConstant tenorTimeScalingModel =
        new TermStructureTenorTimeScalingPicewiseConstant(
            new TenorFromArray(10.0d, 3, 0.5d), new double[] {10.0d, -0.9d, 10.0d, -0.9d});

    TermStructCovarianceModelFromLIBORCovarianceModelParametric
        termStructCovarianceModelFromLIBORCovarianceModelParametric =
            new TermStructCovarianceModelFromLIBORCovarianceModelParametric(
                tenorTimeScalingModel, covarianceModel2);

    // Act
    double actualScaledTenorTime =
        termStructCovarianceModelFromLIBORCovarianceModelParametric.getScaledTenorTime(
            10.0d, 10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals(0.0d, actualScaledTenorTime, 0.0);
  }

  /**
   * Test {@link
   * TermStructCovarianceModelFromLIBORCovarianceModelParametric#getScaledTenorTime(double,
   * double)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * TermStructCovarianceModelFromLIBORCovarianceModelParametric#getScaledTenorTime(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double TermStructCovarianceModelFromLIBORCovarianceModelParametric.getScaledTenorTime(double, double)"
  })
  public void testGetScaledTenorTime_thenReturnZero() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    TermStructCovarianceModelFromLIBORCovarianceModelParametric
        termStructCovarianceModelFromLIBORCovarianceModelParametric =
            new TermStructCovarianceModelFromLIBORCovarianceModelParametric(
                null,
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d));

    // Act
    double actualScaledTenorTime =
        termStructCovarianceModelFromLIBORCovarianceModelParametric.getScaledTenorTime(
            10.0d, 10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals(0.0d, actualScaledTenorTime, 0.0);
  }

  /**
   * Test {@link
   * TermStructCovarianceModelFromLIBORCovarianceModelParametric#getScaledTenorTime(double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 9.5}.
   * </ul>
   *
   * <p>Method under test: {@link
   * TermStructCovarianceModelFromLIBORCovarianceModelParametric#getScaledTenorTime(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double TermStructCovarianceModelFromLIBORCovarianceModelParametric.getScaledTenorTime(double, double)"
  })
  public void testGetScaledTenorTime_when05_thenReturn95() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    TermStructCovarianceModelFromLIBORCovarianceModelParametric
        termStructCovarianceModelFromLIBORCovarianceModelParametric =
            new TermStructCovarianceModelFromLIBORCovarianceModelParametric(
                null,
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d));

    // Act
    double actualScaledTenorTime =
        termStructCovarianceModelFromLIBORCovarianceModelParametric.getScaledTenorTime(0.5d, 10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals(9.5d, actualScaledTenorTime, 0.0);
  }

  /**
   * Test {@link
   * TermStructCovarianceModelFromLIBORCovarianceModelParametric#getScaledTenorTime(double,
   * double)}.
   *
   * <ul>
   *   <li>When {@code -0.5}.
   *   <li>Then return {@code 10.5}.
   * </ul>
   *
   * <p>Method under test: {@link
   * TermStructCovarianceModelFromLIBORCovarianceModelParametric#getScaledTenorTime(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double TermStructCovarianceModelFromLIBORCovarianceModelParametric.getScaledTenorTime(double, double)"
  })
  public void testGetScaledTenorTime_when05_thenReturn105() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    TermStructCovarianceModelFromLIBORCovarianceModelParametric
        termStructCovarianceModelFromLIBORCovarianceModelParametric =
            new TermStructCovarianceModelFromLIBORCovarianceModelParametric(
                null,
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d));

    // Act
    double actualScaledTenorTime =
        termStructCovarianceModelFromLIBORCovarianceModelParametric.getScaledTenorTime(
            -0.5d, 10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals(10.5d, actualScaledTenorTime, 0.0);
  }

  /**
   * Test {@link
   * TermStructCovarianceModelFromLIBORCovarianceModelParametric#getScaledTenorTime(double,
   * double)}.
   *
   * <ul>
   *   <li>When {@link Double#NaN}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link
   * TermStructCovarianceModelFromLIBORCovarianceModelParametric#getScaledTenorTime(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double TermStructCovarianceModelFromLIBORCovarianceModelParametric.getScaledTenorTime(double, double)"
  })
  public void testGetScaledTenorTime_whenNaN_thenReturnNaN() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    TermStructCovarianceModelFromLIBORCovarianceModelParametric
        termStructCovarianceModelFromLIBORCovarianceModelParametric =
            new TermStructCovarianceModelFromLIBORCovarianceModelParametric(
                null,
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d));

    // Act
    double actualScaledTenorTime =
        termStructCovarianceModelFromLIBORCovarianceModelParametric.getScaledTenorTime(
            Double.NaN, 10.0d);

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals(Double.NaN, actualScaledTenorTime, 0.0);
  }

  /**
   * Test {@link TermStructCovarianceModelFromLIBORCovarianceModelParametric#getNumberOfFactors()}.
   *
   * <ul>
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link
   * TermStructCovarianceModelFromLIBORCovarianceModelParametric#getNumberOfFactors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int TermStructCovarianceModelFromLIBORCovarianceModelParametric.getNumberOfFactors()"
  })
  public void testGetNumberOfFactors_thenReturnThree() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    TermStructureTenorTimeScalingPicewiseConstant tenorTimeScalingModel =
        new TermStructureTenorTimeScalingPicewiseConstant(
            new TenorFromArray(10.0d, 1, 0.5d), new double[] {10.0d, -0.9d, 10.0d, -0.9d});

    TermStructCovarianceModelFromLIBORCovarianceModelParametric
        termStructCovarianceModelFromLIBORCovarianceModelParametric =
            new TermStructCovarianceModelFromLIBORCovarianceModelParametric(
                tenorTimeScalingModel, covarianceModel2);

    // Act
    int actualNumberOfFactors =
        termStructCovarianceModelFromLIBORCovarianceModelParametric.getNumberOfFactors();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    assertEquals(3, actualNumberOfFactors);
  }

  /**
   * Test {@link TermStructCovarianceModelFromLIBORCovarianceModelParametric#getParameter()}.
   *
   * <p>Method under test: {@link
   * TermStructCovarianceModelFromLIBORCovarianceModelParametric#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] TermStructCovarianceModelFromLIBORCovarianceModelParametric.getParameter()"
  })
  public void testGetParameter() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getParameter())
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    TermStructCovarianceModelFromLIBORCovarianceModelParametric
        termStructCovarianceModelFromLIBORCovarianceModelParametric =
            new TermStructCovarianceModelFromLIBORCovarianceModelParametric(
                null,
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d));

    // Act
    double[] actualParameter =
        termStructCovarianceModelFromLIBORCovarianceModelParametric.getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(new double[] {10.0d, 10.0d}, actualParameter, 0.0);
  }

  /**
   * Test {@link TermStructCovarianceModelFromLIBORCovarianceModelParametric#getParameter()}.
   *
   * <p>Method under test: {@link
   * TermStructCovarianceModelFromLIBORCovarianceModelParametric#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] TermStructCovarianceModelFromLIBORCovarianceModelParametric.getParameter()"
  })
  public void testGetParameter2() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getParameter()).thenReturn(new RandomVariable[] {Scalar.of(10.0d)});
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    TermStructCovarianceModelFromLIBORCovarianceModelParametric
        termStructCovarianceModelFromLIBORCovarianceModelParametric =
            new TermStructCovarianceModelFromLIBORCovarianceModelParametric(
                null,
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d));

    // Act
    double[] actualParameter =
        termStructCovarianceModelFromLIBORCovarianceModelParametric.getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(new double[] {10.0d, 10.0d}, actualParameter, 0.0);
  }

  /**
   * Test {@link TermStructCovarianceModelFromLIBORCovarianceModelParametric#getParameter()}.
   *
   * <p>Method under test: {@link
   * TermStructCovarianceModelFromLIBORCovarianceModelParametric#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] TermStructCovarianceModelFromLIBORCovarianceModelParametric.getParameter()"
  })
  public void testGetParameter3() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(covarianceModel.getParameter())
        .thenReturn(
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(10.0d)
            });
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    TermStructCovarianceModelFromLIBORCovarianceModelParametric
        termStructCovarianceModelFromLIBORCovarianceModelParametric =
            new TermStructCovarianceModelFromLIBORCovarianceModelParametric(
                null,
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d));

    // Act
    double[] actualParameter =
        termStructCovarianceModelFromLIBORCovarianceModelParametric.getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(new double[] {10.0d, 10.0d, 10.0d}, actualParameter, 0.0);
  }

  /**
   * Test {@link TermStructCovarianceModelFromLIBORCovarianceModelParametric#getParameter()}.
   *
   * <p>Method under test: {@link
   * TermStructCovarianceModelFromLIBORCovarianceModelParametric#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] TermStructCovarianceModelFromLIBORCovarianceModelParametric.getParameter()"
  })
  public void testGetParameter4() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getParameter())
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);
    TermStructCovarianceModelFromLIBORCovarianceModelParametric
        termStructCovarianceModelFromLIBORCovarianceModelParametric =
            new TermStructCovarianceModelFromLIBORCovarianceModelParametric(
                null, new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d));

    // Act
    double[] actualParameter =
        termStructCovarianceModelFromLIBORCovarianceModelParametric.getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(new double[] {10.0d, 10.0d, 10.0d}, actualParameter, 0.0);
  }

  /**
   * Test {@link TermStructCovarianceModelFromLIBORCovarianceModelParametric#getParameter()}.
   *
   * <p>Method under test: {@link
   * TermStructCovarianceModelFromLIBORCovarianceModelParametric#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] TermStructCovarianceModelFromLIBORCovarianceModelParametric.getParameter()"
  })
  public void testGetParameter5() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getParameter()).thenReturn(new RandomVariable[] {Scalar.of(10.0d)});
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);
    TermStructCovarianceModelFromLIBORCovarianceModelParametric
        termStructCovarianceModelFromLIBORCovarianceModelParametric =
            new TermStructCovarianceModelFromLIBORCovarianceModelParametric(
                null, new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d));

    // Act
    double[] actualParameter =
        termStructCovarianceModelFromLIBORCovarianceModelParametric.getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(new double[] {10.0d, 10.0d, 10.0d}, actualParameter, 0.0);
  }

  /**
   * Test {@link TermStructCovarianceModelFromLIBORCovarianceModelParametric#getParameter()}.
   *
   * <p>Method under test: {@link
   * TermStructCovarianceModelFromLIBORCovarianceModelParametric#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double[] TermStructCovarianceModelFromLIBORCovarianceModelParametric.getParameter()"
  })
  public void testGetParameter6() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getParameter())
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel2 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);
    BlendedLocalVolatilityModel covarianceModel3 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel2, 10.0d, true);
    HullWhiteLocalVolatilityModel covarianceModel4 =
        new HullWhiteLocalVolatilityModel(covarianceModel3, 10.0d);
    BlendedLocalVolatilityModel covarianceModel5 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel4, 10.0d, true);
    TermStructCovarianceModelFromLIBORCovarianceModelParametric
        termStructCovarianceModelFromLIBORCovarianceModelParametric =
            new TermStructCovarianceModelFromLIBORCovarianceModelParametric(
                null, new HullWhiteLocalVolatilityModel(covarianceModel5, 10.0d));

    // Act
    double[] actualParameter =
        termStructCovarianceModelFromLIBORCovarianceModelParametric.getParameter();

    // Assert
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel).getParameter();
    assertArrayEquals(new double[] {10.0d, 10.0d, 10.0d, 10.0d}, actualParameter, 0.0);
  }

  /**
   * Test {@link
   * TermStructCovarianceModelFromLIBORCovarianceModelParametric#getCloneWithModifiedParameters(double[])}.
   *
   * <p>Method under test: {@link
   * TermStructCovarianceModelFromLIBORCovarianceModelParametric#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TermStructureCovarianceModelParametric TermStructCovarianceModelFromLIBORCovarianceModelParametric.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParameters() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    AbstractLIBORCovarianceModelParametric covarianceModel2 =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel2.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel2.getCloneWithModifiedParameters(Mockito.<RandomVariable[]>any()))
        .thenReturn(hullWhiteLocalVolatilityModel);
    when(covarianceModel2.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel2.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    TermStructCovarianceModelFromLIBORCovarianceModelParametric
        termStructCovarianceModelFromLIBORCovarianceModelParametric =
            new TermStructCovarianceModelFromLIBORCovarianceModelParametric(
                null,
                new HullWhiteLocalVolatilityModel(
                    new BlendedLocalVolatilityModel(covarianceModel2, 10.0d, true), 10.0d));

    // Act
    TermStructureCovarianceModelParametric actualCloneWithModifiedParameters =
        termStructCovarianceModelFromLIBORCovarianceModelParametric.getCloneWithModifiedParameters(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel2).getLiborPeriodDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel2).getNumberOfFactors();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel2).getTimeDiscretization();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel2).getCloneWithModifiedParameters(isA(RandomVariable[].class));
    assertTrue(
        actualCloneWithModifiedParameters
            instanceof TermStructCovarianceModelFromLIBORCovarianceModelParametric);
    assertEquals(3, actualCloneWithModifiedParameters.getNumberOfFactors());
    assertArrayEquals(
        new double[] {10.0d, 0.5d},
        termStructCovarianceModelFromLIBORCovarianceModelParametric.getParameter(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 0.5d}, actualCloneWithModifiedParameters.getParameter(), 0.0);
  }

  /**
   * Test {@link
   * TermStructCovarianceModelFromLIBORCovarianceModelParametric#getCloneWithModifiedParameters(double[])}.
   *
   * <p>Method under test: {@link
   * TermStructCovarianceModelFromLIBORCovarianceModelParametric#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TermStructureCovarianceModelParametric TermStructCovarianceModelFromLIBORCovarianceModelParametric.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParameters2() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    AbstractLIBORCovarianceModelParametric covarianceModel2 =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel2.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel2.getCloneWithModifiedParameters(Mockito.<RandomVariable[]>any()))
        .thenReturn(hullWhiteLocalVolatilityModel);
    when(covarianceModel2.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel2.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel3 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel2, 10.0d, true), 10.0d);
    BlendedLocalVolatilityModel covarianceModel4 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel3, 10.0d, true);
    TermStructCovarianceModelFromLIBORCovarianceModelParametric
        termStructCovarianceModelFromLIBORCovarianceModelParametric =
            new TermStructCovarianceModelFromLIBORCovarianceModelParametric(
                null, new HullWhiteLocalVolatilityModel(covarianceModel4, 10.0d));

    // Act
    TermStructureCovarianceModelParametric actualCloneWithModifiedParameters =
        termStructCovarianceModelFromLIBORCovarianceModelParametric.getCloneWithModifiedParameters(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel2).getLiborPeriodDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel2).getNumberOfFactors();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel2).getTimeDiscretization();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel2).getCloneWithModifiedParameters(isA(RandomVariable[].class));
    assertTrue(
        actualCloneWithModifiedParameters
            instanceof TermStructCovarianceModelFromLIBORCovarianceModelParametric);
    assertEquals(3, actualCloneWithModifiedParameters.getNumberOfFactors());
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 0.5d},
        termStructCovarianceModelFromLIBORCovarianceModelParametric.getParameter(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 0.5d}, actualCloneWithModifiedParameters.getParameter(), 0.0);
  }

  /**
   * Test {@link
   * TermStructCovarianceModelFromLIBORCovarianceModelParametric#getCloneWithModifiedParameters(double[])}.
   *
   * <p>Method under test: {@link
   * TermStructCovarianceModelFromLIBORCovarianceModelParametric#getCloneWithModifiedParameters(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TermStructureCovarianceModelParametric TermStructCovarianceModelFromLIBORCovarianceModelParametric.getCloneWithModifiedParameters(double[])"
  })
  public void testGetCloneWithModifiedParameters3() {
    // Arrange
    AbstractLIBORCovarianceModelParametric covarianceModel =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel hullWhiteLocalVolatilityModel =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel, 10.0d, true), 10.0d);

    AbstractLIBORCovarianceModelParametric covarianceModel2 =
        mock(AbstractLIBORCovarianceModelParametric.class);
    when(covarianceModel2.getNumberOfFactors()).thenReturn(3);
    when(covarianceModel2.getCloneWithModifiedParameters(Mockito.<RandomVariable[]>any()))
        .thenReturn(hullWhiteLocalVolatilityModel);
    when(covarianceModel2.getLiborPeriodDiscretization())
        .thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    when(covarianceModel2.getTimeDiscretization()).thenReturn(new TenorFromArray(10.0d, 10, 0.5d));
    HullWhiteLocalVolatilityModel covarianceModel3 =
        new HullWhiteLocalVolatilityModel(
            new BlendedLocalVolatilityModel(covarianceModel2, 10.0d, true), 10.0d);
    BlendedLocalVolatilityModel covarianceModel4 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel3, 10.0d, true);
    HullWhiteLocalVolatilityModel covarianceModel5 =
        new HullWhiteLocalVolatilityModel(covarianceModel4, 10.0d);
    BlendedLocalVolatilityModel covarianceModel6 =
        new BlendedLocalVolatilityModel(
            new RandomVariableFloatFactory(), covarianceModel5, 10.0d, true);
    TermStructCovarianceModelFromLIBORCovarianceModelParametric
        termStructCovarianceModelFromLIBORCovarianceModelParametric =
            new TermStructCovarianceModelFromLIBORCovarianceModelParametric(
                null, new HullWhiteLocalVolatilityModel(covarianceModel6, 10.0d));

    // Act
    TermStructureCovarianceModelParametric actualCloneWithModifiedParameters =
        termStructCovarianceModelFromLIBORCovarianceModelParametric.getCloneWithModifiedParameters(
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(covarianceModel2).getLiborPeriodDiscretization();
    verify(covarianceModel).getLiborPeriodDiscretization();
    verify(covarianceModel2).getNumberOfFactors();
    verify(covarianceModel).getNumberOfFactors();
    verify(covarianceModel2).getTimeDiscretization();
    verify(covarianceModel).getTimeDiscretization();
    verify(covarianceModel2).getCloneWithModifiedParameters(isA(RandomVariable[].class));
    assertTrue(
        actualCloneWithModifiedParameters
            instanceof TermStructCovarianceModelFromLIBORCovarianceModelParametric);
    assertEquals(3, actualCloneWithModifiedParameters.getNumberOfFactors());
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d, 0.5d},
        termStructCovarianceModelFromLIBORCovarianceModelParametric.getParameter(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d, 0.5d},
        actualCloneWithModifiedParameters.getParameter(),
        0.0);
  }

  /**
   * Test {@link TermStructCovarianceModelFromLIBORCovarianceModelParametric#clone()}.
   *
   * <ul>
   *   <li>Then return {@link TermStructCovarianceModelFromLIBORCovarianceModelParametric}.
   * </ul>
   *
   * <p>Method under test: {@link
   * TermStructCovarianceModelFromLIBORCovarianceModelParametric#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TermStructureCovarianceModelParametric TermStructCovarianceModelFromLIBORCovarianceModelParametric.clone()"
  })
  public void testClone_thenReturnTermStructCovarianceModelFromLIBORCovarianceModelParametric() {
    // Arrange
    BlendedLocalVolatilityModel covarianceModel = mock(BlendedLocalVolatilityModel.class);
    when(covarianceModel.clone()).thenReturn(null);
    TermStructureTenorTimeScalingPicewiseConstant tenorTimeScalingModel =
        new TermStructureTenorTimeScalingPicewiseConstant(
            new TenorFromArray(10.0d, 1, 0.5d), new double[] {10.0d, -0.9d, 10.0d, -0.9d});

    TermStructCovarianceModelFromLIBORCovarianceModelParametric
        termStructCovarianceModelFromLIBORCovarianceModelParametric =
            new TermStructCovarianceModelFromLIBORCovarianceModelParametric(
                tenorTimeScalingModel, covarianceModel);

    // Act
    TermStructureCovarianceModelParametric actualCloneResult =
        termStructCovarianceModelFromLIBORCovarianceModelParametric.clone();

    // Assert
    verify(covarianceModel).clone();
    assertTrue(
        actualCloneResult instanceof TermStructCovarianceModelFromLIBORCovarianceModelParametric);
  }
}
