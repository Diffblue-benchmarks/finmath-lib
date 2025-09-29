package net.finmath.time;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PeriodDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Period#Period(LocalDate, LocalDate, LocalDate, LocalDate)}
   *   <li>{@link Period#toString()}
   *   <li>{@link Period#getFixing()}
   *   <li>{@link Period#getPayment()}
   *   <li>{@link Period#getPeriodEnd()}
   *   <li>{@link Period#getPeriodStart()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Period.<init>(LocalDate, LocalDate, LocalDate, LocalDate)",
    "LocalDate Period.getFixing()",
    "LocalDate Period.getPayment()",
    "LocalDate Period.getPeriodEnd()",
    "LocalDate Period.getPeriodStart()",
    "String Period.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDate fixing = LocalDate.of(1970, 1, 1);
    LocalDate payment = LocalDate.of(1970, 1, 1);
    LocalDate periodStart = LocalDate.of(1970, 1, 1);
    LocalDate periodEnd = LocalDate.of(1970, 1, 1);

    // Act
    Period actualPeriod = new Period(fixing, payment, periodStart, periodEnd);
    String actualToStringResult = actualPeriod.toString();
    LocalDate actualFixing = actualPeriod.getFixing();
    LocalDate actualPayment = actualPeriod.getPayment();
    LocalDate actualPeriodEnd = actualPeriod.getPeriodEnd();
    LocalDate actualPeriodStart = actualPeriod.getPeriodStart();

    // Assert
    assertEquals("1970-01-01", actualFixing.toString());
    assertEquals("1970-01-01", actualPayment.toString());
    assertEquals("1970-01-01", actualPeriodEnd.toString());
    assertEquals("1970-01-01", actualPeriodStart.toString());
    assertEquals(
        "Period [start=1970-01-01, end=1970-01-01, fixing=1970-01-01, payment=1970-01-01]",
        actualToStringResult);
    assertSame(fixing, actualFixing);
    assertSame(payment, actualPayment);
    assertSame(periodEnd, actualPeriodEnd);
    assertSame(periodStart, actualPeriodStart);
  }

  /**
   * Test {@link Period#compareTo(Period)} with {@code Period}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link Period#compareTo(Period)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int Period.compareTo(Period)"})
  public void testCompareToWithPeriod_thenReturnZero() {
    // Arrange
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    Period o =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    // Act
    int actualCompareToResult = period.compareTo(o);

    // Assert
    assertEquals(0, actualCompareToResult);
  }

  /**
   * Test {@link Period#equals(Object)}, and {@link Period#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Period#equals(Object)}
   *   <li>{@link Period#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Period.equals(Object)", "int Period.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));
    Period period2 =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    // Act and Assert
    assertEquals(period, period2);
    assertEquals(period.hashCode(), period2.hashCode());
  }

  /**
   * Test {@link Period#equals(Object)}, and {@link Period#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Period#equals(Object)}
   *   <li>{@link Period#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Period.equals(Object)", "int Period.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual2() {
    // Arrange
    Period period =
        new Period(
            null, LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1));
    Period period2 =
        new Period(
            null, LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1));

    // Act and Assert
    assertEquals(period, period2);
    assertEquals(period.hashCode(), period2.hashCode());
  }

  /**
   * Test {@link Period#equals(Object)}, and {@link Period#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Period#equals(Object)}
   *   <li>{@link Period#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Period.equals(Object)", "int Period.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual3() {
    // Arrange
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1), null, LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1));
    Period period2 =
        new Period(
            LocalDate.of(1970, 1, 1), null, LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1));

    // Act and Assert
    assertEquals(period, period2);
    assertEquals(period.hashCode(), period2.hashCode());
  }

  /**
   * Test {@link Period#equals(Object)}, and {@link Period#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Period#equals(Object)}
   *   <li>{@link Period#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Period.equals(Object)", "int Period.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual4() {
    // Arrange
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), null, LocalDate.of(1970, 1, 1));
    Period period2 =
        new Period(
            LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), null, LocalDate.of(1970, 1, 1));

    // Act and Assert
    assertEquals(period, period2);
    assertEquals(period.hashCode(), period2.hashCode());
  }

  /**
   * Test {@link Period#equals(Object)}, and {@link Period#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Period#equals(Object)}
   *   <li>{@link Period#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Period.equals(Object)", "int Period.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual5() {
    // Arrange
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), null);
    Period period2 =
        new Period(
            LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), null);

    // Act and Assert
    assertEquals(period, period2);
    assertEquals(period.hashCode(), period2.hashCode());
  }

  /**
   * Test {@link Period#equals(Object)}, and {@link Period#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Period#equals(Object)}
   *   <li>{@link Period#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Period.equals(Object)", "int Period.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    // Act and Assert
    assertEquals(period, period);
    int expectedHashCodeResult = period.hashCode();
    assertEquals(expectedHashCodeResult, period.hashCode());
  }

  /**
   * Test {@link Period#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Period#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Period.equals(Object)", "int Period.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Period period =
        new Period(
            LocalDate.now(),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    // Act and Assert
    assertNotEquals(
        period,
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1)));
  }

  /**
   * Test {@link Period#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Period#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Period.equals(Object)", "int Period.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Period period =
        new Period(
            null, LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1));

    // Act and Assert
    assertNotEquals(
        period,
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1)));
  }

  /**
   * Test {@link Period#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Period#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Period.equals(Object)", "int Period.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.now(),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1));

    // Act and Assert
    assertNotEquals(
        period,
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1)));
  }

  /**
   * Test {@link Period#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Period#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Period.equals(Object)", "int Period.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1), null, LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1));

    // Act and Assert
    assertNotEquals(
        period,
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1)));
  }

  /**
   * Test {@link Period#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Period#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Period.equals(Object)", "int Period.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), null, LocalDate.of(1970, 1, 1));

    // Act and Assert
    assertNotEquals(
        period,
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1)));
  }

  /**
   * Test {@link Period#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Period#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Period.equals(Object)", "int Period.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
    // Arrange
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.now());

    // Act and Assert
    assertNotEquals(
        period,
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1)));
  }

  /**
   * Test {@link Period#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Period#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Period.equals(Object)", "int Period.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual7() {
    // Arrange
    Period period =
        new Period(
            LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), null);

    // Act and Assert
    assertNotEquals(
        period,
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1)));
  }

  /**
   * Test {@link Period#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Period#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Period.equals(Object)", "int Period.hashCode()"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1)),
        null);
  }

  /**
   * Test {@link Period#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Period#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Period.equals(Object)", "int Period.hashCode()"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        new Period(
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1),
            LocalDate.of(1970, 1, 1)),
        "Different type to Period");
  }
}
