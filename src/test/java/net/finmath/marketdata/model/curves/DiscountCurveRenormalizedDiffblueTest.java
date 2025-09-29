package net.finmath.marketdata.model.curves;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DiscountCurveRenormalizedDiffblueTest {
  /**
   * Test {@link DiscountCurveRenormalized#DiscountCurveRenormalized(String, LocalDate, LocalDate,
   * String)}.
   *
   * <ul>
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveRenormalized#DiscountCurveRenormalized(String,
   * LocalDate, LocalDate, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DiscountCurveRenormalized.<init>(String, LocalDate, LocalDate, String)"})
  public void testNewDiscountCurveRenormalized_thenReturnName() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    DiscountCurveRenormalized actualDiscountCurveRenormalized =
        new DiscountCurveRenormalized(
            "Name", referenceDate, LocalDate.of(1970, 1, 1), "Base Curve Name");

    // Assert
    assertEquals("Name", actualDiscountCurveRenormalized.getName());
    assertSame(referenceDate, actualDiscountCurveRenormalized.getReferenceDate());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DiscountCurveRenormalized#getName()}
   *   <li>{@link DiscountCurveRenormalized#getReferenceDate()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DiscountCurveRenormalized.getName()",
    "LocalDate DiscountCurveRenormalized.getReferenceDate()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveRenormalized discountCurveRenormalized =
        new DiscountCurveRenormalized(
            "Name", referenceDate, LocalDate.of(1970, 1, 1), "Base Curve Name");

    // Act
    String actualName = discountCurveRenormalized.getName();
    LocalDate actualReferenceDate = discountCurveRenormalized.getReferenceDate();

    // Assert
    assertEquals("1970-01-01", actualReferenceDate.toString());
    assertEquals("Name", actualName);
    assertSame(referenceDate, actualReferenceDate);
  }

  /**
   * Test {@link DiscountCurveRenormalized#getCloneBuilder()}.
   *
   * <p>Method under test: {@link DiscountCurveRenormalized#getCloneBuilder()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.marketdata.model.curves.CurveBuilder DiscountCurveRenormalized.getCloneBuilder()"
  })
  public void testGetCloneBuilder() {
    // Arrange
    DiscountCurveRenormalized discountCurveRenormalized =
        new DiscountCurveRenormalized(
            "Name", LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), "Base Curve Name");

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> discountCurveRenormalized.getCloneBuilder());
  }

  /**
   * Test {@link DiscountCurveRenormalized#getCloneForParameter(double[])}.
   *
   * <p>Method under test: {@link DiscountCurveRenormalized#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.marketdata.model.curves.Curve DiscountCurveRenormalized.getCloneForParameter(double[])"
  })
  public void testGetCloneForParameter() throws CloneNotSupportedException {
    // Arrange
    DiscountCurveRenormalized discountCurveRenormalized =
        new DiscountCurveRenormalized(
            "Name", LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), "Base Curve Name");

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            discountCurveRenormalized.getCloneForParameter(
                new double[] {10.0d, 0.5d, 10.0d, 0.5d}));
  }

  /**
   * Test {@link DiscountCurveRenormalized#getParameter()}.
   *
   * <p>Method under test: {@link DiscountCurveRenormalized#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] DiscountCurveRenormalized.getParameter()"})
  public void testGetParameter() {
    // Arrange
    DiscountCurveRenormalized discountCurveRenormalized =
        new DiscountCurveRenormalized(
            "Name", LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), "Base Curve Name");

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> discountCurveRenormalized.getParameter());
  }

  /**
   * Test {@link DiscountCurveRenormalized#setParameter(double[])}.
   *
   * <p>Method under test: {@link DiscountCurveRenormalized#setParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DiscountCurveRenormalized.setParameter(double[])"})
  public void testSetParameter() {
    // Arrange
    DiscountCurveRenormalized discountCurveRenormalized =
        new DiscountCurveRenormalized(
            "Name", LocalDate.of(1970, 1, 1), LocalDate.of(1970, 1, 1), "Base Curve Name");

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> discountCurveRenormalized.setParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d}));
  }

  /**
   * Test {@link DiscountCurveRenormalized#clone()}.
   *
   * <ul>
   *   <li>Then return ReferenceDate toString is {@code 1970-01-01}.
   * </ul>
   *
   * <p>Method under test: {@link DiscountCurveRenormalized#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DiscountCurveRenormalized DiscountCurveRenormalized.clone()"})
  public void testClone_thenReturnReferenceDateToStringIs19700101() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveRenormalized discountCurveRenormalized =
        new DiscountCurveRenormalized(
            "Name", referenceDate, LocalDate.of(1970, 1, 1), "Base Curve Name");

    // Act
    DiscountCurveRenormalized actualCloneResult = discountCurveRenormalized.clone();

    // Assert
    LocalDate referenceDate2 = actualCloneResult.getReferenceDate();
    assertEquals("1970-01-01", referenceDate2.toString());
    assertEquals("Name", actualCloneResult.getName());
    assertSame(referenceDate, referenceDate2);
  }
}
