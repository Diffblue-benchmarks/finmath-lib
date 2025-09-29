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

public class SimpsonRealIntegratorDiffblueTest {
  /**
   * Test {@link SimpsonRealIntegrator#SimpsonRealIntegrator(double, double, int)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return LowerBound is ten.
   * </ul>
   *
   * <p>Method under test: {@link SimpsonRealIntegrator#SimpsonRealIntegrator(double, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SimpsonRealIntegrator.<init>(double, double, int)"})
  public void testNewSimpsonRealIntegrator_whenTen_thenReturnLowerBoundIsTen() {
    // Arrange and Act
    SimpsonRealIntegrator actualSimpsonRealIntegrator = new SimpsonRealIntegrator(10.0d, 10.0d, 10);

    // Assert
    assertEquals(10.0d, actualSimpsonRealIntegrator.getLowerBound(), 0.0);
    assertEquals(10.0d, actualSimpsonRealIntegrator.getUpperBound(), 0.0);
  }

  /**
   * Test {@link SimpsonRealIntegrator#SimpsonRealIntegrator(double, double, int, boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return LowerBound is ten.
   * </ul>
   *
   * <p>Method under test: {@link SimpsonRealIntegrator#SimpsonRealIntegrator(double, double, int,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SimpsonRealIntegrator.<init>(double, double, int, boolean)"})
  public void testNewSimpsonRealIntegrator_whenTrue_thenReturnLowerBoundIsTen() {
    // Arrange and Act
    SimpsonRealIntegrator actualSimpsonRealIntegrator =
        new SimpsonRealIntegrator(10.0d, 10.0d, 10, true);

    // Assert
    assertEquals(10.0d, actualSimpsonRealIntegrator.getLowerBound(), 0.0);
    assertEquals(10.0d, actualSimpsonRealIntegrator.getUpperBound(), 0.0);
  }

  /**
   * Test {@link SimpsonRealIntegrator#integrate(DoubleUnaryOperator)}.
   *
   * <p>Method under test: {@link SimpsonRealIntegrator#integrate(DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SimpsonRealIntegrator.integrate(DoubleUnaryOperator)"})
  public void testIntegrate() {
    // Arrange
    SimpsonRealIntegrator simpsonRealIntegrator = new SimpsonRealIntegrator(10.0d, 10.0d, 10);

    DoubleUnaryOperator integrand = mock(DoubleUnaryOperator.class);
    when(integrand.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    double actualIntegrateResult = simpsonRealIntegrator.integrate(integrand);

    // Assert
    verify(integrand, atLeast(1)).applyAsDouble(10.0d);
    assertEquals(0.0d, actualIntegrateResult, 0.0);
  }

  /**
   * Test {@link SimpsonRealIntegrator#integrate(DoubleUnaryOperator)}.
   *
   * <p>Method under test: {@link SimpsonRealIntegrator#integrate(DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SimpsonRealIntegrator.integrate(DoubleUnaryOperator)"})
  public void testIntegrate2() {
    // Arrange
    SimpsonRealIntegrator simpsonRealIntegrator = new SimpsonRealIntegrator(2.0d, 2.0d, 10, true);

    DoubleUnaryOperator integrand = mock(DoubleUnaryOperator.class);
    when(integrand.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    double actualIntegrateResult = simpsonRealIntegrator.integrate(integrand);

    // Assert
    verify(integrand, atLeast(1)).applyAsDouble(2.0d);
    assertEquals(0.0d, actualIntegrateResult, 0.0);
  }
}
