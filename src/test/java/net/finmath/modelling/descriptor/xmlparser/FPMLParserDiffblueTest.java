package net.finmath.modelling.descriptor.xmlparser;

import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import javax.imageio.metadata.IIOMetadataNode;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.w3c.dom.Node;

public class FPMLParserDiffblueTest {
  /**
   * Test {@link FPMLParser#getProductDescriptor(Node)} with {@code node}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link FPMLParser#getProductDescriptor(Node)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.modelling.ProductDescriptor FPMLParser.getProductDescriptor(Node)"
  })
  public void testGetProductDescriptorWithNode_thenThrowIllegalArgumentException() {
    // Arrange
    FPMLParser fpmlParser = new FPMLParser("42", "3");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> fpmlParser.getProductDescriptor(new IIOMetadataNode("fpmlVersion")));
  }
}
