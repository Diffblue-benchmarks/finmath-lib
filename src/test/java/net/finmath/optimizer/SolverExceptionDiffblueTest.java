package net.finmath.optimizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SolverExceptionDiffblueTest {
  /**
   * Test {@link SolverException#SolverException(String)}.
   *
   * <ul>
   *   <li>When {@code An error occurred}.
   *   <li>Then return Cause is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SolverException#SolverException(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SolverException.<init>(String)",
    "void SolverException.<init>(String, Throwable)",
    "void SolverException.<init>(Throwable)"
  })
  public void testNewSolverException_whenAnErrorOccurred_thenReturnCauseIsNull() {
    // Arrange and Act
    SolverException actualSolverException = new SolverException("An error occurred");

    // Assert
    assertEquals("An error occurred", actualSolverException.getMessage());
    assertNull(actualSolverException.getCause());
    assertEquals(0, actualSolverException.getSuppressed().length);
  }

  /**
   * Test {@link SolverException#SolverException(String, Throwable)}.
   *
   * <ul>
   *   <li>When {@code An error occurred}.
   *   <li>Then return Message is {@code An error occurred}.
   * </ul>
   *
   * <p>Method under test: {@link SolverException#SolverException(String, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SolverException.<init>(String)",
    "void SolverException.<init>(String, Throwable)",
    "void SolverException.<init>(Throwable)"
  })
  public void testNewSolverException_whenAnErrorOccurred_thenReturnMessageIsAnErrorOccurred() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    SolverException actualSolverException = new SolverException("An error occurred", cause);

    // Assert
    assertEquals("An error occurred", actualSolverException.getMessage());
    assertEquals(0, actualSolverException.getSuppressed().length);
    assertSame(cause, actualSolverException.getCause());
  }

  /**
   * Test {@link SolverException#SolverException(Throwable)}.
   *
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.
   *   <li>Then return Message is {@code Throwable}.
   * </ul>
   *
   * <p>Method under test: {@link SolverException#SolverException(Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SolverException.<init>(String)",
    "void SolverException.<init>(String, Throwable)",
    "void SolverException.<init>(Throwable)"
  })
  public void testNewSolverException_whenThrowable_thenReturnMessageIsJavaLangThrowable() {
    // Arrange
    Throwable cause = new Throwable();

    // Act
    SolverException actualSolverException = new SolverException(cause);

    // Assert
    assertEquals("java.lang.Throwable", actualSolverException.getMessage());
    assertEquals(0, actualSolverException.getSuppressed().length);
    assertSame(cause, actualSolverException.getCause());
  }
}
