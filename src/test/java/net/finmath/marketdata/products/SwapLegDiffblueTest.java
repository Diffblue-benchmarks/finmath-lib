package net.finmath.marketdata.products;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import net.finmath.modelling.descriptor.InterestRateSwapLegProductDescriptor;
import net.finmath.time.RegularSchedule;
import net.finmath.time.Schedule;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SwapLegDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SwapLeg#SwapLeg(LocalDateTime, Schedule, String, double[], double[], String,
   *       boolean)}
   *   <li>{@link SwapLeg#toString()}
   *   <li>{@link SwapLeg#getDiscountCurveName()}
   *   <li>{@link SwapLeg#getForwardCurveName()}
   *   <li>{@link SwapLeg#getSchedule()}
   *   <li>{@link SwapLeg#isNotionalExchanged()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwapLeg.<init>(LocalDateTime, Schedule, String, double[], double[], String, boolean)",
    "String SwapLeg.getDiscountCurveName()",
    "String SwapLeg.getForwardCurveName()",
    "Schedule SwapLeg.getSchedule()",
    "boolean SwapLeg.isNotionalExchanged()",
    "String SwapLeg.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDateTime cashFlowEffectiveDate = LocalDate.of(1970, 1, 1).atStartOfDay();
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    SwapLeg actualSwapLeg =
        new SwapLeg(
            cashFlowEffectiveDate,
            legSchedule,
            "Forward Curve Name",
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            "3",
            true);
    actualSwapLeg.toString();
    String actualDiscountCurveName = actualSwapLeg.getDiscountCurveName();
    String actualForwardCurveName = actualSwapLeg.getForwardCurveName();
    Schedule actualSchedule = actualSwapLeg.getSchedule();

    // Assert
    assertEquals("3", actualDiscountCurveName);
    assertEquals("Forward Curve Name", actualForwardCurveName);
    assertTrue(actualSwapLeg.isNotionalExchanged());
    assertSame(legSchedule, actualSchedule);
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Optional, Schedule, String, double[], double[], String, boolean)}.
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Optional, Schedule, String, double[], double[],
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwapLeg.<init>(Optional, Schedule, String, double[], double[], String, boolean)"
  })
  public void testNewSwapLeg() {
    // Arrange
    Optional<LocalDateTime> cashFlowEffectiveDate =
        Optional.of(LocalDate.of(1970, 1, 1).atStartOfDay());
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    SwapLeg actualSwapLeg =
        new SwapLeg(
            cashFlowEffectiveDate,
            legSchedule,
            "Forward Curve Name",
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            "3",
            true);

    // Assert
    assertEquals("3", actualSwapLeg.getDiscountCurveName());
    InterestRateSwapLegProductDescriptor descriptor = actualSwapLeg.getDescriptor();
    assertEquals("3", descriptor.getDiscountCurveName());
    assertEquals("Forward Curve Name", actualSwapLeg.getForwardCurveName());
    assertEquals("Forward Curve Name", descriptor.getForwardCurveName());
    assertTrue(actualSwapLeg.isNotionalExchanged());
    assertTrue(descriptor.isNotionalExchanged());
    assertSame(legSchedule, actualSwapLeg.getSchedule());
    assertArrayEquals(new double[] {10.0d, 2.0d, 10.0d, 2.0d}, actualSwapLeg.getSpreads(), 0.0);
    assertArrayEquals(new double[] {10.0d, 2.0d, 10.0d, 2.0d}, descriptor.getNotionals(), 0.0);
    assertArrayEquals(new double[] {10.0d, 2.0d, 10.0d, 2.0d}, descriptor.getSpreads(), 0.0);
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, String, double[], double[], String, boolean)}.
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, String, double[], double[], String,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SwapLeg.<init>(Schedule, String, double[], double[], String, boolean)"})
  public void testNewSwapLeg2() {
    // Arrange
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    SwapLeg actualSwapLeg =
        new SwapLeg(
            legSchedule,
            "Forward Curve Name",
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            new double[] {10.0d, 2.0d, 10.0d, 2.0d},
            "3",
            true);

    // Assert
    assertEquals("3", actualSwapLeg.getDiscountCurveName());
    InterestRateSwapLegProductDescriptor descriptor = actualSwapLeg.getDescriptor();
    assertEquals("3", descriptor.getDiscountCurveName());
    assertEquals("Forward Curve Name", actualSwapLeg.getForwardCurveName());
    assertEquals("Forward Curve Name", descriptor.getForwardCurveName());
    assertTrue(actualSwapLeg.isNotionalExchanged());
    assertTrue(descriptor.isNotionalExchanged());
    assertSame(legSchedule, actualSwapLeg.getSchedule());
    assertArrayEquals(new double[] {10.0d, 2.0d, 10.0d, 2.0d}, actualSwapLeg.getSpreads(), 0.0);
    assertArrayEquals(new double[] {10.0d, 2.0d, 10.0d, 2.0d}, descriptor.getNotionals(), 0.0);
    assertArrayEquals(new double[] {10.0d, 2.0d, 10.0d, 2.0d}, descriptor.getSpreads(), 0.0);
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Optional, Schedule, String, double, String)}.
   *
   * <ul>
   *   <li>Then return DiscountCurveName is {@code 3}.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Optional, Schedule, String, double, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SwapLeg.<init>(Optional, Schedule, String, double, String)"})
  public void testNewSwapLeg_thenReturnDiscountCurveNameIs3() {
    // Arrange
    Optional<LocalDateTime> cashFlowEffectiveDate =
        Optional.of(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    SwapLeg actualSwapLeg =
        new SwapLeg(
            cashFlowEffectiveDate,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Name",
            10.0d,
            "3");

    // Assert
    assertEquals("3", actualSwapLeg.getDiscountCurveName());
    InterestRateSwapLegProductDescriptor descriptor = actualSwapLeg.getDescriptor();
    assertEquals("3", descriptor.getDiscountCurveName());
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        descriptor.getNotionals(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualSwapLeg.getSpreads(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        descriptor.getSpreads(),
        0.0);
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Optional, Schedule, String, double, String, String, boolean)}.
   *
   * <ul>
   *   <li>Then return DiscountCurveName is {@code 3}.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Optional, Schedule, String, double, String,
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwapLeg.<init>(Optional, Schedule, String, double, String, String, boolean)"
  })
  public void testNewSwapLeg_thenReturnDiscountCurveNameIs32() {
    // Arrange
    Optional<LocalDateTime> cashFlowEffectiveDate =
        Optional.of(LocalDate.of(1970, 1, 1).atStartOfDay());
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    SwapLeg actualSwapLeg =
        new SwapLeg(
            cashFlowEffectiveDate, legSchedule, "Forward Curve Name", 10.0d, "3", "3", true);

    // Assert
    assertEquals("3", actualSwapLeg.getDiscountCurveName());
    InterestRateSwapLegProductDescriptor descriptor = actualSwapLeg.getDescriptor();
    assertEquals("3", descriptor.getDiscountCurveName());
    assertEquals("Forward Curve Name", actualSwapLeg.getForwardCurveName());
    assertEquals("Forward Curve Name", descriptor.getForwardCurveName());
    assertEquals(10.0d, actualSwapLeg.getSpread(), 0.0);
    assertTrue(actualSwapLeg.isNotionalExchanged());
    assertTrue(descriptor.isNotionalExchanged());
    assertSame(legSchedule, actualSwapLeg.getSchedule());
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        descriptor.getNotionals(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualSwapLeg.getSpreads(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        descriptor.getSpreads(),
        0.0);
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, String, double, String)}.
   *
   * <ul>
   *   <li>Then return DiscountCurveName is {@code 3}.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, String, double, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SwapLeg.<init>(Schedule, String, double, String)"})
  public void testNewSwapLeg_thenReturnDiscountCurveNameIs33() {
    // Arrange and Act
    SwapLeg actualSwapLeg =
        new SwapLeg(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Name",
            10.0d,
            "3");

    // Assert
    assertEquals("3", actualSwapLeg.getDiscountCurveName());
    InterestRateSwapLegProductDescriptor descriptor = actualSwapLeg.getDescriptor();
    assertEquals("3", descriptor.getDiscountCurveName());
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        descriptor.getNotionals(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualSwapLeg.getSpreads(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        descriptor.getSpreads(),
        0.0);
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, String, double, String, String, boolean)}.
   *
   * <ul>
   *   <li>Then return DiscountCurveName is {@code 3}.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, String, double, String, String,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SwapLeg.<init>(Schedule, String, double, String, String, boolean)"})
  public void testNewSwapLeg_thenReturnDiscountCurveNameIs34() {
    // Arrange
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    SwapLeg actualSwapLeg = new SwapLeg(legSchedule, "Forward Curve Name", 10.0d, "3", "3", true);

    // Assert
    assertEquals("3", actualSwapLeg.getDiscountCurveName());
    InterestRateSwapLegProductDescriptor descriptor = actualSwapLeg.getDescriptor();
    assertEquals("3", descriptor.getDiscountCurveName());
    assertEquals("Forward Curve Name", actualSwapLeg.getForwardCurveName());
    assertEquals("Forward Curve Name", descriptor.getForwardCurveName());
    assertEquals(10.0d, actualSwapLeg.getSpread(), 0.0);
    assertTrue(actualSwapLeg.isNotionalExchanged());
    assertTrue(descriptor.isNotionalExchanged());
    assertSame(legSchedule, actualSwapLeg.getSchedule());
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        descriptor.getNotionals(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualSwapLeg.getSpreads(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        descriptor.getSpreads(),
        0.0);
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, String, double, String, boolean)}.
   *
   * <ul>
   *   <li>Then return DiscountCurveName is {@code 3}.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, String, double, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SwapLeg.<init>(Schedule, String, double, String, boolean)"})
  public void testNewSwapLeg_thenReturnDiscountCurveNameIs35() {
    // Arrange and Act
    SwapLeg actualSwapLeg =
        new SwapLeg(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Name",
            10.0d,
            "3",
            true);

    // Assert
    assertEquals("3", actualSwapLeg.getDiscountCurveName());
    InterestRateSwapLegProductDescriptor descriptor = actualSwapLeg.getDescriptor();
    assertEquals("3", descriptor.getDiscountCurveName());
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        descriptor.getNotionals(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualSwapLeg.getSpreads(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        descriptor.getSpreads(),
        0.0);
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Optional, Schedule, String, double, String, String, boolean)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return DiscountCurveName is {@code 3}.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Optional, Schedule, String, double, String,
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SwapLeg.<init>(Optional, Schedule, String, double, String, String, boolean)"
  })
  public void testNewSwapLeg_whenEmptyString_thenReturnDiscountCurveNameIs3() {
    // Arrange
    Optional<LocalDateTime> cashFlowEffectiveDate =
        Optional.of(LocalDate.of(1970, 1, 1).atStartOfDay());
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    SwapLeg actualSwapLeg =
        new SwapLeg(cashFlowEffectiveDate, legSchedule, "Forward Curve Name", 10.0d, "3", "", true);

    // Assert
    assertEquals("3", actualSwapLeg.getDiscountCurveName());
    InterestRateSwapLegProductDescriptor descriptor = actualSwapLeg.getDescriptor();
    assertEquals("3", descriptor.getDiscountCurveName());
    assertEquals("Forward Curve Name", actualSwapLeg.getForwardCurveName());
    assertEquals("Forward Curve Name", descriptor.getForwardCurveName());
    assertEquals(10.0d, actualSwapLeg.getSpread(), 0.0);
    assertTrue(actualSwapLeg.isNotionalExchanged());
    assertTrue(descriptor.isNotionalExchanged());
    assertSame(legSchedule, actualSwapLeg.getSchedule());
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        descriptor.getNotionals(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualSwapLeg.getSpreads(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        descriptor.getSpreads(),
        0.0);
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, String, double, String, String, boolean)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return DiscountCurveName is {@code 3}.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, String, double, String, String,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SwapLeg.<init>(Schedule, String, double, String, String, boolean)"})
  public void testNewSwapLeg_whenEmptyString_thenReturnDiscountCurveNameIs32() {
    // Arrange
    RegularSchedule legSchedule = new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));

    // Act
    SwapLeg actualSwapLeg = new SwapLeg(legSchedule, "Forward Curve Name", 10.0d, "3", "", true);

    // Assert
    assertEquals("3", actualSwapLeg.getDiscountCurveName());
    InterestRateSwapLegProductDescriptor descriptor = actualSwapLeg.getDescriptor();
    assertEquals("3", descriptor.getDiscountCurveName());
    assertEquals("Forward Curve Name", actualSwapLeg.getForwardCurveName());
    assertEquals("Forward Curve Name", descriptor.getForwardCurveName());
    assertEquals(10.0d, actualSwapLeg.getSpread(), 0.0);
    assertTrue(actualSwapLeg.isNotionalExchanged());
    assertTrue(descriptor.isNotionalExchanged());
    assertSame(legSchedule, actualSwapLeg.getSchedule());
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        descriptor.getNotionals(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualSwapLeg.getSpreads(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        descriptor.getSpreads(),
        0.0);
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Optional, Schedule, String, double, String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return DiscountCurveName is empty string.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Optional, Schedule, String, double, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SwapLeg.<init>(Optional, Schedule, String, double, String)"})
  public void testNewSwapLeg_whenEmptyString_thenReturnDiscountCurveNameIsEmptyString() {
    // Arrange
    Optional<LocalDateTime> cashFlowEffectiveDate =
        Optional.of(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Act
    SwapLeg actualSwapLeg =
        new SwapLeg(
            cashFlowEffectiveDate,
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Name",
            10.0d,
            "");

    // Assert
    assertEquals("", actualSwapLeg.getDiscountCurveName());
    InterestRateSwapLegProductDescriptor descriptor = actualSwapLeg.getDescriptor();
    assertEquals("", descriptor.getDiscountCurveName());
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        descriptor.getNotionals(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualSwapLeg.getSpreads(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        descriptor.getSpreads(),
        0.0);
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, String, double, String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return DiscountCurveName is empty string.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, String, double, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SwapLeg.<init>(Schedule, String, double, String)"})
  public void testNewSwapLeg_whenEmptyString_thenReturnDiscountCurveNameIsEmptyString2() {
    // Arrange and Act
    SwapLeg actualSwapLeg =
        new SwapLeg(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Name",
            10.0d,
            "");

    // Assert
    assertEquals("", actualSwapLeg.getDiscountCurveName());
    InterestRateSwapLegProductDescriptor descriptor = actualSwapLeg.getDescriptor();
    assertEquals("", descriptor.getDiscountCurveName());
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        descriptor.getNotionals(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualSwapLeg.getSpreads(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        descriptor.getSpreads(),
        0.0);
  }

  /**
   * Test {@link SwapLeg#SwapLeg(Schedule, String, double, String, boolean)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return DiscountCurveName is empty string.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#SwapLeg(Schedule, String, double, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SwapLeg.<init>(Schedule, String, double, String, boolean)"})
  public void testNewSwapLeg_whenEmptyString_thenReturnDiscountCurveNameIsEmptyString3() {
    // Arrange and Act
    SwapLeg actualSwapLeg =
        new SwapLeg(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Name",
            10.0d,
            "",
            true);

    // Assert
    assertEquals("", actualSwapLeg.getDiscountCurveName());
    InterestRateSwapLegProductDescriptor descriptor = actualSwapLeg.getDescriptor();
    assertEquals("", descriptor.getDiscountCurveName());
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        descriptor.getNotionals(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualSwapLeg.getSpreads(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        descriptor.getSpreads(),
        0.0);
  }

  /**
   * Test {@link SwapLeg#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <ul>
   *   <li>When {@link AnalyticModelFromCurvesAndVols#AnalyticModelFromCurvesAndVols()}.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwapLeg.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_whenAnalyticModelFromCurvesAndVols() {
    // Arrange
    SwapLeg swapLeg =
        new SwapLeg(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Name",
            10.0d,
            "3");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> swapLeg.getValue(10.0d, new AnalyticModelFromCurvesAndVols()));
  }

  /**
   * Test {@link SwapLeg#getValue(double, AnalyticModel)} with {@code double}, {@code
   * AnalyticModel}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#getValue(double, AnalyticModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwapLeg.getValue(double, AnalyticModel)"})
  public void testGetValueWithDoubleAnalyticModel_whenNull() {
    // Arrange
    SwapLeg swapLeg =
        new SwapLeg(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Name",
            10.0d,
            "3");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> swapLeg.getValue(10.0d, (AnalyticModel) null));
  }

  /**
   * Test {@link SwapLeg#getSpreads()}.
   *
   * <p>Method under test: {@link SwapLeg#getSpreads()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] SwapLeg.getSpreads()"})
  public void testGetSpreads() {
    // Arrange
    SwapLeg swapLeg =
        new SwapLeg(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Name",
            10.0d,
            "3");

    // Act and Assert
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        swapLeg.getSpreads(),
        0.0);
  }

  /**
   * Test {@link SwapLeg#getSpread()}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#getSpread()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwapLeg.getSpread()"})
  public void testGetSpread_thenReturnTen() {
    // Arrange
    SwapLeg swapLeg =
        new SwapLeg(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Name",
            10.0d,
            "3");

    // Act and Assert
    assertEquals(10.0d, swapLeg.getSpread(), 0.0);
  }

  /**
   * Test {@link SwapLeg#getSpread()}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link SwapLeg#getSpread()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SwapLeg.getSpread()"})
  public void testGetSpread_thenThrowUnsupportedOperationException() {
    // Arrange
    SwapLeg swapLeg =
        new SwapLeg(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Name",
            Double.NaN,
            "3");

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> swapLeg.getSpread());
  }

  /**
   * Test {@link SwapLeg#getDescriptor()}.
   *
   * <p>Method under test: {@link SwapLeg#getDescriptor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"InterestRateSwapLegProductDescriptor SwapLeg.getDescriptor()"})
  public void testGetDescriptor() {
    // Arrange
    SwapLeg swapLeg =
        new SwapLeg(
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            "Forward Curve Name",
            10.0d,
            "3");

    // Act
    InterestRateSwapLegProductDescriptor actualDescriptor = swapLeg.getDescriptor();

    // Assert
    assertEquals("3", actualDescriptor.getDiscountCurveName());
    assertEquals("Forward Curve Name", actualDescriptor.getForwardCurveName());
    assertFalse(actualDescriptor.isNotionalExchanged());
    assertArrayEquals(
        new double[] {1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d, 1.0d},
        actualDescriptor.getNotionals(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d},
        actualDescriptor.getSpreads(),
        0.0);
  }
}
