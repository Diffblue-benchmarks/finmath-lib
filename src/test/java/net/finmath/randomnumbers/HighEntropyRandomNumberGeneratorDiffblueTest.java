package net.finmath.randomnumbers;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.security.SecureRandom;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class HighEntropyRandomNumberGeneratorDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link HighEntropyRandomNumberGenerator#HighEntropyRandomNumberGenerator()}
   *   <li>{@link HighEntropyRandomNumberGenerator#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HighEntropyRandomNumberGenerator.<init>()",
    "void HighEntropyRandomNumberGenerator.<init>(SecureRandom)",
    "java.lang.String HighEntropyRandomNumberGenerator.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals(
        "HighEntropyRandomNumberGenerator [algorithm = NativePRNG]",
        new HighEntropyRandomNumberGenerator().toString());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@link SecureRandom#SecureRandom()}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link HighEntropyRandomNumberGenerator#HighEntropyRandomNumberGenerator(SecureRandom)}
   *   <li>{@link HighEntropyRandomNumberGenerator#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HighEntropyRandomNumberGenerator.<init>()",
    "void HighEntropyRandomNumberGenerator.<init>(SecureRandom)",
    "java.lang.String HighEntropyRandomNumberGenerator.toString()"
  })
  public void testGettersAndSetters_whenSecureRandom() {
    // Arrange, Act and Assert
    assertEquals(
        "HighEntropyRandomNumberGenerator [algorithm = NativePRNG]",
        new HighEntropyRandomNumberGenerator(new SecureRandom()).toString());
  }
}
