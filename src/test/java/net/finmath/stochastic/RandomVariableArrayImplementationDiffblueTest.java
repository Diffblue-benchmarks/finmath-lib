package net.finmath.stochastic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RandomVariableArrayImplementationDiffblueTest {
  /**
   * Test {@link RandomVariableArrayImplementation#of(RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return NumberOfElements is two.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableArrayImplementation#of(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariableArray RandomVariableArrayImplementation.of(RandomVariable[])"})
  public void testOf_thenReturnNumberOfElementsIsTwo() {
    // Arrange
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    RandomVariableArray actualOfResult =
        RandomVariableArrayImplementation.of(
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    assertTrue(actualOfResult.abs() instanceof RandomVariableArrayImplementation);
    assertTrue(actualOfResult.average() instanceof RandomVariableArrayImplementation);
    assertTrue(actualOfResult.cos() instanceof RandomVariableArrayImplementation);
    assertTrue(actualOfResult.exp() instanceof RandomVariableArrayImplementation);
    assertTrue(actualOfResult.expectation() instanceof RandomVariableArrayImplementation);
    assertTrue(actualOfResult.expm1() instanceof RandomVariableArrayImplementation);
    assertTrue(actualOfResult.invert() instanceof RandomVariableArrayImplementation);
    assertTrue(actualOfResult.isNaN() instanceof RandomVariableArrayImplementation);
    assertTrue(actualOfResult.sin() instanceof RandomVariableArrayImplementation);
    assertTrue(actualOfResult.sqrt() instanceof RandomVariableArrayImplementation);
    assertTrue(actualOfResult.squared() instanceof RandomVariableArrayImplementation);
    assertTrue(actualOfResult instanceof RandomVariableArrayImplementation);
    assertEquals(2, actualOfResult.getNumberOfElements());
  }

  /**
   * Test {@link RandomVariableArrayImplementation#of(RandomVariable[])}.
   *
   * <ul>
   *   <li>Then variance return {@link RandomVariableArrayImplementation}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableArrayImplementation#of(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariableArray RandomVariableArrayImplementation.of(RandomVariable[])"})
  public void testOf_thenVarianceReturnRandomVariableArrayImplementation() {
    // Arrange and Act
    RandomVariableArray actualOfResult =
        RandomVariableArrayImplementation.of(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertTrue(actualOfResult.abs() instanceof RandomVariableArrayImplementation);
    assertTrue(actualOfResult.average() instanceof RandomVariableArrayImplementation);
    assertTrue(actualOfResult.cos() instanceof RandomVariableArrayImplementation);
    assertTrue(actualOfResult.exp() instanceof RandomVariableArrayImplementation);
    assertTrue(actualOfResult.expectation() instanceof RandomVariableArrayImplementation);
    assertTrue(actualOfResult.expm1() instanceof RandomVariableArrayImplementation);
    assertTrue(actualOfResult.invert() instanceof RandomVariableArrayImplementation);
    assertTrue(actualOfResult.isNaN() instanceof RandomVariableArrayImplementation);
    assertTrue(actualOfResult.sin() instanceof RandomVariableArrayImplementation);
    assertTrue(actualOfResult.sqrt() instanceof RandomVariableArrayImplementation);
    assertTrue(actualOfResult.squared() instanceof RandomVariableArrayImplementation);
    assertTrue(actualOfResult.variance() instanceof RandomVariableArrayImplementation);
    assertTrue(actualOfResult instanceof RandomVariableArrayImplementation);
    assertEquals(1, actualOfResult.getNumberOfElements());
  }

  /**
   * Test {@link RandomVariableArrayImplementation#of(RandomVariable[])}.
   *
   * <ul>
   *   <li>When empty array of {@link RandomVariable}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link RandomVariableArrayImplementation#of(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariableArray RandomVariableArrayImplementation.of(RandomVariable[])"})
  public void testOf_whenEmptyArrayOfRandomVariable_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> RandomVariableArrayImplementation.of(new RandomVariable[] {}));
  }
}
