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

public class EvolutionOfEmissionIndustrialIntensityDiffblueTest {
  /**
   * Test {@link
   * EvolutionOfEmissionIndustrialIntensity#EvolutionOfEmissionIndustrialIntensity(TimeDiscretization)}.
   *
   * <p>Method under test: {@link
   * EvolutionOfEmissionIndustrialIntensity#EvolutionOfEmissionIndustrialIntensity(TimeDiscretization)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EvolutionOfEmissionIndustrialIntensity.<init>(TimeDiscretization)"})
  public void testNewEvolutionOfEmissionIndustrialIntensity() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    EvolutionOfEmissionIndustrialIntensity actualEvolutionOfEmissionIndustrialIntensity =
        new EvolutionOfEmissionIndustrialIntensity(timeDiscretization);

    // Assert
    assertEquals(
        0.0010005003335835344d,
        actualEvolutionOfEmissionIndustrialIntensity.getEmissionIntensityRateDecay(),
        0.0);
    assertEquals(
        0.0152d,
        actualEvolutionOfEmissionIndustrialIntensity.getEmissionIntensityRateInitial(),
        0.0);
    assertEquals(
        0.33981042654028437d,
        actualEvolutionOfEmissionIndustrialIntensity.getEmissionIntensityInitial(),
        0.0);
    assertSame(
        timeDiscretization, actualEvolutionOfEmissionIndustrialIntensity.getTimeDiscretization());
  }

  /**
   * Test {@link
   * EvolutionOfEmissionIndustrialIntensity#EvolutionOfEmissionIndustrialIntensity(TimeDiscretization,
   * double, double, double)}.
   *
   * <p>Method under test: {@link
   * EvolutionOfEmissionIndustrialIntensity#EvolutionOfEmissionIndustrialIntensity(TimeDiscretization,
   * double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void EvolutionOfEmissionIndustrialIntensity.<init>(TimeDiscretization, double, double, double)"
  })
  public void testNewEvolutionOfEmissionIndustrialIntensity2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    EvolutionOfEmissionIndustrialIntensity actualEvolutionOfEmissionIndustrialIntensity =
        new EvolutionOfEmissionIndustrialIntensity(timeDiscretization, 10.0d, 10.0d, 10.0d);

    // Assert
    assertEquals(
        10.0d, actualEvolutionOfEmissionIndustrialIntensity.getEmissionIntensityInitial(), 0.0);
    assertEquals(
        10.0d, actualEvolutionOfEmissionIndustrialIntensity.getEmissionIntensityRateDecay(), 0.0);
    assertEquals(
        10.0d, actualEvolutionOfEmissionIndustrialIntensity.getEmissionIntensityRateInitial(), 0.0);
    assertSame(
        timeDiscretization, actualEvolutionOfEmissionIndustrialIntensity.getTimeDiscretization());
  }

  /**
   * Test {@link EvolutionOfEmissionIndustrialIntensity#apply(Integer, Double)} with {@code
   * Integer}, {@code Double}.
   *
   * <ul>
   *   <li>Then return doubleValue is {@code 9.925076308087693}.
   * </ul>
   *
   * <p>Method under test: {@link EvolutionOfEmissionIndustrialIntensity#apply(Integer, Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double EvolutionOfEmissionIndustrialIntensity.apply(Integer, Double)"})
  public void testApplyWithIntegerDouble_thenReturnDoubleValueIs9925076308087693() {
    // Arrange, Act and Assert
    assertEquals(
        9.925076308087693d,
        new EvolutionOfEmissionIndustrialIntensity(new TenorFromArray(10.0d, 10, 0.5d))
            .apply(1, 10.0d)
            .doubleValue(),
        0.0);
  }

  /**
   * Test {@link EvolutionOfEmissionIndustrialIntensity#apply(Integer, Double)} with {@code
   * Integer}, {@code Double}.
   *
   * <ul>
   *   <li>When {@code 0.0152}.
   *   <li>Then return doubleValue is {@code 0.015086115988293293}.
   * </ul>
   *
   * <p>Method under test: {@link EvolutionOfEmissionIndustrialIntensity#apply(Integer, Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double EvolutionOfEmissionIndustrialIntensity.apply(Integer, Double)"})
  public void testApplyWithIntegerDouble_when00152_thenReturnDoubleValueIs0015086115988293293() {
    // Arrange, Act and Assert
    assertEquals(
        0.015086115988293293d,
        new EvolutionOfEmissionIndustrialIntensity(new TenorFromArray(10.0d, 10, 0.5d))
            .apply(1, 0.0152d)
            .doubleValue(),
        0.0);
  }

  /**
   * Test {@link EvolutionOfEmissionIndustrialIntensity#apply(Integer, Double)} with {@code
   * Integer}, {@code Double}.
   *
   * <ul>
   *   <li>When three.
   *   <li>Then return doubleValue is {@code 9.925150950678683}.
   * </ul>
   *
   * <p>Method under test: {@link EvolutionOfEmissionIndustrialIntensity#apply(Integer, Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double EvolutionOfEmissionIndustrialIntensity.apply(Integer, Double)"})
  public void testApplyWithIntegerDouble_whenThree_thenReturnDoubleValueIs9925150950678683() {
    // Arrange, Act and Assert
    assertEquals(
        9.925150950678683d,
        new EvolutionOfEmissionIndustrialIntensity(new TenorFromArray(10.0d, 10, 0.5d))
            .apply(3, 10.0d)
            .doubleValue(),
        0.0);
  }

  /**
   * Test {@link EvolutionOfEmissionIndustrialIntensity#apply(Integer, Double)} with {@code
   * Integer}, {@code Double}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return doubleValue is {@code 9.9250389589886}.
   * </ul>
   *
   * <p>Method under test: {@link EvolutionOfEmissionIndustrialIntensity#apply(Integer, Double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Double EvolutionOfEmissionIndustrialIntensity.apply(Integer, Double)"})
  public void testApplyWithIntegerDouble_whenZero_thenReturnDoubleValueIs99250389589886() {
    // Arrange, Act and Assert
    assertEquals(
        9.9250389589886d,
        new EvolutionOfEmissionIndustrialIntensity(new TenorFromArray(10.0d, 10, 0.5d))
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
   *   <li>{@link EvolutionOfEmissionIndustrialIntensity#getEmissionIntensityInitial()}
   *   <li>{@link EvolutionOfEmissionIndustrialIntensity#getEmissionIntensityRateDecay()}
   *   <li>{@link EvolutionOfEmissionIndustrialIntensity#getEmissionIntensityRateInitial()}
   *   <li>{@link EvolutionOfEmissionIndustrialIntensity#getTimeDiscretization()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double EvolutionOfEmissionIndustrialIntensity.getEmissionIntensityInitial()",
    "double EvolutionOfEmissionIndustrialIntensity.getEmissionIntensityRateDecay()",
    "double EvolutionOfEmissionIndustrialIntensity.getEmissionIntensityRateInitial()",
    "TimeDiscretization EvolutionOfEmissionIndustrialIntensity.getTimeDiscretization()"
  })
  public void testGettersAndSetters() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    EvolutionOfEmissionIndustrialIntensity evolutionOfEmissionIndustrialIntensity =
        new EvolutionOfEmissionIndustrialIntensity(timeDiscretization);

    // Act
    double actualEmissionIntensityInitial =
        evolutionOfEmissionIndustrialIntensity.getEmissionIntensityInitial();
    double actualEmissionIntensityRateDecay =
        evolutionOfEmissionIndustrialIntensity.getEmissionIntensityRateDecay();
    double actualEmissionIntensityRateInitial =
        evolutionOfEmissionIndustrialIntensity.getEmissionIntensityRateInitial();

    // Assert
    assertEquals(0.0010005003335835344d, actualEmissionIntensityRateDecay, 0.0);
    assertEquals(0.0152d, actualEmissionIntensityRateInitial, 0.0);
    assertEquals(0.33981042654028437d, actualEmissionIntensityInitial, 0.0);
    assertSame(timeDiscretization, evolutionOfEmissionIndustrialIntensity.getTimeDiscretization());
  }
}
