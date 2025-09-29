package net.finmath.montecarlo.interestrate.models.funding;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.stochastic.RandomVariable;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DefaultFactorsDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DefaultFactors#DefaultFactors(RandomVariable, RandomVariable)}
   *   <li>{@link DefaultFactors#getDefaultCompensation()}
   *   <li>{@link DefaultFactors#getSurvivalProbability()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DefaultFactors.<init>(RandomVariable, RandomVariable)",
    "RandomVariable DefaultFactors.getDefaultCompensation()",
    "RandomVariable DefaultFactors.getSurvivalProbability()"
  })
  public void testGettersAndSetters() {
    // Arrange
    RandomVariableFromDoubleArray survivalProbability = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray defaultCompensation = new RandomVariableFromDoubleArray(10.0d);

    // Act
    DefaultFactors actualDefaultFactors =
        new DefaultFactors(survivalProbability, defaultCompensation);
    RandomVariable actualDefaultCompensation = actualDefaultFactors.getDefaultCompensation();
    RandomVariable actualSurvivalProbability = actualDefaultFactors.getSurvivalProbability();

    // Assert
    assertTrue(actualSurvivalProbability instanceof RandomVariableFromDoubleArray);
    assertSame(defaultCompensation, actualDefaultCompensation);
    assertSame(survivalProbability, actualSurvivalProbability);
  }
}
