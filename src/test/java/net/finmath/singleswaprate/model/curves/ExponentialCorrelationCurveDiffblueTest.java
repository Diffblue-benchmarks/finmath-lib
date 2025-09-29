package net.finmath.singleswaprate.model.curves;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.marketdata.model.AnalyticModel;
import net.finmath.marketdata.model.AnalyticModelFromCurvesAndVols;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ExponentialCorrelationCurveDiffblueTest {
  /**
   * Test {@link ExponentialCorrelationCurve#ExponentialCorrelationCurve(String, LocalDate, double,
   * double)}.
   *
   * <p>Method under test: {@link ExponentialCorrelationCurve#ExponentialCorrelationCurve(String,
   * LocalDate, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ExponentialCorrelationCurve.<init>(String, LocalDate, double, double)"})
  public void testNewExponentialCorrelationCurve() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    ExponentialCorrelationCurve actualExponentialCorrelationCurve =
        new ExponentialCorrelationCurve("Name", referenceDate, 10.0d, 10.0d);

    // Assert
    assertEquals("Name", actualExponentialCorrelationCurve.getName());
    assertSame(referenceDate, actualExponentialCorrelationCurve.getReferenceDate());
    assertArrayEquals(
        new double[] {10.0d, 10.0d}, actualExponentialCorrelationCurve.getParameter(), 0.0);
  }

  /**
   * Test {@link ExponentialCorrelationCurve#getValue(AnalyticModel, double)} with {@code model},
   * {@code time}.
   *
   * <p>Method under test: {@link ExponentialCorrelationCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ExponentialCorrelationCurve.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime() {
    // Arrange
    ExponentialCorrelationCurve exponentialCorrelationCurve =
        new ExponentialCorrelationCurve("Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d);

    // Act and Assert
    assertEquals(
        1.0d,
        exponentialCorrelationCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link ExponentialCorrelationCurve#getValue(AnalyticModel, double)} with {@code model},
   * {@code time}.
   *
   * <p>Method under test: {@link ExponentialCorrelationCurve#getValue(AnalyticModel, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double ExponentialCorrelationCurve.getValue(AnalyticModel, double)"})
  public void testGetValueWithModelTime2() {
    // Arrange
    ExponentialCorrelationCurve exponentialCorrelationCurve =
        new ExponentialCorrelationCurve("Name", LocalDate.of(1970, 1, 1), 0.5d, 10.0d);

    // Act and Assert
    assertEquals(
        1.0d,
        exponentialCorrelationCurve.getValue(new AnalyticModelFromCurvesAndVols(), 10.0d),
        0.0);
  }

  /**
   * Test {@link ExponentialCorrelationCurve#getCloneBuilder()}.
   *
   * <p>Method under test: {@link ExponentialCorrelationCurve#getCloneBuilder()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.marketdata.model.curves.CurveBuilder ExponentialCorrelationCurve.getCloneBuilder()"
  })
  public void testGetCloneBuilder() {
    // Arrange
    ExponentialCorrelationCurve exponentialCorrelationCurve =
        new ExponentialCorrelationCurve("Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> exponentialCorrelationCurve.getCloneBuilder());
  }

  /**
   * Test {@link ExponentialCorrelationCurve#getParameter()}.
   *
   * <p>Method under test: {@link ExponentialCorrelationCurve#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] ExponentialCorrelationCurve.getParameter()"})
  public void testGetParameter() {
    // Arrange
    ExponentialCorrelationCurve exponentialCorrelationCurve =
        new ExponentialCorrelationCurve("Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d);

    // Act and Assert
    assertArrayEquals(new double[] {10.0d, 10.0d}, exponentialCorrelationCurve.getParameter(), 0.0);
  }

  /**
   * Test {@link ExponentialCorrelationCurve#setParameter(double[])}.
   *
   * <p>Method under test: {@link ExponentialCorrelationCurve#setParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ExponentialCorrelationCurve.setParameter(double[])"})
  public void testSetParameter() {
    // Arrange
    ExponentialCorrelationCurve exponentialCorrelationCurve =
        new ExponentialCorrelationCurve("Name", LocalDate.of(1970, 1, 1), 10.0d, 10.0d);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> exponentialCorrelationCurve.setParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d}));
  }
}
