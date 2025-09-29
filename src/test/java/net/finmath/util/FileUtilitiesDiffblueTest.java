package net.finmath.util;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.io.IOException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FileUtilitiesDiffblueTest {
  /**
   * Test {@link FileUtilities#loadObject(File)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link FileUtilities#loadObject(File)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Object FileUtilities.loadObject(File)"})
  public void testLoadObject_whenNull_thenReturnNull() throws IOException, ClassNotFoundException {
    // Arrange, Act and Assert
    assertNull(FileUtilities.loadObject(null));
  }
}
