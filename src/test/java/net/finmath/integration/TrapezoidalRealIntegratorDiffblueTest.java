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

public class TrapezoidalRealIntegratorDiffblueTest {
  /**
   * Test {@link TrapezoidalRealIntegrator#TrapezoidalRealIntegrator(double, double, double[])}.
   *
   * <p>Method under test: {@link TrapezoidalRealIntegrator#TrapezoidalRealIntegrator(double,
   * double, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TrapezoidalRealIntegrator.<init>(double, double, double[])"})
  public void testNewTrapezoidalRealIntegrator() {
    // Arrange and Act
    TrapezoidalRealIntegrator actualTrapezoidalRealIntegrator =
        new TrapezoidalRealIntegrator(10.0d, 10.0d, new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertEquals(10.0d, actualTrapezoidalRealIntegrator.getLowerBound(), 0.0);
    assertEquals(10.0d, actualTrapezoidalRealIntegrator.getUpperBound(), 0.0);
  }

  /**
   * Test {@link TrapezoidalRealIntegrator#TrapezoidalRealIntegrator(double, double, int)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return LowerBound is ten.
   * </ul>
   *
   * <p>Method under test: {@link TrapezoidalRealIntegrator#TrapezoidalRealIntegrator(double,
   * double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TrapezoidalRealIntegrator.<init>(double, double, int)"})
  public void testNewTrapezoidalRealIntegrator_whenTen_thenReturnLowerBoundIsTen() {
    // Arrange and Act
    TrapezoidalRealIntegrator actualTrapezoidalRealIntegrator =
        new TrapezoidalRealIntegrator(10.0d, 10.0d, 10);

    // Assert
    assertEquals(10.0d, actualTrapezoidalRealIntegrator.getLowerBound(), 0.0);
    assertEquals(10.0d, actualTrapezoidalRealIntegrator.getUpperBound(), 0.0);
  }

  /**
   * Test {@link TrapezoidalRealIntegrator#integrate(DoubleUnaryOperator)}.
   *
   * <p>Method under test: {@link TrapezoidalRealIntegrator#integrate(DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double TrapezoidalRealIntegrator.integrate(DoubleUnaryOperator)"})
  public void testIntegrate() {
    // Arrange
    TrapezoidalRealIntegrator trapezoidalRealIntegrator =
        new TrapezoidalRealIntegrator(10.0d, 10.0d, 10);

    DoubleUnaryOperator integrand = mock(DoubleUnaryOperator.class);
    when(integrand.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    double actualIntegrateResult = trapezoidalRealIntegrator.integrate(integrand);

    // Assert
    verify(integrand, atLeast(1)).applyAsDouble(10.0d);
    assertEquals(0.0d, actualIntegrateResult, 0.0);
  }

  /**
   * Test {@link TrapezoidalRealIntegrator#integrate(DoubleUnaryOperator)}.
   *
   * <p>Method under test: {@link TrapezoidalRealIntegrator#integrate(DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double TrapezoidalRealIntegrator.integrate(DoubleUnaryOperator)"})
  public void testIntegrate2() {
    // Arrange
    TrapezoidalRealIntegrator trapezoidalRealIntegrator =
        new TrapezoidalRealIntegrator(2.0d, 2.0d, new double[] {2.0d, 10.0d, 2.0d, 10.0d});

    DoubleUnaryOperator integrand = mock(DoubleUnaryOperator.class);
    when(integrand.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    double actualIntegrateResult = trapezoidalRealIntegrator.integrate(integrand);

    // Assert
    verify(integrand, atLeast(1)).applyAsDouble(2.0d);
    assertEquals(0.0d, actualIntegrateResult, 0.0);
  }

  /**
   * Test {@link TrapezoidalRealIntegrator#integrate(DoubleUnaryOperator)}.
   *
   * <p>Method under test: {@link TrapezoidalRealIntegrator#integrate(DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double TrapezoidalRealIntegrator.integrate(DoubleUnaryOperator)"})
  public void testIntegrate3() {
    // Arrange
    TrapezoidalRealIntegrator trapezoidalRealIntegrator =
        new TrapezoidalRealIntegrator(2.0d, 2.0d, new double[] {});

    DoubleUnaryOperator integrand = mock(DoubleUnaryOperator.class);
    when(integrand.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    double actualIntegrateResult = trapezoidalRealIntegrator.integrate(integrand);

    // Assert
    verify(integrand, atLeast(1)).applyAsDouble(2.0d);
    assertEquals(0.0d, actualIntegrateResult, 0.0);
  }

  /**
   * Test {@link TrapezoidalRealIntegrator#integrate(DoubleUnaryOperator)}.
   *
   * <ul>
   *   <li>Then return eighty.
   * </ul>
   *
   * <p>Method under test: {@link TrapezoidalRealIntegrator#integrate(DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double TrapezoidalRealIntegrator.integrate(DoubleUnaryOperator)"})
  public void testIntegrate_thenReturnEighty() {
    // Arrange
    TrapezoidalRealIntegrator trapezoidalRealIntegrator =
        new TrapezoidalRealIntegrator(2.0d, 10.0d, new double[] {2.0d, 10.0d, 2.0d, 10.0d});

    DoubleUnaryOperator integrand = mock(DoubleUnaryOperator.class);
    when(integrand.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    double actualIntegrateResult = trapezoidalRealIntegrator.integrate(integrand);

    // Assert
    verify(integrand, atLeast(1)).applyAsDouble(anyDouble());
    assertEquals(80.0d, actualIntegrateResult, 0.0);
  }

  /**
   * Test {@link TrapezoidalRealIntegrator#integrate(DoubleUnaryOperator)}.
   *
   * <ul>
   *   <li>Then return minus eighty.
   * </ul>
   *
   * <p>Method under test: {@link TrapezoidalRealIntegrator#integrate(DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double TrapezoidalRealIntegrator.integrate(DoubleUnaryOperator)"})
  public void testIntegrate_thenReturnMinusEighty() {
    // Arrange
    TrapezoidalRealIntegrator trapezoidalRealIntegrator =
        new TrapezoidalRealIntegrator(10.0d, 2.0d, new double[] {2.0d, 10.0d, 2.0d, 10.0d});

    DoubleUnaryOperator integrand = mock(DoubleUnaryOperator.class);
    when(integrand.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    double actualIntegrateResult = trapezoidalRealIntegrator.integrate(integrand);

    // Assert
    verify(integrand, atLeast(1)).applyAsDouble(anyDouble());
    assertEquals(-80.0d, actualIntegrateResult, 0.0);
  }
}
