package net.finmath.rootfinder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class NewtonsMethodDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link NewtonsMethod#NewtonsMethod(double)}
   *   <li>{@link NewtonsMethod#getAccuracy()}
   *   <li>{@link NewtonsMethod#getBestPoint()}
   *   <li>{@link NewtonsMethod#getNextPoint()}
   *   <li>{@link NewtonsMethod#getNumberOfIterations()}
   *   <li>{@link NewtonsMethod#isDone()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NewtonsMethod.<init>(double)",
    "double NewtonsMethod.getAccuracy()",
    "double NewtonsMethod.getBestPoint()",
    "double NewtonsMethod.getNextPoint()",
    "int NewtonsMethod.getNumberOfIterations()",
    "boolean NewtonsMethod.isDone()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    NewtonsMethod actualNewtonsMethod = new NewtonsMethod(10.0d);
    double actualAccuracy = actualNewtonsMethod.getAccuracy();
    double actualBestPoint = actualNewtonsMethod.getBestPoint();
    double actualNextPoint = actualNewtonsMethod.getNextPoint();
    int actualNumberOfIterations = actualNewtonsMethod.getNumberOfIterations();

    // Assert
    assertEquals(0, actualNumberOfIterations);
    assertEquals(10.0d, actualBestPoint, 0.0);
    assertEquals(10.0d, actualNextPoint, 0.0);
    assertFalse(actualNewtonsMethod.isDone());
    assertEquals(Double.MAX_VALUE, actualAccuracy, 0.0);
  }

  /**
   * Test {@link NewtonsMethod#setValueAndDerivative(double, double)}.
   *
   * <p>Method under test: {@link NewtonsMethod#setValueAndDerivative(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NewtonsMethod.setValueAndDerivative(double, double)"})
  public void testSetValueAndDerivative() {
    // Arrange
    SecantMethod secantMethod = new SecantMethod(Double.MAX_VALUE, Double.MAX_VALUE);

    // Act
    secantMethod.setValueAndDerivative(10.0d, 10.0d);

    // Assert
    assertEquals(1, secantMethod.getNumberOfIterations());
    assertEquals(10.0d, secantMethod.getAccuracy(), 0.0);
  }

  /**
   * Test {@link NewtonsMethod#setValueAndDerivative(double, double)}.
   *
   * <p>Method under test: {@link NewtonsMethod#setValueAndDerivative(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NewtonsMethod.setValueAndDerivative(double, double)"})
  public void testSetValueAndDerivative2() {
    // Arrange
    NewtonsMethod newtonsMethod = new NewtonsMethod(10.0d);

    // Act
    newtonsMethod.setValueAndDerivative(Double.MAX_VALUE, 10.0d);

    // Assert
    assertEquals(-1.7976931348623158E307d, newtonsMethod.getNextPoint(), 0.0);
    assertEquals(1, newtonsMethod.getNumberOfIterations());
    assertEquals(Double.MAX_VALUE, newtonsMethod.getAccuracy(), 0.0);
  }

  /**
   * Test {@link NewtonsMethod#setValueAndDerivative(double, double)}.
   *
   * <ul>
   *   <li>Then {@link NewtonsMethod#NewtonsMethod(double)} with guess is ten Accuracy is ten.
   * </ul>
   *
   * <p>Method under test: {@link NewtonsMethod#setValueAndDerivative(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NewtonsMethod.setValueAndDerivative(double, double)"})
  public void testSetValueAndDerivative_thenNewtonsMethodWithGuessIsTenAccuracyIsTen() {
    // Arrange
    NewtonsMethod newtonsMethod = new NewtonsMethod(10.0d);

    // Act
    newtonsMethod.setValueAndDerivative(10.0d, 10.0d);

    // Assert
    assertEquals(1, newtonsMethod.getNumberOfIterations());
    assertEquals(10.0d, newtonsMethod.getAccuracy(), 0.0);
    assertEquals(9.0d, newtonsMethod.getNextPoint(), 0.0);
  }
}
