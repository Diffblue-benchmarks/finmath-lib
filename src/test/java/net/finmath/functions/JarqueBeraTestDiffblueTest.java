package net.finmath.functions;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAADFactory;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.stochastic.RandomVariable;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JarqueBeraTestDiffblueTest {
  /**
   * Test {@link JarqueBeraTest#test(RandomVariable)}.
   *
   * <p>Method under test: {@link JarqueBeraTest#test(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JarqueBeraTest.test(RandomVariable)"})
  public void testTest() {
    // Arrange
    JarqueBeraTest jarqueBeraTest = new JarqueBeraTest();

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.size()).thenReturn(3);
    when(randomVariable.getAverage()).thenReturn(10.0d);
    when(randomVariable.getStandardDeviation()).thenReturn(10.0d);
    when(randomVariable.sub(anyDouble())).thenReturn(RandomVariableDifferentiableAAD.of(10.0d));

    // Act
    double actualTestResult = jarqueBeraTest.test(randomVariable);

    // Assert
    verify(randomVariable).getAverage();
    verify(randomVariable).getStandardDeviation();
    verify(randomVariable).size();
    verify(randomVariable, atLeast(1)).sub(10.0d);
    assertEquals(1.0d, actualTestResult, 0.0);
  }

  /**
   * Test {@link JarqueBeraTest#test(RandomVariable)}.
   *
   * <p>Method under test: {@link JarqueBeraTest#test(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JarqueBeraTest.test(RandomVariable)"})
  public void testTest2() {
    // Arrange
    JarqueBeraTest jarqueBeraTest = new JarqueBeraTest();

    RandomVariableAAD randomVariable = mock(RandomVariableAAD.class);
    when(randomVariable.size()).thenReturn(3);
    when(randomVariable.getAverage()).thenReturn(10.0d);
    when(randomVariable.getStandardDeviation()).thenReturn(10.0d);
    RandomVariableDifferentiableAAD values = RandomVariableDifferentiableAAD.of(10.0d);
    RandomVariableDifferentiableAAD randomVariableDifferentiableAAD =
        new RandomVariableDifferentiableAAD(values, new RandomVariableDifferentiableAADFactory());
    when(randomVariable.sub(anyDouble())).thenReturn(randomVariableDifferentiableAAD);

    // Act
    double actualTestResult = jarqueBeraTest.test(randomVariable);

    // Assert
    verify(randomVariable).getAverage();
    verify(randomVariable).getStandardDeviation();
    verify(randomVariable).size();
    verify(randomVariable, atLeast(1)).sub(10.0d);
    assertEquals(1.0d, actualTestResult, 0.0);
  }

  /**
   * Test {@link JarqueBeraTest#test(RandomVariable)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link JarqueBeraTest#test(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JarqueBeraTest.test(RandomVariable)"})
  public void testTest_whenRandomVariableFromDoubleArrayWithValueIsTen_thenReturnNaN() {
    // Arrange
    JarqueBeraTest jarqueBeraTest = new JarqueBeraTest();

    // Act
    double actualTestResult = jarqueBeraTest.test(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(Double.NaN, actualTestResult, 0.0);
  }
}
