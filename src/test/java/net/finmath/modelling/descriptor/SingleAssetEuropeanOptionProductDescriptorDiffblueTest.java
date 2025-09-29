package net.finmath.modelling.descriptor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SingleAssetEuropeanOptionProductDescriptorDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link
   *       SingleAssetEuropeanOptionProductDescriptor#SingleAssetEuropeanOptionProductDescriptor(String,
   *       LocalDate, double)}
   *   <li>{@link SingleAssetEuropeanOptionProductDescriptor#getMaturity()}
   *   <li>{@link SingleAssetEuropeanOptionProductDescriptor#getUnderlyingName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SingleAssetEuropeanOptionProductDescriptor.<init>(String, LocalDate, double)",
    "LocalDate SingleAssetEuropeanOptionProductDescriptor.getMaturity()",
    "String SingleAssetEuropeanOptionProductDescriptor.getUnderlyingName()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDate maturity = LocalDate.of(1970, 1, 1);

    // Act
    SingleAssetEuropeanOptionProductDescriptor actualSingleAssetEuropeanOptionProductDescriptor =
        new SingleAssetEuropeanOptionProductDescriptor("Underlying Name", maturity, 10.0d);
    LocalDate actualMaturity = actualSingleAssetEuropeanOptionProductDescriptor.getMaturity();

    // Assert
    assertEquals("1970-01-01", actualMaturity.toString());
    assertEquals(
        "Underlying Name", actualSingleAssetEuropeanOptionProductDescriptor.getUnderlyingName());
    assertSame(maturity, actualMaturity);
  }

  /**
   * Test {@link SingleAssetEuropeanOptionProductDescriptor#version()}.
   *
   * <p>Method under test: {@link SingleAssetEuropeanOptionProductDescriptor#version()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Integer SingleAssetEuropeanOptionProductDescriptor.version()"})
  public void testVersion() {
    // Arrange
    SingleAssetEuropeanOptionProductDescriptor singleAssetEuropeanOptionProductDescriptor =
        new SingleAssetEuropeanOptionProductDescriptor(
            "Underlying Name", LocalDate.of(1970, 1, 1), 10.0d);

    // Act and Assert
    assertEquals(1, singleAssetEuropeanOptionProductDescriptor.version().intValue());
  }

  /**
   * Test {@link SingleAssetEuropeanOptionProductDescriptor#name()}.
   *
   * <p>Method under test: {@link SingleAssetEuropeanOptionProductDescriptor#name()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SingleAssetEuropeanOptionProductDescriptor.name()"})
  public void testName() {
    // Arrange
    SingleAssetEuropeanOptionProductDescriptor singleAssetEuropeanOptionProductDescriptor =
        new SingleAssetEuropeanOptionProductDescriptor(
            "Underlying Name", LocalDate.of(1970, 1, 1), 10.0d);

    // Act and Assert
    assertEquals("Single asset European option", singleAssetEuropeanOptionProductDescriptor.name());
  }

  /**
   * Test {@link SingleAssetEuropeanOptionProductDescriptor#getStrike()}.
   *
   * <p>Method under test: {@link SingleAssetEuropeanOptionProductDescriptor#getStrike()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Double SingleAssetEuropeanOptionProductDescriptor.getStrike()"})
  public void testGetStrike() {
    // Arrange
    SingleAssetEuropeanOptionProductDescriptor singleAssetEuropeanOptionProductDescriptor =
        new SingleAssetEuropeanOptionProductDescriptor(
            "Underlying Name", LocalDate.of(1970, 1, 1), 10.0d);

    // Act and Assert
    assertEquals(10.0d, singleAssetEuropeanOptionProductDescriptor.getStrike().doubleValue(), 0.0);
  }
}
