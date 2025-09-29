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
import net.finmath.montecarlo.RandomVariableFromFloatArray;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LIBORVolatilityModelFourParameterExponentialFormDiffblueTest {
  /**
   * Test {@link
   * LIBORVolatilityModelFourParameterExponentialForm#LIBORVolatilityModelFourParameterExponentialForm(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, RandomVariable, RandomVariable, RandomVariable,
   * RandomVariable, boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFourParameterExponentialForm#LIBORVolatilityModelFourParameterExponentialForm(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, RandomVariable, RandomVariable, RandomVariable,
   * RandomVariable, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelFourParameterExponentialForm.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, RandomVariable, RandomVariable, RandomVariable, RandomVariable, boolean)"
  })
  public void testNewLIBORVolatilityModelFourParameterExponentialForm() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    RandomVariableFromDoubleArray a = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray b = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray c = new RandomVariableFromDoubleArray(10.0d);

    // Act
    LIBORVolatilityModelFourParameterExponentialForm
        actualLiborVolatilityModelFourParameterExponentialForm =
            new LIBORVolatilityModelFourParameterExponentialForm(
                randomVariableFactory,
                timeDiscretization,
                liborPeriodDiscretization,
                a,
                b,
                c,
                new RandomVariableFromDoubleArray(10.0d),
                true);

    // Assert
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelFourParameterExponentialForm.getLiborPeriodDiscretization());
    assertSame(
        timeDiscretization,
        actualLiborVolatilityModelFourParameterExponentialForm.getTimeDiscretization());
  }

  /**
   * Test {@link
   * LIBORVolatilityModelFourParameterExponentialForm#LIBORVolatilityModelFourParameterExponentialForm(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double, double, double, double, boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFourParameterExponentialForm#LIBORVolatilityModelFourParameterExponentialForm(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double, double, double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelFourParameterExponentialForm.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, double, double, double, double, boolean)"
  })
  public void testNewLIBORVolatilityModelFourParameterExponentialForm2() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    LIBORVolatilityModelFourParameterExponentialForm
        actualLiborVolatilityModelFourParameterExponentialForm =
            new LIBORVolatilityModelFourParameterExponentialForm(
                randomVariableFactory,
                timeDiscretization,
                liborPeriodDiscretization,
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                true);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborVolatilityModelFourParameterExponentialForm.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(4, actualLiborVolatilityModelFourParameterExponentialForm.getParameter().length);
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelFourParameterExponentialForm.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualLiborVolatilityModelFourParameterExponentialForm.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORVolatilityModelFourParameterExponentialForm#LIBORVolatilityModelFourParameterExponentialForm(TimeDiscretization,
   * TimeDiscretization, double, double, double, double, boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFourParameterExponentialForm#LIBORVolatilityModelFourParameterExponentialForm(TimeDiscretization,
   * TimeDiscretization, double, double, double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelFourParameterExponentialForm.<init>(TimeDiscretization, TimeDiscretization, double, double, double, double, boolean)"
  })
  public void testNewLIBORVolatilityModelFourParameterExponentialForm3() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    LIBORVolatilityModelFourParameterExponentialForm
        actualLiborVolatilityModelFourParameterExponentialForm =
            new LIBORVolatilityModelFourParameterExponentialForm(
                timeDiscretization, liborPeriodDiscretization, 10.0d, 10.0d, 10.0d, 10.0d, true);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborVolatilityModelFourParameterExponentialForm.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(4, actualLiborVolatilityModelFourParameterExponentialForm.getParameter().length);
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelFourParameterExponentialForm.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualLiborVolatilityModelFourParameterExponentialForm.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORVolatilityModelFourParameterExponentialForm#LIBORVolatilityModelFourParameterExponentialForm(TimeDiscretization,
   * TimeDiscretization, RandomVariable, RandomVariable, RandomVariable, RandomVariable, boolean)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFourParameterExponentialForm#LIBORVolatilityModelFourParameterExponentialForm(TimeDiscretization,
   * TimeDiscretization, RandomVariable, RandomVariable, RandomVariable, RandomVariable, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelFourParameterExponentialForm.<init>(TimeDiscretization, TimeDiscretization, RandomVariable, RandomVariable, RandomVariable, RandomVariable, boolean)"
  })
  public void testNewLIBORVolatilityModelFourParameterExponentialForm4() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    RandomVariableFromDoubleArray a = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray b = new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray c = new RandomVariableFromDoubleArray(10.0d);

    // Act
    LIBORVolatilityModelFourParameterExponentialForm
        actualLiborVolatilityModelFourParameterExponentialForm =
            new LIBORVolatilityModelFourParameterExponentialForm(
                timeDiscretization,
                liborPeriodDiscretization,
                a,
                b,
                c,
                new RandomVariableFromDoubleArray(10.0d),
                true);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborVolatilityModelFourParameterExponentialForm.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(4, actualLiborVolatilityModelFourParameterExponentialForm.getParameter().length);
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelFourParameterExponentialForm.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualLiborVolatilityModelFourParameterExponentialForm.getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORVolatilityModelFourParameterExponentialForm#getParameter()}.
   *
   * <ul>
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORVolatilityModelFourParameterExponentialForm#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORVolatilityModelFourParameterExponentialForm.getParameter()"
  })
  public void testGetParameter_thenFirstElementReturnScalar() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelFourParameterExponentialForm
        liborVolatilityModelFourParameterExponentialForm =
            new LIBORVolatilityModelFourParameterExponentialForm(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                true);

    // Act
    RandomVariable[] actualParameter =
        liborVolatilityModelFourParameterExponentialForm.getParameter();

    // Assert
    assertTrue(actualParameter[0] instanceof Scalar);
    assertTrue(actualParameter[1] instanceof Scalar);
    assertTrue(actualParameter[2] instanceof Scalar);
    assertTrue(actualParameter[3] instanceof Scalar);
    assertEquals(4, actualParameter.length);
  }

  /**
   * Test {@link LIBORVolatilityModelFourParameterExponentialForm#getParameter()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LIBORVolatilityModelFourParameterExponentialForm#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORVolatilityModelFourParameterExponentialForm.getParameter()"
  })
  public void testGetParameter_thenReturnNull() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelFourParameterExponentialForm
        liborVolatilityModelFourParameterExponentialForm =
            new LIBORVolatilityModelFourParameterExponentialForm(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                false);

    // Act and Assert
    assertNull(liborVolatilityModelFourParameterExponentialForm.getParameter());
  }

  /**
   * Test {@link
   * LIBORVolatilityModelFourParameterExponentialForm#getCloneWithModifiedParameter(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFourParameterExponentialForm#getCloneWithModifiedParameter(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORVolatilityModelFourParameterExponentialForm LIBORVolatilityModelFourParameterExponentialForm.getCloneWithModifiedParameter(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParameter() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelFourParameterExponentialForm
        liborVolatilityModelFourParameterExponentialForm =
            new LIBORVolatilityModelFourParameterExponentialForm(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                false);

    // Act
    LIBORVolatilityModelFourParameterExponentialForm actualCloneWithModifiedParameter =
        liborVolatilityModelFourParameterExponentialForm.getCloneWithModifiedParameter(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    assertSame(liborVolatilityModelFourParameterExponentialForm, actualCloneWithModifiedParameter);
  }

  /**
   * Test {@link LIBORVolatilityModelFourParameterExponentialForm#getVolatility(int, int)}.
   *
   * <ul>
   *   <li>Then return Average is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFourParameterExponentialForm#getVolatility(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORVolatilityModelFourParameterExponentialForm.getVolatility(int, int)"
  })
  public void testGetVolatility_thenReturnAverageIsTen() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(0.0d, 10, 0.5d);
    LIBORVolatilityModelFourParameterExponentialForm
        liborVolatilityModelFourParameterExponentialForm =
            new LIBORVolatilityModelFourParameterExponentialForm(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                true);

    // Act
    RandomVariable actualVolatility =
        liborVolatilityModelFourParameterExponentialForm.getVolatility(1, 1);

    // Assert
    assertTrue(actualVolatility instanceof Scalar);
    assertEquals(10.0d, actualVolatility.getAverage(), 0.0);
    assertEquals(10.0d, actualVolatility.getMax(), 0.0);
    assertEquals(10.0d, actualVolatility.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualVolatility.expectation();
    assertSame(actualVolatility, actualExpectationResult);
  }

  /**
   * Test {@link LIBORVolatilityModelFourParameterExponentialForm#getVolatility(int, int)}.
   *
   * <ul>
   *   <li>Then return Average is zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFourParameterExponentialForm#getVolatility(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORVolatilityModelFourParameterExponentialForm.getVolatility(int, int)"
  })
  public void testGetVolatility_thenReturnAverageIsZero() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelFourParameterExponentialForm
        liborVolatilityModelFourParameterExponentialForm =
            new LIBORVolatilityModelFourParameterExponentialForm(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                true);

    // Act
    RandomVariable actualVolatility =
        liborVolatilityModelFourParameterExponentialForm.getVolatility(1, 1);

    // Assert
    assertTrue(actualVolatility instanceof Scalar);
    assertEquals(0.0d, actualVolatility.getAverage(), 0.0);
    assertEquals(0.0d, actualVolatility.getMax(), 0.0);
    assertEquals(0.0d, actualVolatility.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualVolatility.expectation();
    assertSame(actualVolatility, actualExpectationResult);
  }

  /**
   * Test {@link LIBORVolatilityModelFourParameterExponentialForm#clone()}.
   *
   * <p>Method under test: {@link LIBORVolatilityModelFourParameterExponentialForm#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object LIBORVolatilityModelFourParameterExponentialForm.clone()"})
  public void testClone() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelFourParameterExponentialForm
        liborVolatilityModelFourParameterExponentialForm =
            new LIBORVolatilityModelFourParameterExponentialForm(
                timeDiscretization, liborPeriodDiscretization, 10.0d, 10.0d, 10.0d, 10.0d, true);

    // Act
    Object actualCloneResult = liborVolatilityModelFourParameterExponentialForm.clone();

    // Assert
    assertTrue(actualCloneResult instanceof LIBORVolatilityModelFourParameterExponentialForm);
    TimeDiscretization timeDiscretization2 =
        ((LIBORVolatilityModelFourParameterExponentialForm) actualCloneResult)
            .getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(
        4,
        ((LIBORVolatilityModelFourParameterExponentialForm) actualCloneResult)
            .getParameter()
            .length);
    assertSame(
        liborPeriodDiscretization,
        ((LIBORVolatilityModelFourParameterExponentialForm) actualCloneResult)
            .getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        ((LIBORVolatilityModelFourParameterExponentialForm) actualCloneResult)
            .getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORVolatilityModelFourParameterExponentialForm#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@code a} is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFourParameterExponentialForm#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORVolatilityModel LIBORVolatilityModelFourParameterExponentialForm.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_whenHashMapAIsTen() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelFourParameterExponentialForm
        liborVolatilityModelFourParameterExponentialForm =
            new LIBORVolatilityModelFourParameterExponentialForm(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                true);

    HashMap<String, Object> dataModified = new HashMap<>();
    dataModified.put("randomVariableFactory", new RandomVariableFloatFactory());
    dataModified.put("timeDiscretization", new TenorFromArray(10.0d, 10, 0.5d));
    dataModified.put("liborPeriodDiscretization", new TenorFromArray(10.0d, 10, 0.5d));
    dataModified.put("isCalibrateable", true);
    dataModified.put("a", 10.0d);
    dataModified.put("b", new RandomVariableFromDoubleArray(10.0d));
    dataModified.put("c", new RandomVariableFromDoubleArray(10.0d));
    dataModified.put("d", new RandomVariableFromDoubleArray(10.0d));

    // Act
    LIBORVolatilityModel actualCloneWithModifiedData =
        liborVolatilityModelFourParameterExponentialForm.getCloneWithModifiedData(dataModified);

    // Assert
    RandomVariable[] parameter = actualCloneWithModifiedData.getParameter();
    assertTrue(parameter[0] instanceof RandomVariableFromFloatArray);
    assertTrue(parameter[1] instanceof RandomVariableFromFloatArray);
    assertTrue(parameter[2] instanceof RandomVariableFromFloatArray);
    assertTrue(parameter[3] instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualCloneWithModifiedData instanceof LIBORVolatilityModelFourParameterExponentialForm);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(4, parameter.length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedData.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link LIBORVolatilityModelFourParameterExponentialForm#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@code b} is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFourParameterExponentialForm#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORVolatilityModel LIBORVolatilityModelFourParameterExponentialForm.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_whenHashMapBIsTen() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelFourParameterExponentialForm
        liborVolatilityModelFourParameterExponentialForm =
            new LIBORVolatilityModelFourParameterExponentialForm(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                true);

    HashMap<String, Object> dataModified = new HashMap<>();
    dataModified.put("randomVariableFactory", new RandomVariableFloatFactory());
    dataModified.put("timeDiscretization", new TenorFromArray(10.0d, 10, 0.5d));
    dataModified.put("liborPeriodDiscretization", new TenorFromArray(10.0d, 10, 0.5d));
    dataModified.put("isCalibrateable", true);
    dataModified.put("a", new RandomVariableFromDoubleArray(10.0d));
    dataModified.put("b", 10.0d);
    dataModified.put("c", new RandomVariableFromDoubleArray(10.0d));
    dataModified.put("d", new RandomVariableFromDoubleArray(10.0d));

    // Act
    LIBORVolatilityModel actualCloneWithModifiedData =
        liborVolatilityModelFourParameterExponentialForm.getCloneWithModifiedData(dataModified);

    // Assert
    RandomVariable[] parameter = actualCloneWithModifiedData.getParameter();
    assertTrue(parameter[0] instanceof RandomVariableFromFloatArray);
    assertTrue(parameter[1] instanceof RandomVariableFromFloatArray);
    assertTrue(parameter[2] instanceof RandomVariableFromFloatArray);
    assertTrue(parameter[3] instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualCloneWithModifiedData instanceof LIBORVolatilityModelFourParameterExponentialForm);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(4, parameter.length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedData.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link LIBORVolatilityModelFourParameterExponentialForm#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@code c} is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFourParameterExponentialForm#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORVolatilityModel LIBORVolatilityModelFourParameterExponentialForm.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_whenHashMapCIsTen() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelFourParameterExponentialForm
        liborVolatilityModelFourParameterExponentialForm =
            new LIBORVolatilityModelFourParameterExponentialForm(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                true);

    HashMap<String, Object> dataModified = new HashMap<>();
    dataModified.put("randomVariableFactory", new RandomVariableFloatFactory());
    dataModified.put("timeDiscretization", new TenorFromArray(10.0d, 10, 0.5d));
    dataModified.put("liborPeriodDiscretization", new TenorFromArray(10.0d, 10, 0.5d));
    dataModified.put("isCalibrateable", true);
    dataModified.put("a", new RandomVariableFromDoubleArray(10.0d));
    dataModified.put("b", new RandomVariableFromDoubleArray(10.0d));
    dataModified.put("c", 10.0d);
    dataModified.put("d", new RandomVariableFromDoubleArray(10.0d));

    // Act
    LIBORVolatilityModel actualCloneWithModifiedData =
        liborVolatilityModelFourParameterExponentialForm.getCloneWithModifiedData(dataModified);

    // Assert
    RandomVariable[] parameter = actualCloneWithModifiedData.getParameter();
    assertTrue(parameter[0] instanceof RandomVariableFromFloatArray);
    assertTrue(parameter[1] instanceof RandomVariableFromFloatArray);
    assertTrue(parameter[2] instanceof RandomVariableFromFloatArray);
    assertTrue(parameter[3] instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualCloneWithModifiedData instanceof LIBORVolatilityModelFourParameterExponentialForm);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(4, parameter.length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedData.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link LIBORVolatilityModelFourParameterExponentialForm#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@code d} is ten.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFourParameterExponentialForm#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORVolatilityModel LIBORVolatilityModelFourParameterExponentialForm.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_whenHashMapDIsTen() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelFourParameterExponentialForm
        liborVolatilityModelFourParameterExponentialForm =
            new LIBORVolatilityModelFourParameterExponentialForm(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                true);

    HashMap<String, Object> dataModified = new HashMap<>();
    dataModified.put("randomVariableFactory", new RandomVariableFloatFactory());
    dataModified.put("timeDiscretization", new TenorFromArray(10.0d, 10, 0.5d));
    dataModified.put("liborPeriodDiscretization", new TenorFromArray(10.0d, 10, 0.5d));
    dataModified.put("isCalibrateable", true);
    dataModified.put("a", new RandomVariableFromDoubleArray(10.0d));
    dataModified.put("b", new RandomVariableFromDoubleArray(10.0d));
    dataModified.put("c", new RandomVariableFromDoubleArray(10.0d));
    dataModified.put("d", 10.0d);

    // Act
    LIBORVolatilityModel actualCloneWithModifiedData =
        liborVolatilityModelFourParameterExponentialForm.getCloneWithModifiedData(dataModified);

    // Assert
    RandomVariable[] parameter = actualCloneWithModifiedData.getParameter();
    assertTrue(parameter[0] instanceof RandomVariableFromFloatArray);
    assertTrue(parameter[1] instanceof RandomVariableFromFloatArray);
    assertTrue(parameter[2] instanceof RandomVariableFromFloatArray);
    assertTrue(parameter[3] instanceof RandomVariableFromFloatArray);
    assertTrue(
        actualCloneWithModifiedData instanceof LIBORVolatilityModelFourParameterExponentialForm);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(4, parameter.length);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedData.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link LIBORVolatilityModelFourParameterExponentialForm#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFourParameterExponentialForm#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORVolatilityModel LIBORVolatilityModelFourParameterExponentialForm.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_whenHashMap_thenFirstElementReturnScalar() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelFourParameterExponentialForm
        liborVolatilityModelFourParameterExponentialForm =
            new LIBORVolatilityModelFourParameterExponentialForm(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                true);

    // Act
    LIBORVolatilityModel actualCloneWithModifiedData =
        liborVolatilityModelFourParameterExponentialForm.getCloneWithModifiedData(new HashMap<>());

    // Assert
    assertTrue(
        actualCloneWithModifiedData instanceof LIBORVolatilityModelFourParameterExponentialForm);
    RandomVariable[] parameter = actualCloneWithModifiedData.getParameter();
    assertTrue(parameter[0] instanceof Scalar);
    assertTrue(parameter[1] instanceof Scalar);
    assertTrue(parameter[2] instanceof Scalar);
    assertTrue(parameter[3] instanceof Scalar);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(4, parameter.length);
    assertEquals(timeDiscretization2, liborPeriodDiscretization);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedData.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }

  /**
   * Test {@link LIBORVolatilityModelFourParameterExponentialForm#getCloneWithModifiedData(Map)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelFourParameterExponentialForm#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORVolatilityModel LIBORVolatilityModelFourParameterExponentialForm.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData_whenNull() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelFourParameterExponentialForm
        liborVolatilityModelFourParameterExponentialForm =
            new LIBORVolatilityModelFourParameterExponentialForm(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                10.0d,
                10.0d,
                10.0d,
                10.0d,
                true);

    // Act
    LIBORVolatilityModel actualCloneWithModifiedData =
        liborVolatilityModelFourParameterExponentialForm.getCloneWithModifiedData(null);

    // Assert
    assertTrue(
        actualCloneWithModifiedData instanceof LIBORVolatilityModelFourParameterExponentialForm);
    TimeDiscretization liborPeriodDiscretization =
        actualCloneWithModifiedData.getLiborPeriodDiscretization();
    assertTrue(liborPeriodDiscretization instanceof TenorFromArray);
    TimeDiscretization timeDiscretization2 = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualCloneWithModifiedData.getParameterAsDouble(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        liborPeriodDiscretization.getAsDoubleArray(),
        0.0);
    assertArrayEquals(
        new double[] {10.0d, 10.5d, 11.0d, 11.5d, 12.0d, 12.5d, 13.0d, 13.5d, 14.0d, 14.5d, 15.0d},
        timeDiscretization2.getAsDoubleArray(),
        0.0);
  }
}
