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

public class RombergRealIntegrationDiffblueTest {
  /**
   * Test {@link RombergRealIntegration#RombergRealIntegration(double, double, int)}.
   *
   * <p>Method under test: {@link RombergRealIntegration#RombergRealIntegration(double, double,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RombergRealIntegration.<init>(double, double, int)"})
  public void testNewRombergRealIntegration() {
    // Arrange and Act
    RombergRealIntegration actualRombergRealIntegration =
        new RombergRealIntegration(10.0d, 10.0d, 10);

    // Assert
    assertEquals(10.0d, actualRombergRealIntegration.getLowerBound(), 0.0);
    assertEquals(10.0d, actualRombergRealIntegration.getUpperBound(), 0.0);
  }

  /**
   * Test {@link RombergRealIntegration#integrate(DoubleUnaryOperator)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return ninety-five.
   * </ul>
   *
   * <p>Method under test: {@link RombergRealIntegration#integrate(DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RombergRealIntegration.integrate(DoubleUnaryOperator)"})
  public void testIntegrate_givenTen_thenReturnNinetyFive() {
    // Arrange
    RombergRealIntegration rombergRealIntegration = new RombergRealIntegration(0.5d, 10.0d, 10);

    DoubleUnaryOperator integrand = mock(DoubleUnaryOperator.class);
    when(integrand.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    double actualIntegrateResult = rombergRealIntegration.integrate(integrand);

    // Assert
    verify(integrand, atLeast(1)).applyAsDouble(anyDouble());
    assertEquals(95.0d, actualIntegrateResult, 0.0);
  }
}
