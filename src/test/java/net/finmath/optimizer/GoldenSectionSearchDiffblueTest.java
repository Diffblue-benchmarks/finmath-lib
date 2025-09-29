package net.finmath.optimizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class GoldenSectionSearchDiffblueTest {
  /**
   * Test {@link GoldenSectionSearch#GoldenSectionSearch(double, double)}.
   *
   * <p>Method under test: {@link GoldenSectionSearch#GoldenSectionSearch(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GoldenSectionSearch.<init>(double, double)"})
  public void testNewGoldenSectionSearch() {
    // Arrange and Act
    GoldenSectionSearch actualGoldenSectionSearch = new GoldenSectionSearch(10.0d, 10.0d);

    // Assert
    assertEquals(0, actualGoldenSectionSearch.getNumberOfIterations());
    assertEquals(0.0d, actualGoldenSectionSearch.getAccuracy(), 0.0);
    assertEquals(10.0d, actualGoldenSectionSearch.getBestPoint(), 0.0);
    assertEquals(10.0d, actualGoldenSectionSearch.getNextPoint(), 0.0);
    assertFalse(actualGoldenSectionSearch.isDone());
  }

  /**
   * Test {@link GoldenSectionSearch#getBestPoint()}.
   *
   * <p>Method under test: {@link GoldenSectionSearch#getBestPoint()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double GoldenSectionSearch.getBestPoint()"})
  public void testGetBestPoint() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new GoldenSectionSearch(10.0d, 10.0d).getBestPoint(), 0.0);
  }

  /**
   * Test {@link GoldenSectionSearch#setValue(double)}.
   *
   * <p>Method under test: {@link GoldenSectionSearch#setValue(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GoldenSectionSearch.setValue(double)"})
  public void testSetValue() {
    // Arrange, Act and Assert
    assertThrows(
        RuntimeException.class, () -> new GoldenSectionSearch(10.0d, 10.0d).setValue(10.0d));
  }

  /**
   * Test {@link GoldenSectionSearch#optimize()}.
   *
   * <p>Method under test: {@link GoldenSectionSearch#optimize()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"GoldenSectionSearch GoldenSectionSearch.optimize()"})
  public void testOptimize() {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class, () -> new GoldenSectionSearch(10.0d, 10.0d).optimize());
  }

  /**
   * Test {@link GoldenSectionSearch#value(double)}.
   *
   * <p>Method under test: {@link GoldenSectionSearch#value(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double GoldenSectionSearch.value(double)"})
  public void testValue() {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class, () -> new GoldenSectionSearch(10.0d, 10.0d).value(10.0d));
  }

  /**
   * Test {@link GoldenSectionSearch#getGoldenSection(double, double)}.
   *
   * <ul>
   *   <li>When {@code 0.5}.
   *   <li>Then return {@code 6.371322893124002}.
   * </ul>
   *
   * <p>Method under test: {@link GoldenSectionSearch#getGoldenSection(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double GoldenSectionSearch.getGoldenSection(double, double)"})
  public void testGetGoldenSection_when05_thenReturn6371322893124002() {
    // Arrange, Act and Assert
    assertEquals(6.371322893124002d, GoldenSectionSearch.getGoldenSection(0.5d, 10.0d), 0.0);
  }

  /**
   * Test {@link GoldenSectionSearch#getGoldenSection(double, double)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@code 5.798373876248844}.
   * </ul>
   *
   * <p>Method under test: {@link GoldenSectionSearch#getGoldenSection(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double GoldenSectionSearch.getGoldenSection(double, double)"})
  public void testGetGoldenSection_whenMinusOne_thenReturn5798373876248844() {
    // Arrange, Act and Assert
    assertEquals(5.798373876248844d, GoldenSectionSearch.getGoldenSection(-1.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link GoldenSectionSearch#getGoldenSection(double, double)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@code 6.562305898749054}.
   * </ul>
   *
   * <p>Method under test: {@link GoldenSectionSearch#getGoldenSection(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double GoldenSectionSearch.getGoldenSection(double, double)"})
  public void testGetGoldenSection_whenOne_thenReturn6562305898749054() {
    // Arrange, Act and Assert
    assertEquals(6.562305898749054d, GoldenSectionSearch.getGoldenSection(1.0d, 10.0d), 0.0);
  }

  /**
   * Test {@link GoldenSectionSearch#getGoldenSection(double, double)}.
   *
   * <ul>
   *   <li>When ten.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link GoldenSectionSearch#getGoldenSection(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double GoldenSectionSearch.getGoldenSection(double, double)"})
  public void testGetGoldenSection_whenTen_thenReturnTen() {
    // Arrange, Act and Assert
    assertEquals(10.0d, GoldenSectionSearch.getGoldenSection(10.0d, 10.0d), 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link GoldenSectionSearch#getAccuracy()}
   *   <li>{@link GoldenSectionSearch#getNextPoint()}
   *   <li>{@link GoldenSectionSearch#getNumberOfIterations()}
   *   <li>{@link GoldenSectionSearch#isDone()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double GoldenSectionSearch.getAccuracy()",
    "double GoldenSectionSearch.getNextPoint()",
    "int GoldenSectionSearch.getNumberOfIterations()",
    "boolean GoldenSectionSearch.isDone()"
  })
  public void testGettersAndSetters() {
    // Arrange
    GoldenSectionSearch goldenSectionSearch = new GoldenSectionSearch(10.0d, 10.0d);

    // Act
    double actualAccuracy = goldenSectionSearch.getAccuracy();
    double actualNextPoint = goldenSectionSearch.getNextPoint();
    int actualNumberOfIterations = goldenSectionSearch.getNumberOfIterations();

    // Assert
    assertEquals(0, actualNumberOfIterations);
    assertEquals(0.0d, actualAccuracy, 0.0);
    assertEquals(10.0d, actualNextPoint, 0.0);
    assertFalse(goldenSectionSearch.isDone());
  }
}
