package net.finmath.integration;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PiecewiseContantDoubleUnaryOperatorDiffblueTest {
  /**
   * Test {@link PiecewiseContantDoubleUnaryOperator#PiecewiseContantDoubleUnaryOperator(List,
   * List)}.
   *
   * <ul>
   *   <li>Then return apply ten doubleValue is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * PiecewiseContantDoubleUnaryOperator#PiecewiseContantDoubleUnaryOperator(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PiecewiseContantDoubleUnaryOperator.<init>(List, List)"})
  public void testNewPiecewiseContantDoubleUnaryOperator_thenReturnApplyTenDoubleValueIsTen() {
    // Arrange
    ArrayList<Double> intervalRightPoints = new ArrayList<>();

    ArrayList<Double> values = new ArrayList<>();
    values.add(10.0d);

    // Act
    PiecewiseContantDoubleUnaryOperator actualPiecewiseContantDoubleUnaryOperator =
        new PiecewiseContantDoubleUnaryOperator(intervalRightPoints, values);

    // Assert
    assertEquals(10.0d, actualPiecewiseContantDoubleUnaryOperator.apply(10.0d).doubleValue(), 0.0);
    assertEquals(10.0d, actualPiecewiseContantDoubleUnaryOperator.applyAsDouble(10.0d), 0.0);
  }

  /**
   * Test {@link PiecewiseContantDoubleUnaryOperator#getIntegral(double, double,
   * DoubleUnaryOperator)} with {@code double}, {@code double}, {@code DoubleUnaryOperator}.
   *
   * <ul>
   *   <li>Given ten.
   *   <li>Then return ninety.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseContantDoubleUnaryOperator#getIntegral(double, double,
   * DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double PiecewiseContantDoubleUnaryOperator.getIntegral(double, double, DoubleUnaryOperator)"
  })
  public void testGetIntegralWithDoubleDoubleDoubleUnaryOperator_givenTen_thenReturnNinety() {
    // Arrange
    ArrayList<Double> values = new ArrayList<>();
    values.add(10.0d);
    PiecewiseContantDoubleUnaryOperator piecewiseContantDoubleUnaryOperator =
        new PiecewiseContantDoubleUnaryOperator(new ArrayList<>(), values);

    DoubleUnaryOperator operator = mock(DoubleUnaryOperator.class);
    when(operator.applyAsDouble(anyDouble())).thenReturn(10.0d);

    // Act
    double actualIntegral = piecewiseContantDoubleUnaryOperator.getIntegral(1.0d, 10.0d, operator);

    // Assert
    verify(operator).applyAsDouble(10.0d);
    assertEquals(90.0d, actualIntegral, 0.0);
  }

  /**
   * Test {@link PiecewiseContantDoubleUnaryOperator#getIntegral(double, double,
   * DoubleUnaryOperator)} with {@code double}, {@code double}, {@code DoubleUnaryOperator}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseContantDoubleUnaryOperator#getIntegral(double, double,
   * DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double PiecewiseContantDoubleUnaryOperator.getIntegral(double, double, DoubleUnaryOperator)"
  })
  public void testGetIntegralWithDoubleDoubleDoubleUnaryOperator_thenReturnZero() {
    // Arrange
    ArrayList<Double> values = new ArrayList<>();
    values.add(10.0d);
    PiecewiseContantDoubleUnaryOperator piecewiseContantDoubleUnaryOperator =
        new PiecewiseContantDoubleUnaryOperator(new ArrayList<>(), values);

    // Act and Assert
    assertEquals(
        0.0d,
        piecewiseContantDoubleUnaryOperator.getIntegral(
            10.0d, 10.0d, mock(DoubleUnaryOperator.class)),
        0.0);
  }

  /**
   * Test {@link PiecewiseContantDoubleUnaryOperator#getIntegral(double, double,
   * DoubleUnaryOperator)} with {@code double}, {@code double}, {@code DoubleUnaryOperator}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return minus five.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseContantDoubleUnaryOperator#getIntegral(double, double,
   * DoubleUnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double PiecewiseContantDoubleUnaryOperator.getIntegral(double, double, DoubleUnaryOperator)"
  })
  public void testGetIntegralWithDoubleDoubleDoubleUnaryOperator_when05_thenReturnMinusFive() {
    // Arrange
    ArrayList<Double> values = new ArrayList<>();
    values.add(10.0d);
    PiecewiseContantDoubleUnaryOperator piecewiseContantDoubleUnaryOperator =
        new PiecewiseContantDoubleUnaryOperator(new ArrayList<>(), values);

    // Act and Assert
    assertEquals(
        -5.0d,
        piecewiseContantDoubleUnaryOperator.getIntegral(
            1.0d, 0.5d, mock(DoubleUnaryOperator.class)),
        0.0);
  }

  /**
   * Test {@link PiecewiseContantDoubleUnaryOperator#getIntegral(double, double)} with {@code
   * double}, {@code double}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add ten.
   *   <li>When {@code 0.5}.
   *   <li>Then return ninety-five.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseContantDoubleUnaryOperator#getIntegral(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double PiecewiseContantDoubleUnaryOperator.getIntegral(double, double)"})
  public void testGetIntegralWithDoubleDouble_givenArrayListAddTen_when05_thenReturnNinetyFive() {
    // Arrange
    ArrayList<Double> values = new ArrayList<>();
    values.add(10.0d);
    PiecewiseContantDoubleUnaryOperator piecewiseContantDoubleUnaryOperator =
        new PiecewiseContantDoubleUnaryOperator(new ArrayList<>(), values);

    // Act and Assert
    assertEquals(95.0d, piecewiseContantDoubleUnaryOperator.getIntegral(0.5d, 10.0d), 0.0);
  }

  /**
   * Test {@link PiecewiseContantDoubleUnaryOperator#getIntegral(double, double)} with {@code
   * double}, {@code double}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add ten.
   *   <li>When one.
   *   <li>Then return ninety.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseContantDoubleUnaryOperator#getIntegral(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double PiecewiseContantDoubleUnaryOperator.getIntegral(double, double)"})
  public void testGetIntegralWithDoubleDouble_givenArrayListAddTen_whenOne_thenReturnNinety() {
    // Arrange
    ArrayList<Double> values = new ArrayList<>();
    values.add(10.0d);
    PiecewiseContantDoubleUnaryOperator piecewiseContantDoubleUnaryOperator =
        new PiecewiseContantDoubleUnaryOperator(new ArrayList<>(), values);

    // Act and Assert
    assertEquals(90.0d, piecewiseContantDoubleUnaryOperator.getIntegral(1.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link PiecewiseContantDoubleUnaryOperator#getIntegral(double, double)} with {@code
   * double}, {@code double}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add ten.
   *   <li>When ten.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseContantDoubleUnaryOperator#getIntegral(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double PiecewiseContantDoubleUnaryOperator.getIntegral(double, double)"})
  public void testGetIntegralWithDoubleDouble_givenArrayListAddTen_whenTen_thenReturnZero() {
    // Arrange
    ArrayList<Double> values = new ArrayList<>();
    values.add(10.0d);
    PiecewiseContantDoubleUnaryOperator piecewiseContantDoubleUnaryOperator =
        new PiecewiseContantDoubleUnaryOperator(new ArrayList<>(), values);

    // Act and Assert
    assertEquals(0.0d, piecewiseContantDoubleUnaryOperator.getIntegral(10.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link PiecewiseContantDoubleUnaryOperator#getIntegral(double, double)} with {@code
   * double}, {@code double}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return minus ninety.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseContantDoubleUnaryOperator#getIntegral(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double PiecewiseContantDoubleUnaryOperator.getIntegral(double, double)"})
  public void testGetIntegralWithDoubleDouble_whenOne_thenReturnMinusNinety() {
    // Arrange
    ArrayList<Double> values = new ArrayList<>();
    values.add(10.0d);
    PiecewiseContantDoubleUnaryOperator piecewiseContantDoubleUnaryOperator =
        new PiecewiseContantDoubleUnaryOperator(new ArrayList<>(), values);

    // Act and Assert
    assertEquals(-90.0d, piecewiseContantDoubleUnaryOperator.getIntegral(10.0d, 1.0d), 0.0);
  }

  /**
   * Test {@link PiecewiseContantDoubleUnaryOperator#getIntegral(double, double)} with {@code
   * double}, {@code double}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return one hundred.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseContantDoubleUnaryOperator#getIntegral(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double PiecewiseContantDoubleUnaryOperator.getIntegral(double, double)"})
  public void testGetIntegralWithDoubleDouble_whenZero_thenReturnOneHundred() {
    // Arrange
    ArrayList<Double> values = new ArrayList<>();
    values.add(10.0d);
    PiecewiseContantDoubleUnaryOperator piecewiseContantDoubleUnaryOperator =
        new PiecewiseContantDoubleUnaryOperator(new ArrayList<>(), values);

    // Act and Assert
    assertEquals(100.0d, piecewiseContantDoubleUnaryOperator.getIntegral(0.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link PiecewiseContantDoubleUnaryOperator#applyAsDouble(double)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code 0.5}.
   *   <li>Then return {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseContantDoubleUnaryOperator#applyAsDouble(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double PiecewiseContantDoubleUnaryOperator.applyAsDouble(double)"})
  public void testApplyAsDouble_givenArrayListAdd05_thenReturn05() {
    // Arrange
    ArrayList<Double> intervalRightPoints = new ArrayList<>();
    intervalRightPoints.add(10.0d);

    ArrayList<Double> values = new ArrayList<>();
    values.add(0.5d);
    values.add(10.0d);

    PiecewiseContantDoubleUnaryOperator piecewiseContantDoubleUnaryOperator =
        new PiecewiseContantDoubleUnaryOperator(intervalRightPoints, values);

    // Act and Assert
    assertEquals(0.5d, piecewiseContantDoubleUnaryOperator.applyAsDouble(10.0d), 0.0);
  }

  /**
   * Test {@link PiecewiseContantDoubleUnaryOperator#applyAsDouble(double)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add ten.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseContantDoubleUnaryOperator#applyAsDouble(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double PiecewiseContantDoubleUnaryOperator.applyAsDouble(double)"})
  public void testApplyAsDouble_givenArrayListAddTen_thenReturnTen() {
    // Arrange
    ArrayList<Double> values = new ArrayList<>();
    values.add(10.0d);
    PiecewiseContantDoubleUnaryOperator piecewiseContantDoubleUnaryOperator =
        new PiecewiseContantDoubleUnaryOperator(new ArrayList<>(), values);

    // Act and Assert
    assertEquals(10.0d, piecewiseContantDoubleUnaryOperator.applyAsDouble(10.0d), 0.0);
  }

  /**
   * Test {@link PiecewiseContantDoubleUnaryOperator#apply(Double)} with {@code Double}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code 0.5}.
   *   <li>Then return doubleValue is {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseContantDoubleUnaryOperator#apply(Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double PiecewiseContantDoubleUnaryOperator.apply(Double)"})
  public void testApplyWithDouble_givenArrayListAdd05_thenReturnDoubleValueIs05() {
    // Arrange
    ArrayList<Double> intervalRightPoints = new ArrayList<>();
    intervalRightPoints.add(10.0d);

    ArrayList<Double> values = new ArrayList<>();
    values.add(0.5d);
    values.add(10.0d);

    PiecewiseContantDoubleUnaryOperator piecewiseContantDoubleUnaryOperator =
        new PiecewiseContantDoubleUnaryOperator(intervalRightPoints, values);

    // Act and Assert
    assertEquals(0.5d, piecewiseContantDoubleUnaryOperator.apply(10.0d).doubleValue(), 0.0);
  }

  /**
   * Test {@link PiecewiseContantDoubleUnaryOperator#apply(Double)} with {@code Double}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add ten.
   *   <li>Then return doubleValue is ten.
   * </ul>
   *
   * <p>Method under test: {@link PiecewiseContantDoubleUnaryOperator#apply(Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double PiecewiseContantDoubleUnaryOperator.apply(Double)"})
  public void testApplyWithDouble_givenArrayListAddTen_thenReturnDoubleValueIsTen() {
    // Arrange
    ArrayList<Double> values = new ArrayList<>();
    values.add(10.0d);
    PiecewiseContantDoubleUnaryOperator piecewiseContantDoubleUnaryOperator =
        new PiecewiseContantDoubleUnaryOperator(new ArrayList<>(), values);

    // Act and Assert
    assertEquals(10.0d, piecewiseContantDoubleUnaryOperator.apply(10.0d).doubleValue(), 0.0);
  }
}
