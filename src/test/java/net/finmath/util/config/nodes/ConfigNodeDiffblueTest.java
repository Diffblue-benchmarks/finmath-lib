package net.finmath.util.config.nodes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ConfigNodeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ConfigNode#ConfigNode(String, Map)}
   *   <li>{@link ConfigNode#getKey()}
   *   <li>{@link ConfigNode#getValueToConfig()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ConfigNode.<init>(String, Map)",
    "String ConfigNode.getKey()",
    "Map ConfigNode.getValueToConfig()"
  })
  public void testGettersAndSetters() {
    // Arrange
    HashMap<Object, Node> valueToConfig = new HashMap<>();

    // Act
    ConfigNode actualConfigNode = new ConfigNode("Key", valueToConfig);
    String actualKey = actualConfigNode.getKey();
    Map<Object, Node> actualValueToConfig = actualConfigNode.getValueToConfig();

    // Assert
    assertEquals("Key", actualKey);
    assertTrue(actualValueToConfig.isEmpty());
    assertSame(valueToConfig, actualValueToConfig);
  }
}
