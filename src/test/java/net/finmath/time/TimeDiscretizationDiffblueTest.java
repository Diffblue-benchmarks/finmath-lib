package net.finmath.time;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.function.DoublePredicate;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TimeDiscretizationDiffblueTest {
  /**
   * Test {@link TimeDiscretization#getFirstTime()}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretization#getFirstTime()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double TimeDiscretization.getFirstTime()"})
  public void testGetFirstTime_thenReturnTen() {
    // Arrange, Act and Assert
    assertEquals(10.0d, new TenorFromArray(10.0d, 10, 0.5d).getFirstTime(), 0.0);
  }

  /**
   * Test {@link TimeDiscretization#getLastTime()}.
   *
   * <ul>
   *   <li>Then return fifteen.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretization#getLastTime()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double TimeDiscretization.getLastTime()"})
  public void testGetLastTime_thenReturnFifteen() {
    // Arrange, Act and Assert
    assertEquals(15.0d, new TenorFromArray(10.0d, 10, 0.5d).getLastTime(), 0.0);
  }

  /**
   * Test {@link TimeDiscretization#filter(DoublePredicate)}.
   *
   * <ul>
   *   <li>Then return NumberOfTimeSteps is minus one.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretization#filter(DoublePredicate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TimeDiscretization TimeDiscretization.filter(DoublePredicate)"})
  public void testFilter_thenReturnNumberOfTimeStepsIsMinusOne() {
    // Arrange and Act
    TimeDiscretization actualFilterResult =
        new TenorFromArray(10.0d, -1, 0.5d).filter(mock(DoublePredicate.class));

    // Assert
    assertTrue(actualFilterResult instanceof TimeDiscretizationFromArray);
    assertEquals(-1, actualFilterResult.getNumberOfTimeSteps());
    assertEquals(0, actualFilterResult.getNumberOfTimes());
    assertFalse(actualFilterResult.iterator().hasNext());
    assertTrue(actualFilterResult.getAsArrayList().isEmpty());
    assertArrayEquals(new double[] {}, actualFilterResult.getAsDoubleArray(), 0.0);
  }

  /**
   * Test {@link TimeDiscretization#filter(DoublePredicate)}.
   *
   * <ul>
   *   <li>Then return NumberOfTimeSteps is ten.
   * </ul>
   *
   * <p>Method under test: {@link TimeDiscretization#filter(DoublePredicate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TimeDiscretization TimeDiscretization.filter(DoublePredicate)"})
  public void testFilter_thenReturnNumberOfTimeStepsIsTen() {
    // Arrange
    TenorFromArray tenorFromArray = new TenorFromArray(10.0d, 10, 0.5d);

    DoublePredicate timesToKeep = mock(DoublePredicate.class);
    when(timesToKeep.test(anyDouble())).thenReturn(true);

    // Act
    TimeDiscretization actualFilterResult = tenorFromArray.filter(timesToKeep);

    // Assert
    verify(timesToKeep, atLeast(1)).test(anyDouble());
    assertTrue(actualFilterResult instanceof TimeDiscretizationFromArray);
    assertEquals(10, actualFilterResult.getNumberOfTimeSteps());
    assertEquals(10.0d, actualFilterResult.getFirstTime(), 0.0);
    assertEquals(11, actualFilterResult.getAsArrayList().size());
    assertEquals(11, actualFilterResult.getNumberOfTimes());
    assertEquals(15.0d, actualFilterResult.getLastTime(), 0.0);
    assertTrue(actualFilterResult.iterator().hasNext());
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        actualFilterResult.getAsDoubleArray(),
        0.0);
  }
}
