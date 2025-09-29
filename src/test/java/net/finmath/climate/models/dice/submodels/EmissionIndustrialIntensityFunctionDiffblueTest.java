package net.finmath.climate.models.dice.submodels;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class EmissionIndustrialIntensityFunctionDiffblueTest {
  /**
   * Test {@link
   * EmissionIndustrialIntensityFunction#EmissionIndustrialIntensityFunction(TimeDiscretization)}.
   *
   * <p>Method under test: {@link
   * EmissionIndustrialIntensityFunction#EmissionIndustrialIntensityFunction(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EmissionIndustrialIntensityFunction.<init>(TimeDiscretization)"})
  public void testNewEmissionIndustrialIntensityFunction() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    EmissionIndustrialIntensityFunction actualEmissionIndustrialIntensityFunction =
        new EmissionIndustrialIntensityFunction(timeDiscretization);

    // Assert
    assertEquals(
        0.0010005003335835344d,
        actualEmissionIndustrialIntensityFunction.getEmissionIntensityRateDecay(),
        0.0);
    assertEquals(
        0.0152d, actualEmissionIndustrialIntensityFunction.getEmissionIntensityRateInitial(), 0.0);
    assertEquals(
        0.33981042654028437d,
        actualEmissionIndustrialIntensityFunction.getEmissionIntensityInitial(),
        0.0);
    assertSame(
        timeDiscretization, actualEmissionIndustrialIntensityFunction.getTimeDiscretization());
  }

  /**
   * Test {@link
   * EmissionIndustrialIntensityFunction#EmissionIndustrialIntensityFunction(TimeDiscretization,
   * double, double, double)}.
   *
   * <p>Method under test: {@link
   * EmissionIndustrialIntensityFunction#EmissionIndustrialIntensityFunction(TimeDiscretization,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void EmissionIndustrialIntensityFunction.<init>(TimeDiscretization, double, double, double)"
  })
  public void testNewEmissionIndustrialIntensityFunction2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    EmissionIndustrialIntensityFunction actualEmissionIndustrialIntensityFunction =
        new EmissionIndustrialIntensityFunction(timeDiscretization, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(
        10.0d, actualEmissionIndustrialIntensityFunction.getEmissionIntensityInitial(), 0.0);
    assertEquals(
        10.0d, actualEmissionIndustrialIntensityFunction.getEmissionIntensityRateDecay(), 0.0);
    assertEquals(
        10.0d, actualEmissionIndustrialIntensityFunction.getEmissionIntensityRateInitial(), 0.0);
    assertSame(
        timeDiscretization, actualEmissionIndustrialIntensityFunction.getTimeDiscretization());
  }

  /**
   * Test {@link EmissionIndustrialIntensityFunction#apply(Integer, Double)} with {@code Integer},
   * {@code Double}.
   *
   * <ul>
   *   <li>When {@code 0.0152}.
   *   <li>Then return doubleValue is {@code 0.2899252944499057}.
   * </ul>
   *
   * <p>Method under test: {@link EmissionIndustrialIntensityFunction#apply(Integer, Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double EmissionIndustrialIntensityFunction.apply(Integer, Double)"})
  public void testApplyWithIntegerDouble_when00152_thenReturnDoubleValueIs02899252944499057() {
    // Arrange, Act and Assert
    assertEquals(
        0.2899252944499057d,
        new EmissionIndustrialIntensityFunction(new TenorFromArray(10.0d, 10, 0.5d))
            .apply(1, 0.0152d)
            .doubleValue(),
        0.0);
  }

  /**
   * Test {@link EmissionIndustrialIntensityFunction#apply(Integer, Double)} with {@code Integer},
   * {@code Double}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return doubleValue is {@code 0.2899252944499057}.
   * </ul>
   *
   * <p>Method under test: {@link EmissionIndustrialIntensityFunction#apply(Integer, Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double EmissionIndustrialIntensityFunction.apply(Integer, Double)"})
  public void testApplyWithIntegerDouble_whenOne_thenReturnDoubleValueIs02899252944499057() {
    // Arrange, Act and Assert
    assertEquals(
        0.2899252944499057d,
        new EmissionIndustrialIntensityFunction(new TenorFromArray(10.0d, 10, 0.5d))
            .apply(1, 10.0d)
            .doubleValue(),
        0.0);
  }

  /**
   * Test {@link EmissionIndustrialIntensityFunction#apply(Integer, Double)} with {@code Integer},
   * {@code Double}.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return doubleValue is {@code 0.2855992631129509}.
   * </ul>
   *
   * <p>Method under test: {@link EmissionIndustrialIntensityFunction#apply(Integer, Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double EmissionIndustrialIntensityFunction.apply(Integer, Double)"})
  public void testApplyWithIntegerDouble_whenThree_thenReturnDoubleValueIs02855992631129509() {
    // Arrange, Act and Assert
    assertEquals(
        0.2855992631129509d,
        new EmissionIndustrialIntensityFunction(new TenorFromArray(10.0d, 10, 0.5d))
            .apply(3, 10.0d)
            .doubleValue(),
        0.0);
  }

  /**
   * Test {@link EmissionIndustrialIntensityFunction#apply(Integer, Double)} with {@code Integer},
   * {@code Double}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return doubleValue is {@code 0.2921144693661421}.
   * </ul>
   *
   * <p>Method under test: {@link EmissionIndustrialIntensityFunction#apply(Integer, Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double EmissionIndustrialIntensityFunction.apply(Integer, Double)"})
  public void testApplyWithIntegerDouble_whenZero_thenReturnDoubleValueIs02921144693661421() {
    // Arrange, Act and Assert
    assertEquals(
        0.2921144693661421d,
        new EmissionIndustrialIntensityFunction(new TenorFromArray(10.0d, 10, 0.5d))
            .apply(0, 10.0d)
            .doubleValue(),
        0.0);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link EmissionIndustrialIntensityFunction#getEmissionIntensityInitial()}
   *   <li>{@link EmissionIndustrialIntensityFunction#getEmissionIntensityRateDecay()}
   *   <li>{@link EmissionIndustrialIntensityFunction#getEmissionIntensityRateInitial()}
   *   <li>{@link EmissionIndustrialIntensityFunction#getTimeDiscretization()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double EmissionIndustrialIntensityFunction.getEmissionIntensityInitial()",
    "double EmissionIndustrialIntensityFunction.getEmissionIntensityRateDecay()",
    "double EmissionIndustrialIntensityFunction.getEmissionIntensityRateInitial()",
    "TimeDiscretization EmissionIndustrialIntensityFunction.getTimeDiscretization()"
  })
  public void testGettersAndSetters() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    EmissionIndustrialIntensityFunction emissionIndustrialIntensityFunction =
        new EmissionIndustrialIntensityFunction(timeDiscretization);

    // Act
    double actualEmissionIntensityInitial =
        emissionIndustrialIntensityFunction.getEmissionIntensityInitial();
    double actualEmissionIntensityRateDecay =
        emissionIndustrialIntensityFunction.getEmissionIntensityRateDecay();
    double actualEmissionIntensityRateInitial =
        emissionIndustrialIntensityFunction.getEmissionIntensityRateInitial();

    // Assert
    assertEquals(0.0010005003335835344d, actualEmissionIntensityRateDecay, 0.0);
    assertEquals(0.0152d, actualEmissionIntensityRateInitial, 0.0);
    assertEquals(0.33981042654028437d, actualEmissionIntensityInitial, 0.0);
    assertSame(timeDiscretization, emissionIndustrialIntensityFunction.getTimeDiscretization());
  }
}
