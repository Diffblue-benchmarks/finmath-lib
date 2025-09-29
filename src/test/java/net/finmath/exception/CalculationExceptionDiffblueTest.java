package net.finmath.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CalculationExceptionDiffblueTest {
  /**
   * Test {@link CalculationException#CalculationException(String)}.
   *
   * <ul>
   *   <li>Then return Message is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link CalculationException#CalculationException(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalculationException.<init>()",
    "void CalculationException.<init>(String)",
    "void CalculationException.<init>(String, Throwable)",
    "void CalculationException.<init>(Throwable)"
  })
  public void testNewCalculationException_thenReturnMessageIsAnErrorOccurred() {
    // Arrange and Act
    CalculationException actualCalculationException = new CalculationException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualCalculationException.getMessage());
    assertNull(actualCalculationException.getCause());
    assertEquals(0, actualCalculationException.getSuppressed().length);
  }

  /**
   * Test {@link CalculationException#CalculationException(String, Throwable)}.
   *
   * <ul>
   *   <li>Then return Message is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link CalculationException#CalculationException(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalculationException.<init>()",
    "void CalculationException.<init>(String)",
    "void CalculationException.<init>(String, Throwable)",
    "void CalculationException.<init>(Throwable)"
  })
  public void testNewCalculationException_thenReturnMessageIsAnErrorOccurred2() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    CalculationException actualCalculationException =
        new CalculationException("An error occurred", cause);

    // Assert
    assertEquals("An error occurred", actualCalculationException.getMessage());
    assertEquals(0, actualCalculationException.getSuppressed().length);
    assertSame(cause, actualCalculationException.getCause());
  }

  /**
   * Test {@link CalculationException#CalculationException()}.
   *
   * <ul>
   *   <li>Then return Message is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CalculationException#CalculationException()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalculationException.<init>()",
    "void CalculationException.<init>(String)",
    "void CalculationException.<init>(String, Throwable)",
    "void CalculationException.<init>(Throwable)"
  })
  public void testNewCalculationException_thenReturnMessageIsNull() {
    // Arrange and Act
    CalculationException actualCalculationException = new CalculationException();

    // Assert
    assertNull(actualCalculationException.getMessage());
    assertNull(actualCalculationException.getCause());
    assertEquals(0, actualCalculationException.getSuppressed().length);
  }

  /**
   * Test {@link CalculationException#CalculationException(Throwable)}.
   *
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.
   *   <li>Then return Message is {@code Throwable}.
   * </ul>
   *
   * <p>Method under test: {@link CalculationException#CalculationException(Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalculationException.<init>()",
    "void CalculationException.<init>(String)",
    "void CalculationException.<init>(String, Throwable)",
    "void CalculationException.<init>(Throwable)"
  })
  public void testNewCalculationException_whenThrowable_thenReturnMessageIsJavaLangThrowable() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    CalculationException actualCalculationException = new CalculationException(cause);

    // Assert
    assertEquals("java.lang.Throwable", actualCalculationException.getMessage());
    assertEquals(0, actualCalculationException.getSuppressed().length);
    assertSame(cause, actualCalculationException.getCause());
  }
}
