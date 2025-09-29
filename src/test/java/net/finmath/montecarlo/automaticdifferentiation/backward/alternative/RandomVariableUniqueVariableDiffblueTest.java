package net.finmath.montecarlo.automaticdifferentiation.backward.alternative;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import net.finmath.functions.DoubleTernaryOperator;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.montecarlo.RandomVariableLazyEvaluation;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableUniqueVariable.OperatorType;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RandomVariableUniqueVariableDiffblueTest {
  /**
   * Test {@link RandomVariableUniqueVariable#RandomVariableUniqueVariable(double, double[])}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#RandomVariableUniqueVariable(double,
   * double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableUniqueVariable.<init>(double, double[])"})
  public void testNewRandomVariableUniqueVariable() {
    // Arrange and Act
    RandomVariableUniqueVariable actualRandomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Assert
    assertTrue(actualRandomVariableUniqueVariable.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableUniqueVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertNull(actualRandomVariableUniqueVariable.abs());
    assertNull(actualRandomVariableUniqueVariable.average());
    assertNull(actualRandomVariableUniqueVariable.invert());
    assertNull(actualRandomVariableUniqueVariable.expectation());
    assertNull(actualRandomVariableUniqueVariable.expm1());
    assertEquals(0.0d, actualRandomVariableUniqueVariable.getAverage(), 0.0);
    assertEquals(0.0d, actualRandomVariableUniqueVariable.getMax(), 0.0);
    assertEquals(0.0d, actualRandomVariableUniqueVariable.getMin(), 0.0);
    assertEquals(0.0d, actualRandomVariableUniqueVariable.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableUniqueVariable.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableUniqueVariable.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableUniqueVariable.getVariance(), 0.0);
    assertEquals(10.0d, actualRandomVariableUniqueVariable.getFiltrationTime(), 0.0);
    assertEquals(3, actualRandomVariableUniqueVariable.getTypePriority());
    assertEquals(4, actualRandomVariableUniqueVariable.size());
    assertFalse(actualRandomVariableUniqueVariable.isDeterministic());
    assertTrue(actualRandomVariableUniqueVariable.isVariable());
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d},
        actualRandomVariableUniqueVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#RandomVariableUniqueVariable(double, double[],
   * boolean, ArrayList, OperatorType)}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#RandomVariableUniqueVariable(double,
   * double[], boolean, ArrayList, OperatorType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableUniqueVariable.<init>(double, double[], boolean, ArrayList, OperatorType)"
  })
  public void testNewRandomVariableUniqueVariable2() {
    // Arrange
    ArrayList<RandomVariableUniqueVariable> parentVariables = new ArrayList<>();
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(
            Double.NaN, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});
    parentVariables.add(randomVariableUniqueVariable);

    // Act
    RandomVariableUniqueVariable actualRandomVariableUniqueVariable =
        new RandomVariableUniqueVariable(
            10.0d,
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            true,
            parentVariables,
            OperatorType.ADD);

    // Assert
    assertTrue(actualRandomVariableUniqueVariable.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableUniqueVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d},
        actualRandomVariableUniqueVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#RandomVariableUniqueVariable(double, double[],
   * boolean, ArrayList, OperatorType)}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#RandomVariableUniqueVariable(double,
   * double[], boolean, ArrayList, OperatorType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableUniqueVariable.<init>(double, double[], boolean, ArrayList, OperatorType)"
  })
  public void testNewRandomVariableUniqueVariable3() {
    // Arrange
    ArrayList<RandomVariableUniqueVariable> parentVariables = new ArrayList<>();
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(
            Double.NaN, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});
    parentVariables.add(randomVariableUniqueVariable);
    RandomVariableUniqueVariable randomVariableUniqueVariable2 =
        new RandomVariableUniqueVariable(
            Double.NaN, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});
    parentVariables.add(randomVariableUniqueVariable2);

    // Act
    RandomVariableUniqueVariable actualRandomVariableUniqueVariable =
        new RandomVariableUniqueVariable(
            10.0d,
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            true,
            parentVariables,
            OperatorType.ADD);

    // Assert
    assertTrue(actualRandomVariableUniqueVariable.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableUniqueVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d},
        actualRandomVariableUniqueVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#RandomVariableUniqueVariable(RandomVariable)}.
   *
   * <p>Method under test: {@link
   * RandomVariableUniqueVariable#RandomVariableUniqueVariable(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableUniqueVariable.<init>(RandomVariable)"})
  public void testNewRandomVariableUniqueVariable4() {
    // Arrange
    RandomVariableFromDoubleArray randomVariable = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariableUniqueVariable actualRandomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable);

    // Assert
    RandomVariable cacheResult = actualRandomVariableUniqueVariable.cache();
    assertTrue(cacheResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableUniqueVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertNull(actualRandomVariableUniqueVariable.abs());
    assertNull(actualRandomVariableUniqueVariable.average());
    assertNull(actualRandomVariableUniqueVariable.invert());
    assertNull(actualRandomVariableUniqueVariable.expectation());
    assertNull(actualRandomVariableUniqueVariable.expm1());
    assertEquals(0.0d, actualRandomVariableUniqueVariable.getAverage(), 0.0);
    assertEquals(0.0d, actualRandomVariableUniqueVariable.getMax(), 0.0);
    assertEquals(0.0d, actualRandomVariableUniqueVariable.getMin(), 0.0);
    assertEquals(0.0d, actualRandomVariableUniqueVariable.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualRandomVariableUniqueVariable.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualRandomVariableUniqueVariable.getStandardError(), 0.0);
    assertEquals(0.0d, actualRandomVariableUniqueVariable.getVariance(), 0.0);
    assertEquals(1, actualRandomVariableUniqueVariable.size());
    assertEquals(3, actualRandomVariableUniqueVariable.getTypePriority());
    assertTrue(actualRandomVariableUniqueVariable.isDeterministic());
    assertTrue(actualRandomVariableUniqueVariable.isVariable());
    assertEquals(
        Double.NEGATIVE_INFINITY, actualRandomVariableUniqueVariable.getFiltrationTime(), 0.0);
    assertSame(randomVariable, cacheResult);
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableUniqueVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#RandomVariableUniqueVariable(RandomVariable, boolean,
   * ArrayList, OperatorType)}.
   *
   * <p>Method under test: {@link
   * RandomVariableUniqueVariable#RandomVariableUniqueVariable(RandomVariable, boolean, ArrayList,
   * OperatorType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableUniqueVariable.<init>(RandomVariable, boolean, ArrayList, OperatorType)"
  })
  public void testNewRandomVariableUniqueVariable5() {
    // Arrange
    RandomVariableFromDoubleArray randomVariable = new RandomVariableFromDoubleArray(10.0d);

    ArrayList<RandomVariableUniqueVariable> parentVariables = new ArrayList<>();
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    parentVariables.add(randomVariableUniqueVariable);

    // Act
    RandomVariableUniqueVariable actualRandomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true, parentVariables, OperatorType.ADD);

    // Assert
    assertTrue(actualRandomVariableUniqueVariable.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableUniqueVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableUniqueVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#RandomVariableUniqueVariable(RandomVariable, boolean,
   * ArrayList, OperatorType)}.
   *
   * <p>Method under test: {@link
   * RandomVariableUniqueVariable#RandomVariableUniqueVariable(RandomVariable, boolean, ArrayList,
   * OperatorType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableUniqueVariable.<init>(RandomVariable, boolean, ArrayList, OperatorType)"
  })
  public void testNewRandomVariableUniqueVariable6() {
    // Arrange
    RandomVariableFromDoubleArray randomVariable = new RandomVariableFromDoubleArray(10.0d);

    ArrayList<RandomVariableUniqueVariable> parentVariables = new ArrayList<>();
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    parentVariables.add(randomVariableUniqueVariable);
    RandomVariableUniqueVariable randomVariableUniqueVariable2 =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    parentVariables.add(randomVariableUniqueVariable2);

    // Act
    RandomVariableUniqueVariable actualRandomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true, parentVariables, OperatorType.ADD);

    // Assert
    assertTrue(actualRandomVariableUniqueVariable.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableUniqueVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableUniqueVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#RandomVariableUniqueVariable(double, double[],
   * boolean, ArrayList, OperatorType)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#RandomVariableUniqueVariable(double,
   * double[], boolean, ArrayList, OperatorType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableUniqueVariable.<init>(double, double[], boolean, ArrayList, OperatorType)"
  })
  public void testNewRandomVariableUniqueVariable_whenArrayList() {
    // Arrange and Act
    RandomVariableUniqueVariable actualRandomVariableUniqueVariable =
        new RandomVariableUniqueVariable(
            10.0d,
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            true,
            new ArrayList<>(),
            OperatorType.ADD);

    // Assert
    assertTrue(actualRandomVariableUniqueVariable.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableUniqueVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d},
        actualRandomVariableUniqueVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#RandomVariableUniqueVariable(RandomVariable, boolean,
   * ArrayList, OperatorType)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableUniqueVariable#RandomVariableUniqueVariable(RandomVariable, boolean, ArrayList,
   * OperatorType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableUniqueVariable.<init>(RandomVariable, boolean, ArrayList, OperatorType)"
  })
  public void testNewRandomVariableUniqueVariable_whenArrayList2() {
    // Arrange
    RandomVariableFromDoubleArray randomVariable = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariableUniqueVariable actualRandomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true, new ArrayList<>(), OperatorType.ADD);

    // Assert
    assertTrue(actualRandomVariableUniqueVariable.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableUniqueVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableUniqueVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#RandomVariableUniqueVariable(double, double[],
   * boolean, ArrayList, OperatorType)}.
   *
   * <ul>
   *   <li>When {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#RandomVariableUniqueVariable(double,
   * double[], boolean, ArrayList, OperatorType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableUniqueVariable.<init>(double, double[], boolean, ArrayList, OperatorType)"
  })
  public void testNewRandomVariableUniqueVariable_whenFalse() {
    // Arrange and Act
    RandomVariableUniqueVariable actualRandomVariableUniqueVariable =
        new RandomVariableUniqueVariable(
            10.0d,
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            false,
            new ArrayList<>(),
            OperatorType.ADD);

    // Assert
    assertTrue(actualRandomVariableUniqueVariable.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableUniqueVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d},
        actualRandomVariableUniqueVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#RandomVariableUniqueVariable(RandomVariable, boolean,
   * ArrayList, OperatorType)}.
   *
   * <ul>
   *   <li>When {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableUniqueVariable#RandomVariableUniqueVariable(RandomVariable, boolean, ArrayList,
   * OperatorType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RandomVariableUniqueVariable.<init>(RandomVariable, boolean, ArrayList, OperatorType)"
  })
  public void testNewRandomVariableUniqueVariable_whenFalse2() {
    // Arrange
    RandomVariableFromDoubleArray randomVariable = new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariableUniqueVariable actualRandomVariableUniqueVariable =
        new RandomVariableUniqueVariable(
            randomVariable, false, new ArrayList<>(), OperatorType.ADD);

    // Assert
    assertTrue(actualRandomVariableUniqueVariable.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableUniqueVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableUniqueVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#RandomVariableUniqueVariable(double, double[],
   * boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return Variable.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#RandomVariableUniqueVariable(double,
   * double[], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableUniqueVariable.<init>(double, double[], boolean)"})
  public void testNewRandomVariableUniqueVariable_whenFalse_thenReturnVariable() {
    // Arrange and Act
    RandomVariableUniqueVariable actualRandomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d}, false);

    // Assert
    assertTrue(actualRandomVariableUniqueVariable.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableUniqueVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableUniqueVariable.isVariable());
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d},
        actualRandomVariableUniqueVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#RandomVariableUniqueVariable(RandomVariable,
   * boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return Variable.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableUniqueVariable#RandomVariableUniqueVariable(RandomVariable, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableUniqueVariable.<init>(RandomVariable, boolean)"})
  public void testNewRandomVariableUniqueVariable_whenFalse_thenReturnVariable2() {
    // Arrange and Act
    RandomVariableUniqueVariable actualRandomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(10.0d), false);

    // Assert
    assertTrue(actualRandomVariableUniqueVariable.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableUniqueVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableUniqueVariable.isVariable());
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableUniqueVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#RandomVariableUniqueVariable(double, double[],
   * boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return not Variable.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#RandomVariableUniqueVariable(double,
   * double[], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableUniqueVariable.<init>(double, double[], boolean)"})
  public void testNewRandomVariableUniqueVariable_whenTrue_thenReturnNotVariable() {
    // Arrange and Act
    RandomVariableUniqueVariable actualRandomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d}, true);

    // Assert
    assertTrue(actualRandomVariableUniqueVariable.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableUniqueVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertFalse(actualRandomVariableUniqueVariable.isVariable());
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d},
        actualRandomVariableUniqueVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#RandomVariableUniqueVariable(RandomVariable,
   * boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return not Variable.
   * </ul>
   *
   * <p>Method under test: {@link
   * RandomVariableUniqueVariable#RandomVariableUniqueVariable(RandomVariable, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RandomVariableUniqueVariable.<init>(RandomVariable, boolean)"})
  public void testNewRandomVariableUniqueVariable_whenTrue_thenReturnNotVariable2() {
    // Arrange and Act
    RandomVariableUniqueVariable actualRandomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(10.0d), true);

    // Assert
    assertTrue(actualRandomVariableUniqueVariable.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualRandomVariableUniqueVariable.isNaN() instanceof RandomVariableFromDoubleArray);
    assertFalse(actualRandomVariableUniqueVariable.isVariable());
    assertArrayEquals(
        new double[] {10.0d}, actualRandomVariableUniqueVariable.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#isVariable()}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#isVariable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableUniqueVariable.isVariable()"})
  public void testIsVariable_givenRandomVariableFromDoubleArrayWithValueIsTen_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(10.0d), true)
            .isVariable());
  }

  /**
   * Test {@link RandomVariableUniqueVariable#isVariable()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#isVariable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableUniqueVariable.isVariable()"})
  public void testIsVariable_thenReturnTrue() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertTrue(randomVariableUniqueVariable.isVariable());
  }

  /**
   * Test {@link RandomVariableUniqueVariable#equals(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#equals(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableUniqueVariable.equals(RandomVariable)"})
  public void testEqualsWithRandomVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    boolean actualEqualsResult =
        randomVariableUniqueVariable.equals(
            (RandomVariable) new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertFalse(actualEqualsResult);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getFiltrationTime()}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getFiltrationTime()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableUniqueVariable.getFiltrationTime()"})
  public void testGetFiltrationTime() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertEquals(10.0d, randomVariableUniqueVariable.getFiltrationTime(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getFiltrationTime()}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getFiltrationTime()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableUniqueVariable.getFiltrationTime()"})
  public void testGetFiltrationTime2() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(Double.NEGATIVE_INFINITY)});

    // Act and Assert
    assertEquals(
        10.0d, new RandomVariableUniqueVariable(randomVariable, true).getFiltrationTime(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getFiltrationTime()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then return {@link Double#NEGATIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getFiltrationTime()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableUniqueVariable.getFiltrationTime()"})
  public void testGetFiltrationTime_givenScalarWithValueIsTen_thenReturnNegative_infinity() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);

    // Act and Assert
    assertEquals(
        Double.NEGATIVE_INFINITY,
        new RandomVariableUniqueVariable(randomVariable, true).getFiltrationTime(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#get(int)}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#get(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableUniqueVariable.get(int)"})
  public void testGet() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertEquals(-1.0d, new RandomVariableUniqueVariable(randomVariable, true).get(1), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#get(int)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#get(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableUniqueVariable.get(int)"})
  public void testGet_givenRandomVariableFromDoubleArrayWithValueIsTen_thenReturnTen() {
    // Arrange, Act and Assert
    assertEquals(
        10.0d,
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(10.0d), true).get(1),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#get(int)}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#get(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableUniqueVariable.get(int)"})
  public void testGet_givenScalarWithValueIsTen_thenReturnTen() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);

    // Act and Assert
    assertEquals(10.0d, new RandomVariableUniqueVariable(randomVariable, true).get(1), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#get(int)}.
   *
   * <ul>
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#get(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableUniqueVariable.get(int)"})
  public void testGet_thenReturnMinusOne() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertEquals(-1.0d, randomVariableUniqueVariable.get(1), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#size()}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#size()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int RandomVariableUniqueVariable.size()"})
  public void testSize() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertEquals(4, new RandomVariableUniqueVariable(randomVariable, true).size());
  }

  /**
   * Test {@link RandomVariableUniqueVariable#size()}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#size()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int RandomVariableUniqueVariable.size()"})
  public void testSize_givenRandomVariableFromDoubleArrayWithValueIsTen_thenReturnOne() {
    // Arrange, Act and Assert
    assertEquals(
        1, new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(10.0d), true).size());
  }

  /**
   * Test {@link RandomVariableUniqueVariable#size()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#size()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int RandomVariableUniqueVariable.size()"})
  public void testSize_givenScalarWithValueIsTen_thenReturnOne() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);

    // Act and Assert
    assertEquals(1, new RandomVariableUniqueVariable(randomVariable, true).size());
  }

  /**
   * Test {@link RandomVariableUniqueVariable#size()}.
   *
   * <ul>
   *   <li>Then return four.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#size()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int RandomVariableUniqueVariable.size()"})
  public void testSize_thenReturnFour() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertEquals(4, randomVariableUniqueVariable.size());
  }

  /**
   * Test {@link RandomVariableUniqueVariable#isDeterministic()}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#isDeterministic()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableUniqueVariable.isDeterministic()"})
  public void testIsDeterministic() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertFalse(new RandomVariableUniqueVariable(randomVariable, true).isDeterministic());
  }

  /**
   * Test {@link RandomVariableUniqueVariable#isDeterministic()}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#isDeterministic()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableUniqueVariable.isDeterministic()"})
  public void testIsDeterministic_givenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange, Act and Assert
    assertTrue(
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(10.0d), true)
            .isDeterministic());
  }

  /**
   * Test {@link RandomVariableUniqueVariable#isDeterministic()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#isDeterministic()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableUniqueVariable.isDeterministic()"})
  public void testIsDeterministic_givenScalarWithValueIsTen_thenReturnTrue() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);

    // Act and Assert
    assertTrue(new RandomVariableUniqueVariable(randomVariable, true).isDeterministic());
  }

  /**
   * Test {@link RandomVariableUniqueVariable#isDeterministic()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#isDeterministic()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RandomVariableUniqueVariable.isDeterministic()"})
  public void testIsDeterministic_thenReturnFalse() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertFalse(randomVariableUniqueVariable.isDeterministic());
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getRealizations()}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getRealizations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableUniqueVariable.getRealizations()"})
  public void testGetRealizations() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d},
        new RandomVariableUniqueVariable(randomVariable, true).getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getRealizations()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getRealizations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableUniqueVariable.getRealizations()"})
  public void testGetRealizations_givenScalarWithValueIsTen_thenReturnNull() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);

    // Act and Assert
    assertNull(new RandomVariableUniqueVariable(randomVariable, true).getRealizations());
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getRealizations()}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getRealizations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableUniqueVariable.getRealizations()"})
  public void testGetRealizations_thenReturnArrayOfDoubleWithTen() {
    // Arrange, Act and Assert
    assertArrayEquals(
        new double[] {10.0d},
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(10.0d), true)
            .getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getRealizations()}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with ten and minus one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getRealizations()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableUniqueVariable.getRealizations()"})
  public void testGetRealizations_thenReturnArrayOfDoubleWithTenAndMinusOne() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d},
        randomVariableUniqueVariable.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#doubleValue()}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#doubleValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double RandomVariableUniqueVariable.doubleValue()"})
  public void testDoubleValue() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(10.0d), true);

    // Act and Assert
    assertEquals(
        10.0d,
        new RandomVariableUniqueVariable(randomVariable, true).doubleValue().doubleValue(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#doubleValue()}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#doubleValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double RandomVariableUniqueVariable.doubleValue()"})
  public void testDoubleValue_givenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange, Act and Assert
    assertEquals(
        10.0d,
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(10.0d), true)
            .doubleValue()
            .doubleValue(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#doubleValue()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then return doubleValue is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#doubleValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double RandomVariableUniqueVariable.doubleValue()"})
  public void testDoubleValue_givenScalarWithValueIsTen_thenReturnDoubleValueIsTen() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);

    // Act and Assert
    assertEquals(
        10.0d,
        new RandomVariableUniqueVariable(randomVariable, true).doubleValue().doubleValue(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getAverage(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getAverage(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableUniqueVariable.getAverage(RandomVariable)"})
  public void testGetAverageWithRandomVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    double actualAverage =
        randomVariableUniqueVariable.getAverage(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualAverage, 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RandomVariableUniqueVariable#getAverage()}
   *   <li>{@link RandomVariableUniqueVariable#getMax()}
   *   <li>{@link RandomVariableUniqueVariable#getMin()}
   *   <li>{@link RandomVariableUniqueVariable#getSampleVariance()}
   *   <li>{@link RandomVariableUniqueVariable#getStandardDeviation()}
   *   <li>{@link RandomVariableUniqueVariable#getStandardError()}
   *   <li>{@link RandomVariableUniqueVariable#getTypePriority()}
   *   <li>{@link RandomVariableUniqueVariable#getVariableID()}
   *   <li>{@link RandomVariableUniqueVariable#getVariance()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double RandomVariableUniqueVariable.getAverage()",
    "double RandomVariableUniqueVariable.getMax()",
    "double RandomVariableUniqueVariable.getMin()",
    "double RandomVariableUniqueVariable.getSampleVariance()",
    "double RandomVariableUniqueVariable.getStandardDeviation()",
    "double RandomVariableUniqueVariable.getStandardError()",
    "int RandomVariableUniqueVariable.getTypePriority()",
    "int RandomVariableUniqueVariable.getVariableID()",
    "double RandomVariableUniqueVariable.getVariance()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    double actualAverage = randomVariableUniqueVariable.getAverage();
    double actualMax = randomVariableUniqueVariable.getMax();
    double actualMin = randomVariableUniqueVariable.getMin();
    double actualSampleVariance = randomVariableUniqueVariable.getSampleVariance();
    double actualStandardDeviation = randomVariableUniqueVariable.getStandardDeviation();
    double actualStandardError = randomVariableUniqueVariable.getStandardError();
    int actualTypePriority = randomVariableUniqueVariable.getTypePriority();
    randomVariableUniqueVariable.getVariableID();

    // Assert
    assertEquals(0.0d, actualAverage, 0.0);
    assertEquals(0.0d, actualMax, 0.0);
    assertEquals(0.0d, actualMin, 0.0);
    assertEquals(0.0d, actualSampleVariance, 0.0);
    assertEquals(0.0d, actualStandardDeviation, 0.0);
    assertEquals(0.0d, actualStandardError, 0.0);
    assertEquals(0.0d, randomVariableUniqueVariable.getVariance(), 0.0);
    assertEquals(3, actualTypePriority);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getVariance(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getVariance(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableUniqueVariable.getVariance(RandomVariable)"})
  public void testGetVarianceWithRandomVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    double actualVariance =
        randomVariableUniqueVariable.getVariance(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualVariance, 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getStandardDeviation(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getStandardDeviation(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableUniqueVariable.getStandardDeviation(RandomVariable)"})
  public void testGetStandardDeviationWithRandomVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    double actualStandardDeviation =
        randomVariableUniqueVariable.getStandardDeviation(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualStandardDeviation, 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getStandardError(RandomVariable)} with {@code
   * RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getStandardError(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableUniqueVariable.getStandardError(RandomVariable)"})
  public void testGetStandardErrorWithRandomVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    double actualStandardError =
        randomVariableUniqueVariable.getStandardError(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertEquals(0.0d, actualStandardError, 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getQuantile(double)} with {@code quantile}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getQuantile(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableUniqueVariable.getQuantile(double)"})
  public void testGetQuantileWithQuantile() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertEquals(0.0d, randomVariableUniqueVariable.getQuantile(10.0d), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getQuantile(double, RandomVariable)} with {@code
   * quantile}, {@code probabilities}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getQuantile(double, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableUniqueVariable.getQuantile(double, RandomVariable)"})
  public void testGetQuantileWithQuantileProbabilities() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertEquals(
        0.0d,
        randomVariableUniqueVariable.getQuantile(10.0d, new RandomVariableFromDoubleArray(10.0d)),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getQuantileExpectation(double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getQuantileExpectation(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableUniqueVariable.getQuantileExpectation(double, double)"})
  public void testGetQuantileExpectation_when05() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertEquals(0.0d, randomVariableUniqueVariable.getQuantileExpectation(0.5d, 10.0d), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getQuantileExpectation(double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getQuantileExpectation(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableUniqueVariable.getQuantileExpectation(double, double)"})
  public void testGetQuantileExpectation_whenMinusOne() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertEquals(0.0d, randomVariableUniqueVariable.getQuantileExpectation(-1.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getQuantileExpectation(double, double)}.
   *
   * <ul>
   *   <li>When ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getQuantileExpectation(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableUniqueVariable.getQuantileExpectation(double, double)"})
  public void testGetQuantileExpectation_whenTen() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertEquals(0.0d, randomVariableUniqueVariable.getQuantileExpectation(10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getQuantileExpectation(double, double)}.
   *
   * <ul>
   *   <li>When zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getQuantileExpectation(double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RandomVariableUniqueVariable.getQuantileExpectation(double, double)"})
  public void testGetQuantileExpectation_whenZero() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertEquals(0.0d, randomVariableUniqueVariable.getQuantileExpectation(0.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getHistogram(double[])} with {@code intervalPoints}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableUniqueVariable.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints() {
    // Arrange and Act
    double[] actualHistogram =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(10.0d), true)
            .getHistogram(new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Assert
    assertArrayEquals(new double[] {0.0d, 1.0d, 0.0d, 0.0d, 1.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getHistogram(double[])} with {@code intervalPoints}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableUniqueVariable.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints2() {
    // Arrange and Act
    double[] actualHistogram =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(-1.0d), true)
            .getHistogram(new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Assert
    assertArrayEquals(new double[] {0.0d, 0.0d, 0.0d, 0.0d, 1.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getHistogram(double[])} with {@code intervalPoints}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableUniqueVariable.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints3() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    double[] actualHistogram =
        new RandomVariableUniqueVariable(randomVariable, true)
            .getHistogram(new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Assert
    assertArrayEquals(new double[] {1.0d, 0.0d, 0.0d, 0.0d, 0.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getHistogram(double[])} with {@code intervalPoints}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableUniqueVariable.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints4() {
    // Arrange and Act
    double[] actualHistogram =
        new RandomVariableUniqueVariable(new RandomVariableFromFloatArray(10.0d), true)
            .getHistogram(new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Assert
    assertArrayEquals(new double[] {0.0d, 1.0d, 0.0d, 0.0d, 1.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getHistogram(double[])} with {@code intervalPoints}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableUniqueVariable.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints5() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {});

    // Act
    double[] actualHistogram =
        randomVariableUniqueVariable.getHistogram(new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Assert
    assertArrayEquals(new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getHistogram(double[])} with {@code intervalPoints}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with {@code 0.5} and zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableUniqueVariable.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints_thenReturnArrayOfDoubleWith05AndZero() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    double[] actualHistogram =
        randomVariableUniqueVariable.getHistogram(new double[] {-1.0d, -1.0d, 10.0d, -1.0d});

    // Assert
    assertArrayEquals(new double[] {0.5d, 0.0d, 0.5d, 0.0d, 0.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getHistogram(double[])} with {@code intervalPoints}.
   *
   * <ul>
   *   <li>Then return array of {@code double} with one and zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getHistogram(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] RandomVariableUniqueVariable.getHistogram(double[])"})
  public void testGetHistogramWithIntervalPoints_thenReturnArrayOfDoubleWithOneAndZero() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    double[] actualHistogram =
        randomVariableUniqueVariable.getHistogram(new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Assert
    assertArrayEquals(new double[] {1.0d, 0.0d, 0.0d, 0.0d, 0.0d}, actualHistogram, 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getHistogram(int, double)} with {@code
   * numberOfPoints}, {@code standardDeviations}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] RandomVariableUniqueVariable.getHistogram(int, double)"})
  public void testGetHistogramWithNumberOfPointsStandardDeviations() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    double[][] actualHistogram = randomVariableUniqueVariable.getHistogram(10, 10.0d);

    // Assert
    assertEquals(2, actualHistogram.length);
    assertArrayEquals(
        new double[] {
          -56.611111111111114d,
          -44.388888888888886d,
          -32.16666666666667d,
          -19.944444444444443d,
          -7.722222222222221d,
          4.5d,
          16.72222222222222d,
          28.944444444444446d,
          41.16666666666667d,
          53.388888888888886d,
          65.61111111111111d
        },
        actualHistogram[0],
        0.0);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualHistogram[1],
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getHistogram(int, double)} with {@code
   * numberOfPoints}, {@code standardDeviations}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] RandomVariableUniqueVariable.getHistogram(int, double)"})
  public void testGetHistogramWithNumberOfPointsStandardDeviations2() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {});

    // Act
    double[][] actualHistogram = randomVariableUniqueVariable.getHistogram(10, 10.0d);

    // Assert
    assertEquals(2, actualHistogram.length);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualHistogram[1],
        0.0);
    assertArrayEquals(
        new double[] {
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN,
          Double.NaN
        },
        actualHistogram[0],
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getHistogram(int, double)} with {@code
   * numberOfPoints}, {@code standardDeviations}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] RandomVariableUniqueVariable.getHistogram(int, double)"})
  public void testGetHistogramWithNumberOfPointsStandardDeviations3() {
    // Arrange and Act
    double[][] actualHistogram =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(2.0d), true)
            .getHistogram(10, 10.0d);

    // Assert
    assertEquals(2, actualHistogram.length);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d},
        actualHistogram[1],
        0.0);
    assertArrayEquals(
        new double[] {2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d},
        actualHistogram[0],
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getHistogram(int, double)} with {@code
   * numberOfPoints}, {@code standardDeviations}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] RandomVariableUniqueVariable.getHistogram(int, double)"})
  public void testGetHistogramWithNumberOfPointsStandardDeviations4() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(2.0d, new double[] {2.0d, 1.0d, 2.0d, 1.0d});

    // Act
    double[][] actualHistogram =
        new RandomVariableUniqueVariable(randomVariable, true).getHistogram(10, 10.0d);

    // Assert
    assertEquals(2, actualHistogram.length);
    assertArrayEquals(
        new double[] {
          -4.055555555555555d,
          -2.9444444444444446d,
          -1.8333333333333333d,
          -0.7222222222222221d,
          0.38888888888888884d,
          1.4999999999999998d,
          2.6111111111111107d,
          3.7222222222222223d,
          4.833333333333334d,
          5.944444444444445d,
          7.055555555555555d
        },
        actualHistogram[0],
        0.0);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d},
        actualHistogram[1],
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getHistogram(int, double)} with {@code
   * numberOfPoints}, {@code standardDeviations}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getHistogram(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[][] RandomVariableUniqueVariable.getHistogram(int, double)"})
  public void testGetHistogramWithNumberOfPointsStandardDeviations5() {
    // Arrange and Act
    double[][] actualHistogram =
        new RandomVariableUniqueVariable(new RandomVariableFromFloatArray(2.0d), true)
            .getHistogram(10, 10.0d);

    // Assert
    assertEquals(2, actualHistogram.length);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d},
        actualHistogram[1],
        0.0);
    assertArrayEquals(
        new double[] {2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d, 2.0d},
        actualHistogram[0],
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#cache()}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#cache()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.cache()"})
  public void testCache() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualCacheResult = randomVariableUniqueVariable.cache();

    // Assert
    assertTrue(actualCacheResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCacheResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCacheResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCacheResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCacheResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCacheResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCacheResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCacheResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCacheResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCacheResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCacheResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualCacheResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#cache()}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#cache()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.cache()"})
  public void testCache2() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    randomVariable.addSumProduct(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)},
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Act
    RandomVariable actualCacheResult =
        new RandomVariableUniqueVariable(randomVariable, true).cache();

    // Assert
    assertTrue(actualCacheResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCacheResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCacheResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCacheResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCacheResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCacheResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCacheResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCacheResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCacheResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCacheResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCacheResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d}, actualCacheResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#cache()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then return {@link Scalar} with value is ten.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#cache()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.cache()"})
  public void testCache_givenScalarWithValueIsTen_thenReturnScalarWithValueIsTen() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);

    // Act and Assert
    assertSame(randomVariable, new RandomVariableUniqueVariable(randomVariable, true).cache());
  }

  /**
   * Test {@link RandomVariableUniqueVariable#floor(double)} with {@code double}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#floor(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.floor(double)"})
  public void testFloorWithDouble() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertNull(randomVariableUniqueVariable.floor(10.0d));
  }

  /**
   * Test {@link RandomVariableUniqueVariable#floor(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#floor(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.floor(RandomVariable)"})
  public void testFloorWithRandomVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualFloorResult =
        randomVariableUniqueVariable.floor(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertNull(actualFloorResult);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#add(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.add(RandomVariable)"})
  public void testAddWithRandomVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(Double.NaN), true);

    // Act
    RandomVariable actualAddResult =
        randomVariableUniqueVariable.add(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#add(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.add(RandomVariable)"})
  public void testAddWithRandomVariable2() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);

    // Act
    RandomVariable actualAddResult =
        randomVariableUniqueVariable.add(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then cache return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenCacheReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddResult =
        randomVariableUniqueVariable.add(RandomVariableDifferentiableAADPathwise.of(Double.NaN));

    // Assert
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.cache() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAddResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, Double.NaN, Double.NaN, Double.NaN},
        actualAddResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then cache return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenCacheReturnRandomVariableDifferentiableAADPathwise2() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);

    // Act
    RandomVariable actualAddResult =
        randomVariableUniqueVariable.add(RandomVariableDifferentiableAADPathwise.of(Double.NaN));

    // Assert
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.cache() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualAddResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then cache return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenCacheReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromFloatArray(10.0d), true);

    // Act
    RandomVariable actualAddResult =
        randomVariableUniqueVariable.add(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddResult.cache() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualAddResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then NaN return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenNaNReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableLazyEvaluation(10.0d), true);

    // Act
    RandomVariable actualAddResult =
        randomVariableUniqueVariable.add(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualAddResult instanceof RandomVariableUniqueVariable);
    assertEquals(0.0d, actualAddResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {20.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return FiltrationTime is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenReturnFiltrationTimeIsNaN() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(
            Double.NaN, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});

    // Act
    RandomVariable actualAddResult = randomVariableUniqueVariable.add(randomVariable);

    // Assert
    assertTrue(actualAddResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableUniqueVariable);
    assertEquals(Double.NaN, actualAddResult.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {Double.NaN, 9.0d, Double.NaN, 9.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and {@link
   *       Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithNaNAndNaN() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddResult = randomVariableUniqueVariable.add(Scalar.of(Double.NaN));

    // Assert
    assertTrue(actualAddResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, Double.NaN, Double.NaN, Double.NaN},
        actualAddResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and twenty.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithNaNAndTwenty() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(
            10.0d, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);

    // Act
    RandomVariable actualAddResult =
        randomVariableUniqueVariable.add(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, 20.0d, Double.NaN, 20.0d},
        actualAddResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with twenty and nine.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.add(RandomVariable)"})
  public void testAddWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithTwentyAndNine() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualAddResult =
        randomVariableUniqueVariable.add(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualAddResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {20.0d, 9.0d, 20.0d, 9.0d}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.add(RandomVariable)"})
  public void testAddWithRandomVariable_whenScalarWithValueIsNaN() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(Double.NaN), true);

    // Act
    RandomVariable actualAddResult = randomVariableUniqueVariable.add(Scalar.of(Double.NaN));

    // Assert
    assertTrue(actualAddResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualAddResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualAddResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#add(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is {@link Double#NaN}.
   *   <li>Then cache return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#add(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.add(RandomVariable)"})
  public void testAddWithRandomVariable_whenScalarWithValueIsNaN_thenCacheReturnScalar() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);

    // Act
    RandomVariable actualAddResult = randomVariableUniqueVariable.add(Scalar.of(Double.NaN));

    // Assert
    assertTrue(actualAddResult instanceof RandomVariableUniqueVariable);
    assertTrue(actualAddResult.cache() instanceof Scalar);
    assertTrue(actualAddResult.isNaN() instanceof Scalar);
    assertNull(actualAddResult.getRealizations());
  }

  /**
   * Test {@link RandomVariableUniqueVariable#add(double)} with {@code value}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#add(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.add(double)"})
  public void testAddWithValue() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertNull(randomVariableUniqueVariable.add(10.0d));
  }

  /**
   * Test {@link RandomVariableUniqueVariable#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.sub(RandomVariable)"})
  public void testSubWithRandomVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualSubResult =
        randomVariableUniqueVariable.sub(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {0.0d, -11.0d, 0.0d, -11.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.sub(RandomVariable)"})
  public void testSubWithRandomVariable2() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(Double.NaN), true);

    // Act
    RandomVariable actualSubResult =
        randomVariableUniqueVariable.sub(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.sub(RandomVariable)"})
  public void testSubWithRandomVariable3() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(
            Double.NaN, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});

    // Act
    RandomVariable actualSubResult = randomVariableUniqueVariable.sub(randomVariable);

    // Assert
    assertTrue(actualSubResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, -11.0d, Double.NaN, -11.0d},
        actualSubResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.sub(RandomVariable)"})
  public void testSubWithRandomVariable4() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromFloatArray(10.0d), true);

    // Act
    RandomVariable actualSubResult =
        randomVariableUniqueVariable.sub(RandomVariableDifferentiableAADPathwise.of(Double.NaN));

    // Assert
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.cache() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.sub(RandomVariable)"})
  public void testSubWithRandomVariable5() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromFloatArray(10.0d), true);
    RandomVariableFromDoubleArray randomVariable =
        new RandomVariableFromDoubleArray(Double.NaN, Double.NaN, 2);

    // Act
    RandomVariable actualSubResult = randomVariableUniqueVariable.sub(randomVariable);

    // Assert
    assertTrue(actualSubResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.sub(RandomVariable)"})
  public void testSubWithRandomVariable6() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(10.0d, 10.0d, 2), true);

    // Act
    RandomVariable actualSubResult =
        randomVariableUniqueVariable.sub(RandomVariableDifferentiableAADPathwise.of(Double.NaN));

    // Assert
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.cache() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then cache return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenCacheReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(Double.NaN), true);

    // Act
    RandomVariable actualSubResult =
        randomVariableUniqueVariable.sub(RandomVariableDifferentiableAADPathwise.of(Double.NaN));

    // Assert
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.cache() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then cache return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenCacheReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromFloatArray(10.0d), true);

    // Act
    RandomVariable actualSubResult =
        randomVariableUniqueVariable.sub(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubResult.cache() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualSubResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then NaN return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenNaNReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableLazyEvaluation(10.0d), true);

    // Act
    RandomVariable actualSubResult =
        randomVariableUniqueVariable.sub(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualSubResult instanceof RandomVariableUniqueVariable);
    assertEquals(0.0d, actualSubResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@code -0.0}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWith00() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);

    // Act
    RandomVariable actualSubResult =
        randomVariableUniqueVariable.sub(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {-0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and {@code
   *       -0.0}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithNaNAnd00() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);
    RandomVariableUniqueVariable randomVariable2 =
        new RandomVariableUniqueVariable(
            Double.NaN, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});

    // Act
    RandomVariable actualSubResult = randomVariableUniqueVariable.sub(randomVariable2);

    // Assert
    assertTrue(actualSubResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, -0.0d, Double.NaN, -0.0d},
        actualSubResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and {@link
   *       Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithNaNAndNaN() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualSubResult = randomVariableUniqueVariable.sub(Scalar.of(Double.NaN));

    // Assert
    assertTrue(actualSubResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, Double.NaN, Double.NaN, Double.NaN},
        actualSubResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and {@link
   *       Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithNaNAndNaN2() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualSubResult =
        randomVariableUniqueVariable.sub(RandomVariableDifferentiableAADPathwise.of(Double.NaN));

    // Assert
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.cache() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualSubResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, Double.NaN, Double.NaN, Double.NaN},
        actualSubResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and {@link
   *       Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithNaNAndNaN3() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(Double.NaN), true);
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(
            Double.NaN, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});

    // Act
    RandomVariable actualSubResult = randomVariableUniqueVariable.sub(randomVariable);

    // Assert
    assertTrue(actualSubResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, Double.NaN, Double.NaN, Double.NaN},
        actualSubResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.sub(RandomVariable)"})
  public void testSubWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithNaNAndZero() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(
            10.0d, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);

    // Act
    RandomVariable actualSubResult =
        randomVariableUniqueVariable.sub(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualSubResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, 0.0d, Double.NaN, 0.0d}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.sub(RandomVariable)"})
  public void testSubWithRandomVariable_whenScalarWithValueIsNaN() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(Double.NaN), true);

    // Act
    RandomVariable actualSubResult = randomVariableUniqueVariable.sub(Scalar.of(Double.NaN));

    // Assert
    assertTrue(actualSubResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSubResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualSubResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#sub(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is {@link Double#NaN}.
   *   <li>Then cache return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#sub(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.sub(RandomVariable)"})
  public void testSubWithRandomVariable_whenScalarWithValueIsNaN_thenCacheReturnScalar() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);

    // Act
    RandomVariable actualSubResult = randomVariableUniqueVariable.sub(Scalar.of(Double.NaN));

    // Assert
    assertTrue(actualSubResult instanceof RandomVariableUniqueVariable);
    assertTrue(actualSubResult.cache() instanceof Scalar);
    assertTrue(actualSubResult.isNaN() instanceof Scalar);
    assertNull(actualSubResult.getRealizations());
  }

  /**
   * Test {@link RandomVariableUniqueVariable#sub(double)} with {@code value}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#sub(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.sub(double)"})
  public void testSubWithValue() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertNull(randomVariableUniqueVariable.sub(10.0d));
  }

  /**
   * Test {@link RandomVariableUniqueVariable#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.mult(RandomVariable)"})
  public void testMultWithRandomVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualMultResult =
        randomVariableUniqueVariable.mult(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualMultResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {100.0d, -10.0d, 100.0d, -10.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.mult(RandomVariable)"})
  public void testMultWithRandomVariable2() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(Double.NaN), true);

    // Act
    RandomVariable actualMultResult =
        randomVariableUniqueVariable.mult(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualMultResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.mult(RandomVariable)"})
  public void testMultWithRandomVariable3() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualMultResult =
        randomVariableUniqueVariable.mult(RandomVariableDifferentiableAADPathwise.of(Double.NaN));

    // Assert
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.cache() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualMultResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, Double.NaN, Double.NaN, Double.NaN},
        actualMultResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.mult(RandomVariable)"})
  public void testMultWithRandomVariable4() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(
            10.0d, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);

    // Act
    RandomVariable actualMultResult =
        randomVariableUniqueVariable.mult(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualMultResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, 100.0d, Double.NaN, 100.0d},
        actualMultResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.mult(RandomVariable)"})
  public void testMultWithRandomVariable5() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);

    // Act
    RandomVariable actualMultResult =
        randomVariableUniqueVariable.mult(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualMultResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.mult(RandomVariable)"})
  public void testMultWithRandomVariable6() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);

    // Act
    RandomVariable actualMultResult =
        randomVariableUniqueVariable.mult(RandomVariableDifferentiableAADPathwise.of(Double.NaN));

    // Assert
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.cache() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualMultResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then cache return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenCacheReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromFloatArray(10.0d), true);

    // Act
    RandomVariable actualMultResult =
        randomVariableUniqueVariable.mult(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualMultResult.cache() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualMultResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then NaN return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenNaNReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableLazyEvaluation(10.0d), true);

    // Act
    RandomVariable actualMultResult =
        randomVariableUniqueVariable.mult(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualMultResult instanceof RandomVariableUniqueVariable);
    assertEquals(0.0d, actualMultResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {100.0d}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return FiltrationTime is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenReturnFiltrationTimeIsNaN() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(
            Double.NaN, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});

    // Act
    RandomVariable actualMultResult = randomVariableUniqueVariable.mult(randomVariable);

    // Assert
    assertTrue(actualMultResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableUniqueVariable);
    assertEquals(Double.NaN, actualMultResult.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {Double.NaN, -10.0d, Double.NaN, -10.0d},
        actualMultResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and {@link
   *       Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.mult(RandomVariable)"})
  public void testMultWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithNaNAndNaN() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualMultResult = randomVariableUniqueVariable.mult(Scalar.of(Double.NaN));

    // Assert
    assertTrue(actualMultResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, Double.NaN, Double.NaN, Double.NaN},
        actualMultResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.mult(RandomVariable)"})
  public void testMultWithRandomVariable_whenScalarWithValueIsNaN() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(Double.NaN), true);

    // Act
    RandomVariable actualMultResult = randomVariableUniqueVariable.mult(Scalar.of(Double.NaN));

    // Assert
    assertTrue(actualMultResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualMultResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualMultResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#mult(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is {@link Double#NaN}.
   *   <li>Then cache return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#mult(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.mult(RandomVariable)"})
  public void testMultWithRandomVariable_whenScalarWithValueIsNaN_thenCacheReturnScalar() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);

    // Act
    RandomVariable actualMultResult = randomVariableUniqueVariable.mult(Scalar.of(Double.NaN));

    // Assert
    assertTrue(actualMultResult instanceof RandomVariableUniqueVariable);
    assertTrue(actualMultResult.cache() instanceof Scalar);
    assertTrue(actualMultResult.isNaN() instanceof Scalar);
    assertNull(actualMultResult.getRealizations());
  }

  /**
   * Test {@link RandomVariableUniqueVariable#mult(double)} with {@code value}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#mult(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.mult(double)"})
  public void testMultWithValue() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertNull(randomVariableUniqueVariable.mult(10.0d));
  }

  /**
   * Test {@link RandomVariableUniqueVariable#div(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.div(RandomVariable)"})
  public void testDivWithRandomVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(Double.NaN), true);

    // Act
    RandomVariable actualDivResult =
        randomVariableUniqueVariable.div(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualDivResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#div(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.div(RandomVariable)"})
  public void testDivWithRandomVariable2() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(
            10.0d, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);

    // Act
    RandomVariable actualDivResult =
        randomVariableUniqueVariable.div(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualDivResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, 1.0d, Double.NaN, 1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#div(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.div(RandomVariable)"})
  public void testDivWithRandomVariable3() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);

    // Act
    RandomVariable actualDivResult =
        randomVariableUniqueVariable.div(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualDivResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#div(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.div(RandomVariable)"})
  public void testDivWithRandomVariable4() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromFloatArray(10.0d), true);

    // Act
    RandomVariable actualDivResult =
        randomVariableUniqueVariable.div(RandomVariableDifferentiableAADPathwise.of(Double.NaN));

    // Assert
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.cache() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualDivResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#div(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.div(RandomVariable)"})
  public void testDivWithRandomVariable5() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromFloatArray(10.0d), true);
    RandomVariableFromDoubleArray randomVariable =
        new RandomVariableFromDoubleArray(Double.NaN, Double.NaN, 2);

    // Act
    RandomVariable actualDivResult = randomVariableUniqueVariable.div(randomVariable);

    // Assert
    assertTrue(actualDivResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#div(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.div(RandomVariable)"})
  public void testDivWithRandomVariable6() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(10.0d, 10.0d, 2), true);

    // Act
    RandomVariable actualDivResult =
        randomVariableUniqueVariable.div(RandomVariableDifferentiableAADPathwise.of(Double.NaN));

    // Assert
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.cache() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualDivResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then cache return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenCacheReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(Double.NaN), true);

    // Act
    RandomVariable actualDivResult =
        randomVariableUniqueVariable.div(RandomVariableDifferentiableAADPathwise.of(Double.NaN));

    // Assert
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.cache() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualDivResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then cache return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenCacheReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromFloatArray(10.0d), true);

    // Act
    RandomVariable actualDivResult =
        randomVariableUniqueVariable.div(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualDivResult.cache() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualDivResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then NaN return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenNaNReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableLazyEvaluation(10.0d), true);

    // Act
    RandomVariable actualDivResult =
        randomVariableUniqueVariable.div(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualDivResult instanceof RandomVariableUniqueVariable);
    assertEquals(0.0d, actualDivResult.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and {@code
   *       -0.1}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithNaNAnd01() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(
            Double.NaN, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});

    // Act
    RandomVariable actualDivResult = randomVariableUniqueVariable.div(randomVariable);

    // Assert
    assertTrue(actualDivResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, -0.1d, Double.NaN, -0.1d},
        actualDivResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and {@link
   *       Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithNaNAndNaN() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualDivResult = randomVariableUniqueVariable.div(Scalar.of(Double.NaN));

    // Assert
    assertTrue(actualDivResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, Double.NaN, Double.NaN, Double.NaN},
        actualDivResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and {@link
   *       Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithNaNAndNaN2() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualDivResult =
        randomVariableUniqueVariable.div(RandomVariableDifferentiableAADPathwise.of(Double.NaN));

    // Assert
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.cache() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualDivResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, Double.NaN, Double.NaN, Double.NaN},
        actualDivResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and {@link
   *       Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithNaNAndNaN3() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(Double.NaN), true);
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(
            Double.NaN, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});

    // Act
    RandomVariable actualDivResult = randomVariableUniqueVariable.div(randomVariable);

    // Assert
    assertTrue(actualDivResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, Double.NaN, Double.NaN, Double.NaN},
        actualDivResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithNaNAndOne() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);
    RandomVariableUniqueVariable randomVariable2 =
        new RandomVariableUniqueVariable(
            Double.NaN, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});

    // Act
    RandomVariable actualDivResult = randomVariableUniqueVariable.div(randomVariable2);

    // Assert
    assertTrue(actualDivResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, 1.0d, Double.NaN, 1.0d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with one and {@code -0.1}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.div(RandomVariable)"})
  public void testDivWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithOneAnd01() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualDivResult =
        randomVariableUniqueVariable.div(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualDivResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {1.0d, -0.1d, 1.0d, -0.1d}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.div(RandomVariable)"})
  public void testDivWithRandomVariable_whenScalarWithValueIsNaN() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(Double.NaN), true);

    // Act
    RandomVariable actualDivResult = randomVariableUniqueVariable.div(Scalar.of(Double.NaN));

    // Assert
    assertTrue(actualDivResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualDivResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualDivResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#div(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is {@link Double#NaN}.
   *   <li>Then cache return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#div(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.div(RandomVariable)"})
  public void testDivWithRandomVariable_whenScalarWithValueIsNaN_thenCacheReturnScalar() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);

    // Act
    RandomVariable actualDivResult = randomVariableUniqueVariable.div(Scalar.of(Double.NaN));

    // Assert
    assertTrue(actualDivResult instanceof RandomVariableUniqueVariable);
    assertTrue(actualDivResult.cache() instanceof Scalar);
    assertTrue(actualDivResult.isNaN() instanceof Scalar);
    assertNull(actualDivResult.getRealizations());
  }

  /**
   * Test {@link RandomVariableUniqueVariable#div(double)} with {@code value}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#div(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.div(double)"})
  public void testDivWithValue() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertNull(randomVariableUniqueVariable.div(10.0d));
  }

  /**
   * Test {@link RandomVariableUniqueVariable#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.vid(RandomVariable)"})
  public void testVidWithRandomVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualVidResult =
        randomVariableUniqueVariable.vid(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualVidResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {1.0d, -10.0d, 1.0d, -10.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.vid(RandomVariable)"})
  public void testVidWithRandomVariable2() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(Double.NaN), true);

    // Act
    RandomVariable actualVidResult =
        randomVariableUniqueVariable.vid(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualVidResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.vid(RandomVariable)"})
  public void testVidWithRandomVariable3() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(
            Double.NaN, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});

    // Act
    RandomVariable actualVidResult = randomVariableUniqueVariable.vid(randomVariable);

    // Assert
    assertTrue(actualVidResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, -10.0d, Double.NaN, -10.0d},
        actualVidResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.vid(RandomVariable)"})
  public void testVidWithRandomVariable4() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);
    RandomVariableFromDoubleArray randomVariable2 =
        new RandomVariableFromDoubleArray(Double.NaN, Double.NaN, 2);

    // Act
    RandomVariable actualVidResult = randomVariableUniqueVariable.vid(randomVariable2);

    // Assert
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.cache() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualVidResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then cache return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.vid(RandomVariable)"})
  public void testVidWithRandomVariable_givenScalarWithValueIsTen_thenCacheReturnScalar() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);

    // Act
    RandomVariable actualVidResult = randomVariableUniqueVariable.vid(Scalar.of(Double.NaN));

    // Assert
    assertTrue(actualVidResult instanceof RandomVariableUniqueVariable);
    assertTrue(actualVidResult.cache() instanceof Scalar);
    assertTrue(actualVidResult.isNaN() instanceof Scalar);
    assertNull(actualVidResult.getRealizations());
  }

  /**
   * Test {@link RandomVariableUniqueVariable#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then cache return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenCacheReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);

    // Act
    RandomVariable actualVidResult =
        randomVariableUniqueVariable.vid(new RandomVariableFromFloatArray(Double.NaN));

    // Assert
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.cache() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualVidResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then cache return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenCacheReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualVidResult =
        randomVariableUniqueVariable.vid(new RandomVariableFromFloatArray(Double.NaN));

    // Assert
    assertTrue(actualVidResult.cache() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, Double.NaN, Double.NaN, Double.NaN},
        actualVidResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then cache return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenCacheReturnRandomVariableFromFloatArray2() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(
            10.0d, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);

    // Act
    RandomVariable actualVidResult =
        randomVariableUniqueVariable.vid(new RandomVariableFromFloatArray(Double.NaN));

    // Assert
    assertTrue(actualVidResult.cache() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualVidResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, Double.NaN, Double.NaN, Double.NaN},
        actualVidResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then cache return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenCacheReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualVidResult =
        randomVariableUniqueVariable.vid(new RandomVariableLazyEvaluation(Double.NaN));

    // Assert
    assertTrue(actualVidResult.cache() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualVidResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, Double.NaN, Double.NaN, Double.NaN},
        actualVidResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and {@link
   *       Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithNaNAndNaN() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualVidResult = randomVariableUniqueVariable.vid(Scalar.of(Double.NaN));

    // Assert
    assertTrue(actualVidResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, Double.NaN, Double.NaN, Double.NaN},
        actualVidResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and {@link
   *       Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithNaNAndNaN2() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(Double.NaN), true);
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(
            Double.NaN, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});

    // Act
    RandomVariable actualVidResult = randomVariableUniqueVariable.vid(randomVariable);

    // Assert
    assertTrue(actualVidResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, Double.NaN, Double.NaN, Double.NaN},
        actualVidResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithNaNAndOne() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(
            10.0d, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);

    // Act
    RandomVariable actualVidResult =
        randomVariableUniqueVariable.vid(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualVidResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, 1.0d, Double.NaN, 1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithNaNAndOne2() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);
    RandomVariableUniqueVariable randomVariable2 =
        new RandomVariableUniqueVariable(
            Double.NaN, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});

    // Act
    RandomVariable actualVidResult = randomVariableUniqueVariable.vid(randomVariable2);

    // Assert
    assertTrue(actualVidResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, 1.0d, Double.NaN, 1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithNaNAndOne3() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);
    RandomVariableUniqueVariable randomVariable2 =
        new RandomVariableUniqueVariable(
            Double.NaN, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});

    // Act
    RandomVariable actualVidResult = randomVariableUniqueVariable.vid(randomVariable2);

    // Assert
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.cache() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualVidResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, 1.0d, Double.NaN, 1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithOne() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);

    // Act
    RandomVariable actualVidResult =
        randomVariableUniqueVariable.vid(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualVidResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.vid(RandomVariable)"})
  public void testVidWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithOne2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);

    // Act
    RandomVariable actualVidResult =
        randomVariableUniqueVariable.vid(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.cache() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualVidResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {1.0d}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#vid(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#vid(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.vid(RandomVariable)"})
  public void testVidWithRandomVariable_whenScalarWithValueIsNaN() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(Double.NaN), true);

    // Act
    RandomVariable actualVidResult = randomVariableUniqueVariable.vid(Scalar.of(Double.NaN));

    // Assert
    assertTrue(actualVidResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualVidResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualVidResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#pow(double)}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#pow(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.pow(double)"})
  public void testPow() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertNull(randomVariableUniqueVariable.pow(10.0d));
  }

  /**
   * Test {@link RandomVariableUniqueVariable#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.bus(RandomVariable)"})
  public void testBusWithRandomVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(Double.NaN), true);

    // Act
    RandomVariable actualBusResult =
        randomVariableUniqueVariable.bus(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBusResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.bus(RandomVariable)"})
  public void testBusWithRandomVariable2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);
    RandomVariableFromDoubleArray randomVariable2 =
        new RandomVariableFromDoubleArray(Double.NaN, Double.NaN, 2);

    // Act
    RandomVariable actualBusResult = randomVariableUniqueVariable.bus(randomVariable2);

    // Assert
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.cache() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualBusResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is ten.
   *   <li>Then cache return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.bus(RandomVariable)"})
  public void testBusWithRandomVariable_givenScalarWithValueIsTen_thenCacheReturnScalar() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);

    // Act
    RandomVariable actualBusResult = randomVariableUniqueVariable.bus(Scalar.of(Double.NaN));

    // Assert
    assertTrue(actualBusResult instanceof RandomVariableUniqueVariable);
    assertTrue(actualBusResult.cache() instanceof Scalar);
    assertTrue(actualBusResult.isNaN() instanceof Scalar);
    assertNull(actualBusResult.getRealizations());
  }

  /**
   * Test {@link RandomVariableUniqueVariable#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then cache return {@link RandomVariableDifferentiableAADPathwise}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenCacheReturnRandomVariableDifferentiableAADPathwise() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);

    // Act
    RandomVariable actualBusResult =
        randomVariableUniqueVariable.bus(new RandomVariableFromFloatArray(Double.NaN));

    // Assert
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.cache() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualBusResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then cache return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenCacheReturnRandomVariableFromFloatArray() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualBusResult =
        randomVariableUniqueVariable.bus(new RandomVariableFromFloatArray(Double.NaN));

    // Assert
    assertTrue(actualBusResult.cache() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, Double.NaN, Double.NaN, Double.NaN},
        actualBusResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then cache return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenCacheReturnRandomVariableFromFloatArray2() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(Double.NaN), true);

    // Act
    RandomVariable actualBusResult =
        randomVariableUniqueVariable.bus(new RandomVariableFromFloatArray(Double.NaN));

    // Assert
    assertTrue(actualBusResult.cache() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then cache return {@link RandomVariableFromFloatArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenCacheReturnRandomVariableFromFloatArray3() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(
            10.0d, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);

    // Act
    RandomVariable actualBusResult =
        randomVariableUniqueVariable.bus(new RandomVariableFromFloatArray(Double.NaN));

    // Assert
    assertTrue(actualBusResult.cache() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromFloatArray);
    assertTrue(actualBusResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, Double.NaN, Double.NaN, Double.NaN},
        actualBusResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then cache return {@link RandomVariableLazyEvaluation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenCacheReturnRandomVariableLazyEvaluation() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualBusResult =
        randomVariableUniqueVariable.bus(new RandomVariableLazyEvaluation(Double.NaN));

    // Assert
    assertTrue(actualBusResult.cache() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableLazyEvaluation);
    assertTrue(actualBusResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, Double.NaN, Double.NaN, Double.NaN},
        actualBusResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and eleven.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithNaNAndEleven() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(
            Double.NaN, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});

    // Act
    RandomVariable actualBusResult = randomVariableUniqueVariable.bus(randomVariable);

    // Assert
    assertTrue(actualBusResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, 11.0d, Double.NaN, 11.0d},
        actualBusResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and {@link
   *       Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithNaNAndNaN() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualBusResult = randomVariableUniqueVariable.bus(Scalar.of(Double.NaN));

    // Assert
    assertTrue(actualBusResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, Double.NaN, Double.NaN, Double.NaN},
        actualBusResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and {@link
   *       Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithNaNAndNaN2() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(Double.NaN), true);
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(
            Double.NaN, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});

    // Act
    RandomVariable actualBusResult = randomVariableUniqueVariable.bus(randomVariable);

    // Assert
    assertTrue(actualBusResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, Double.NaN, Double.NaN, Double.NaN},
        actualBusResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithNaNAndZero() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(
            10.0d, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);

    // Act
    RandomVariable actualBusResult =
        randomVariableUniqueVariable.bus(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBusResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, 0.0d, Double.NaN, 0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithNaNAndZero2() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);
    RandomVariableUniqueVariable randomVariable2 =
        new RandomVariableUniqueVariable(
            Double.NaN, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});

    // Act
    RandomVariable actualBusResult = randomVariableUniqueVariable.bus(randomVariable2);

    // Assert
    assertTrue(actualBusResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, 0.0d, Double.NaN, 0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithNaNAndZero3() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);
    RandomVariableUniqueVariable randomVariable2 =
        new RandomVariableUniqueVariable(
            Double.NaN, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});

    // Act
    RandomVariable actualBusResult = randomVariableUniqueVariable.bus(randomVariable2);

    // Assert
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.cache() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualBusResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, 0.0d, Double.NaN, 0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithZero() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);

    // Act
    RandomVariable actualBusResult =
        randomVariableUniqueVariable.bus(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBusResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithZero2() {
    // Arrange
    RandomVariableDifferentiableAADPathwise randomVariable =
        RandomVariableDifferentiableAADPathwise.of(10.0d);
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(randomVariable, true);

    // Act
    RandomVariable actualBusResult =
        randomVariableUniqueVariable.bus(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.cache() instanceof RandomVariableDifferentiableAADPathwise);
    assertTrue(actualBusResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {0.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with zero and eleven.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.bus(RandomVariable)"})
  public void testBusWithRandomVariable_thenReturnRealizationsIsArrayOfDoubleWithZeroAndEleven() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualBusResult =
        randomVariableUniqueVariable.bus(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertTrue(actualBusResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {0.0d, 11.0d, 0.0d, 11.0d}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#bus(RandomVariable)} with {@code randomVariable}.
   *
   * <ul>
   *   <li>When {@link Scalar} with value is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#bus(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.bus(RandomVariable)"})
  public void testBusWithRandomVariable_whenScalarWithValueIsNaN() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(Double.NaN), true);

    // Act
    RandomVariable actualBusResult = randomVariableUniqueVariable.bus(Scalar.of(Double.NaN));

    // Assert
    assertTrue(actualBusResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualBusResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualBusResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#cap(double)} with {@code double}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#cap(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.cap(double)"})
  public void testCapWithDouble() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertNull(randomVariableUniqueVariable.cap(10.0d));
  }

  /**
   * Test {@link RandomVariableUniqueVariable#cap(RandomVariable)} with {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#cap(RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.cap(RandomVariable)"})
  public void testCapWithRandomVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualCapResult =
        randomVariableUniqueVariable.cap(new RandomVariableFromDoubleArray(10.0d));

    // Assert
    assertNull(actualCapResult);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#getRealizationsStream()}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#getRealizationsStream()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.util.stream.DoubleStream RandomVariableUniqueVariable.getRealizationsStream()"
  })
  public void testGetRealizationsStream() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> randomVariableUniqueVariable.getRealizationsStream());
  }

  /**
   * Test {@link RandomVariableUniqueVariable#average()}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#average()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.average()"})
  public void testAverage() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertNull(randomVariableUniqueVariable.average());
  }

  /**
   * Test {@link RandomVariableUniqueVariable#squared()}.
   *
   * <ul>
   *   <li>Then cache return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#squared()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.squared()"})
  public void testSquared_thenCacheReturnRandomVariableFromDoubleArray() {
    // Arrange and Act
    RandomVariable actualSquaredResult =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(Double.NaN), true)
            .squared();

    // Assert
    assertTrue(actualSquaredResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSquaredResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSquaredResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualSquaredResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#squared()}.
   *
   * <ul>
   *   <li>Then cache return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#squared()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.squared()"})
  public void testSquared_thenCacheReturnScalar() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);

    // Act
    RandomVariable actualSquaredResult =
        new RandomVariableUniqueVariable(randomVariable, true).squared();

    // Assert
    assertTrue(actualSquaredResult instanceof RandomVariableUniqueVariable);
    assertTrue(actualSquaredResult.cache() instanceof Scalar);
    assertTrue(actualSquaredResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#squared()}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and one
   *       hundred.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#squared()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.squared()"})
  public void testSquared_thenReturnRealizationsIsArrayOfDoubleWithNaNAndOneHundred() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(
            10.0d, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});

    // Act
    RandomVariable actualSquaredResult =
        new RandomVariableUniqueVariable(randomVariable, true).squared();

    // Assert
    assertTrue(actualSquaredResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSquaredResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSquaredResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, 100.0d, Double.NaN, 100.0d},
        actualSquaredResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#squared()}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with one hundred and one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#squared()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.squared()"})
  public void testSquared_thenReturnRealizationsIsArrayOfDoubleWithOneHundredAndOne() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualSquaredResult = randomVariableUniqueVariable.squared();

    // Assert
    assertTrue(actualSquaredResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSquaredResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSquaredResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {100.0d, 1.0d, 100.0d, 1.0d}, actualSquaredResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#sqrt()}.
   *
   * <ul>
   *   <li>Then cache return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#sqrt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.sqrt()"})
  public void testSqrt_thenCacheReturnRandomVariableFromDoubleArray() {
    // Arrange and Act
    RandomVariable actualSqrtResult =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(Double.NaN), true)
            .sqrt();

    // Assert
    assertTrue(actualSqrtResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSqrtResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSqrtResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualSqrtResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#sqrt()}.
   *
   * <ul>
   *   <li>Then cache return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#sqrt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.sqrt()"})
  public void testSqrt_thenCacheReturnScalar() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);

    // Act
    RandomVariable actualSqrtResult = new RandomVariableUniqueVariable(randomVariable, true).sqrt();

    // Assert
    assertTrue(actualSqrtResult instanceof RandomVariableUniqueVariable);
    assertTrue(actualSqrtResult.cache() instanceof Scalar);
    assertTrue(actualSqrtResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#sqrt()}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@code 3.1622776601683795} and
   *       {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#sqrt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.sqrt()"})
  public void testSqrt_thenReturnRealizationsIsArrayOfDoubleWith31622776601683795AndNaN() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualSqrtResult = randomVariableUniqueVariable.sqrt();

    // Assert
    assertTrue(actualSqrtResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSqrtResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSqrtResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {3.1622776601683795d, Double.NaN, 3.1622776601683795d, Double.NaN},
        actualSqrtResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#sqrt()}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and {@code
   *       3.1622776601683795}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#sqrt()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.sqrt()"})
  public void testSqrt_thenReturnRealizationsIsArrayOfDoubleWithNaNAnd31622776601683795() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(
            10.0d, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});

    // Act
    RandomVariable actualSqrtResult = new RandomVariableUniqueVariable(randomVariable, true).sqrt();

    // Assert
    assertTrue(actualSqrtResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSqrtResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSqrtResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, 3.1622776601683795d, Double.NaN, 3.1622776601683795d},
        actualSqrtResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#exp()}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#exp()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.exp()"})
  public void testExp() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualExpResult = randomVariableUniqueVariable.exp();

    // Assert
    assertTrue(actualExpResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {
          22026.465794806718d, 0.36787944117144233d, 22026.465794806718d, 0.36787944117144233d
        },
        actualExpResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#exp()}.
   *
   * <ul>
   *   <li>Then cache return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#exp()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.exp()"})
  public void testExp_thenCacheReturnRandomVariableFromDoubleArray() {
    // Arrange and Act
    RandomVariable actualExpResult =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(Double.NaN), true).exp();

    // Assert
    assertTrue(actualExpResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualExpResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#exp()}.
   *
   * <ul>
   *   <li>Then cache return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#exp()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.exp()"})
  public void testExp_thenCacheReturnScalar() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);

    // Act
    RandomVariable actualExpResult = new RandomVariableUniqueVariable(randomVariable, true).exp();

    // Assert
    assertTrue(actualExpResult instanceof RandomVariableUniqueVariable);
    assertTrue(actualExpResult.cache() instanceof Scalar);
    assertTrue(actualExpResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#exp()}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and {@code
   *       22026.465794806718}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#exp()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.exp()"})
  public void testExp_thenReturnRealizationsIsArrayOfDoubleWithNaNAnd22026465794806718() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(
            10.0d, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});

    // Act
    RandomVariable actualExpResult = new RandomVariableUniqueVariable(randomVariable, true).exp();

    // Assert
    assertTrue(actualExpResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualExpResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, 22026.465794806718d, Double.NaN, 22026.465794806718d},
        actualExpResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#log()}.
   *
   * <ul>
   *   <li>Then cache return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#log()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.log()"})
  public void testLog_thenCacheReturnRandomVariableFromDoubleArray() {
    // Arrange and Act
    RandomVariable actualLogResult =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(Double.NaN), true).log();

    // Assert
    assertTrue(actualLogResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLogResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLogResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualLogResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#log()}.
   *
   * <ul>
   *   <li>Then cache return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#log()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.log()"})
  public void testLog_thenCacheReturnScalar() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);

    // Act
    RandomVariable actualLogResult = new RandomVariableUniqueVariable(randomVariable, true).log();

    // Assert
    assertTrue(actualLogResult instanceof RandomVariableUniqueVariable);
    assertTrue(actualLogResult.cache() instanceof Scalar);
    assertTrue(actualLogResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#log()}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@code 2.302585092994046} and
   *       {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#log()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.log()"})
  public void testLog_thenReturnRealizationsIsArrayOfDoubleWith2302585092994046AndNaN() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualLogResult = randomVariableUniqueVariable.log();

    // Assert
    assertTrue(actualLogResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLogResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLogResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {2.302585092994046d, Double.NaN, 2.302585092994046d, Double.NaN},
        actualLogResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#log()}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and {@code
   *       2.302585092994046}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#log()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.log()"})
  public void testLog_thenReturnRealizationsIsArrayOfDoubleWithNaNAnd2302585092994046() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(
            10.0d, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});

    // Act
    RandomVariable actualLogResult = new RandomVariableUniqueVariable(randomVariable, true).log();

    // Assert
    assertTrue(actualLogResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLogResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualLogResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, 2.302585092994046d, Double.NaN, 2.302585092994046d},
        actualLogResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#sin()}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#sin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.sin()"})
  public void testSin() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualSinResult = randomVariableUniqueVariable.sin();

    // Assert
    assertTrue(actualSinResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSinResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSinResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {
          -0.5440211108893698d, -0.8414709848078965d, -0.5440211108893698d, -0.8414709848078965d
        },
        actualSinResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#sin()}.
   *
   * <ul>
   *   <li>Then cache return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#sin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.sin()"})
  public void testSin_thenCacheReturnRandomVariableFromDoubleArray() {
    // Arrange and Act
    RandomVariable actualSinResult =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(Double.NaN), true).sin();

    // Assert
    assertTrue(actualSinResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSinResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSinResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualSinResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#sin()}.
   *
   * <ul>
   *   <li>Then cache return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#sin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.sin()"})
  public void testSin_thenCacheReturnScalar() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);

    // Act
    RandomVariable actualSinResult = new RandomVariableUniqueVariable(randomVariable, true).sin();

    // Assert
    assertTrue(actualSinResult instanceof RandomVariableUniqueVariable);
    assertTrue(actualSinResult.cache() instanceof Scalar);
    assertTrue(actualSinResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#sin()}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and {@code
   *       -0.5440211108893698}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#sin()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.sin()"})
  public void testSin_thenReturnRealizationsIsArrayOfDoubleWithNaNAnd05440211108893698() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(
            10.0d, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});

    // Act
    RandomVariable actualSinResult = new RandomVariableUniqueVariable(randomVariable, true).sin();

    // Assert
    assertTrue(actualSinResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSinResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualSinResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, -0.5440211108893698d, Double.NaN, -0.5440211108893698d},
        actualSinResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#cos()}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#cos()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.cos()"})
  public void testCos() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualCosResult = randomVariableUniqueVariable.cos();

    // Assert
    assertTrue(actualCosResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCosResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCosResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {
          -0.8390715290764524d, 0.5403023058681398d, -0.8390715290764524d, 0.5403023058681398d
        },
        actualCosResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#cos()}.
   *
   * <ul>
   *   <li>Then cache return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#cos()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.cos()"})
  public void testCos_thenCacheReturnRandomVariableFromDoubleArray() {
    // Arrange and Act
    RandomVariable actualCosResult =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(Double.NaN), true).cos();

    // Assert
    assertTrue(actualCosResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCosResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCosResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(new double[] {Double.NaN}, actualCosResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#cos()}.
   *
   * <ul>
   *   <li>Then cache return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#cos()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.cos()"})
  public void testCos_thenCacheReturnScalar() {
    // Arrange
    Scalar randomVariable = Scalar.of(10.0d);

    // Act
    RandomVariable actualCosResult = new RandomVariableUniqueVariable(randomVariable, true).cos();

    // Assert
    assertTrue(actualCosResult instanceof RandomVariableUniqueVariable);
    assertTrue(actualCosResult.cache() instanceof Scalar);
    assertTrue(actualCosResult.isNaN() instanceof Scalar);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#cos()}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with {@link Double#NaN} and {@code
   *       -0.8390715290764524}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#cos()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.cos()"})
  public void testCos_thenReturnRealizationsIsArrayOfDoubleWithNaNAnd08390715290764524() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(
            10.0d, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});

    // Act
    RandomVariable actualCosResult = new RandomVariableUniqueVariable(randomVariable, true).cos();

    // Assert
    assertTrue(actualCosResult.cache() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCosResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualCosResult instanceof RandomVariableUniqueVariable);
    assertArrayEquals(
        new double[] {Double.NaN, -0.8390715290764524d, Double.NaN, -0.8390715290764524d},
        actualCosResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#accrue(RandomVariable, double)}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#accrue(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.accrue(RandomVariable, double)"})
  public void testAccrue() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertNull(
        randomVariableUniqueVariable.accrue(new RandomVariableFromDoubleArray(10.0d), 10.0d));
  }

  /**
   * Test {@link RandomVariableUniqueVariable#discount(RandomVariable, double)}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#discount(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableUniqueVariable.discount(RandomVariable, double)"
  })
  public void testDiscount() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertNull(
        randomVariableUniqueVariable.discount(new RandomVariableFromDoubleArray(10.0d), 10.0d));
  }

  /**
   * Test {@link RandomVariableUniqueVariable#choose(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#choose(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableUniqueVariable.choose(RandomVariable, RandomVariable)"
  })
  public void testChoose() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    RandomVariableFromDoubleArray valueIfTriggerNonNegative =
        new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertNull(
        randomVariableUniqueVariable.choose(
            valueIfTriggerNonNegative, new RandomVariableFromDoubleArray(10.0d)));
  }

  /**
   * Test {@link RandomVariableUniqueVariable#invert()}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#invert()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.invert()"})
  public void testInvert() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertNull(randomVariableUniqueVariable.invert());
  }

  /**
   * Test {@link RandomVariableUniqueVariable#abs()}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#abs()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.abs()"})
  public void testAbs() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertNull(randomVariableUniqueVariable.abs());
  }

  /**
   * Test {@link RandomVariableUniqueVariable#addProduct(RandomVariable, double)} with {@code
   * RandomVariable}, {@code double}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#addProduct(RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableUniqueVariable.addProduct(RandomVariable, double)"
  })
  public void testAddProductWithRandomVariableDouble() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertNull(
        randomVariableUniqueVariable.addProduct(new RandomVariableFromDoubleArray(10.0d), 10.0d));
  }

  /**
   * Test {@link RandomVariableUniqueVariable#addProduct(RandomVariable, RandomVariable)} with
   * {@code RandomVariable}, {@code RandomVariable}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#addProduct(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableUniqueVariable.addProduct(RandomVariable, RandomVariable)"
  })
  public void testAddProductWithRandomVariableRandomVariable() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    RandomVariableFromDoubleArray factor1 = new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertNull(
        randomVariableUniqueVariable.addProduct(factor1, new RandomVariableFromDoubleArray(10.0d)));
  }

  /**
   * Test {@link RandomVariableUniqueVariable#addRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#addRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableUniqueVariable.addRatio(RandomVariable, RandomVariable)"
  })
  public void testAddRatio() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertNull(
        randomVariableUniqueVariable.addRatio(numerator, new RandomVariableFromDoubleArray(10.0d)));
  }

  /**
   * Test {@link RandomVariableUniqueVariable#subRatio(RandomVariable, RandomVariable)}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#subRatio(RandomVariable,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableUniqueVariable.subRatio(RandomVariable, RandomVariable)"
  })
  public void testSubRatio() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    RandomVariableFromDoubleArray numerator = new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertNull(
        randomVariableUniqueVariable.subRatio(numerator, new RandomVariableFromDoubleArray(10.0d)));
  }

  /**
   * Test {@link RandomVariableUniqueVariable#isNaN()}.
   *
   * <ul>
   *   <li>Given {@link Scalar} with value is one.
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.isNaN()"})
  public void testIsNaN_givenScalarWithValueIsOne_thenReturnScalar() {
    // Arrange
    Scalar randomVariable = Scalar.of(1.0d);

    // Act
    RandomVariable actualIsNaNResult =
        new RandomVariableUniqueVariable(randomVariable, true).isNaN();

    // Assert
    assertTrue(actualIsNaNResult instanceof Scalar);
    assertTrue(actualIsNaNResult.abs() instanceof Scalar);
    assertTrue(actualIsNaNResult.cos() instanceof Scalar);
    assertTrue(actualIsNaNResult.exp() instanceof Scalar);
    assertTrue(actualIsNaNResult.expm1() instanceof Scalar);
    assertTrue(actualIsNaNResult.invert() instanceof Scalar);
    assertTrue(actualIsNaNResult.isNaN() instanceof Scalar);
    assertTrue(actualIsNaNResult.sin() instanceof Scalar);
    assertTrue(actualIsNaNResult.sqrt() instanceof Scalar);
    assertTrue(actualIsNaNResult.squared() instanceof Scalar);
    assertTrue(actualIsNaNResult.variance() instanceof Scalar);
    assertNull(actualIsNaNResult.getRealizations());
    assertNull(actualIsNaNResult.getOperator());
    assertNull(actualIsNaNResult.getRealizationsStream());
    assertEquals(0, actualIsNaNResult.getTypePriority());
    assertEquals(0.0d, actualIsNaNResult.getAverage(), 0.0);
    assertEquals(0.0d, actualIsNaNResult.getMax(), 0.0);
    assertEquals(0.0d, actualIsNaNResult.getMin(), 0.0);
    assertEquals(0.0d, actualIsNaNResult.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualIsNaNResult.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualIsNaNResult.getStandardError(), 0.0);
    assertEquals(0.0d, actualIsNaNResult.getVariance(), 0.0);
    assertEquals(1, actualIsNaNResult.size());
    assertTrue(actualIsNaNResult.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualIsNaNResult.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualIsNaNResult.expectation();
    assertSame(actualIsNaNResult, actualExpectationResult);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#isNaN()}.
   *
   * <ul>
   *   <li>Then return Average is one.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.isNaN()"})
  public void testIsNaN_thenReturnAverageIsOne() {
    // Arrange and Act
    RandomVariable actualIsNaNResult =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(Double.NaN), true)
            .isNaN();

    // Assert
    assertTrue(actualIsNaNResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(1.0d, actualIsNaNResult.getAverage(), 0.0);
    assertEquals(1.0d, actualIsNaNResult.getMin(), 0.0);
    assertArrayEquals(new double[] {1.0d}, actualIsNaNResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#isNaN()}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.isNaN()"})
  public void testIsNaN_thenReturnRealizationsIsArrayOfDoubleWithZero() {
    // Arrange and Act
    RandomVariable actualIsNaNResult =
        new RandomVariableUniqueVariable(new RandomVariableFromDoubleArray(1.0d), true).isNaN();

    // Assert
    assertTrue(actualIsNaNResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(new double[] {0.0d}, actualIsNaNResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#isNaN()}.
   *
   * <ul>
   *   <li>Then return Realizations is array of {@code double} with zero and zero.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.isNaN()"})
  public void testIsNaN_thenReturnRealizationsIsArrayOfDoubleWithZeroAndZero() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    RandomVariable actualIsNaNResult = randomVariableUniqueVariable.isNaN();

    // Assert
    assertTrue(actualIsNaNResult instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualIsNaNResult.variance() instanceof RandomVariableFromDoubleArray);
    assertArrayEquals(
        new double[] {0.0d, 0.0d, 0.0d, 0.0d}, actualIsNaNResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#isNaN()}.
   *
   * <ul>
   *   <li>Then return StandardError is {@code 0.25}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.isNaN()"})
  public void testIsNaN_thenReturnStandardErrorIs025() {
    // Arrange
    RandomVariableUniqueVariable randomVariable =
        new RandomVariableUniqueVariable(1.0d, new double[] {Double.NaN, 10.0d, Double.NaN, 10.0d});

    // Act
    RandomVariable actualIsNaNResult =
        new RandomVariableUniqueVariable(randomVariable, true).isNaN();

    // Assert
    assertTrue(actualIsNaNResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.25d, actualIsNaNResult.getStandardError(), 0.0);
    assertEquals(0.3333333333333333d, actualIsNaNResult.getSampleVariance(), 0.0);
    assertEquals(1.0d, actualIsNaNResult.getFiltrationTime(), 0.0);
    assertArrayEquals(
        new double[] {1.0d, 0.0d, 1.0d, 0.0d}, actualIsNaNResult.getRealizations(), 0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#isNaN()}.
   *
   * <ul>
   *   <li>Then return StandardError is {@code 0.17677669529663687}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#isNaN()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.isNaN()"})
  public void testIsNaN_thenReturnStandardErrorIs017677669529663687() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(
            10.0d,
            new double[] {
              Double.NaN, 10.0d, Double.NaN, 10.0d, Double.NaN, 10.0d, Double.NaN, 10.0d
            });

    // Act
    RandomVariable actualIsNaNResult = randomVariableUniqueVariable.isNaN();

    // Assert
    assertTrue(actualIsNaNResult instanceof RandomVariableFromDoubleArray);
    assertEquals(0.17677669529663687d, actualIsNaNResult.getStandardError(), 0.0);
    assertEquals(0.2857142857142857d, actualIsNaNResult.getSampleVariance(), 0.0);
    assertEquals(8, actualIsNaNResult.size());
    assertArrayEquals(
        new double[] {1.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d},
        actualIsNaNResult.getRealizations(),
        0.0);
  }

  /**
   * Test {@link RandomVariableUniqueVariable#apply(DoubleUnaryOperator)} with {@code operator}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#apply(DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable RandomVariableUniqueVariable.apply(DoubleUnaryOperator)"})
  public void testApplyWithOperator() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> randomVariableUniqueVariable.apply(mock(DoubleUnaryOperator.class)));
  }

  /**
   * Test {@link RandomVariableUniqueVariable#apply(DoubleBinaryOperator, RandomVariable)} with
   * {@code operator}, {@code argument}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#apply(DoubleBinaryOperator,
   * RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableUniqueVariable.apply(DoubleBinaryOperator, RandomVariable)"
  })
  public void testApplyWithOperatorArgument() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    DoubleBinaryOperator operator = mock(DoubleBinaryOperator.class);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            randomVariableUniqueVariable.apply(operator, new RandomVariableFromDoubleArray(10.0d)));
  }

  /**
   * Test {@link RandomVariableUniqueVariable#apply(DoubleTernaryOperator, RandomVariable,
   * RandomVariable)} with {@code operator}, {@code argument1}, {@code argument2}.
   *
   * <p>Method under test: {@link RandomVariableUniqueVariable#apply(DoubleTernaryOperator,
   * RandomVariable, RandomVariable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable RandomVariableUniqueVariable.apply(DoubleTernaryOperator, RandomVariable, RandomVariable)"
  })
  public void testApplyWithOperatorArgument1Argument2() {
    // Arrange
    RandomVariableUniqueVariable randomVariableUniqueVariable =
        new RandomVariableUniqueVariable(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});
    DoubleTernaryOperator operator = mock(DoubleTernaryOperator.class);
    RandomVariableFromDoubleArray argument1 = new RandomVariableFromDoubleArray(10.0d);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            randomVariableUniqueVariable.apply(
                operator, argument1, new RandomVariableFromDoubleArray(10.0d)));
  }
}
