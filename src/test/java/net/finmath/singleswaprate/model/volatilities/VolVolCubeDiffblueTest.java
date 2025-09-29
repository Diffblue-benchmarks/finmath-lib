package net.finmath.singleswaprate.model.volatilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.marketdata.model.volatilities.VolatilitySurface;
import net.finmath.marketdata.model.volatilities.VolatilitySurface.QuotingConvention;
import net.finmath.singleswaprate.model.VolatilityCubeModel;
import net.finmath.time.RegularSchedule;
import net.finmath.time.Schedule;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class VolVolCubeDiffblueTest {
  /**
   * Test {@link VolVolCube#VolVolCube(String, LocalDate, String, Schedule, double[])}.
   *
   * <ul>
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link VolVolCube#VolVolCube(String, LocalDate, String, Schedule,
   * double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void VolVolCube.<init>(String, LocalDate, String, Schedule, double[])"})
  public void testNewVolVolCube_thenReturnName() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);

    // Act
    VolVolCube actualVolVolCube =
        new VolVolCube(
            "Name",
            referenceDate,
            "Reference Cube Name",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    assertEquals("Name", actualVolVolCube.getName());
    assertEquals("Reference Cube Name", actualVolVolCube.getReferenceCubeName());
    assertSame(referenceDate, actualVolVolCube.getReferenceDate());
  }

  /**
   * Test {@link VolVolCube#getValue(VolatilityCubeModel, double, double, double,
   * QuotingConvention)} with {@code model}, {@code termination}, {@code maturity}, {@code strike},
   * {@code quotingConvention}.
   *
   * <p>Method under test: {@link VolVolCube#getValue(VolatilityCubeModel, double, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double VolVolCube.getValue(VolatilityCubeModel, double, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelTerminationMaturityStrikeQuotingConvention() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    VolVolCube volVolCube =
        new VolVolCube(
            "Name",
            referenceDate,
            "Reference Cube Name",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);

    // Act
    double actualValue =
        volVolCube.getValue(model, 10.0d, 10.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL);

    // Assert
    verify(model).getVolatilityCube("Reference Cube Name");
    assertEquals(0.8333333333333333d, actualValue, 0.0);
  }

  /**
   * Test {@link VolVolCube#getValue(VolatilityCubeModel, double, double, double,
   * QuotingConvention)} with {@code model}, {@code termination}, {@code maturity}, {@code strike},
   * {@code quotingConvention}.
   *
   * <p>Method under test: {@link VolVolCube#getValue(VolatilityCubeModel, double, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double VolVolCube.getValue(VolatilityCubeModel, double, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelTerminationMaturityStrikeQuotingConvention2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    VolVolCube volVolCube =
        new VolVolCube(
            "Name",
            referenceDate,
            "Reference Cube Name",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    LocalDate referenceDate2 = LocalDate.of(1970, 1, 1);
    VolVolCube volVolCube2 =
        new VolVolCube(
            "Name",
            referenceDate2,
            "Reference Cube Name",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(volVolCube2);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            volVolCube.getValue(model, 10.0d, 10.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL));
    verify(model).getVolatilityCube("Reference Cube Name");
  }

  /**
   * Test {@link VolVolCube#getValue(VolatilityCubeModel, double, double, double,
   * QuotingConvention)} with {@code model}, {@code termination}, {@code maturity}, {@code strike},
   * {@code quotingConvention}.
   *
   * <p>Method under test: {@link VolVolCube#getValue(VolatilityCubeModel, double, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double VolVolCube.getValue(VolatilityCubeModel, double, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelTerminationMaturityStrikeQuotingConvention3() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    VolVolCube volVolCube =
        new VolVolCube(
            "Name",
            referenceDate,
            "Reference Cube Name",
            new RegularSchedule(new TenorFromArray(15.0d, 10, 0.5d)),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);

    // Act
    double actualValue =
        volVolCube.getValue(model, 10.0d, 10.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL);

    // Assert
    verify(model).getVolatilityCube("Reference Cube Name");
    assertEquals(0.09345794392523364d, actualValue, 0.0);
  }

  /**
   * Test {@link VolVolCube#getValue(VolatilityCubeModel, double, double, double,
   * QuotingConvention)} with {@code model}, {@code termination}, {@code maturity}, {@code strike},
   * {@code quotingConvention}.
   *
   * <p>Method under test: {@link VolVolCube#getValue(VolatilityCubeModel, double, double, double,
   * QuotingConvention)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double VolVolCube.getValue(VolatilityCubeModel, double, double, double, QuotingConvention)"
  })
  public void testGetValueWithModelTerminationMaturityStrikeQuotingConvention4() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    VolVolCube volVolCube =
        new VolVolCube(
            "Name",
            referenceDate,
            "Reference Cube Name",
            new RegularSchedule(new TenorFromArray(1.0d, 10, 0.5d)),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);

    // Act
    double actualValue =
        volVolCube.getValue(model, 10.0d, 10.0d, 10.0d, QuotingConvention.VOLATILITYLOGNORMAL);

    // Assert
    verify(model).getVolatilityCube("Reference Cube Name");
    assertEquals(-1.4285714285714284d, actualValue, 0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link VolVolCube#toString()}
   *   <li>{@link VolVolCube#getName()}
   *   <li>{@link VolVolCube#getReferenceCubeName()}
   *   <li>{@link VolVolCube#getReferenceDate()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String VolVolCube.getName()",
    "String VolVolCube.getReferenceCubeName()",
    "LocalDate VolVolCube.getReferenceDate()",
    "String VolVolCube.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    VolVolCube volVolCube =
        new VolVolCube(
            "Name",
            referenceDate,
            "Reference Cube Name",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act
    volVolCube.toString();
    String actualName = volVolCube.getName();
    String actualReferenceCubeName = volVolCube.getReferenceCubeName();
    LocalDate actualReferenceDate = volVolCube.getReferenceDate();

    // Assert
    assertEquals("1970-01-01", actualReferenceDate.toString());
    assertEquals("Name", actualName);
    assertEquals("Reference Cube Name", actualReferenceCubeName);
    assertSame(referenceDate, actualReferenceDate);
  }

  /**
   * Test {@link VolVolCube#getCorrelationDecay()}.
   *
   * <p>Method under test: {@link VolVolCube#getCorrelationDecay()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double VolVolCube.getCorrelationDecay()"})
  public void testGetCorrelationDecay() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    VolVolCube volVolCube =
        new VolVolCube(
            "Name",
            referenceDate,
            "Reference Cube Name",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> volVolCube.getCorrelationDecay());
  }

  /**
   * Test {@link VolVolCube#getParameters()}.
   *
   * <p>Method under test: {@link VolVolCube#getParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Map VolVolCube.getParameters()"})
  public void testGetParameters() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    VolVolCube volVolCube =
        new VolVolCube(
            "Name",
            referenceDate,
            "Reference Cube Name",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> volVolCube.getParameters());
  }

  /**
   * Test {@link VolVolCube#getLowestStrike(VolatilityCubeModel)}.
   *
   * <ul>
   *   <li>Then return {@link Double#NEGATIVE_INFINITY}.
   * </ul>
   *
   * <p>Method under test: {@link VolVolCube#getLowestStrike(VolatilityCubeModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double VolVolCube.getLowestStrike(VolatilityCubeModel)"})
  public void testGetLowestStrike_thenReturnNegative_infinity() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    VolVolCube volVolCube =
        new VolVolCube(
            "Name",
            referenceDate,
            "Reference Cube Name",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    VolatilityCubeModel model = mock(VolatilityCubeModel.class);
    StaticVolatilityCube staticVolatilityCube =
        new StaticVolatilityCube("Name", LocalDate.of(1970, 1, 1), 10.0d);
    when(model.getVolatilityCube(Mockito.<String>any())).thenReturn(staticVolatilityCube);

    // Act
    double actualLowestStrike = volVolCube.getLowestStrike(model);

    // Assert
    verify(model).getVolatilityCube("Reference Cube Name");
    assertEquals(Double.NEGATIVE_INFINITY, actualLowestStrike, 0.0);
  }

  /**
   * Test {@link VolVolCube#getIborOisDecorrelation()}.
   *
   * <p>Method under test: {@link VolVolCube#getIborOisDecorrelation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double VolVolCube.getIborOisDecorrelation()"})
  public void testGetIborOisDecorrelation() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    VolVolCube volVolCube =
        new VolVolCube(
            "Name",
            referenceDate,
            "Reference Cube Name",
            new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)),
            new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> volVolCube.getIborOisDecorrelation());
  }
}
