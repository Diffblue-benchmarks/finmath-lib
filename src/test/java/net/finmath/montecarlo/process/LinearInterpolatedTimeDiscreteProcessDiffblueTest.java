package net.finmath.montecarlo.process;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.DoubleUnaryOperator;
import net.finmath.exception.CalculationException;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class LinearInterpolatedTimeDiscreteProcessDiffblueTest {
  /**
   * Test {@link LinearInterpolatedTimeDiscreteProcess#LinearInterpolatedTimeDiscreteProcess(Map)}.
   *
   * <p>Method under test: {@link
   * LinearInterpolatedTimeDiscreteProcess#LinearInterpolatedTimeDiscreteProcess(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LinearInterpolatedTimeDiscreteProcess.<init>(Map)"})
  public void testNewLinearInterpolatedTimeDiscreteProcess() {
    // Arrange, Act and Assert
    TimeDiscretization timeDiscretization =
        new LinearInterpolatedTimeDiscreteProcess(new HashMap<>()).getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TimeDiscretizationFromArray);
    assertEquals(-1, timeDiscretization.getNumberOfTimeSteps());
    assertEquals(0, timeDiscretization.getNumberOfTimes());
    assertFalse(timeDiscretization.iterator().hasNext());
    assertTrue(timeDiscretization.getAsArrayList().isEmpty());
    assertArrayEquals(new double[] {}, timeDiscretization.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link LinearInterpolatedTimeDiscreteProcess#LinearInterpolatedTimeDiscreteProcess(Map)}.
   *
   * <p>Method under test: {@link
   * LinearInterpolatedTimeDiscreteProcess#LinearInterpolatedTimeDiscreteProcess(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LinearInterpolatedTimeDiscreteProcess.<init>(Map)"})
  public void testNewLinearInterpolatedTimeDiscreteProcess2() {
    // Arrange
    HashMap<Double, RandomVariable> realizations = new HashMap<>();
    realizations.put(10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    TimeDiscretization timeDiscretization =
        new LinearInterpolatedTimeDiscreteProcess(realizations).getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TimeDiscretizationFromArray);
    assertEquals(0, timeDiscretization.getNumberOfTimeSteps());
    ArrayList<Double> asArrayList = timeDiscretization.getAsArrayList();
    assertEquals(1, asArrayList.size());
    assertEquals(1, timeDiscretization.getNumberOfTimes());
    assertEquals(10.0d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = timeDiscretization.iterator();
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(10.0d, timeDiscretization.getFirstTime(), 0.0);
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(new double[] {10.0d}, timeDiscretization.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link LinearInterpolatedTimeDiscreteProcess#LinearInterpolatedTimeDiscreteProcess(Map)}.
   *
   * <p>Method under test: {@link
   * LinearInterpolatedTimeDiscreteProcess#LinearInterpolatedTimeDiscreteProcess(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LinearInterpolatedTimeDiscreteProcess.<init>(Map)"})
  public void testNewLinearInterpolatedTimeDiscreteProcess3() {
    // Arrange
    HashMap<Double, RandomVariable> realizations = new HashMap<>();
    realizations.put(0.5d, new RandomVariableFromDoubleArray(10.0d));
    realizations.put(10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    TimeDiscretization timeDiscretization =
        new LinearInterpolatedTimeDiscreteProcess(realizations).getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TimeDiscretizationFromArray);
    ArrayList<Double> asArrayList = timeDiscretization.getAsArrayList();
    assertEquals(2, asArrayList.size());
    assertEquals(0.5d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = timeDiscretization.iterator();
    assertEquals(0.5d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(0.5d, timeDiscretization.getFirstTime(), 0.0);
    assertEquals(1, timeDiscretization.getNumberOfTimeSteps());
    assertEquals(10.0d, asArrayList.get(1).doubleValue(), 0.0);
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(2, timeDiscretization.getNumberOfTimes());
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(new double[] {0.5d, 10.0d}, timeDiscretization.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link LinearInterpolatedTimeDiscreteProcess#add(LinearInterpolatedTimeDiscreteProcess)}.
   *
   * <ul>
   *   <li>Then return TimeDiscretization NumberOfTimeSteps is minus one.
   * </ul>
   *
   * <p>Method under test: {@link
   * LinearInterpolatedTimeDiscreteProcess#add(LinearInterpolatedTimeDiscreteProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LinearInterpolatedTimeDiscreteProcess LinearInterpolatedTimeDiscreteProcess.add(LinearInterpolatedTimeDiscreteProcess)"
  })
  public void testAdd_thenReturnTimeDiscretizationNumberOfTimeStepsIsMinusOne()
      throws CalculationException {
    // Arrange
    LinearInterpolatedTimeDiscreteProcess linearInterpolatedTimeDiscreteProcess =
        new LinearInterpolatedTimeDiscreteProcess(new HashMap<>());

    // Act and Assert
    TimeDiscretization timeDiscretization =
        linearInterpolatedTimeDiscreteProcess
            .add(new LinearInterpolatedTimeDiscreteProcess(new HashMap<>()))
            .getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TimeDiscretizationFromArray);
    assertEquals(-1, timeDiscretization.getNumberOfTimeSteps());
    assertEquals(0, timeDiscretization.getNumberOfTimes());
    assertFalse(timeDiscretization.iterator().hasNext());
    assertTrue(timeDiscretization.getAsArrayList().isEmpty());
    assertArrayEquals(new double[] {}, timeDiscretization.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link LinearInterpolatedTimeDiscreteProcess#add(LinearInterpolatedTimeDiscreteProcess)}.
   *
   * <ul>
   *   <li>Then return TimeDiscretization NumberOfTimeSteps is zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * LinearInterpolatedTimeDiscreteProcess#add(LinearInterpolatedTimeDiscreteProcess)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LinearInterpolatedTimeDiscreteProcess LinearInterpolatedTimeDiscreteProcess.add(LinearInterpolatedTimeDiscreteProcess)"
  })
  public void testAdd_thenReturnTimeDiscretizationNumberOfTimeStepsIsZero()
      throws CalculationException {
    // Arrange
    RandomVariableAAD randomVariableAAD = mock(RandomVariableAAD.class);
    when(randomVariableAAD.add(Mockito.<RandomVariable>any()))
        .thenReturn(new RandomVariableFromDoubleArray(10.0d));

    HashMap<Double, RandomVariable> realizations = new HashMap<>();
    realizations.put(10.0d, randomVariableAAD);
    LinearInterpolatedTimeDiscreteProcess linearInterpolatedTimeDiscreteProcess =
        new LinearInterpolatedTimeDiscreteProcess(realizations);

    HashMap<Double, RandomVariable> realizations2 = new HashMap<>();
    realizations2.put(10.0d, new RandomVariableFromDoubleArray(-1.0d));

    // Act
    LinearInterpolatedTimeDiscreteProcess actualAddResult =
        linearInterpolatedTimeDiscreteProcess.add(
            new LinearInterpolatedTimeDiscreteProcess(realizations2));

    // Assert
    verify(randomVariableAAD).add((RandomVariable) isNull());
    TimeDiscretization timeDiscretization = actualAddResult.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TimeDiscretizationFromArray);
    assertEquals(0, timeDiscretization.getNumberOfTimeSteps());
    ArrayList<Double> asArrayList = timeDiscretization.getAsArrayList();
    assertEquals(1, asArrayList.size());
    assertEquals(1, timeDiscretization.getNumberOfTimes());
    assertEquals(10.0d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = timeDiscretization.iterator();
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(10.0d, timeDiscretization.getFirstTime(), 0.0);
    assertEquals(10.0d, timeDiscretization.getLastTime(), 0.0);
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(new double[] {10.0d}, timeDiscretization.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link LinearInterpolatedTimeDiscreteProcess#apply(DoubleUnaryOperator)}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return TimeDiscretization NumberOfTimeSteps is zero.
   * </ul>
   *
   * <p>Method under test: {@link LinearInterpolatedTimeDiscreteProcess#apply(DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LinearInterpolatedTimeDiscreteProcess LinearInterpolatedTimeDiscreteProcess.apply(DoubleUnaryOperator)"
  })
  public void testApply_givenTen_thenReturnTimeDiscretizationNumberOfTimeStepsIsZero() {
    // Arrange
    HashMap<Double, RandomVariable> realizations = new HashMap<>();
    realizations.put(10.0d, new RandomVariableFromDoubleArray(10.0d));
    LinearInterpolatedTimeDiscreteProcess linearInterpolatedTimeDiscreteProcess =
        new LinearInterpolatedTimeDiscreteProcess(realizations);

    DoubleUnaryOperator function = mock(DoubleUnaryOperator.class);
    when(function.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    LinearInterpolatedTimeDiscreteProcess actualApplyResult =
        linearInterpolatedTimeDiscreteProcess.apply(function);

    // Assert
    verify(function).applyAsDouble(10.0d);
    TimeDiscretization timeDiscretization = actualApplyResult.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TimeDiscretizationFromArray);
    assertEquals(0, timeDiscretization.getNumberOfTimeSteps());
    ArrayList<Double> asArrayList = timeDiscretization.getAsArrayList();
    assertEquals(1, asArrayList.size());
    assertEquals(1, timeDiscretization.getNumberOfTimes());
    assertEquals(10.0d, asArrayList.get(0).doubleValue(), 0.0);
    Iterator<Double> iteratorResult = timeDiscretization.iterator();
    assertEquals(10.0d, iteratorResult.next().doubleValue(), 0.0);
    assertEquals(10.0d, timeDiscretization.getFirstTime(), 0.0);
    assertEquals(10.0d, timeDiscretization.getLastTime(), 0.0);
    assertFalse(iteratorResult.hasNext());
    assertArrayEquals(new double[] {10.0d}, timeDiscretization.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link LinearInterpolatedTimeDiscreteProcess#apply(DoubleUnaryOperator)}.
   *
   * <ul>
   *   <li>Then return TimeDiscretization NumberOfTimeSteps is minus one.
   * </ul>
   *
   * <p>Method under test: {@link LinearInterpolatedTimeDiscreteProcess#apply(DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LinearInterpolatedTimeDiscreteProcess LinearInterpolatedTimeDiscreteProcess.apply(DoubleUnaryOperator)"
  })
  public void testApply_thenReturnTimeDiscretizationNumberOfTimeStepsIsMinusOne() {
    // Arrange, Act and Assert
    TimeDiscretization timeDiscretization =
        new LinearInterpolatedTimeDiscreteProcess(new HashMap<>())
            .apply(mock(DoubleUnaryOperator.class))
            .getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TimeDiscretizationFromArray);
    assertEquals(-1, timeDiscretization.getNumberOfTimeSteps());
    assertEquals(0, timeDiscretization.getNumberOfTimes());
    assertFalse(timeDiscretization.iterator().hasNext());
    assertTrue(timeDiscretization.getAsArrayList().isEmpty());
    assertArrayEquals(new double[] {}, timeDiscretization.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link LinearInterpolatedTimeDiscreteProcess#getProcessValue(double, int)} with {@code
   * time}, {@code component}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LinearInterpolatedTimeDiscreteProcess#getProcessValue(double,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LinearInterpolatedTimeDiscreteProcess.getProcessValue(double, int)"
  })
  public void testGetProcessValueWithTimeComponent_thenReturnNull() {
    // Arrange
    HashMap<Double, RandomVariable> realizations = new HashMap<>();
    realizations.put(10.0d, new RandomVariableFromDoubleArray(10.0d));
    realizations.putIfAbsent(-1.0d, new RandomVariableFromDoubleArray(-1.0d));

    // Act and Assert
    assertNull(new LinearInterpolatedTimeDiscreteProcess(realizations).getProcessValue(10.0d, 1));
  }

  /**
   * Test {@link LinearInterpolatedTimeDiscreteProcess#getProcessValue(int, int)} with {@code
   * timeIndex}, {@code component}.
   *
   * <p>Method under test: {@link LinearInterpolatedTimeDiscreteProcess#getProcessValue(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LinearInterpolatedTimeDiscreteProcess.getProcessValue(int, int)"
  })
  public void testGetProcessValueWithTimeIndexComponent() {
    // Arrange
    HashMap<Double, RandomVariable> realizations = new HashMap<>();
    realizations.put(0.5d, new RandomVariableFromDoubleArray(10.0d));
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    realizations.put(10.0d, randomVariableFromDoubleArray);

    // Act and Assert
    assertSame(
        randomVariableFromDoubleArray,
        new LinearInterpolatedTimeDiscreteProcess(realizations).getProcessValue(1, 1));
  }

  /**
   * Test {@link LinearInterpolatedTimeDiscreteProcess#getMonteCarloWeights(int)}.
   *
   * <p>Method under test: {@link LinearInterpolatedTimeDiscreteProcess#getMonteCarloWeights(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LinearInterpolatedTimeDiscreteProcess.getMonteCarloWeights(int)"
  })
  public void testGetMonteCarloWeights() {
    // Arrange, Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> new LinearInterpolatedTimeDiscreteProcess(new HashMap<>()).getMonteCarloWeights(1));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link LinearInterpolatedTimeDiscreteProcess#getNumberOfComponents()}
   *   <li>{@link LinearInterpolatedTimeDiscreteProcess#getTimeDiscretization()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int LinearInterpolatedTimeDiscreteProcess.getNumberOfComponents()",
    "TimeDiscretization LinearInterpolatedTimeDiscreteProcess.getTimeDiscretization()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LinearInterpolatedTimeDiscreteProcess linearInterpolatedTimeDiscreteProcess =
        new LinearInterpolatedTimeDiscreteProcess(new HashMap<>());

    // Act
    int actualNumberOfComponents = linearInterpolatedTimeDiscreteProcess.getNumberOfComponents();

    // Assert
    assertTrue(
        linearInterpolatedTimeDiscreteProcess.getTimeDiscretization()
            instanceof TimeDiscretizationFromArray);
    assertEquals(1, actualNumberOfComponents);
  }

  /**
   * Test {@link LinearInterpolatedTimeDiscreteProcess#getTime(int)}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link LinearInterpolatedTimeDiscreteProcess#getTime(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double LinearInterpolatedTimeDiscreteProcess.getTime(int)"})
  public void testGetTime_thenReturnTen() {
    // Arrange
    HashMap<Double, RandomVariable> realizations = new HashMap<>();
    realizations.put(0.5d, new RandomVariableFromDoubleArray(10.0d));
    realizations.put(10.0d, new RandomVariableFromDoubleArray(10.0d));

    // Act and Assert
    assertEquals(10.0d, new LinearInterpolatedTimeDiscreteProcess(realizations).getTime(1), 0.0);
  }

  /**
   * Test {@link LinearInterpolatedTimeDiscreteProcess#getTimeIndex(double)}.
   *
   * <p>Method under test: {@link LinearInterpolatedTimeDiscreteProcess#getTimeIndex(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int LinearInterpolatedTimeDiscreteProcess.getTimeIndex(double)"})
  public void testGetTimeIndex() {
    // Arrange, Act and Assert
    assertEquals(
        -1, new LinearInterpolatedTimeDiscreteProcess(new HashMap<>()).getTimeIndex(10.0d));
  }

  /**
   * Test {@link LinearInterpolatedTimeDiscreteProcess#clone()}.
   *
   * <p>Method under test: {@link LinearInterpolatedTimeDiscreteProcess#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Process LinearInterpolatedTimeDiscreteProcess.clone()"})
  public void testClone() {
    // Arrange and Act
    Process actualCloneResult = new LinearInterpolatedTimeDiscreteProcess(new HashMap<>()).clone();

    // Assert
    assertTrue(actualCloneResult instanceof LinearInterpolatedTimeDiscreteProcess);
    TimeDiscretization timeDiscretization = actualCloneResult.getTimeDiscretization();
    assertTrue(timeDiscretization instanceof TimeDiscretizationFromArray);
    assertNull(actualCloneResult.getModel());
    assertEquals(-1, timeDiscretization.getNumberOfTimeSteps());
    assertEquals(0, timeDiscretization.getNumberOfTimes());
    assertEquals(1, actualCloneResult.getNumberOfComponents());
    assertEquals(1.1415525114155251E-4d, timeDiscretization.getTickSize(), 0.0);
    assertFalse(timeDiscretization.iterator().hasNext());
    assertTrue(timeDiscretization.getAsArrayList().isEmpty());
    assertArrayEquals(new double[] {}, timeDiscretization.getAsDoubleArray(), 0.0);
  }
}
