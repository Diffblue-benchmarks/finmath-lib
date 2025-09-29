package net.finmath.climate.models.dice.submodels;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AbatementCostFunctionDiffblueTest {
  /**
   * Test {@link AbatementCostFunction#AbatementCostFunction()}.
   *
   * <p>Method under test: {@link AbatementCostFunction#AbatementCostFunction()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbatementCostFunction.<init>()"})
  public void testNewAbatementCostFunction() {
    // Arrange, Act and Assert
    assertEquals(
        80.05686382849235d, new AbatementCostFunction().apply(10.0d, 10.0d).doubleValue(), 0.0);
  }

  /**
   * Test {@link AbatementCostFunction#AbatementCostFunction(double, double, double)}.
   *
   * <p>Method under test: {@link AbatementCostFunction#AbatementCostFunction(double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbatementCostFunction.<init>(double, double, double)"})
  public void testNewAbatementCostFunction2() {
    // Arrange, Act and Assert
    assertEquals(
        3.720075976020836E-34d,
        new AbatementCostFunction(10.0d, 10.0d, 10.0d).apply(10.0d, 10.0d).doubleValue(),
        0.0);
  }

  /**
   * Test {@link AbatementCostFunction#apply(Double, Double)} with {@code Double}, {@code Double}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return doubleValue is {@code 84.00203419884679}.
   * </ul>
   *
   * <p>Method under test: {@link AbatementCostFunction#apply(Double, Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double AbatementCostFunction.apply(Double, Double)"})
  public void testApplyWithDoubleDouble_when05_thenReturnDoubleValueIs8400203419884679() {
    // Arrange, Act and Assert
    assertEquals(
        84.00203419884679d, new AbatementCostFunction().apply(0.5d, 10.0d).doubleValue(), 0.0);
  }

  /**
   * Test {@link AbatementCostFunction#apply(Double, Double)} with {@code Double}, {@code Double}.
   *
   * <ul>
   *   <li>When {@code 0.55}.
   *   <li>Then return doubleValue is {@code 83.98076941712732}.
   * </ul>
   *
   * <p>Method under test: {@link AbatementCostFunction#apply(Double, Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double AbatementCostFunction.apply(Double, Double)"})
  public void testApplyWithDoubleDouble_when055_thenReturnDoubleValueIs8398076941712732() {
    // Arrange, Act and Assert
    assertEquals(
        83.98076941712732d, new AbatementCostFunction().apply(0.55d, 10.0d).doubleValue(), 0.0);
  }

  /**
   * Test {@link AbatementCostFunction#apply(Double, Double)} with {@code Double}, {@code Double}.
   *
   * <ul>
   *   <li>When {@code 0.975}.
   *   <li>Then return doubleValue is {@code 83.80023597748597}.
   * </ul>
   *
   * <p>Method under test: {@link AbatementCostFunction#apply(Double, Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double AbatementCostFunction.apply(Double, Double)"})
  public void testApplyWithDoubleDouble_when0975_thenReturnDoubleValueIs8380023597748597() {
    // Arrange, Act and Assert
    assertEquals(
        83.80023597748597d, new AbatementCostFunction().apply(0.975d, 10.0d).doubleValue(), 0.0);
  }

  /**
   * Test {@link AbatementCostFunction#apply(Double, Double)} with {@code Double}, {@code Double}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return doubleValue is {@code 80.05686382849235}.
   * </ul>
   *
   * <p>Method under test: {@link AbatementCostFunction#apply(Double, Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double AbatementCostFunction.apply(Double, Double)"})
  public void testApplyWithDoubleDouble_whenTen_thenReturnDoubleValueIs8005686382849235() {
    // Arrange, Act and Assert
    assertEquals(
        80.05686382849235d, new AbatementCostFunction().apply(10.0d, 10.0d).doubleValue(), 0.0);
  }
}
