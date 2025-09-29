package net.finmath.information;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LibraryDiffblueTest {
  /**
   * Test {@link Library#getVersionString()}.
   *
   * <p>Method under test: {@link Library#getVersionString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String Library.getVersionString()"})
  public void testGetVersionString() {
    // Arrange, Act and Assert
    assertEquals("6.0.21-SNAPSHOT", Library.getVersionString());
  }

  /**
   * Test {@link Library#getBuildString()}.
   *
   * <p>Method under test: {@link Library#getBuildString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String Library.getBuildString()"})
  public void testGetBuildString() {
    // Arrange, Act and Assert
    assertEquals("7aa982ebda17678496514e95d429c67a06dc364c", Library.getBuildString());
  }
}
