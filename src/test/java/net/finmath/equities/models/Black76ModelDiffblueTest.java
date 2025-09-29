package net.finmath.equities.models;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class Black76ModelDiffblueTest {
  /**
   * Test {@link Black76Model#optionPrice(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return one hundred.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionPrice(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionPrice(double, double, double, double, boolean, double)"
  })
  public void testOptionPrice_whenFalse_thenReturnOneHundred() {
    // Arrange and Act
    double actualOptionPriceResult =
        Black76Model.optionPrice(10.0d, 10.0d, 10.0d, 10.0d, false, 10.0d);

    // Assert
    assertEquals(100.0d, actualOptionPriceResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionPrice(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return one hundred.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionPrice(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionPrice(double, double, double, double, boolean, double)"
  })
  public void testOptionPrice_whenFalse_thenReturnOneHundred2() {
    // Arrange and Act
    double actualOptionPriceResult =
        Black76Model.optionPrice(10.0d, 10.0d, 10.0d, Double.POSITIVE_INFINITY, false, 10.0d);

    // Assert
    assertEquals(100.0d, actualOptionPriceResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionPrice(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionPrice(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionPrice(double, double, double, double, boolean, double)"
  })
  public void testOptionPrice_whenMinusOne_thenReturnZero() {
    // Arrange and Act
    double actualOptionPriceResult =
        Black76Model.optionPrice(10.0d, 10.0d, -1.0d, 10.0d, true, 10.0d);

    // Assert
    assertEquals(0.0d, actualOptionPriceResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionPrice(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When {@link Double#POSITIVE_INFINITY}.
   *   <li>Then return one hundred.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionPrice(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionPrice(double, double, double, double, boolean, double)"
  })
  public void testOptionPrice_whenPositive_infinity_thenReturnOneHundred() {
    // Arrange and Act
    double actualOptionPriceResult =
        Black76Model.optionPrice(10.0d, 10.0d, 10.0d, Double.POSITIVE_INFINITY, true, 10.0d);

    // Assert
    assertEquals(100.0d, actualOptionPriceResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionPrice(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When {@link Double#POSITIVE_INFINITY}.
   *   <li>Then return {@link Double#POSITIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionPrice(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionPrice(double, double, double, double, boolean, double)"
  })
  public void testOptionPrice_whenPositive_infinity_thenReturnPositive_infinity() {
    // Arrange and Act
    double actualOptionPriceResult =
        Black76Model.optionPrice(Double.POSITIVE_INFINITY, 10.0d, 10.0d, 10.0d, true, 10.0d);

    // Assert
    assertEquals(Double.POSITIVE_INFINITY, actualOptionPriceResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionPrice(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return one hundred.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionPrice(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionPrice(double, double, double, double, boolean, double)"
  })
  public void testOptionPrice_whenTrue_thenReturnOneHundred() {
    // Arrange and Act
    double actualOptionPriceResult =
        Black76Model.optionPrice(10.0d, 10.0d, 10.0d, 10.0d, true, 10.0d);

    // Assert
    assertEquals(100.0d, actualOptionPriceResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionPrice(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionPrice(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionPrice(double, double, double, double, boolean, double)"
  })
  public void testOptionPrice_whenZero_thenReturnZero() {
    // Arrange and Act
    double actualOptionPriceResult =
        Black76Model.optionPrice(0.0d, 10.0d, 10.0d, 10.0d, true, 10.0d);

    // Assert
    assertEquals(0.0d, actualOptionPriceResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionPrice(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionPrice(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionPrice(double, double, double, double, boolean, double)"
  })
  public void testOptionPrice_whenZero_thenReturnZero2() {
    // Arrange and Act
    double actualOptionPriceResult =
        Black76Model.optionPrice(10.0d, 10.0d, 0.0d, 10.0d, true, 10.0d);

    // Assert
    assertEquals(0.0d, actualOptionPriceResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionPrice(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionPrice(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionPrice(double, double, double, double, boolean, double)"
  })
  public void testOptionPrice_whenZero_thenReturnZero3() {
    // Arrange and Act
    double actualOptionPriceResult =
        Black76Model.optionPrice(10.0d, 10.0d, 10.0d, 0.0d, true, 10.0d);

    // Assert
    assertEquals(0.0d, actualOptionPriceResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionDelta(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return {@code -1.2984035196701909E-55}.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionDelta(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionDelta(double, double, double, double, boolean, double)"
  })
  public void testOptionDelta_whenFalse_thenReturn12984035196701909e55() {
    // Arrange and Act
    double actualOptionDeltaResult =
        Black76Model.optionDelta(10.0d, 10.0d, 10.0d, 10.0d, false, 10.0d);

    // Assert
    assertEquals(-1.2984035196701909E-55d, actualOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionDelta(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionDelta(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionDelta(double, double, double, double, boolean, double)"
  })
  public void testOptionDelta_whenFalse_thenReturnZero() {
    // Arrange and Act
    double actualOptionDeltaResult =
        Black76Model.optionDelta(10.0d, 10.0d, 10.0d, Double.POSITIVE_INFINITY, false, 10.0d);

    // Assert
    assertEquals(0.0d, actualOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionDelta(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionDelta(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionDelta(double, double, double, double, boolean, double)"
  })
  public void testOptionDelta_whenMinusOne_thenReturnZero() {
    // Arrange and Act
    double actualOptionDeltaResult =
        Black76Model.optionDelta(10.0d, 10.0d, -1.0d, 10.0d, true, 10.0d);

    // Assert
    assertEquals(0.0d, actualOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionDelta(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionDelta(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionDelta(double, double, double, double, boolean, double)"
  })
  public void testOptionDelta_whenOne_thenReturnTen() {
    // Arrange and Act
    double actualOptionDeltaResult =
        Black76Model.optionDelta(1.0d, 10.0d, 10.0d, 10.0d, true, 10.0d);

    // Assert
    assertEquals(10.0d, actualOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionDelta(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionDelta(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionDelta(double, double, double, double, boolean, double)"
  })
  public void testOptionDelta_whenOne_thenReturnZero() {
    // Arrange and Act
    double actualOptionDeltaResult =
        Black76Model.optionDelta(1.0d, 10.0d, 0.0d, 10.0d, true, 10.0d);

    // Assert
    assertEquals(0.0d, actualOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionDelta(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When {@link Double#POSITIVE_INFINITY}.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionDelta(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionDelta(double, double, double, double, boolean, double)"
  })
  public void testOptionDelta_whenPositive_infinity_thenReturnTen() {
    // Arrange and Act
    double actualOptionDeltaResult =
        Black76Model.optionDelta(Double.POSITIVE_INFINITY, 10.0d, 10.0d, 10.0d, true, 10.0d);

    // Assert
    assertEquals(10.0d, actualOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionDelta(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When {@link Double#POSITIVE_INFINITY}.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionDelta(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionDelta(double, double, double, double, boolean, double)"
  })
  public void testOptionDelta_whenPositive_infinity_thenReturnTen2() {
    // Arrange and Act
    double actualOptionDeltaResult =
        Black76Model.optionDelta(10.0d, 10.0d, 10.0d, Double.POSITIVE_INFINITY, true, 10.0d);

    // Assert
    assertEquals(10.0d, actualOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionDelta(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When {@link Double#POSITIVE_INFINITY}.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionDelta(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionDelta(double, double, double, double, boolean, double)"
  })
  public void testOptionDelta_whenPositive_infinity_thenReturnTen3() {
    // Arrange and Act
    double actualOptionDeltaResult =
        Black76Model.optionDelta(Double.POSITIVE_INFINITY, 10.0d, 0.0d, 10.0d, true, 10.0d);

    // Assert
    assertEquals(10.0d, actualOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionDelta(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionDelta(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionDelta(double, double, double, double, boolean, double)"
  })
  public void testOptionDelta_whenTrue_thenReturnTen() {
    // Arrange and Act
    double actualOptionDeltaResult =
        Black76Model.optionDelta(10.0d, 10.0d, 10.0d, 10.0d, true, 10.0d);

    // Assert
    assertEquals(10.0d, actualOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionDelta(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return five.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionDelta(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionDelta(double, double, double, double, boolean, double)"
  })
  public void testOptionDelta_whenZero_thenReturnFive() {
    // Arrange and Act
    double actualOptionDeltaResult =
        Black76Model.optionDelta(10.0d, 10.0d, 0.0d, 10.0d, true, 10.0d);

    // Assert
    assertEquals(5.0d, actualOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionDelta(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return five.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionDelta(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionDelta(double, double, double, double, boolean, double)"
  })
  public void testOptionDelta_whenZero_thenReturnFive2() {
    // Arrange and Act
    double actualOptionDeltaResult =
        Black76Model.optionDelta(10.0d, 10.0d, 10.0d, 0.0d, true, 10.0d);

    // Assert
    assertEquals(5.0d, actualOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionDelta(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionDelta(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionDelta(double, double, double, double, boolean, double)"
  })
  public void testOptionDelta_whenZero_thenReturnZero() {
    // Arrange and Act
    double actualOptionDeltaResult =
        Black76Model.optionDelta(0.0d, 10.0d, 10.0d, 10.0d, true, 10.0d);

    // Assert
    assertEquals(0.0d, actualOptionDeltaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionVega(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionVega(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionVega(double, double, double, double, boolean, double)"
  })
  public void testOptionVega_whenMinusOne_thenReturnZero() {
    // Arrange and Act
    double actualOptionVegaResult =
        Black76Model.optionVega(10.0d, 10.0d, -1.0d, 10.0d, true, 10.0d);

    // Assert
    assertEquals(0.0d, actualOptionVegaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionVega(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When {@link Double#POSITIVE_INFINITY}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionVega(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionVega(double, double, double, double, boolean, double)"
  })
  public void testOptionVega_whenPositive_infinity_thenReturnZero() {
    // Arrange and Act
    double actualOptionVegaResult =
        Black76Model.optionVega(10.0d, 10.0d, 10.0d, Double.POSITIVE_INFINITY, true, 10.0d);

    // Assert
    assertEquals(0.0d, actualOptionVegaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionVega(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return {@code 6.517781960574815E-53}.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionVega(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionVega(double, double, double, double, boolean, double)"
  })
  public void testOptionVega_whenTrue_thenReturn6517781960574815e53() {
    // Arrange and Act
    double actualOptionVegaResult =
        Black76Model.optionVega(10.0d, 10.0d, 10.0d, 10.0d, true, 10.0d);

    // Assert
    assertEquals(6.517781960574815E-53d, actualOptionVegaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionVega(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionVega(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionVega(double, double, double, double, boolean, double)"
  })
  public void testOptionVega_whenZero_thenReturnZero() {
    // Arrange and Act
    double actualOptionVegaResult = Black76Model.optionVega(10.0d, 10.0d, 0.0d, 10.0d, true, 10.0d);

    // Assert
    assertEquals(0.0d, actualOptionVegaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionVega(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionVega(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionVega(double, double, double, double, boolean, double)"
  })
  public void testOptionVega_whenZero_thenReturnZero2() {
    // Arrange and Act
    double actualOptionVegaResult = Black76Model.optionVega(10.0d, 10.0d, 10.0d, 0.0d, true, 10.0d);

    // Assert
    assertEquals(0.0d, actualOptionVegaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionGamma(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionGamma(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionGamma(double, double, double, double, boolean, double)"
  })
  public void testOptionGamma_whenMinusOne_thenReturnZero() {
    // Arrange and Act
    double actualOptionGammaResult =
        Black76Model.optionGamma(10.0d, 10.0d, -1.0d, 10.0d, true, 10.0d);

    // Assert
    assertEquals(0.0d, actualOptionGammaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionGamma(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When {@link Double#POSITIVE_INFINITY}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionGamma(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionGamma(double, double, double, double, boolean, double)"
  })
  public void testOptionGamma_whenPositive_infinity_thenReturnZero() {
    // Arrange and Act
    double actualOptionGammaResult =
        Black76Model.optionGamma(10.0d, 10.0d, 10.0d, Double.POSITIVE_INFINITY, true, 10.0d);

    // Assert
    assertEquals(0.0d, actualOptionGammaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionGamma(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return {@code 6.517781960574816E-57}.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionGamma(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionGamma(double, double, double, double, boolean, double)"
  })
  public void testOptionGamma_whenTrue_thenReturn6517781960574816e57() {
    // Arrange and Act
    double actualOptionGammaResult =
        Black76Model.optionGamma(10.0d, 10.0d, 10.0d, 10.0d, true, 10.0d);

    // Assert
    assertEquals(6.517781960574816E-57d, actualOptionGammaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionGamma(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionGamma(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionGamma(double, double, double, double, boolean, double)"
  })
  public void testOptionGamma_whenZero_thenReturnZero() {
    // Arrange and Act
    double actualOptionGammaResult =
        Black76Model.optionGamma(10.0d, 10.0d, 0.0d, 10.0d, true, 10.0d);

    // Assert
    assertEquals(0.0d, actualOptionGammaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionGamma(double, double, double, double, boolean, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionGamma(double, double, double, double, boolean,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionGamma(double, double, double, double, boolean, double)"
  })
  public void testOptionGamma_whenZero_thenReturnZero2() {
    // Arrange and Act
    double actualOptionGammaResult =
        Black76Model.optionGamma(10.0d, 10.0d, 10.0d, 0.0d, true, 10.0d);

    // Assert
    assertEquals(0.0d, actualOptionGammaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionTheta(double, double, double, double, boolean, double, double)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionTheta(double, double, double, double, boolean,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionTheta(double, double, double, double, boolean, double, double)"
  })
  public void testOptionTheta_whenFalse_thenReturnNaN() {
    // Arrange and Act
    double actualOptionThetaResult =
        Black76Model.optionTheta(
            10.0d, 10.0d, 10.0d, Double.POSITIVE_INFINITY, false, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualOptionThetaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionTheta(double, double, double, double, boolean, double, double)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return one thousand.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionTheta(double, double, double, double, boolean,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionTheta(double, double, double, double, boolean, double, double)"
  })
  public void testOptionTheta_whenFalse_thenReturnOneThousand() {
    // Arrange and Act
    double actualOptionThetaResult =
        Black76Model.optionTheta(10.0d, 10.0d, 10.0d, 10.0d, false, 10.0d, 10.0d);

    // Assert
    assertEquals(1000.0d, actualOptionThetaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionTheta(double, double, double, double, boolean, double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionTheta(double, double, double, double, boolean,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionTheta(double, double, double, double, boolean, double, double)"
  })
  public void testOptionTheta_whenMinusOne_thenReturnZero() {
    // Arrange and Act
    double actualOptionThetaResult =
        Black76Model.optionTheta(10.0d, 10.0d, -1.0d, 10.0d, true, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualOptionThetaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionTheta(double, double, double, double, boolean, double, double)}.
   *
   * <ul>
   *   <li>When {@link Double#POSITIVE_INFINITY}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionTheta(double, double, double, double, boolean,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionTheta(double, double, double, double, boolean, double, double)"
  })
  public void testOptionTheta_whenPositive_infinity_thenReturnNaN() {
    // Arrange and Act
    double actualOptionThetaResult =
        Black76Model.optionTheta(Double.POSITIVE_INFINITY, 10.0d, 10.0d, 10.0d, true, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualOptionThetaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionTheta(double, double, double, double, boolean, double, double)}.
   *
   * <ul>
   *   <li>When {@link Double#POSITIVE_INFINITY}.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionTheta(double, double, double, double, boolean,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionTheta(double, double, double, double, boolean, double, double)"
  })
  public void testOptionTheta_whenPositive_infinity_thenReturnNaN2() {
    // Arrange and Act
    double actualOptionThetaResult =
        Black76Model.optionTheta(10.0d, 10.0d, 10.0d, Double.POSITIVE_INFINITY, true, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualOptionThetaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionTheta(double, double, double, double, boolean, double, double)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return one thousand.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionTheta(double, double, double, double, boolean,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionTheta(double, double, double, double, boolean, double, double)"
  })
  public void testOptionTheta_whenTrue_thenReturnOneThousand() {
    // Arrange and Act
    double actualOptionThetaResult =
        Black76Model.optionTheta(10.0d, 10.0d, 10.0d, 10.0d, true, 10.0d, 10.0d);

    // Assert
    assertEquals(1000.0d, actualOptionThetaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionTheta(double, double, double, double, boolean, double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionTheta(double, double, double, double, boolean,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionTheta(double, double, double, double, boolean, double, double)"
  })
  public void testOptionTheta_whenZero_thenReturnNaN() {
    // Arrange and Act
    double actualOptionThetaResult =
        Black76Model.optionTheta(0.0d, 10.0d, 10.0d, 10.0d, true, 10.0d, 10.0d);

    // Assert
    assertEquals(Double.NaN, actualOptionThetaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionTheta(double, double, double, double, boolean, double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionTheta(double, double, double, double, boolean,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionTheta(double, double, double, double, boolean, double, double)"
  })
  public void testOptionTheta_whenZero_thenReturnZero() {
    // Arrange and Act
    double actualOptionThetaResult =
        Black76Model.optionTheta(10.0d, 10.0d, 0.0d, 10.0d, true, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualOptionThetaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionTheta(double, double, double, double, boolean, double, double)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionTheta(double, double, double, double, boolean,
   * double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionTheta(double, double, double, double, boolean, double, double)"
  })
  public void testOptionTheta_whenZero_thenReturnZero2() {
    // Arrange and Act
    double actualOptionThetaResult =
        Black76Model.optionTheta(10.0d, 10.0d, 10.0d, 0.0d, true, 10.0d, 10.0d);

    // Assert
    assertEquals(0.0d, actualOptionThetaResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionImpliedVolatility(double, double, double, double, boolean)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 5.350345731202939}.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionImpliedVolatility(double, double, double,
   * double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionImpliedVolatility(double, double, double, double, boolean)"
  })
  public void testOptionImpliedVolatility_when05_thenReturn5350345731202939() {
    // Arrange and Act
    double actualOptionImpliedVolatilityResult =
        Black76Model.optionImpliedVolatility(0.5d, 10.0d, 10.0d, 0.5d, true);

    // Assert
    assertEquals(5.350345731202939d, actualOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionImpliedVolatility(double, double, double, double, boolean)}.
   *
   * <ul>
   *   <li>When {@code 0.223606797749979}.
   *   <li>Then return {@code 0.8467461138721366}.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionImpliedVolatility(double, double, double,
   * double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionImpliedVolatility(double, double, double, double, boolean)"
  })
  public void testOptionImpliedVolatility_when0223606797749979_thenReturn08467461138721366() {
    // Arrange and Act
    double actualOptionImpliedVolatilityResult =
        Black76Model.optionImpliedVolatility(0.5d, 10.0d, 10.0d, 0.223606797749979d, true);

    // Assert
    assertEquals(0.8467461138721366d, actualOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionImpliedVolatility(double, double, double, double, boolean)}.
   *
   * <ul>
   *   <li>When {@code 0.316227766016838}.
   *   <li>Then return {@code 0.6592655988781132}.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionImpliedVolatility(double, double, double,
   * double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionImpliedVolatility(double, double, double, double, boolean)"
  })
  public void testOptionImpliedVolatility_when0316227766016838_thenReturn06592655988781132() {
    // Arrange and Act
    double actualOptionImpliedVolatilityResult =
        Black76Model.optionImpliedVolatility(1.0d, 10.0d, 10.0d, 0.316227766016838d, true);

    // Assert
    assertEquals(0.6592655988781132d, actualOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionImpliedVolatility(double, double, double, double, boolean)}.
   *
   * <ul>
   *   <li>When {@code 4.47213595499958}.
   *   <li>Then return {@code 5.248249487063825}.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionImpliedVolatility(double, double, double,
   * double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionImpliedVolatility(double, double, double, double, boolean)"
  })
  public void testOptionImpliedVolatility_when447213595499958_thenReturn5248249487063825() {
    // Arrange and Act
    double actualOptionImpliedVolatilityResult =
        Black76Model.optionImpliedVolatility(10.0d, 4.47213595499958d, 10.0d, 10.0d, true);

    // Assert
    assertEquals(5.248249487063825d, actualOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionImpliedVolatility(double, double, double, double, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionImpliedVolatility(double, double, double,
   * double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionImpliedVolatility(double, double, double, double, boolean)"
  })
  public void testOptionImpliedVolatility_whenFalse_thenReturnZero() {
    // Arrange and Act
    double actualOptionImpliedVolatilityResult =
        Black76Model.optionImpliedVolatility(10.0d, 10.0d, 10.0d, 10.0d, false);

    // Assert
    assertEquals(0.0d, actualOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionImpliedVolatility(double, double, double, double, boolean)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionImpliedVolatility(double, double, double,
   * double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionImpliedVolatility(double, double, double, double, boolean)"
  })
  public void testOptionImpliedVolatility_whenOne_thenReturnNaN() {
    // Arrange and Act
    double actualOptionImpliedVolatilityResult =
        Black76Model.optionImpliedVolatility(1.0d, 10.0d, 10.0d, 10.0d, false);

    // Assert
    assertEquals(Double.NaN, actualOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionImpliedVolatility(double, double, double, double, boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionImpliedVolatility(double, double, double,
   * double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionImpliedVolatility(double, double, double, double, boolean)"
  })
  public void testOptionImpliedVolatility_whenTrue_thenReturnZero() {
    // Arrange and Act
    double actualOptionImpliedVolatilityResult =
        Black76Model.optionImpliedVolatility(10.0d, 10.0d, 10.0d, 10.0d, true);

    // Assert
    assertEquals(0.0d, actualOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionImpliedVolatility(double, double, double, double, boolean)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionImpliedVolatility(double, double, double,
   * double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionImpliedVolatility(double, double, double, double, boolean)"
  })
  public void testOptionImpliedVolatility_whenZero_thenReturnNaN() {
    // Arrange and Act
    double actualOptionImpliedVolatilityResult =
        Black76Model.optionImpliedVolatility(0.5d, 10.0d, 10.0d, 0.0d, true);

    // Assert
    assertEquals(Double.NaN, actualOptionImpliedVolatilityResult, 0.0);
  }

  /**
   * Test {@link Black76Model#optionImpliedVolatility(double, double, double, double, boolean)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link Black76Model#optionImpliedVolatility(double, double, double,
   * double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Black76Model.optionImpliedVolatility(double, double, double, double, boolean)"
  })
  public void testOptionImpliedVolatility_whenZero_thenReturnZero() {
    // Arrange and Act
    double actualOptionImpliedVolatilityResult =
        Black76Model.optionImpliedVolatility(0.5d, 0.5d, 10.0d, 0.0d, true);

    // Assert
    assertEquals(0.0d, actualOptionImpliedVolatilityResult, 0.0);
  }
}
