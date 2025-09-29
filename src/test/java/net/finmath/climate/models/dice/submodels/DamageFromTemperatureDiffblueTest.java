package net.finmath.climate.models.dice.submodels;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DamageFromTemperatureDiffblueTest {
  /**
   * Test {@link DamageFromTemperature#DamageFromTemperature()}.
   *
   * <p>Method under test: {@link DamageFromTemperature#DamageFromTemperature()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DamageFromTemperature.<init>()"})
  public void testNewDamageFromTemperature() {
    // Arrange, Act and Assert
    assertEquals(0.23600000000000004d, new DamageFromTemperature().applyAsDouble(10.0d), 0.0);
  }

  /**
   * Test {@link DamageFromTemperature#DamageFromTemperature(double, double, double)}.
   *
   * <p>Method under test: {@link DamageFromTemperature#DamageFromTemperature(double, double,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DamageFromTemperature.<init>(double, double, double)"})
  public void testNewDamageFromTemperature2() {
    // Arrange, Act and Assert
    assertEquals(1110.0d, new DamageFromTemperature(10.0d, 10.0d, 10.0d).applyAsDouble(10.0d), 0.0);
  }

  /**
   * Test {@link DamageFromTemperature#applyAsDouble(double)}.
   *
   * <p>Method under test: {@link DamageFromTemperature#applyAsDouble(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double DamageFromTemperature.applyAsDouble(double)"})
  public void testApplyAsDouble() {
    // Arrange, Act and Assert
    assertEquals(0.23600000000000004d, new DamageFromTemperature().applyAsDouble(10.0d), 0.0);
  }
}
