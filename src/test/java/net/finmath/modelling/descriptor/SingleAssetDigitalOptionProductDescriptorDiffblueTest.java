package net.finmath.modelling.descriptor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SingleAssetDigitalOptionProductDescriptorDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link
   *       SingleAssetDigitalOptionProductDescriptor#SingleAssetDigitalOptionProductDescriptor(String,
   *       LocalDate, double)}
   *   <li>{@link SingleAssetDigitalOptionProductDescriptor#getMaturity()}
   *   <li>{@link SingleAssetDigitalOptionProductDescriptor#getNameOfUnderlying()}
   *   <li>{@link SingleAssetDigitalOptionProductDescriptor#getStrike()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SingleAssetDigitalOptionProductDescriptor.<init>(String, LocalDate, double)",
    "LocalDate SingleAssetDigitalOptionProductDescriptor.getMaturity()",
    "String SingleAssetDigitalOptionProductDescriptor.getNameOfUnderlying()",
    "double SingleAssetDigitalOptionProductDescriptor.getStrike()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDate maturity = LocalDate.of(1970, 1, 1);

    // Act
    SingleAssetDigitalOptionProductDescriptor actualSingleAssetDigitalOptionProductDescriptor =
        new SingleAssetDigitalOptionProductDescriptor("Name Of Underlying", maturity, 10.0d);
    LocalDate actualMaturity = actualSingleAssetDigitalOptionProductDescriptor.getMaturity();
    String actualNameOfUnderlying =
        actualSingleAssetDigitalOptionProductDescriptor.getNameOfUnderlying();

    // Assert
    assertEquals("1970-01-01", actualMaturity.toString());
    assertEquals("Name Of Underlying", actualNameOfUnderlying);
    assertEquals(10.0d, actualSingleAssetDigitalOptionProductDescriptor.getStrike(), 0.0);
    assertSame(maturity, actualMaturity);
  }

  /**
   * Test {@link SingleAssetDigitalOptionProductDescriptor#version()}.
   *
   * <p>Method under test: {@link SingleAssetDigitalOptionProductDescriptor#version()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Integer SingleAssetDigitalOptionProductDescriptor.version()"})
  public void testVersion() {
    // Arrange
    SingleAssetDigitalOptionProductDescriptor singleAssetDigitalOptionProductDescriptor =
        new SingleAssetDigitalOptionProductDescriptor(
            "Name Of Underlying", LocalDate.of(1970, 1, 1), 10.0d);

    // Act and Assert
    assertEquals(1, singleAssetDigitalOptionProductDescriptor.version().intValue());
  }

  /**
   * Test {@link SingleAssetDigitalOptionProductDescriptor#name()}.
   *
   * <p>Method under test: {@link SingleAssetDigitalOptionProductDescriptor#name()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SingleAssetDigitalOptionProductDescriptor.name()"})
  public void testName() {
    // Arrange
    SingleAssetDigitalOptionProductDescriptor singleAssetDigitalOptionProductDescriptor =
        new SingleAssetDigitalOptionProductDescriptor(
            "Name Of Underlying", LocalDate.of(1970, 1, 1), 10.0d);

    // Act and Assert
    assertEquals("Single asset Digital option", singleAssetDigitalOptionProductDescriptor.name());
  }
}
