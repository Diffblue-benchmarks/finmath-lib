package net.finmath.montecarlo.interestrate.models.covariance;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import net.finmath.montecarlo.RandomVariableFactory;
import net.finmath.montecarlo.RandomVariableFloatFactory;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LIBORVolatilityModelTwoParameterExponentialFormDiffblueTest {
  /**
   * Test {@link
   * LIBORVolatilityModelTwoParameterExponentialForm#LIBORVolatilityModelTwoParameterExponentialForm(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, RandomVariable, RandomVariable, boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelTwoParameterExponentialForm#LIBORVolatilityModelTwoParameterExponentialForm(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, RandomVariable, RandomVariable, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelTwoParameterExponentialForm.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, RandomVariable, RandomVariable, boolean)"
  })
  public void testNewLIBORVolatilityModelTwoParameterExponentialForm() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    RandomVariableFromDoubleArray a = new RandomVariableFromDoubleArray(10.0d);

    // Act
    LIBORVolatilityModelTwoParameterExponentialForm
        actualLiborVolatilityModelTwoParameterExponentialForm =
            new LIBORVolatilityModelTwoParameterExponentialForm(
                randomVariableFactory,
                timeDiscretization,
                liborPeriodDiscretization,
                a,
                new RandomVariableFromDoubleArray(10.0d),
                true);

    // Assert
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelTwoParameterExponentialForm.getLiborPeriodDiscretization());
    assertSame(
        timeDiscretization,
        actualLiborVolatilityModelTwoParameterExponentialForm.getTimeDiscretization());
  }

  /**
   * Test {@link
   * LIBORVolatilityModelTwoParameterExponentialForm#LIBORVolatilityModelTwoParameterExponentialForm(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double, double, boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelTwoParameterExponentialForm#LIBORVolatilityModelTwoParameterExponentialForm(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelTwoParameterExponentialForm.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, double, double, boolean)"
  })
  public void testNewLIBORVolatilityModelTwoParameterExponentialForm2() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    LIBORVolatilityModelTwoParameterExponentialForm
        actualLiborVolatilityModelTwoParameterExponentialForm =
            new LIBORVolatilityModelTwoParameterExponentialForm(
                randomVariableFactory,
                timeDiscretization,
                liborPeriodDiscretization,
                10.0d,
                10.0d,
                true);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborVolatilityModelTwoParameterExponentialForm.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(2, actualLiborVolatilityModelTwoParameterExponentialForm.getParameter().length);
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelTwoParameterExponentialForm.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {10.0d, 10.0d},
        actualLiborVolatilityModelTwoParameterExponentialForm.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORVolatilityModelTwoParameterExponentialForm#LIBORVolatilityModelTwoParameterExponentialForm(TimeDiscretization,
   * TimeDiscretization, double, double)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelTwoParameterExponentialForm#LIBORVolatilityModelTwoParameterExponentialForm(TimeDiscretization,
   * TimeDiscretization, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelTwoParameterExponentialForm.<init>(TimeDiscretization, TimeDiscretization, double, double)"
  })
  public void testNewLIBORVolatilityModelTwoParameterExponentialForm3() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    LIBORVolatilityModelTwoParameterExponentialForm
        actualLiborVolatilityModelTwoParameterExponentialForm =
            new LIBORVolatilityModelTwoParameterExponentialForm(
                timeDiscretization, liborPeriodDiscretization, 10.0d, 10.0d);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborVolatilityModelTwoParameterExponentialForm.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(2, actualLiborVolatilityModelTwoParameterExponentialForm.getParameter().length);
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelTwoParameterExponentialForm.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {10.0d, 10.0d},
        actualLiborVolatilityModelTwoParameterExponentialForm.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORVolatilityModelTwoParameterExponentialForm#getParameter()}.
   *
   * <ul>
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORVolatilityModelTwoParameterExponentialForm#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORVolatilityModelTwoParameterExponentialForm.getParameter()"
  })
  public void testGetParameter_thenFirstElementReturnScalar() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm
        liborVolatilityModelTwoParameterExponentialForm =
            new LIBORVolatilityModelTwoParameterExponentialForm(
                timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);

    // Act
    RandomVariable[] actualParameter =
        liborVolatilityModelTwoParameterExponentialForm.getParameter();

    // Assert
    assertTrue(actualParameter[0] instanceof Scalar);
    assertTrue(actualParameter[1] instanceof Scalar);
    assertEquals(2, actualParameter.length);
  }

  /**
   * Test {@link LIBORVolatilityModelTwoParameterExponentialForm#getParameter()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORVolatilityModelTwoParameterExponentialForm#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORVolatilityModelTwoParameterExponentialForm.getParameter()"
  })
  public void testGetParameter_thenReturnNull() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelTwoParameterExponentialForm
        liborVolatilityModelTwoParameterExponentialForm =
            new LIBORVolatilityModelTwoParameterExponentialForm(
                randomVariableFactory,
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                10.0d,
                10.0d,
                false);

    // Act and Assert
    assertNull(liborVolatilityModelTwoParameterExponentialForm.getParameter());
  }

  /**
   * Test {@link
   * LIBORVolatilityModelTwoParameterExponentialForm#getCloneWithModifiedParameter(RandomVariable[])}.
   *
   * <ul>
   *   <li>Then TimeDiscretization return {@link TenorFromArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelTwoParameterExponentialForm#getCloneWithModifiedParameter(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORVolatilityModelTwoParameterExponentialForm LIBORVolatilityModelTwoParameterExponentialForm.getCloneWithModifiedParameter(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParameter_thenTimeDiscretizationReturnTenorFromArray() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelTwoParameterExponentialForm
        liborVolatilityModelTwoParameterExponentialForm =
            new LIBORVolatilityModelTwoParameterExponentialForm(
                timeDiscretization, liborPeriodDiscretization, 10.0d, 10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    LIBORVolatilityModelTwoParameterExponentialForm actualCloneWithModifiedParameter =
        liborVolatilityModelTwoParameterExponentialForm.getCloneWithModifiedParameter(
            new RandomVariable[] {
              randomVariableFromDoubleArray, new RandomVariableFromDoubleArray(10.0d)
            });

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualCloneWithModifiedParameter.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(2, actualCloneWithModifiedParameter.getParameter().length);
    assertSame(
        liborPeriodDiscretization, actualCloneWithModifiedParameter.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {10.0d, 10.0d}, actualCloneWithModifiedParameter.getParameterAsDouble(), 0.0);
  }

  /**
   * Test {@link LIBORVolatilityModelTwoParameterExponentialForm#getVolatility(int, int)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 3.720075976020836E-43}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORVolatilityModelTwoParameterExponentialForm#getVolatility(int,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORVolatilityModelTwoParameterExponentialForm.getVolatility(int, int)"
  })
  public void testGetVolatility_thenReturnAverageIs3720075976020836e43() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(0.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm
        liborVolatilityModelTwoParameterExponentialForm =
            new LIBORVolatilityModelTwoParameterExponentialForm(
                timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);

    // Act
    RandomVariable actualVolatility =
        liborVolatilityModelTwoParameterExponentialForm.getVolatility(1, 1);

    // Assert
    assertTrue(actualVolatility instanceof Scalar);
    assertEquals(3.720075976020836E-43d, actualVolatility.getAverage(), 0.0);
    assertEquals(3.720075976020836E-43d, actualVolatility.getMax(), 0.0);
    assertEquals(3.720075976020836E-43d, actualVolatility.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualVolatility.expectation();
    assertSame(actualVolatility, actualExpectationResult);
  }

  /**
   * Test {@link LIBORVolatilityModelTwoParameterExponentialForm#getVolatility(int, int)}.
   *
   * <ul>
   *   <li>Then return Average is zero.
   * </ul>
   *
   * <p>Method under test: {@link LIBORVolatilityModelTwoParameterExponentialForm#getVolatility(int,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORVolatilityModelTwoParameterExponentialForm.getVolatility(int, int)"
  })
  public void testGetVolatility_thenReturnAverageIsZero() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm
        liborVolatilityModelTwoParameterExponentialForm =
            new LIBORVolatilityModelTwoParameterExponentialForm(
                timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);

    // Act
    RandomVariable actualVolatility =
        liborVolatilityModelTwoParameterExponentialForm.getVolatility(1, 1);

    // Assert
    assertTrue(actualVolatility instanceof Scalar);
    assertEquals(0.0d, actualVolatility.getAverage(), 0.0);
    assertEquals(0.0d, actualVolatility.getMax(), 0.0);
    assertEquals(0.0d, actualVolatility.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualVolatility.expectation();
    assertSame(actualVolatility, actualExpectationResult);
  }

  /**
   * Test {@link LIBORVolatilityModelTwoParameterExponentialForm#clone()}.
   *
   * <p>Method under test: {@link LIBORVolatilityModelTwoParameterExponentialForm#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LIBORVolatilityModelTwoParameterExponentialForm.clone()"})
  public void testClone() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelTwoParameterExponentialForm
        liborVolatilityModelTwoParameterExponentialForm =
            new LIBORVolatilityModelTwoParameterExponentialForm(
                timeDiscretization, liborPeriodDiscretization, 10.0d, 10.0d);

    // Act
    Object actualCloneResult = liborVolatilityModelTwoParameterExponentialForm.clone();

    // Assert
    assertTrue(actualCloneResult instanceof LIBORVolatilityModelTwoParameterExponentialForm);
    TimeDiscretization timeDiscretization2 =
        ((LIBORVolatilityModelTwoParameterExponentialForm) actualCloneResult)
            .getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(
        2,
        ((LIBORVolatilityModelTwoParameterExponentialForm) actualCloneResult)
            .getParameter()
            .length);
    assertSame(
        liborPeriodDiscretization,
        ((LIBORVolatilityModelTwoParameterExponentialForm) actualCloneResult)
            .getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {10.0d, 10.0d},
        ((LIBORVolatilityModelTwoParameterExponentialForm) actualCloneResult)
            .getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORVolatilityModelTwoParameterExponentialForm#getCloneWithModifiedData(Map)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelTwoParameterExponentialForm#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.montecarlo.interestrate.models.covariance.LIBORVolatilityModel LIBORVolatilityModelTwoParameterExponentialForm.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelTwoParameterExponentialForm
        liborVolatilityModelTwoParameterExponentialForm =
            new LIBORVolatilityModelTwoParameterExponentialForm(
                timeDiscretization, new TenorFromArray(10.0d, 10, 0.5d), 10.0d, 10.0d);

    // Act and Assert
    assertNull(
        liborVolatilityModelTwoParameterExponentialForm.getCloneWithModifiedData(new HashMap<>()));
  }
}
