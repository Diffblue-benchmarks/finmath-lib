package net.finmath.montecarlo.interestrate.products.components;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AbstractPeriodDiffblueTest {
  /**
   * Test {@link AbstractPeriod#getCurrency()}.
   *
   * <ul>
   *   <li>Given {@link NotionalFromConstant#NotionalFromConstant(double)} with notional is ten.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractPeriod#getCurrency()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AbstractPeriod.getCurrency()"})
  public void testGetCurrency_givenNotionalFromConstantWithNotionalIsTen_thenReturnNull() {
    // Arrange
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Period period =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, new Numeraire(), true, true, true);

    // Act and Assert
    assertNull(period.getCurrency());
  }

  /**
   * Test {@link AbstractPeriod#getReferenceDate()}.
   *
   * <p>Method under test: {@link AbstractPeriod#getReferenceDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.time.LocalDateTime AbstractPeriod.getReferenceDate()"})
  public void testGetReferenceDate() {
    // Arrange
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Period period =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, new Numeraire(), true, true, true);

    // Act and Assert
    assertNull(period.getReferenceDate());
  }

  /**
   * Test {@link AbstractPeriod#getPeriodStart()}.
   *
   * <p>Method under test: {@link AbstractPeriod#getPeriodStart()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AbstractPeriod.getPeriodStart()"})
  public void testGetPeriodStart() {
    // Arrange
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Period period =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, new Numeraire(), true, true, true);

    // Act and Assert
    assertEquals(10.0d, period.getPeriodStart(), 0.0);
  }

  /**
   * Test {@link AbstractPeriod#getPeriodEnd()}.
   *
   * <p>Method under test: {@link AbstractPeriod#getPeriodEnd()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AbstractPeriod.getPeriodEnd()"})
  public void testGetPeriodEnd() {
    // Arrange
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Period period =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, new Numeraire(), true, true, true);

    // Act and Assert
    assertEquals(10.0d, period.getPeriodEnd(), 0.0);
  }

  /**
   * Test {@link AbstractPeriod#getFixingDate()}.
   *
   * <p>Method under test: {@link AbstractPeriod#getFixingDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AbstractPeriod.getFixingDate()"})
  public void testGetFixingDate() {
    // Arrange
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Period period =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, new Numeraire(), true, true, true);

    // Act and Assert
    assertEquals(10.0d, period.getFixingDate(), 0.0);
  }

  /**
   * Test {@link AbstractPeriod#getPaymentDate()}.
   *
   * <p>Method under test: {@link AbstractPeriod#getPaymentDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AbstractPeriod.getPaymentDate()"})
  public void testGetPaymentDate() {
    // Arrange
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Period period =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, new Numeraire(), true, true, true);

    // Act and Assert
    assertEquals(10.0d, period.getPaymentDate(), 0.0);
  }

  /**
   * Test {@link AbstractPeriod#getNotional()}.
   *
   * <p>Method under test: {@link AbstractPeriod#getNotional()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Notional AbstractPeriod.getNotional()"})
  public void testGetNotional() {
    // Arrange
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Period period =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, new Numeraire(), true, true, true);

    // Act
    Notional actualNotional = period.getNotional();

    // Assert
    assertTrue(actualNotional instanceof NotionalFromConstant);
    assertNull(actualNotional.getCurrency());
    assertSame(notional, actualNotional);
  }

  /**
   * Test {@link AbstractPeriod#getIndex()}.
   *
   * <p>Method under test: {@link AbstractPeriod#getIndex()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AbstractProductComponent AbstractPeriod.getIndex()"})
  public void testGetIndex() {
    // Arrange
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Numeraire index = new Numeraire();

    Period period = new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, index, true, true, true);

    // Act
    AbstractProductComponent actualIndex = period.getIndex();

    // Assert
    assertTrue(actualIndex instanceof Numeraire);
    assertNull(actualIndex.getCurrency());
    assertSame(index, actualIndex);
  }

  /**
   * Test {@link AbstractPeriod#getDaycountFraction()}.
   *
   * <p>Method under test: {@link AbstractPeriod#getDaycountFraction()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double AbstractPeriod.getDaycountFraction()"})
  public void testGetDaycountFraction() {
    // Arrange
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Period period =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, new Numeraire(), true, true, true);

    // Act and Assert
    assertEquals(0.0d, period.getDaycountFraction(), 0.0);
  }

  /**
   * Test {@link AbstractPeriod#queryUnderlyings()}.
   *
   * <p>Method under test: {@link AbstractPeriod#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Set AbstractPeriod.queryUnderlyings()"})
  public void testQueryUnderlyings() {
    // Arrange
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    NotionalFromConstant notional2 = new NotionalFromConstant(10.0d);
    Period index =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional2, new Numeraire(), true, true, true);

    Period period = new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, index, true, true, true);

    // Act and Assert
    assertNull(period.queryUnderlyings());
  }

  /**
   * Test {@link AbstractPeriod#queryUnderlyings()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractPeriod#queryUnderlyings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Set AbstractPeriod.queryUnderlyings()"})
  public void testQueryUnderlyings_thenReturnNull() {
    // Arrange
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Period period =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, new Numeraire(), true, true, true);

    // Act and Assert
    assertNull(period.queryUnderlyings());
  }

  /**
   * Test {@link AbstractPeriod#toString()}.
   *
   * <p>Method under test: {@link AbstractPeriod#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AbstractPeriod.toString()"})
  public void testToString() {
    // Arrange
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    Period period =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, new Numeraire(), true, true, true);

    // Act and Assert
    assertEquals(
        "Period [couponFlow=true, notionalFlow=true, payer=true, toString()=AbstractPeriod [periodStart=10.0,"
            + " periodEnd=10.0, fixingDate=10.0, paymentDate=10.0, notional=Notional [currency=null, notional"
            + "=RandomVariableFromDoubleArray[ realizations=10.0, isDeterministic()=true, filtrationTime=0.0,"
            + " typePriority=1]], index=AbstractMonteCarloProduct [currency=null], daycountFraction=0.0]]",
        period.toString());
  }

  /**
   * Test {@link AbstractPeriod#toString()}.
   *
   * <p>Method under test: {@link AbstractPeriod#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AbstractPeriod.toString()"})
  public void testToString2() {
    // Arrange
    NotionalFromConstant notional = new NotionalFromConstant(10.0d);
    NotionalFromConstant notional2 = new NotionalFromConstant(10.0d);
    Period index =
        new Period(10.0d, 10.0d, 10.0d, 10.0d, notional2, new Numeraire(), true, true, true);

    Period period = new Period(10.0d, 10.0d, 10.0d, 10.0d, notional, index, true, true, true);

    // Act and Assert
    assertEquals(
        "Period [couponFlow=true, notionalFlow=true, payer=true, toString()=AbstractPeriod [periodStart=10.0,"
            + " periodEnd=10.0, fixingDate=10.0, paymentDate=10.0, notional=Notional [currency=null, notional"
            + "=RandomVariableFromDoubleArray[ realizations=10.0, isDeterministic()=true, filtrationTime=0.0,"
            + " typePriority=1]], index=Period [couponFlow=true, notionalFlow=true, payer=true, toString()=AbstractPeriod"
            + " [periodStart=10.0, periodEnd=10.0, fixingDate=10.0, paymentDate=10.0, notional=Notional [currency=null,"
            + " notional=RandomVariableFromDoubleArray[ realizations=10.0, isDeterministic()=true, filtrationTime=0.0,"
            + " typePriority=1]], index=AbstractMonteCarloProduct [currency=null], daycountFraction=0.0]],"
            + " daycountFraction=0.0]]",
        period.toString());
  }
}
