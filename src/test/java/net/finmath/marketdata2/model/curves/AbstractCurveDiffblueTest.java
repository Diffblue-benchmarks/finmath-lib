package net.finmath.marketdata2.model.curves;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.marketdata2.model.curves.ForwardCurveInterpolation.InterpolationEntityForward;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.stochastic.RandomVariable;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AbstractCurveDiffblueTest {
  /**
   * Test {@link AbstractCurve#getName()}.
   *
   * <p>Method under test: {@link AbstractCurve#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AbstractCurve.getName()"})
  public void testGetName() {
    // Arrange, Act and Assert
    assertEquals(
        "DiscountCurveFromForwardCurve(Forward Curve Name)",
        new DiscountCurveFromForwardCurve("Forward Curve Name").getName());
  }

  /**
   * Test {@link AbstractCurve#getReferenceDate()}.
   *
   * <p>Method under test: {@link AbstractCurve#getReferenceDate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"LocalDate AbstractCurve.getReferenceDate()"})
  public void testGetReferenceDate() {
    // Arrange, Act and Assert
    assertNull(new DiscountCurveFromForwardCurve("Forward Curve Name").getReferenceDate());
  }

  /**
   * Test {@link AbstractCurve#getValue(double)} with {@code double}.
   *
   * <ul>
   *   <li>Then return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractCurve#getValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable AbstractCurve.getValue(double)"})
  public void testGetValueWithDouble_thenReturnRandomVariableFromDoubleArray() {
    // Arrange
    ForwardCurveFromDiscountCurve forwardCurve =
        new ForwardCurveFromDiscountCurve("3", LocalDate.of(1970, 1, 1), "Payment Offset Code");

    // Act
    RandomVariable actualValue =
        new DiscountCurveFromForwardCurve(forwardCurve).getValue(Double.NEGATIVE_INFINITY);

    // Assert
    assertTrue(actualValue instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.abs() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.average() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.cos() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.expectation() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.invert() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.isNaN() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sin() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.sqrt() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.squared() instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValue.variance() instanceof RandomVariableFromDoubleArray);
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, actualValue.getTypePriority());
    assertEquals(1, actualValue.size());
    assertEquals(1.0d, actualValue.getAverage(), 0.0);
    assertEquals(1.0d, actualValue.getMax(), 0.0);
    assertEquals(1.0d, actualValue.getMin(), 0.0);
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getFiltrationTime(), 0.0);
    assertArrayEquals(new double[] {1.0d}, actualValue.getRealizations(), 0.0);
  }

  /**
   * Test {@link AbstractCurve#getValues(double[])}.
   *
   * <ul>
   *   <li>Then first element return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractCurve#getValues(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] AbstractCurve.getValues(double[])"})
  public void testGetValues_thenFirstElementReturnRandomVariableFromDoubleArray() {
    // Arrange
    ForwardCurveInterpolation forwardCurve =
        new ForwardCurveInterpolation(
            "(?<=[0-9|\\.])(?=[A-Z|a-z])", 1.0d, InterpolationEntityForward.FORWARD, "3");
    forwardCurve.addPoint(1.0d, new RandomVariableFromDoubleArray(10.0d), false);

    // Act
    RandomVariable[] actualValues =
        new DiscountCurveFromForwardCurve(forwardCurve)
            .getValues(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertTrue(actualValues[0] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValues[1] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValues[2] instanceof RandomVariableFromDoubleArray);
    assertTrue(actualValues[3] instanceof RandomVariableFromDoubleArray);
    assertEquals(4, actualValues.length);
  }

  /**
   * Test {@link AbstractCurve#getValues(double[])}.
   *
   * <ul>
   *   <li>When empty array of {@code double}.
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link AbstractCurve#getValues(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] AbstractCurve.getValues(double[])"})
  public void testGetValues_whenEmptyArrayOfDouble_thenReturnArrayLengthIsZero() {
    // Arrange, Act and Assert
    assertEquals(
        0,
        new DiscountCurveFromForwardCurve("Forward Curve Name").getValues(new double[] {}).length);
  }

  /**
   * Test {@link AbstractCurve#clone()}.
   *
   * <p>Method under test: {@link AbstractCurve#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"AbstractCurve AbstractCurve.clone()"})
  public void testClone() throws CloneNotSupportedException {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act
    AbstractCurve actualCloneResult = discountCurveFromForwardCurve.clone();

    // Assert
    assertTrue(actualCloneResult instanceof DiscountCurveFromForwardCurve);
    assertEquals(discountCurveFromForwardCurve, actualCloneResult);
  }

  /**
   * Test {@link AbstractCurve#getCloneForParameter(RandomVariable[])}.
   *
   * <p>Method under test: {@link AbstractCurve#getCloneForParameter(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.marketdata2.model.curves.Curve AbstractCurve.getCloneForParameter(RandomVariable[])"
  })
  public void testGetCloneForParameter() throws CloneNotSupportedException {
    // Arrange
    DiscountCurveFromForwardCurve discountCurveFromForwardCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    // Act and Assert
    assertThrows(
        CloneNotSupportedException.class,
        () ->
            discountCurveFromForwardCurve.getCloneForParameter(
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)}));
  }

  /**
   * Test {@link AbstractCurve#toString()}.
   *
   * <p>Method under test: {@link AbstractCurve#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AbstractCurve.toString()"})
  public void testToString() {
    // Arrange, Act and Assert
    assertEquals(
        "AbstractCurve [name=DiscountCurveFromForwardCurve(Forward Curve Name), referenceDate=null]",
        new DiscountCurveFromForwardCurve("Forward Curve Name").toString());
  }
}
