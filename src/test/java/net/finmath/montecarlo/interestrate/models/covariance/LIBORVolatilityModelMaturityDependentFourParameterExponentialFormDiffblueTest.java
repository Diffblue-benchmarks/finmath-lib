package net.finmath.montecarlo.interestrate.models.covariance;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
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

public class LIBORVolatilityModelMaturityDependentFourParameterExponentialFormDiffblueTest {
  /**
   * Test {@link
   * LIBORVolatilityModelMaturityDependentFourParameterExponentialForm#LIBORVolatilityModelMaturityDependentFourParameterExponentialForm(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double, double, double, double)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelMaturityDependentFourParameterExponentialForm#LIBORVolatilityModelMaturityDependentFourParameterExponentialForm(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelMaturityDependentFourParameterExponentialForm.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, double, double, double, double)"
  })
  public void testNewLIBORVolatilityModelMaturityDependentFourParameterExponentialForm() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    LIBORVolatilityModelMaturityDependentFourParameterExponentialForm
        actualLiborVolatilityModelMaturityDependentFourParameterExponentialForm =
            new LIBORVolatilityModelMaturityDependentFourParameterExponentialForm(
                randomVariableFactory,
                timeDiscretization,
                liborPeriodDiscretization,
                10.0d,
                10.0d,
                10.0d,
                10.0d);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborVolatilityModelMaturityDependentFourParameterExponentialForm
            .getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(
        40,
        actualLiborVolatilityModelMaturityDependentFourParameterExponentialForm.getParameter()
            .length);
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelMaturityDependentFourParameterExponentialForm
            .getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {
          10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d,
          10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d,
          10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d,
          10.0d
        },
        actualLiborVolatilityModelMaturityDependentFourParameterExponentialForm
            .getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORVolatilityModelMaturityDependentFourParameterExponentialForm#LIBORVolatilityModelMaturityDependentFourParameterExponentialForm(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double[], double[], double[], double[])}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelMaturityDependentFourParameterExponentialForm#LIBORVolatilityModelMaturityDependentFourParameterExponentialForm(RandomVariableFactory,
   * TimeDiscretization, TimeDiscretization, double[], double[], double[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelMaturityDependentFourParameterExponentialForm.<init>(RandomVariableFactory, TimeDiscretization, TimeDiscretization, double[], double[], double[], double[])"
  })
  public void testNewLIBORVolatilityModelMaturityDependentFourParameterExponentialForm2() {
    // Arrange
    RandomVariableFloatFactory randomVariableFactory = new RandomVariableFloatFactory();
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    LIBORVolatilityModelMaturityDependentFourParameterExponentialForm
        actualLiborVolatilityModelMaturityDependentFourParameterExponentialForm =
            new LIBORVolatilityModelMaturityDependentFourParameterExponentialForm(
                randomVariableFactory,
                timeDiscretization,
                liborPeriodDiscretization,
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborVolatilityModelMaturityDependentFourParameterExponentialForm
            .getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(
        Short.SIZE,
        actualLiborVolatilityModelMaturityDependentFourParameterExponentialForm.getParameter()
            .length);
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelMaturityDependentFourParameterExponentialForm
            .getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {
          10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d,
          10.0d, 1.0d
        },
        actualLiborVolatilityModelMaturityDependentFourParameterExponentialForm
            .getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORVolatilityModelMaturityDependentFourParameterExponentialForm#LIBORVolatilityModelMaturityDependentFourParameterExponentialForm(TimeDiscretization,
   * TimeDiscretization, double, double, double, double)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelMaturityDependentFourParameterExponentialForm#LIBORVolatilityModelMaturityDependentFourParameterExponentialForm(TimeDiscretization,
   * TimeDiscretization, double, double, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelMaturityDependentFourParameterExponentialForm.<init>(TimeDiscretization, TimeDiscretization, double, double, double, double)"
  })
  public void testNewLIBORVolatilityModelMaturityDependentFourParameterExponentialForm3() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    LIBORVolatilityModelMaturityDependentFourParameterExponentialForm
        actualLiborVolatilityModelMaturityDependentFourParameterExponentialForm =
            new LIBORVolatilityModelMaturityDependentFourParameterExponentialForm(
                timeDiscretization, liborPeriodDiscretization, 10.0d, 10.0d, 10.0d, 10.0d);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborVolatilityModelMaturityDependentFourParameterExponentialForm
            .getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(
        40,
        actualLiborVolatilityModelMaturityDependentFourParameterExponentialForm.getParameter()
            .length);
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelMaturityDependentFourParameterExponentialForm
            .getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {
          10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d,
          10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d,
          10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d,
          10.0d
        },
        actualLiborVolatilityModelMaturityDependentFourParameterExponentialForm
            .getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORVolatilityModelMaturityDependentFourParameterExponentialForm#LIBORVolatilityModelMaturityDependentFourParameterExponentialForm(TimeDiscretization,
   * TimeDiscretization, double[], double[], double[], double[])}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelMaturityDependentFourParameterExponentialForm#LIBORVolatilityModelMaturityDependentFourParameterExponentialForm(TimeDiscretization,
   * TimeDiscretization, double[], double[], double[], double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelMaturityDependentFourParameterExponentialForm.<init>(TimeDiscretization, TimeDiscretization, double[], double[], double[], double[])"
  })
  public void testNewLIBORVolatilityModelMaturityDependentFourParameterExponentialForm4() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    // Act
    LIBORVolatilityModelMaturityDependentFourParameterExponentialForm
        actualLiborVolatilityModelMaturityDependentFourParameterExponentialForm =
            new LIBORVolatilityModelMaturityDependentFourParameterExponentialForm(
                timeDiscretization,
                liborPeriodDiscretization,
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new double[] {10.0d, 1.0d, 10.0d, 1.0d},
                new double[] {10.0d, 1.0d, 10.0d, 1.0d});

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborVolatilityModelMaturityDependentFourParameterExponentialForm
            .getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(
        Short.SIZE,
        actualLiborVolatilityModelMaturityDependentFourParameterExponentialForm.getParameter()
            .length);
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelMaturityDependentFourParameterExponentialForm
            .getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {
          10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d, 10.0d, 1.0d,
          10.0d, 1.0d
        },
        actualLiborVolatilityModelMaturityDependentFourParameterExponentialForm
            .getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORVolatilityModelMaturityDependentFourParameterExponentialForm#LIBORVolatilityModelMaturityDependentFourParameterExponentialForm(TimeDiscretization,
   * TimeDiscretization, RandomVariable[], RandomVariable[], RandomVariable[], RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelMaturityDependentFourParameterExponentialForm#LIBORVolatilityModelMaturityDependentFourParameterExponentialForm(TimeDiscretization,
   * TimeDiscretization, RandomVariable[], RandomVariable[], RandomVariable[], RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LIBORVolatilityModelMaturityDependentFourParameterExponentialForm.<init>(TimeDiscretization, TimeDiscretization, RandomVariable[], RandomVariable[], RandomVariable[], RandomVariable[])"
  })
  public void testNewLIBORVolatilityModelMaturityDependentFourParameterExponentialForm5() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    RandomVariable[] parameterA = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] parameterB = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] parameterC = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] parameterD = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    // Act
    LIBORVolatilityModelMaturityDependentFourParameterExponentialForm
        actualLiborVolatilityModelMaturityDependentFourParameterExponentialForm =
            new LIBORVolatilityModelMaturityDependentFourParameterExponentialForm(
                timeDiscretization,
                liborPeriodDiscretization,
                parameterA,
                parameterB,
                parameterC,
                parameterD);

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualLiborVolatilityModelMaturityDependentFourParameterExponentialForm
            .getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(
        4,
        actualLiborVolatilityModelMaturityDependentFourParameterExponentialForm.getParameter()
            .length);
    assertSame(
        liborPeriodDiscretization,
        actualLiborVolatilityModelMaturityDependentFourParameterExponentialForm
            .getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {10.0d, 10.0d, 10.0d, 10.0d},
        actualLiborVolatilityModelMaturityDependentFourParameterExponentialForm
            .getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link LIBORVolatilityModelMaturityDependentFourParameterExponentialForm#getParameter()}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelMaturityDependentFourParameterExponentialForm#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable[] LIBORVolatilityModelMaturityDependentFourParameterExponentialForm.getParameter()"
  })
  public void testGetParameter() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelMaturityDependentFourParameterExponentialForm
        liborVolatilityModelMaturityDependentFourParameterExponentialForm =
            new LIBORVolatilityModelMaturityDependentFourParameterExponentialForm(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                10.0d,
                10.0d,
                10.0d,
                10.0d);

    // Act
    RandomVariable[] actualParameter =
        liborVolatilityModelMaturityDependentFourParameterExponentialForm.getParameter();

    // Assert
    RandomVariable randomVariable = actualParameter[0];
    assertTrue(randomVariable instanceof Scalar);
    RandomVariable randomVariable2 = actualParameter[10];
    assertTrue(randomVariable2 instanceof Scalar);
    RandomVariable randomVariable3 = actualParameter[20];
    assertTrue(randomVariable3 instanceof Scalar);
    RandomVariable randomVariable4 = actualParameter[30];
    assertTrue(randomVariable4 instanceof Scalar);
    assertEquals(40, actualParameter.length);
    assertSame(randomVariable, actualParameter[1]);
    assertSame(randomVariable, actualParameter[2]);
    assertSame(randomVariable, actualParameter[3]);
    assertSame(randomVariable, actualParameter[4]);
    assertSame(randomVariable, actualParameter[5]);
    assertSame(randomVariable, actualParameter[6]);
    assertSame(randomVariable, actualParameter[7]);
    assertSame(randomVariable, actualParameter[8]);
    assertSame(randomVariable, actualParameter[9]);
    assertSame(randomVariable2, actualParameter[11]);
    assertSame(randomVariable2, actualParameter[12]);
    assertSame(randomVariable2, actualParameter[13]);
    assertSame(randomVariable2, actualParameter[14]);
    assertSame(randomVariable2, actualParameter[15]);
    assertSame(randomVariable2, actualParameter[17]);
    assertSame(randomVariable2, actualParameter[18]);
    assertSame(randomVariable2, actualParameter[19]);
    assertSame(randomVariable2, actualParameter[Short.SIZE]);
    assertSame(randomVariable3, actualParameter[21]);
    assertSame(randomVariable3, actualParameter[22]);
    assertSame(randomVariable3, actualParameter[23]);
    assertSame(randomVariable3, actualParameter[24]);
    assertSame(randomVariable3, actualParameter[25]);
    assertSame(randomVariable3, actualParameter[26]);
    assertSame(randomVariable3, actualParameter[27]);
    assertSame(randomVariable3, actualParameter[28]);
    assertSame(randomVariable3, actualParameter[29]);
    assertSame(randomVariable4, actualParameter[31]);
    assertSame(randomVariable4, actualParameter[33]);
    assertSame(randomVariable4, actualParameter[34]);
    assertSame(randomVariable4, actualParameter[35]);
    assertSame(randomVariable4, actualParameter[36]);
    assertSame(randomVariable4, actualParameter[37]);
    assertSame(randomVariable4, actualParameter[38]);
    assertSame(randomVariable4, actualParameter[39]);
    assertSame(randomVariable4, actualParameter[Integer.SIZE]);
  }

  /**
   * Test {@link
   * LIBORVolatilityModelMaturityDependentFourParameterExponentialForm#getCloneWithModifiedParameter(RandomVariable[])}.
   *
   * <ul>
   *   <li>Then TimeDiscretization return {@link TenorFromArray}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelMaturityDependentFourParameterExponentialForm#getCloneWithModifiedParameter(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORVolatilityModelMaturityDependentFourParameterExponentialForm LIBORVolatilityModelMaturityDependentFourParameterExponentialForm.getCloneWithModifiedParameter(RandomVariable[])"
  })
  public void testGetCloneWithModifiedParameter_thenTimeDiscretizationReturnTenorFromArray() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    RandomVariable[] parameterA = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] parameterC = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};
    RandomVariable[] parameterD = new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)};

    LIBORVolatilityModelMaturityDependentFourParameterExponentialForm
        liborVolatilityModelMaturityDependentFourParameterExponentialForm =
            new LIBORVolatilityModelMaturityDependentFourParameterExponentialForm(
                timeDiscretization,
                liborPeriodDiscretization,
                parameterA,
                new RandomVariable[] {},
                parameterC,
                parameterD);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray2 =
        new RandomVariableFromDoubleArray(10.0d);

    // Act
    LIBORVolatilityModelMaturityDependentFourParameterExponentialForm
        actualCloneWithModifiedParameter =
            liborVolatilityModelMaturityDependentFourParameterExponentialForm
                .getCloneWithModifiedParameter(
                    new RandomVariable[] {
                      randomVariableFromDoubleArray,
                      randomVariableFromDoubleArray2,
                      new RandomVariableFromDoubleArray(10.0d)
                    });

    // Assert
    TimeDiscretization timeDiscretization2 =
        actualCloneWithModifiedParameter.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(3, actualCloneWithModifiedParameter.getParameter().length);
    assertSame(
        liborPeriodDiscretization, actualCloneWithModifiedParameter.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
  }

  /**
   * Test {@link
   * LIBORVolatilityModelMaturityDependentFourParameterExponentialForm#getVolatility(int, int)}.
   *
   * <ul>
   *   <li>Then return Average is {@code 10.101069204986281}.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelMaturityDependentFourParameterExponentialForm#getVolatility(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORVolatilityModelMaturityDependentFourParameterExponentialForm.getVolatility(int, int)"
  })
  public void testGetVolatility_thenReturnAverageIs10101069204986281() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(new double[] {10.0d, 0.0d, 10.0d, 0.0d});
    LIBORVolatilityModelMaturityDependentFourParameterExponentialForm
        liborVolatilityModelMaturityDependentFourParameterExponentialForm =
            new LIBORVolatilityModelMaturityDependentFourParameterExponentialForm(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                10.0d,
                10.0d,
                10.0d,
                10.0d);

    // Act
    RandomVariable actualVolatility =
        liborVolatilityModelMaturityDependentFourParameterExponentialForm.getVolatility(1, 1);

    // Assert
    assertTrue(actualVolatility instanceof Scalar);
    assertEquals(10.101069204986281d, actualVolatility.getAverage(), 0.0);
    assertEquals(10.101069204986281d, actualVolatility.getMax(), 0.0);
    assertEquals(10.101069204986281d, actualVolatility.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualVolatility.expectation();
    assertSame(actualVolatility, actualExpectationResult);
  }

  /**
   * Test {@link
   * LIBORVolatilityModelMaturityDependentFourParameterExponentialForm#getVolatility(int, int)}.
   *
   * <ul>
   *   <li>Then return Average is zero.
   * </ul>
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelMaturityDependentFourParameterExponentialForm#getVolatility(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "RandomVariable LIBORVolatilityModelMaturityDependentFourParameterExponentialForm.getVolatility(int, int)"
  })
  public void testGetVolatility_thenReturnAverageIsZero() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    LIBORVolatilityModelMaturityDependentFourParameterExponentialForm
        liborVolatilityModelMaturityDependentFourParameterExponentialForm =
            new LIBORVolatilityModelMaturityDependentFourParameterExponentialForm(
                timeDiscretization,
                new TenorFromArray(10.0d, 10, 0.5d),
                10.0d,
                10.0d,
                10.0d,
                10.0d);

    // Act
    RandomVariable actualVolatility =
        liborVolatilityModelMaturityDependentFourParameterExponentialForm.getVolatility(1, 1);

    // Assert
    assertTrue(actualVolatility instanceof Scalar);
    assertEquals(0.0d, actualVolatility.getAverage(), 0.0);
    assertEquals(0.0d, actualVolatility.getMax(), 0.0);
    assertEquals(0.0d, actualVolatility.getMin(), 0.0);
    RandomVariable actualExpectationResult = actualVolatility.expectation();
    assertSame(actualVolatility, actualExpectationResult);
  }

  /**
   * Test {@link LIBORVolatilityModelMaturityDependentFourParameterExponentialForm#clone()}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelMaturityDependentFourParameterExponentialForm#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object LIBORVolatilityModelMaturityDependentFourParameterExponentialForm.clone()"
  })
  public void testClone() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelMaturityDependentFourParameterExponentialForm
        liborVolatilityModelMaturityDependentFourParameterExponentialForm =
            new LIBORVolatilityModelMaturityDependentFourParameterExponentialForm(
                timeDiscretization, liborPeriodDiscretization, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    Object actualCloneResult =
        liborVolatilityModelMaturityDependentFourParameterExponentialForm.clone();

    // Assert
    assertTrue(
        actualCloneResult
            instanceof LIBORVolatilityModelMaturityDependentFourParameterExponentialForm);
    TimeDiscretization timeDiscretization2 =
        ((LIBORVolatilityModelMaturityDependentFourParameterExponentialForm) actualCloneResult)
            .getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(
        40,
        ((LIBORVolatilityModelMaturityDependentFourParameterExponentialForm) actualCloneResult)
            .getParameter()
            .length);
    assertSame(
        liborPeriodDiscretization,
        ((LIBORVolatilityModelMaturityDependentFourParameterExponentialForm) actualCloneResult)
            .getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {
          10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d,
          10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d,
          10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d,
          10.0d
        },
        ((LIBORVolatilityModelMaturityDependentFourParameterExponentialForm) actualCloneResult)
            .getParameterAsDouble(),
        0.0);
  }

  /**
   * Test {@link
   * LIBORVolatilityModelMaturityDependentFourParameterExponentialForm#getCloneWithModifiedData(Map)}.
   *
   * <p>Method under test: {@link
   * LIBORVolatilityModelMaturityDependentFourParameterExponentialForm#getCloneWithModifiedData(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "LIBORVolatilityModel LIBORVolatilityModelMaturityDependentFourParameterExponentialForm.getCloneWithModifiedData(Map)"
  })
  public void testGetCloneWithModifiedData() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);
    TenorFromArray liborPeriodDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    LIBORVolatilityModelMaturityDependentFourParameterExponentialForm
        liborVolatilityModelMaturityDependentFourParameterExponentialForm =
            new LIBORVolatilityModelMaturityDependentFourParameterExponentialForm(
                timeDiscretization, liborPeriodDiscretization, 10.0d, 10.0d, 10.0d, 10.0d);

    // Act
    LIBORVolatilityModel actualCloneWithModifiedData =
        liborVolatilityModelMaturityDependentFourParameterExponentialForm.getCloneWithModifiedData(
            null);

    // Assert
    assertTrue(
        actualCloneWithModifiedData
            instanceof LIBORVolatilityModelMaturityDependentFourParameterExponentialForm);
    TimeDiscretization timeDiscretization2 = actualCloneWithModifiedData.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(40, actualCloneWithModifiedData.getParameter().length);
    assertSame(
        liborPeriodDiscretization, actualCloneWithModifiedData.getLiborPeriodDiscretization());
    assertSame(timeDiscretization, timeDiscretization2);
    assertArrayEquals(
        new double[] {
          10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d,
          10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d,
          10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, 10.0d,
          10.0d
        },
        actualCloneWithModifiedData.getParameterAsDouble(),
        0.0);
  }
}
