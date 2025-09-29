package net.finmath.concurrency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FutureWrapperDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link FutureWrapper#FutureWrapper(Object)}
   *   <li>{@link FutureWrapper#get()}
   *   <li>{@link FutureWrapper#isCancelled()}
   *   <li>{@link FutureWrapper#isDone()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void FutureWrapper.<init>(Object)",
    "Object FutureWrapper.get()",
    "boolean FutureWrapper.isCancelled()",
    "boolean FutureWrapper.isDone()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    FutureWrapper<Object> actualFutureWrapper = new FutureWrapper<>("Object");
    Object actualGetResult = actualFutureWrapper.get();
    boolean actualIsCancelledResult = actualFutureWrapper.isCancelled();
    boolean actualIsDoneResult = actualFutureWrapper.isDone();

    // Assert
    assertEquals("Object", actualFutureWrapper.get());
    assertEquals("Object", actualGetResult);
    assertFalse(actualIsCancelledResult);
    assertTrue(actualFutureWrapper.isDone());
    assertTrue(actualIsDoneResult);
  }

  /**
   * Test {@link FutureWrapper#cancel(boolean)}.
   *
   * <p>Method under test: {@link FutureWrapper#cancel(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FutureWrapper.cancel(boolean)"})
  public void testCancel() {
    // Arrange
    FutureWrapper<Object> futureWrapper = new FutureWrapper<>("Object");

    // Act and Assert
    assertFalse(futureWrapper.cancel(true));
  }

  /**
   * Test {@link FutureWrapper#get(long, TimeUnit)} with {@code long}, {@code TimeUnit}.
   *
   * <p>Method under test: {@link FutureWrapper#get(long, TimeUnit)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object FutureWrapper.get(long, TimeUnit)"})
  public void testGetWithLongTimeUnit() {
    // Arrange
    FutureWrapper<Object> futureWrapper = new FutureWrapper<>("Object");

    // Act and Assert
    assertEquals("Object", futureWrapper.get(1L, TimeUnit.NANOSECONDS));
  }
}
