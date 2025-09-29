package net.finmath.montecarlo.process;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.stochastic.RandomVariable;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ProcessDiffblueTest {
  /**
   * Test {@link Process#getModel()}.
   *
   * <p>Method under test: {@link Process#getModel()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.montecarlo.model.ProcessModel Process.getModel()"})
  public void testGetModel() {
    // Arrange, Act and Assert
    assertNull(new LinearInterpolatedTimeDiscreteProcess(new HashMap<>()).getModel());
  }

  /**
   * Test {@link Process#getProcessValue(int)} with {@code int}.
   *
   * <ul>
   *   <li>Then first element return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link Process#getProcessValue(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] Process.getProcessValue(int)"})
  public void testGetProcessValueWithInt_thenFirstElementReturnRandomVariableFromDoubleArray()
      throws CalculationException {
    // Arrange
    HashMap<Double, RandomVariable> realizations = new HashMap<>();
    realizations.put(0.5d, new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    realizations.put(10.0d, randomVariableFromDoubleArray);

    // Act
    RandomVariable[] actualProcessValue =
        new LinearInterpolatedTimeDiscreteProcess(realizations).getProcessValue(1);

    // Assert
    RandomVariable randomVariable = actualProcessValue[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualProcessValue.length);
    assertSame(randomVariableFromDoubleArray, randomVariable);
  }
}
