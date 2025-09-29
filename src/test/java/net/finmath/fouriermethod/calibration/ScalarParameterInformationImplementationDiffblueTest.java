package net.finmath.fouriermethod.calibration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ScalarParameterInformationImplementationDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@link NegativityConstraint} (default constructor).
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link
   *       ScalarParameterInformationImplementation#ScalarParameterInformationImplementation(ScalarConstraint)}
   *   <li>{@link ScalarParameterInformationImplementation#getConstraint()}
   *   <li>{@link ScalarParameterInformationImplementation#getIsParameterToCalibrate()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ScalarParameterInformationImplementation.<init>(ScalarConstraint)",
    "void ScalarParameterInformationImplementation.<init>(boolean, ScalarConstraint)",
    "ScalarConstraint ScalarParameterInformationImplementation.getConstraint()",
    "boolean ScalarParameterInformationImplementation.getIsParameterToCalibrate()"
  })
  public void testGettersAndSetters_whenNegativityConstraint() {
    // Arrange
    NegativityConstraint constraint = new NegativityConstraint();

    // Act
    ScalarParameterInformationImplementation actualScalarParameterInformationImplementation =
        new ScalarParameterInformationImplementation(constraint);
    ScalarConstraint actualConstraint =
        actualScalarParameterInformationImplementation.getConstraint();

    // Assert
    assertTrue(actualScalarParameterInformationImplementation.getIsParameterToCalibrate());
    assertSame(constraint, actualConstraint);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code true}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link
   *       ScalarParameterInformationImplementation#ScalarParameterInformationImplementation(boolean,
   *       ScalarConstraint)}
   *   <li>{@link ScalarParameterInformationImplementation#getConstraint()}
   *   <li>{@link ScalarParameterInformationImplementation#getIsParameterToCalibrate()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ScalarParameterInformationImplementation.<init>(ScalarConstraint)",
    "void ScalarParameterInformationImplementation.<init>(boolean, ScalarConstraint)",
    "ScalarConstraint ScalarParameterInformationImplementation.getConstraint()",
    "boolean ScalarParameterInformationImplementation.getIsParameterToCalibrate()"
  })
  public void testGettersAndSetters_whenTrue() {
    // Arrange
    NegativityConstraint constraint = new NegativityConstraint();

    // Act
    ScalarParameterInformationImplementation actualScalarParameterInformationImplementation =
        new ScalarParameterInformationImplementation(true, constraint);
    ScalarConstraint actualConstraint =
        actualScalarParameterInformationImplementation.getConstraint();

    // Assert
    assertTrue(actualScalarParameterInformationImplementation.getIsParameterToCalibrate());
    assertSame(constraint, actualConstraint);
  }

  /**
   * Test {@link
   * ScalarParameterInformationImplementation#ScalarParameterInformationImplementation(boolean)}.
   *
   * <p>Method under test: {@link
   * ScalarParameterInformationImplementation#ScalarParameterInformationImplementation(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ScalarParameterInformationImplementation.<init>(boolean)"})
  public void testNewScalarParameterInformationImplementation() {
    // Arrange and Act
    ScalarParameterInformationImplementation actualScalarParameterInformationImplementation =
        new ScalarParameterInformationImplementation(true);

    // Assert
    ScalarConstraint constraint = actualScalarParameterInformationImplementation.getConstraint();
    assertTrue(constraint instanceof Unconstrained);
    assertTrue(actualScalarParameterInformationImplementation.getIsParameterToCalibrate());
    assertEquals(Double.NEGATIVE_INFINITY, constraint.getLowerBound(), 0.0);
    assertEquals(Double.POSITIVE_INFINITY, constraint.getUpperBound(), 0.0);
  }
}
