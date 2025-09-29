package net.finmath.integration;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.DoubleUnaryOperator;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MonteCarloIntegratorDiffblueTest {
  /**
   * Test {@link MonteCarloIntegrator#MonteCarloIntegrator(double, double, int, int, boolean)}.
   *
   * <ul>
   *   <li>When forty-two.
   *   <li>Then return NumberOfEvaluationPoints is ten.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloIntegrator#MonteCarloIntegrator(double, double, int,
   * int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MonteCarloIntegrator.<init>(double, double, int, int, boolean)"})
  public void testNewMonteCarloIntegrator_whenFortyTwo_thenReturnNumberOfEvaluationPointsIsTen() {
    // Arrange and Act
    MonteCarloIntegrator actualMonteCarloIntegrator =
        new MonteCarloIntegrator(10.0d, 10.0d, 10, 42, true);

    // Assert
    assertEquals(10, actualMonteCarloIntegrator.getNumberOfEvaluationPoints());
    assertEquals(10.0d, actualMonteCarloIntegrator.getLowerBound(), 0.0);
    assertEquals(10.0d, actualMonteCarloIntegrator.getUpperBound(), 0.0);
    assertEquals(42, actualMonteCarloIntegrator.getSeed());
  }

  /**
   * Test {@link MonteCarloIntegrator#MonteCarloIntegrator(double, double, int)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return NumberOfEvaluationPoints is ten.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloIntegrator#MonteCarloIntegrator(double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MonteCarloIntegrator.<init>(double, double, int)"})
  public void testNewMonteCarloIntegrator_whenTen_thenReturnNumberOfEvaluationPointsIsTen() {
    // Arrange and Act
    MonteCarloIntegrator actualMonteCarloIntegrator = new MonteCarloIntegrator(10.0d, 10.0d, 10);

    // Assert
    assertEquals(10, actualMonteCarloIntegrator.getNumberOfEvaluationPoints());
    assertEquals(10.0d, actualMonteCarloIntegrator.getLowerBound(), 0.0);
    assertEquals(10.0d, actualMonteCarloIntegrator.getUpperBound(), 0.0);
    assertEquals(3141, actualMonteCarloIntegrator.getSeed());
  }

  /**
   * Test {@link MonteCarloIntegrator#MonteCarloIntegrator(double, double, int, boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return NumberOfEvaluationPoints is ten.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloIntegrator#MonteCarloIntegrator(double, double, int,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MonteCarloIntegrator.<init>(double, double, int, boolean)"})
  public void testNewMonteCarloIntegrator_whenTrue_thenReturnNumberOfEvaluationPointsIsTen() {
    // Arrange and Act
    MonteCarloIntegrator actualMonteCarloIntegrator =
        new MonteCarloIntegrator(10.0d, 10.0d, 10, true);

    // Assert
    assertEquals(10, actualMonteCarloIntegrator.getNumberOfEvaluationPoints());
    assertEquals(10.0d, actualMonteCarloIntegrator.getLowerBound(), 0.0);
    assertEquals(10.0d, actualMonteCarloIntegrator.getUpperBound(), 0.0);
    assertEquals(3141, actualMonteCarloIntegrator.getSeed());
  }

  /**
   * Test {@link MonteCarloIntegrator#integrate(DoubleUnaryOperator)}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link MonteCarloIntegrator#integrate(DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double MonteCarloIntegrator.integrate(DoubleUnaryOperator)"})
  public void testIntegrate_thenReturnZero() {
    // Arrange
    MonteCarloIntegrator monteCarloIntegrator = new MonteCarloIntegrator(10.0d, 10.0d, 10);

    DoubleUnaryOperator integrand = mock(DoubleUnaryOperator.class);
    when(integrand.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    double actualIntegrateResult = monteCarloIntegrator.integrate(integrand);

    // Assert
    verify(integrand, atLeast(1)).applyAsDouble(10.0d);
    assertEquals(0.0d, actualIntegrateResult, 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link MonteCarloIntegrator#getNumberOfEvaluationPoints()}
   *   <li>{@link MonteCarloIntegrator#getSeed()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int MonteCarloIntegrator.getNumberOfEvaluationPoints()",
    "int MonteCarloIntegrator.getSeed()"
  })
  public void testGettersAndSetters() {
    // Arrange
    MonteCarloIntegrator monteCarloIntegrator = new MonteCarloIntegrator(10.0d, 10.0d, 10);

    // Act
    int actualNumberOfEvaluationPoints = monteCarloIntegrator.getNumberOfEvaluationPoints();

    // Assert
    assertEquals(10, actualNumberOfEvaluationPoints);
    assertEquals(3141, monteCarloIntegrator.getSeed());
  }
}
