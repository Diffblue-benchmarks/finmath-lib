package net.finmath.rootfinder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class BisectionSearchDiffblueTest {
  /**
   * Test {@link BisectionSearch#BisectionSearch(double, double)}.
   *
   * <p>Method under test: {@link BisectionSearch#BisectionSearch(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void BisectionSearch.<init>(double, double)"})
  public void testNewBisectionSearch() {
    // Arrange and Act
    BisectionSearch actualBisectionSearch = new BisectionSearch(10.0d, 10.0d);

    // Assert
    assertEquals(0, actualBisectionSearch.getNumberOfIterations());
    assertEquals(0.0d, actualBisectionSearch.getAccuracy(), 0.0);
    assertEquals(10.0d, actualBisectionSearch.getBestPoint(), 0.0);
    assertEquals(10.0d, actualBisectionSearch.getNextPoint(), 0.0);
    assertFalse(actualBisectionSearch.isDone());
  }

  /**
   * Test {@link BisectionSearch#getBestPoint()}.
   *
   * <p>Method under test: {@link BisectionSearch#getBestPoint()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double BisectionSearch.getBestPoint()"})
  public void testGetBestPoint() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new BisectionSearch(10.0d, 10.0d).getBestPoint(), 0.0);
  }

  /**
   * Test {@link BisectionSearch#setValue(double)}.
   *
   * <p>Method under test: {@link BisectionSearch#setValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void BisectionSearch.setValue(double)"})
  public void testSetValue() {
    // Arrange
    BisectionSearch bisectionSearch = new BisectionSearch(10.0d, 10.0d);

    // Act
    bisectionSearch.setValue(10.0d);

    // Assert
    assertEquals(1, bisectionSearch.getNumberOfIterations());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link BisectionSearch#getAccuracy()}
   *   <li>{@link BisectionSearch#getNextPoint()}
   *   <li>{@link BisectionSearch#getNumberOfIterations()}
   *   <li>{@link BisectionSearch#isDone()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double BisectionSearch.getAccuracy()",
    "double BisectionSearch.getNextPoint()",
    "int BisectionSearch.getNumberOfIterations()",
    "boolean BisectionSearch.isDone()"
  })
  public void testGettersAndSetters() {
    // Arrange
    BisectionSearch bisectionSearch = new BisectionSearch(10.0d, 10.0d);

    // Act
    double actualAccuracy = bisectionSearch.getAccuracy();
    double actualNextPoint = bisectionSearch.getNextPoint();
    int actualNumberOfIterations = bisectionSearch.getNumberOfIterations();

    // Assert
    assertEquals(0, actualNumberOfIterations);
    assertEquals(0.0d, actualAccuracy, 0.0);
    assertEquals(10.0d, actualNextPoint, 0.0);
    assertFalse(bisectionSearch.isDone());
  }
}
