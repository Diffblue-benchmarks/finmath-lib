package net.finmath.util.config.nodes;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ValueNodeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ValueNode#ValueNode(Object)}
   *   <li>{@link ValueNode#getValue()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ValueNode.<init>(Object)", "Object ValueNode.getValue()"})
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("Value", new ValueNode("Value").getValue());
  }
}
