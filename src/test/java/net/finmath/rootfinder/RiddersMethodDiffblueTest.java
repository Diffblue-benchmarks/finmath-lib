package net.finmath.rootfinder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RiddersMethodDiffblueTest {
  /**
   * Test {@link RiddersMethod#RiddersMethod(double, double)}.
   *
   * <p>Method under test: {@link RiddersMethod#RiddersMethod(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RiddersMethod.<init>(double, double)"})
  public void testNewRiddersMethod() {
    // Arrange and Act
    RiddersMethod actualRiddersMethod = new RiddersMethod(10.0d, 10.0d);

    // Assert
    assertEquals(0, actualRiddersMethod.getNumberOfIterations());
    assertEquals(0.0d, actualRiddersMethod.getAccuracy(), 0.0);
    assertEquals(10.0d, actualRiddersMethod.getBestPoint(), 0.0);
    assertEquals(10.0d, actualRiddersMethod.getNextPoint(), 0.0);
    assertFalse(actualRiddersMethod.isDone());
  }

  /**
   * Test {@link RiddersMethod#setValue(double)}.
   *
   * <p>Method under test: {@link RiddersMethod#setValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RiddersMethod.setValue(double)"})
  public void testSetValue() {
    // Arrange
    RiddersMethod riddersMethod = new RiddersMethod(10.0d, 10.0d);

    // Act
    riddersMethod.setValue(10.0d);

    // Assert
    assertEquals(1, riddersMethod.getNumberOfIterations());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RiddersMethod#getAccuracy()}
   *   <li>{@link RiddersMethod#getBestPoint()}
   *   <li>{@link RiddersMethod#getNextPoint()}
   *   <li>{@link RiddersMethod#getNumberOfIterations()}
   *   <li>{@link RiddersMethod#isDone()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double RiddersMethod.getAccuracy()",
    "double RiddersMethod.getBestPoint()",
    "double RiddersMethod.getNextPoint()",
    "int RiddersMethod.getNumberOfIterations()",
    "boolean RiddersMethod.isDone()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RiddersMethod riddersMethod = new RiddersMethod(10.0d, 10.0d);

    // Act
    double actualAccuracy = riddersMethod.getAccuracy();
    double actualBestPoint = riddersMethod.getBestPoint();
    double actualNextPoint = riddersMethod.getNextPoint();
    int actualNumberOfIterations = riddersMethod.getNumberOfIterations();

    // Assert
    assertEquals(0, actualNumberOfIterations);
    assertEquals(0.0d, actualAccuracy, 0.0);
    assertEquals(10.0d, actualBestPoint, 0.0);
    assertEquals(10.0d, actualNextPoint, 0.0);
    assertFalse(riddersMethod.isDone());
  }
}
