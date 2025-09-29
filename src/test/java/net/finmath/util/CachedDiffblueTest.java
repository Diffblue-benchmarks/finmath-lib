package net.finmath.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.Function;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class CachedDiffblueTest {
  /**
   * Test {@link Cached#of(Function)}.
   *
   * <ul>
   *   <li>Given {@code Apply}.
   *   <li>When {@link Function} {@link Function#apply(Object)} return {@code Apply}.
   *   <li>Then return apply {@code 42} is {@code Apply}.
   * </ul>
   *
   * <p>Method under test: {@link Cached#of(Function)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Function Cached.of(Function)"})
  public void testOf_givenApply_whenFunctionApplyReturnApply_thenReturnApply42IsApply() {
    // Arrange
    Function<Object, Object> mappingFunction = mock(Function.class);
    when(mappingFunction.apply(Mockito.<Object>any())).thenReturn("Apply");

    // Act
    Function<Object, Object> actualOfResult = Cached.of(mappingFunction);
    Object actualApplyResult = actualOfResult.apply("42");

    // Assert
    verify(mappingFunction).apply(isA(Object.class));
    assertTrue(actualOfResult instanceof Cached);
    assertEquals("Apply", actualApplyResult);
    assertEquals("Apply", actualOfResult.apply("Key"));
  }

  /**
   * Test {@link Cached#of(Function)}.
   *
   * <ul>
   *   <li>When {@link Function}.
   *   <li>Then return apply {@code Key} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link Cached#of(Function)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Function Cached.of(Function)"})
  public void testOf_whenFunction_thenReturnApplyKeyIsNull() {
    // Arrange and Act
    Function<Object, Object> actualOfResult = Cached.of(mock(Function.class));

    // Assert
    assertTrue(actualOfResult instanceof Cached);
    assertNull(actualOfResult.apply("Key"));
  }

  /**
   * Test {@link Cached#apply(Object)}.
   *
   * <p>Method under test: {@link Cached#apply(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Cached.apply(Object)"})
  public void testApply() {
    // Arrange
    Function<Object, Object> mappingFunction = mock(Function.class);
    when(mappingFunction.apply(Mockito.<Object>any())).thenReturn("Apply");
    Cached<Object, Object> cached = new Cached<>(mappingFunction);

    // Act
    Object actualApplyResult = cached.apply("Key");

    // Assert
    verify(mappingFunction).apply(isA(Object.class));
    assertEquals("Apply", actualApplyResult);
  }
}
