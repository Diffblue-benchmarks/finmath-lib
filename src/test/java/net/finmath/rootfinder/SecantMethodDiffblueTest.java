package net.finmath.rootfinder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SecantMethodDiffblueTest {
  /**
   * Test {@link SecantMethod#SecantMethod(double, double)}.
   *
   * <p>Method under test: {@link SecantMethod#SecantMethod(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SecantMethod.<init>(double, double)"})
  public void testNewSecantMethod() {
    // Arrange and Act
    SecantMethod actualSecantMethod = new SecantMethod(10.0d, 10.0d);

    // Assert
    assertEquals(0, actualSecantMethod.getNumberOfIterations());
    assertEquals(10.0d, actualSecantMethod.getBestPoint(), 0.0);
    assertEquals(10.0d, actualSecantMethod.getNextPoint(), 0.0);
    assertFalse(actualSecantMethod.isDone());
    assertEquals(Double.MAX_VALUE, actualSecantMethod.getAccuracy(), 0.0);
  }

  /**
   * Test {@link SecantMethod#getNextPoint()}.
   *
   * <p>Method under test: {@link SecantMethod#getNextPoint()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double SecantMethod.getNextPoint()"})
  public void testGetNextPoint() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new SecantMethod(10.0d, 10.0d).getNextPoint(), 0.0);
  }

  /**
   * Test {@link SecantMethod#setValue(double)}.
   *
   * <p>Method under test: {@link SecantMethod#setValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SecantMethod.setValue(double)"})
  public void testSetValue() {
    // Arrange
    SecantMethod secantMethod = new SecantMethod(10.0d, 10.0d);

    // Act
    secantMethod.setValue(Double.MAX_VALUE);

    // Assert
    assertEquals(0.0d, secantMethod.getNextPoint(), 0.0);
    assertEquals(1, secantMethod.getNumberOfIterations());
    assertEquals(Double.MAX_VALUE, secantMethod.getAccuracy(), 0.0);
  }

  /**
   * Test {@link SecantMethod#setValue(double)}.
   *
   * <ul>
   *   <li>Then {@link SecantMethod#SecantMethod(double, double)} with firstGuess is ten and
   *       secondGuess is ten Accuracy is ten.
   * </ul>
   *
   * <p>Method under test: {@link SecantMethod#setValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SecantMethod.setValue(double)"})
  public void testSetValue_thenSecantMethodWithFirstGuessIsTenAndSecondGuessIsTenAccuracyIsTen() {
    // Arrange
    SecantMethod secantMethod = new SecantMethod(10.0d, 10.0d);

    // Act
    secantMethod.setValue(10.0d);

    // Assert
    assertEquals(0.0d, secantMethod.getNextPoint(), 0.0);
    assertEquals(1, secantMethod.getNumberOfIterations());
    assertEquals(10.0d, secantMethod.getAccuracy(), 0.0);
  }

  /**
   * Test {@link SecantMethod#setValueAndDerivative(double, double)}.
   *
   * <p>Method under test: {@link SecantMethod#setValueAndDerivative(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SecantMethod.setValueAndDerivative(double, double)"})
  public void testSetValueAndDerivative() {
    // Arrange
    SecantMethod secantMethod = new SecantMethod(10.0d, 10.0d);

    // Act
    secantMethod.setValueAndDerivative(10.0d, 10.0d);

    // Assert
    assertEquals(1, secantMethod.getNumberOfIterations());
    assertEquals(10.0d, secantMethod.getAccuracy(), 0.0);
    assertEquals(9.0d, secantMethod.getNextPoint(), 0.0);
  }

  /**
   * Test {@link SecantMethod#setValueAndDerivative(double, double)}.
   *
   * <p>Method under test: {@link SecantMethod#setValueAndDerivative(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SecantMethod.setValueAndDerivative(double, double)"})
  public void testSetValueAndDerivative2() {
    // Arrange
    SecantMethod secantMethod = new SecantMethod(10.0d, 10.0d);

    // Act
    secantMethod.setValueAndDerivative(Double.MAX_VALUE, 10.0d);

    // Assert
    assertEquals(-1.7976931348623158E307d, secantMethod.getNextPoint(), 0.0);
    assertEquals(1, secantMethod.getNumberOfIterations());
    assertEquals(Double.MAX_VALUE, secantMethod.getAccuracy(), 0.0);
  }
}
