package net.finmath.equities.models;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.time.daycount.DayCountConvention;
import net.finmath.time.daycount.DayCountConvention_30E_360;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SviVolatilitySurfaceDiffblueTest {
  /**
   * Test {@link SviVolatilitySurface#SviVolatilitySurface(DayCountConvention, boolean)}.
   *
   * <p>Method under test: {@link SviVolatilitySurface#SviVolatilitySurface(DayCountConvention,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SviVolatilitySurface.<init>(DayCountConvention, boolean)"})
  public void testNewSviVolatilitySurface() {
    // Arrange and Act
    SviVolatilitySurface actualSviVolatilitySurface =
        new SviVolatilitySurface(new DayCountConvention_30E_360(true), true);

    // Assert
    assertEquals(0, actualSviVolatilitySurface.getSmiles().length);
    assertEquals(0.0d, actualSviVolatilitySurface.getShift(), 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SviVolatilitySurface#getShift()}
   *   <li>{@link SviVolatilitySurface#getSmiles()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double SviVolatilitySurface.getShift()",
    "net.finmath.equities.models.SviVolatilitySmile[] SviVolatilitySurface.getSmiles()"
  })
  public void testGettersAndSetters() {
    // Arrange
    SviVolatilitySurface sviVolatilitySurface =
        new SviVolatilitySurface(new DayCountConvention_30E_360(true), true);

    // Act
    double actualShift = sviVolatilitySurface.getShift();

    // Assert
    assertEquals(0, sviVolatilitySurface.getSmiles().length);
    assertEquals(0.0d, actualShift, 0.0);
  }
}
