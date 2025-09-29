package net.finmath.functions;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.functions.BarrierOptions.BarrierType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BarrierOptionsDiffblueTest {
  /**
   * Test {@link BarrierOptions#blackScholesBarrierOptionValue(double, double, double, double,
   * double, double, boolean, double, double, BarrierType)}.
   *
   * <ul>
   *   <li>When {@code DOWN_IN}.
   *   <li>Then return {@code 1.860037988010418E-44}.
   * </ul>
   *
   * <p>Method under test: {@link BarrierOptions#blackScholesBarrierOptionValue(double, double,
   * double, double, double, double, boolean, double, double, BarrierType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BarrierOptions.blackScholesBarrierOptionValue(double, double, double, double, double, double, boolean, double, double, BarrierType)"
  })
  public void testBlackScholesBarrierOptionValue_whenDownIn_thenReturn1860037988010418e44() {
    // Arrange and Act
    double actualBlackScholesBarrierOptionValueResult =
        BarrierOptions.blackScholesBarrierOptionValue(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 0.5d, false, 10.0d, 10.0d, BarrierType.DOWN_IN);

    // Assert
    assertEquals(1.860037988010418E-44d, actualBlackScholesBarrierOptionValueResult, 0.0);
  }

  /**
   * Test {@link BarrierOptions#blackScholesBarrierOptionValue(double, double, double, double,
   * double, double, boolean, double, double, BarrierType)}.
   *
   * <ul>
   *   <li>When {@code DOWN_IN}.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link BarrierOptions#blackScholesBarrierOptionValue(double, double,
   * double, double, double, double, boolean, double, double, BarrierType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BarrierOptions.blackScholesBarrierOptionValue(double, double, double, double, double, double, boolean, double, double, BarrierType)"
  })
  public void testBlackScholesBarrierOptionValue_whenDownIn_thenReturnTen() {
    // Arrange and Act
    double actualBlackScholesBarrierOptionValueResult =
        BarrierOptions.blackScholesBarrierOptionValue(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, true, 10.0d, 10.0d, BarrierType.DOWN_IN);

    // Assert
    assertEquals(10.0d, actualBlackScholesBarrierOptionValueResult, 0.0);
  }

  /**
   * Test {@link BarrierOptions#blackScholesBarrierOptionValue(double, double, double, double,
   * double, double, boolean, double, double, BarrierType)}.
   *
   * <ul>
   *   <li>When {@code DOWN_IN}.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link BarrierOptions#blackScholesBarrierOptionValue(double, double,
   * double, double, double, double, boolean, double, double, BarrierType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BarrierOptions.blackScholesBarrierOptionValue(double, double, double, double, double, double, boolean, double, double, BarrierType)"
  })
  public void testBlackScholesBarrierOptionValue_whenDownIn_thenReturnTen2() {
    // Arrange and Act
    double actualBlackScholesBarrierOptionValueResult =
        BarrierOptions.blackScholesBarrierOptionValue(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 0.5d, true, 10.0d, 10.0d, BarrierType.DOWN_IN);

    // Assert
    assertEquals(10.0d, actualBlackScholesBarrierOptionValueResult, 0.0);
  }

  /**
   * Test {@link BarrierOptions#blackScholesBarrierOptionValue(double, double, double, double,
   * double, double, boolean, double, double, BarrierType)}.
   *
   * <ul>
   *   <li>When {@code DOWN_IN}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link BarrierOptions#blackScholesBarrierOptionValue(double, double,
   * double, double, double, double, boolean, double, double, BarrierType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BarrierOptions.blackScholesBarrierOptionValue(double, double, double, double, double, double, boolean, double, double, BarrierType)"
  })
  public void testBlackScholesBarrierOptionValue_whenDownIn_thenReturnZero() {
    // Arrange and Act
    double actualBlackScholesBarrierOptionValueResult =
        BarrierOptions.blackScholesBarrierOptionValue(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, false, 10.0d, 10.0d, BarrierType.DOWN_IN);

    // Assert
    assertEquals(0.0d, actualBlackScholesBarrierOptionValueResult, 0.0);
  }

  /**
   * Test {@link BarrierOptions#blackScholesBarrierOptionValue(double, double, double, double,
   * double, double, boolean, double, double, BarrierType)}.
   *
   * <ul>
   *   <li>When {@code DOWN_OUT}.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link BarrierOptions#blackScholesBarrierOptionValue(double, double,
   * double, double, double, double, boolean, double, double, BarrierType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BarrierOptions.blackScholesBarrierOptionValue(double, double, double, double, double, double, boolean, double, double, BarrierType)"
  })
  public void testBlackScholesBarrierOptionValue_whenDownOut_thenReturnTen() {
    // Arrange and Act
    double actualBlackScholesBarrierOptionValueResult =
        BarrierOptions.blackScholesBarrierOptionValue(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, false, 10.0d, 10.0d, BarrierType.DOWN_OUT);

    // Assert
    assertEquals(10.0d, actualBlackScholesBarrierOptionValueResult, 0.0);
  }

  /**
   * Test {@link BarrierOptions#blackScholesBarrierOptionValue(double, double, double, double,
   * double, double, boolean, double, double, BarrierType)}.
   *
   * <ul>
   *   <li>When {@code DOWN_OUT}.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link BarrierOptions#blackScholesBarrierOptionValue(double, double,
   * double, double, double, double, boolean, double, double, BarrierType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BarrierOptions.blackScholesBarrierOptionValue(double, double, double, double, double, double, boolean, double, double, BarrierType)"
  })
  public void testBlackScholesBarrierOptionValue_whenDownOut_thenReturnTen2() {
    // Arrange and Act
    double actualBlackScholesBarrierOptionValueResult =
        BarrierOptions.blackScholesBarrierOptionValue(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, true, 10.0d, 10.0d, BarrierType.DOWN_OUT);

    // Assert
    assertEquals(10.0d, actualBlackScholesBarrierOptionValueResult, 0.0);
  }

  /**
   * Test {@link BarrierOptions#blackScholesBarrierOptionValue(double, double, double, double,
   * double, double, boolean, double, double, BarrierType)}.
   *
   * <ul>
   *   <li>When {@code DOWN_OUT}.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link BarrierOptions#blackScholesBarrierOptionValue(double, double,
   * double, double, double, double, boolean, double, double, BarrierType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BarrierOptions.blackScholesBarrierOptionValue(double, double, double, double, double, double, boolean, double, double, BarrierType)"
  })
  public void testBlackScholesBarrierOptionValue_whenDownOut_thenReturnTen3() {
    // Arrange and Act
    double actualBlackScholesBarrierOptionValueResult =
        BarrierOptions.blackScholesBarrierOptionValue(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 0.5d, true, 10.0d, 10.0d, BarrierType.DOWN_OUT);

    // Assert
    assertEquals(10.0d, actualBlackScholesBarrierOptionValueResult, 0.0);
  }

  /**
   * Test {@link BarrierOptions#blackScholesBarrierOptionValue(double, double, double, double,
   * double, double, boolean, double, double, BarrierType)}.
   *
   * <ul>
   *   <li>When {@code DOWN_OUT}.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link BarrierOptions#blackScholesBarrierOptionValue(double, double,
   * double, double, double, double, boolean, double, double, BarrierType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BarrierOptions.blackScholesBarrierOptionValue(double, double, double, double, double, double, boolean, double, double, BarrierType)"
  })
  public void testBlackScholesBarrierOptionValue_whenDownOut_thenReturnTen4() {
    // Arrange and Act
    double actualBlackScholesBarrierOptionValueResult =
        BarrierOptions.blackScholesBarrierOptionValue(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 0.5d, false, 10.0d, 10.0d, BarrierType.DOWN_OUT);

    // Assert
    assertEquals(10.0d, actualBlackScholesBarrierOptionValueResult, 0.0);
  }

  /**
   * Test {@link BarrierOptions#blackScholesBarrierOptionValue(double, double, double, double,
   * double, double, boolean, double, double, BarrierType)}.
   *
   * <ul>
   *   <li>When {@code UP_IN}.
   *   <li>Then return {@code 1.860037988010418E-44}.
   * </ul>
   *
   * <p>Method under test: {@link BarrierOptions#blackScholesBarrierOptionValue(double, double,
   * double, double, double, double, boolean, double, double, BarrierType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BarrierOptions.blackScholesBarrierOptionValue(double, double, double, double, double, double, boolean, double, double, BarrierType)"
  })
  public void testBlackScholesBarrierOptionValue_whenUpIn_thenReturn1860037988010418e44() {
    // Arrange and Act
    double actualBlackScholesBarrierOptionValueResult =
        BarrierOptions.blackScholesBarrierOptionValue(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 0.5d, false, 10.0d, 10.0d, BarrierType.UP_IN);

    // Assert
    assertEquals(1.860037988010418E-44d, actualBlackScholesBarrierOptionValueResult, 0.0);
  }

  /**
   * Test {@link BarrierOptions#blackScholesBarrierOptionValue(double, double, double, double,
   * double, double, boolean, double, double, BarrierType)}.
   *
   * <ul>
   *   <li>When {@code UP_IN}.
   *   <li>Then return {@code 3.720075976020836E-43}.
   * </ul>
   *
   * <p>Method under test: {@link BarrierOptions#blackScholesBarrierOptionValue(double, double,
   * double, double, double, double, boolean, double, double, BarrierType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BarrierOptions.blackScholesBarrierOptionValue(double, double, double, double, double, double, boolean, double, double, BarrierType)"
  })
  public void testBlackScholesBarrierOptionValue_whenUpIn_thenReturn3720075976020836e43() {
    // Arrange and Act
    double actualBlackScholesBarrierOptionValueResult =
        BarrierOptions.blackScholesBarrierOptionValue(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, false, 10.0d, 10.0d, BarrierType.UP_IN);

    // Assert
    assertEquals(3.720075976020836E-43d, actualBlackScholesBarrierOptionValueResult, 0.0);
  }

  /**
   * Test {@link BarrierOptions#blackScholesBarrierOptionValue(double, double, double, double,
   * double, double, boolean, double, double, BarrierType)}.
   *
   * <ul>
   *   <li>When {@code UP_IN}.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link BarrierOptions#blackScholesBarrierOptionValue(double, double,
   * double, double, double, double, boolean, double, double, BarrierType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BarrierOptions.blackScholesBarrierOptionValue(double, double, double, double, double, double, boolean, double, double, BarrierType)"
  })
  public void testBlackScholesBarrierOptionValue_whenUpIn_thenReturnTen() {
    // Arrange and Act
    double actualBlackScholesBarrierOptionValueResult =
        BarrierOptions.blackScholesBarrierOptionValue(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, true, 10.0d, 10.0d, BarrierType.UP_IN);

    // Assert
    assertEquals(10.0d, actualBlackScholesBarrierOptionValueResult, 0.0);
  }

  /**
   * Test {@link BarrierOptions#blackScholesBarrierOptionValue(double, double, double, double,
   * double, double, boolean, double, double, BarrierType)}.
   *
   * <ul>
   *   <li>When {@code UP_IN}.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link BarrierOptions#blackScholesBarrierOptionValue(double, double,
   * double, double, double, double, boolean, double, double, BarrierType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BarrierOptions.blackScholesBarrierOptionValue(double, double, double, double, double, double, boolean, double, double, BarrierType)"
  })
  public void testBlackScholesBarrierOptionValue_whenUpIn_thenReturnTen2() {
    // Arrange and Act
    double actualBlackScholesBarrierOptionValueResult =
        BarrierOptions.blackScholesBarrierOptionValue(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 0.5d, true, 10.0d, 10.0d, BarrierType.UP_IN);

    // Assert
    assertEquals(10.0d, actualBlackScholesBarrierOptionValueResult, 0.0);
  }

  /**
   * Test {@link BarrierOptions#blackScholesBarrierOptionValue(double, double, double, double,
   * double, double, boolean, double, double, BarrierType)}.
   *
   * <ul>
   *   <li>When {@code UP_OUT}.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link BarrierOptions#blackScholesBarrierOptionValue(double, double,
   * double, double, double, double, boolean, double, double, BarrierType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BarrierOptions.blackScholesBarrierOptionValue(double, double, double, double, double, double, boolean, double, double, BarrierType)"
  })
  public void testBlackScholesBarrierOptionValue_whenUpOut_thenReturnTen() {
    // Arrange and Act
    double actualBlackScholesBarrierOptionValueResult =
        BarrierOptions.blackScholesBarrierOptionValue(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, false, 10.0d, 10.0d, BarrierType.UP_OUT);

    // Assert
    assertEquals(10.0d, actualBlackScholesBarrierOptionValueResult, 0.0);
  }

  /**
   * Test {@link BarrierOptions#blackScholesBarrierOptionValue(double, double, double, double,
   * double, double, boolean, double, double, BarrierType)}.
   *
   * <ul>
   *   <li>When {@code UP_OUT}.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link BarrierOptions#blackScholesBarrierOptionValue(double, double,
   * double, double, double, double, boolean, double, double, BarrierType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BarrierOptions.blackScholesBarrierOptionValue(double, double, double, double, double, double, boolean, double, double, BarrierType)"
  })
  public void testBlackScholesBarrierOptionValue_whenUpOut_thenReturnTen2() {
    // Arrange and Act
    double actualBlackScholesBarrierOptionValueResult =
        BarrierOptions.blackScholesBarrierOptionValue(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, true, 10.0d, 10.0d, BarrierType.UP_OUT);

    // Assert
    assertEquals(10.0d, actualBlackScholesBarrierOptionValueResult, 0.0);
  }

  /**
   * Test {@link BarrierOptions#blackScholesBarrierOptionValue(double, double, double, double,
   * double, double, boolean, double, double, BarrierType)}.
   *
   * <ul>
   *   <li>When {@code UP_OUT}.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link BarrierOptions#blackScholesBarrierOptionValue(double, double,
   * double, double, double, double, boolean, double, double, BarrierType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BarrierOptions.blackScholesBarrierOptionValue(double, double, double, double, double, double, boolean, double, double, BarrierType)"
  })
  public void testBlackScholesBarrierOptionValue_whenUpOut_thenReturnTen3() {
    // Arrange and Act
    double actualBlackScholesBarrierOptionValueResult =
        BarrierOptions.blackScholesBarrierOptionValue(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 0.5d, true, 10.0d, 10.0d, BarrierType.UP_OUT);

    // Assert
    assertEquals(10.0d, actualBlackScholesBarrierOptionValueResult, 0.0);
  }

  /**
   * Test {@link BarrierOptions#blackScholesBarrierOptionValue(double, double, double, double,
   * double, double, boolean, double, double, BarrierType)}.
   *
   * <ul>
   *   <li>When {@code UP_OUT}.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link BarrierOptions#blackScholesBarrierOptionValue(double, double,
   * double, double, double, double, boolean, double, double, BarrierType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BarrierOptions.blackScholesBarrierOptionValue(double, double, double, double, double, double, boolean, double, double, BarrierType)"
  })
  public void testBlackScholesBarrierOptionValue_whenUpOut_thenReturnTen4() {
    // Arrange and Act
    double actualBlackScholesBarrierOptionValueResult =
        BarrierOptions.blackScholesBarrierOptionValue(
            10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 0.5d, false, 10.0d, 10.0d, BarrierType.UP_OUT);

    // Assert
    assertEquals(10.0d, actualBlackScholesBarrierOptionValueResult, 0.0);
  }

  /**
   * Test {@link BarrierOptions#blackScholesBarrierOptionValue(double, double, double, double,
   * double, double, boolean, double, double, BarrierType)}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link BarrierOptions#blackScholesBarrierOptionValue(double, double,
   * double, double, double, double, boolean, double, double, BarrierType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BarrierOptions.blackScholesBarrierOptionValue(double, double, double, double, double, double, boolean, double, double, BarrierType)"
  })
  public void testBlackScholesBarrierOptionValue_whenZero_thenReturnNaN() {
    // Arrange and Act
    double actualBlackScholesBarrierOptionValueResult =
        BarrierOptions.blackScholesBarrierOptionValue(
            0.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, true, 10.0d, 10.0d, BarrierType.DOWN_IN);

    // Assert
    assertEquals(Double.NaN, actualBlackScholesBarrierOptionValueResult, 0.0);
  }
}
