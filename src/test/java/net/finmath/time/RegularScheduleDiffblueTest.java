package net.finmath.time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.time.daycount.DayCountConvention;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RegularScheduleDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RegularSchedule#RegularSchedule(TimeDiscretization)}
   *   <li>{@link RegularSchedule#getDaycountconvention()}
   *   <li>{@link RegularSchedule#getReferenceDate()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RegularSchedule.<init>(TimeDiscretization)",
    "DayCountConvention RegularSchedule.getDaycountconvention()",
    "java.time.LocalDate RegularSchedule.getReferenceDate()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    RegularSchedule actualRegularSchedule =
        new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d));
    DayCountConvention actualDaycountconvention = actualRegularSchedule.getDaycountconvention();

    // Assert
    assertNull(actualRegularSchedule.getReferenceDate());
    assertNull(actualDaycountconvention);
  }

  /**
   * Test {@link RegularSchedule#getPeriods()}.
   *
   * <p>Method under test: {@link RegularSchedule#getPeriods()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.ArrayList RegularSchedule.getPeriods()"})
  public void testGetPeriods() {
    // Arrange, Act and Assert
    assertNull(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)).getPeriods());
  }

  /**
   * Test {@link RegularSchedule#getNumberOfPeriods()}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link RegularSchedule#getNumberOfPeriods()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int RegularSchedule.getNumberOfPeriods()"})
  public void testGetNumberOfPeriods_thenReturnTen() {
    // Arrange, Act and Assert
    assertEquals(10, new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)).getNumberOfPeriods());
  }

  /**
   * Test {@link RegularSchedule#getPeriod(int)}.
   *
   * <p>Method under test: {@link RegularSchedule#getPeriod(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"net.finmath.time.Period RegularSchedule.getPeriod(int)"})
  public void testGetPeriod() {
    // Arrange, Act and Assert
    assertNull(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)).getPeriod(1));
  }

  /**
   * Test {@link RegularSchedule#getFixing(int)}.
   *
   * <ul>
   *   <li>Then return {@code 10.5}.
   * </ul>
   *
   * <p>Method under test: {@link RegularSchedule#getFixing(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RegularSchedule.getFixing(int)"})
  public void testGetFixing_thenReturn105() {
    // Arrange, Act and Assert
    assertEquals(10.5d, new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)).getFixing(1), 0.0);
  }

  /**
   * Test {@link RegularSchedule#getPayment(int)}.
   *
   * <ul>
   *   <li>Then return eleven.
   * </ul>
   *
   * <p>Method under test: {@link RegularSchedule#getPayment(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RegularSchedule.getPayment(int)"})
  public void testGetPayment_thenReturnEleven() {
    // Arrange, Act and Assert
    assertEquals(
        11.0d, new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)).getPayment(1), 0.0);
  }

  /**
   * Test {@link RegularSchedule#getPeriodStart(int)}.
   *
   * <ul>
   *   <li>Then return {@code 10.5}.
   * </ul>
   *
   * <p>Method under test: {@link RegularSchedule#getPeriodStart(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RegularSchedule.getPeriodStart(int)"})
  public void testGetPeriodStart_thenReturn105() {
    // Arrange, Act and Assert
    assertEquals(
        10.5d, new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)).getPeriodStart(1), 0.0);
  }

  /**
   * Test {@link RegularSchedule#getPeriodEnd(int)}.
   *
   * <ul>
   *   <li>Then return eleven.
   * </ul>
   *
   * <p>Method under test: {@link RegularSchedule#getPeriodEnd(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RegularSchedule.getPeriodEnd(int)"})
  public void testGetPeriodEnd_thenReturnEleven() {
    // Arrange, Act and Assert
    assertEquals(
        11.0d, new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)).getPeriodEnd(1), 0.0);
  }

  /**
   * Test {@link RegularSchedule#getPeriodLength(int)}.
   *
   * <ul>
   *   <li>Then return {@code 0.5}.
   * </ul>
   *
   * <p>Method under test: {@link RegularSchedule#getPeriodLength(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double RegularSchedule.getPeriodLength(int)"})
  public void testGetPeriodLength_thenReturn05() {
    // Arrange, Act and Assert
    assertEquals(
        0.5d, new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)).getPeriodLength(1), 0.0);
  }

  /**
   * Test {@link RegularSchedule#iterator()}.
   *
   * <p>Method under test: {@link RegularSchedule#iterator()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Iterator RegularSchedule.iterator()"})
  public void testIterator() {
    // Arrange, Act and Assert
    assertNull(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)).iterator());
  }

  /**
   * Test {@link RegularSchedule#getPeriodIndex(double)} with {@code time}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link RegularSchedule#getPeriodIndex(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int RegularSchedule.getPeriodIndex(double)"})
  public void testGetPeriodIndexWithTime_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0, new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)).getPeriodIndex(10.0d));
  }
}
