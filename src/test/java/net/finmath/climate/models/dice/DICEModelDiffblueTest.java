package net.finmath.climate.models.dice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;
import net.finmath.climate.models.CarbonConcentration;
import net.finmath.climate.models.Temperature;
import net.finmath.climate.models.dice.submodels.CarbonConcentration3DScalar;
import net.finmath.climate.models.dice.submodels.Temperature2DScalar;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import net.finmath.time.TenorFromArray;
import net.finmath.time.TimeDiscretization;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DICEModelDiffblueTest {
  /**
   * Test {@link DICEModel#DICEModel(TimeDiscretization, UnaryOperator)}.
   *
   * <p>Method under test: {@link DICEModel#DICEModel(TimeDiscretization, UnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DICEModel.<init>(TimeDiscretization, UnaryOperator)"})
  public void testNewDICEModel() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    UnaryOperator<Double> abatementFunction = mock(UnaryOperator.class);
    when(abatementFunction.apply(Mockito.<Double>any())).thenReturn(10.0d);

    // Act
    DICEModel actualDiceModel = new DICEModel(timeDiscretization, abatementFunction);

    // Assert
    verify(abatementFunction, atLeast(1)).apply(Mockito.<Double>any());
    CarbonConcentration[] carbonConcentration = actualDiceModel.getCarbonConcentration();
    assertTrue(carbonConcentration instanceof CarbonConcentration3DScalar[]);
    assertTrue(actualDiceModel.getAbatementCost() instanceof Scalar);
    assertTrue(actualDiceModel.getDamageCost() instanceof Scalar);
    assertTrue(actualDiceModel.getValue() instanceof Scalar);
    TimeDiscretization timeDiscretization2 = actualDiceModel.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(11, actualDiceModel.getAbatement().length);
    assertEquals(11, actualDiceModel.getAbatementCosts().length);
    assertEquals(11, actualDiceModel.getConsumptions().length);
    assertEquals(11, actualDiceModel.getDamage().length);
    assertEquals(11, carbonConcentration.length);
    assertSame(timeDiscretization, timeDiscretization2);
  }

  /**
   * Test {@link DICEModel#DICEModel(TimeDiscretization, UnaryOperator)}.
   *
   * <p>Method under test: {@link DICEModel#DICEModel(TimeDiscretization, UnaryOperator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DICEModel.<init>(TimeDiscretization, UnaryOperator)"})
  public void testNewDICEModel2() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 5.0d);

    UnaryOperator<Double> abatementFunction = mock(UnaryOperator.class);
    when(abatementFunction.apply(Mockito.<Double>any())).thenReturn(10.0d);

    // Act
    DICEModel actualDiceModel = new DICEModel(timeDiscretization, abatementFunction);

    // Assert
    verify(abatementFunction, atLeast(1)).apply(Mockito.<Double>any());
    CarbonConcentration[] carbonConcentration = actualDiceModel.getCarbonConcentration();
    assertTrue(carbonConcentration instanceof CarbonConcentration3DScalar[]);
    assertTrue(actualDiceModel.getAbatementCost() instanceof Scalar);
    assertTrue(actualDiceModel.getDamageCost() instanceof Scalar);
    assertTrue(actualDiceModel.getValue() instanceof Scalar);
    TimeDiscretization timeDiscretization2 = actualDiceModel.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(11, actualDiceModel.getAbatement().length);
    assertEquals(11, actualDiceModel.getAbatementCosts().length);
    assertEquals(11, actualDiceModel.getConsumptions().length);
    assertEquals(11, actualDiceModel.getDamage().length);
    assertEquals(11, carbonConcentration.length);
    assertSame(timeDiscretization, timeDiscretization2);
  }

  /**
   * Test {@link DICEModel#DICEModel(TimeDiscretization, UnaryOperator, UnaryOperator, double)}.
   *
   * <p>Method under test: {@link DICEModel#DICEModel(TimeDiscretization, UnaryOperator,
   * UnaryOperator, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DICEModel.<init>(TimeDiscretization, UnaryOperator, UnaryOperator, double)"
  })
  public void testNewDICEModel3() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    UnaryOperator<Double> abatementFunction = mock(UnaryOperator.class);
    when(abatementFunction.apply(Mockito.<Double>any())).thenReturn(10.0d);

    UnaryOperator<Double> savingsRateFunction = mock(UnaryOperator.class);
    when(savingsRateFunction.apply(Mockito.<Double>any())).thenReturn(10.0d);

    // Act
    DICEModel actualDiceModel =
        new DICEModel(timeDiscretization, abatementFunction, savingsRateFunction, 10.0d);

    // Assert
    verify(abatementFunction, atLeast(1)).apply(Mockito.<Double>any());
    verify(savingsRateFunction, atLeast(1)).apply(Mockito.<Double>any());
    CarbonConcentration[] carbonConcentration = actualDiceModel.getCarbonConcentration();
    assertTrue(carbonConcentration instanceof CarbonConcentration3DScalar[]);
    assertTrue(actualDiceModel.getAbatementCost() instanceof Scalar);
    assertTrue(actualDiceModel.getDamageCost() instanceof Scalar);
    assertTrue(actualDiceModel.getValue() instanceof Scalar);
    TimeDiscretization timeDiscretization2 = actualDiceModel.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(11, actualDiceModel.getAbatement().length);
    assertEquals(11, actualDiceModel.getAbatementCosts().length);
    assertEquals(11, actualDiceModel.getConsumptions().length);
    assertEquals(11, actualDiceModel.getDamage().length);
    assertEquals(11, carbonConcentration.length);
    assertSame(timeDiscretization, timeDiscretization2);
  }

  /**
   * Test {@link DICEModel#DICEModel(TimeDiscretization, UnaryOperator, UnaryOperator, double)}.
   *
   * <p>Method under test: {@link DICEModel#DICEModel(TimeDiscretization, UnaryOperator,
   * UnaryOperator, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DICEModel.<init>(TimeDiscretization, UnaryOperator, UnaryOperator, double)"
  })
  public void testNewDICEModel4() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 5.0d);

    UnaryOperator<Double> abatementFunction = mock(UnaryOperator.class);
    when(abatementFunction.apply(Mockito.<Double>any())).thenReturn(10.0d);

    UnaryOperator<Double> savingsRateFunction = mock(UnaryOperator.class);
    when(savingsRateFunction.apply(Mockito.<Double>any())).thenReturn(10.0d);

    // Act
    DICEModel actualDiceModel =
        new DICEModel(timeDiscretization, abatementFunction, savingsRateFunction, 10.0d);

    // Assert
    verify(abatementFunction, atLeast(1)).apply(Mockito.<Double>any());
    verify(savingsRateFunction, atLeast(1)).apply(Mockito.<Double>any());
    CarbonConcentration[] carbonConcentration = actualDiceModel.getCarbonConcentration();
    assertTrue(carbonConcentration instanceof CarbonConcentration3DScalar[]);
    assertTrue(actualDiceModel.getAbatementCost() instanceof Scalar);
    assertTrue(actualDiceModel.getDamageCost() instanceof Scalar);
    assertTrue(actualDiceModel.getValue() instanceof Scalar);
    TimeDiscretization timeDiscretization2 = actualDiceModel.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(11, actualDiceModel.getAbatement().length);
    assertEquals(11, actualDiceModel.getAbatementCosts().length);
    assertEquals(11, actualDiceModel.getConsumptions().length);
    assertEquals(11, actualDiceModel.getDamage().length);
    assertEquals(11, carbonConcentration.length);
    assertSame(timeDiscretization, timeDiscretization2);
  }

  /**
   * Test {@link DICEModel#DICEModel(TimeDiscretization, UnaryOperator, UnaryOperator, double,
   * Map)}.
   *
   * <ul>
   *   <li>Then CarbonConcentration return {@code CarbonConcentration3DScalar[]}.
   * </ul>
   *
   * <p>Method under test: {@link DICEModel#DICEModel(TimeDiscretization, UnaryOperator,
   * UnaryOperator, double, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DICEModel.<init>(TimeDiscretization, UnaryOperator, UnaryOperator, double, Map)"
  })
  public void testNewDICEModel_thenCarbonConcentrationReturnCarbonConcentration3DScalar() {
    // Arrange
    TenorFromArray timeDiscretization = new TenorFromArray(10.0d, 10, 0.5d);

    UnaryOperator<Double> abatementFunction = mock(UnaryOperator.class);
    when(abatementFunction.apply(Mockito.<Double>any())).thenReturn(10.0d);

    UnaryOperator<Double> savingsRateFunction = mock(UnaryOperator.class);
    when(savingsRateFunction.apply(Mockito.<Double>any())).thenReturn(10.0d);

    // Act
    DICEModel actualDiceModel =
        new DICEModel(
            timeDiscretization, abatementFunction, savingsRateFunction, 10.0d, new HashMap<>());

    // Assert
    verify(abatementFunction, atLeast(1)).apply(Mockito.<Double>any());
    verify(savingsRateFunction, atLeast(1)).apply(Mockito.<Double>any());
    CarbonConcentration[] carbonConcentration = actualDiceModel.getCarbonConcentration();
    assertTrue(carbonConcentration instanceof CarbonConcentration3DScalar[]);
    Temperature[] temperature = actualDiceModel.getTemperature();
    assertTrue(temperature instanceof Temperature2DScalar[]);
    assertTrue(actualDiceModel.getAbatementCost() instanceof Scalar);
    assertTrue(actualDiceModel.getDamageCost() instanceof Scalar);
    assertTrue(actualDiceModel.getValue() instanceof Scalar);
    TimeDiscretization timeDiscretization2 = actualDiceModel.getTimeDiscretization();
    assertTrue(timeDiscretization2 instanceof TenorFromArray);
    assertEquals(11, actualDiceModel.getAbatement().length);
    assertEquals(11, actualDiceModel.getAbatementCosts().length);
    assertEquals(11, actualDiceModel.getConsumptions().length);
    assertEquals(11, actualDiceModel.getDamage().length);
    assertEquals(11, actualDiceModel.getDamageCosts().length);
    assertEquals(11, actualDiceModel.getEmission().length);
    assertEquals(11, actualDiceModel.getGDP().length);
    assertEquals(11, actualDiceModel.getValues().length);
    assertEquals(11, carbonConcentration.length);
    assertEquals(11, temperature.length);
    assertSame(timeDiscretization, timeDiscretization2);
  }

  /**
   * Test {@link DICEModel#getTemperature(double)} with {@code double}.
   *
   * <ul>
   *   <li>Given {@link UnaryOperator} {@link UnaryOperator#apply(Object)} return ten.
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link DICEModel#getTemperature(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable DICEModel.getTemperature(double)"})
  public void testGetTemperatureWithDouble_givenUnaryOperatorApplyReturnTen_thenReturnScalar() {
    // Arrange
    UnaryOperator<Double> abatementFunction = mock(UnaryOperator.class);
    when(abatementFunction.apply(Mockito.<Double>any())).thenReturn(10.0d);
    DICEModel diceModel = new DICEModel(new TenorFromArray(10.0d, 10, 0.5d), abatementFunction);

    // Act
    RandomVariable actualTemperature = diceModel.getTemperature(10.0d);

    // Assert
    verify(abatementFunction, atLeast(1)).apply(Mockito.<Double>any());
    assertTrue(actualTemperature instanceof Scalar);
    assertTrue(actualTemperature.abs() instanceof Scalar);
    assertTrue(actualTemperature.cos() instanceof Scalar);
    assertTrue(actualTemperature.exp() instanceof Scalar);
    assertTrue(actualTemperature.expm1() instanceof Scalar);
    assertTrue(actualTemperature.invert() instanceof Scalar);
    assertTrue(actualTemperature.isNaN() instanceof Scalar);
    assertTrue(actualTemperature.sin() instanceof Scalar);
    assertTrue(actualTemperature.sqrt() instanceof Scalar);
    assertTrue(actualTemperature.squared() instanceof Scalar);
    assertTrue(actualTemperature.variance() instanceof Scalar);
    assertNull(actualTemperature.getRealizations());
    assertNull(actualTemperature.getOperator());
    assertNull(actualTemperature.getRealizationsStream());
    assertEquals(0, actualTemperature.getTypePriority());
    assertEquals(0.0d, actualTemperature.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualTemperature.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualTemperature.getStandardError(), 0.0);
    assertEquals(0.0d, actualTemperature.getVariance(), 0.0);
    assertEquals(0.85d, actualTemperature.getAverage(), 0.0);
    assertEquals(0.85d, actualTemperature.getMax(), 0.0);
    assertEquals(0.85d, actualTemperature.getMin(), 0.0);
    assertEquals(1, actualTemperature.size());
    assertTrue(actualTemperature.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualTemperature.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualTemperature.expectation();
    assertSame(actualTemperature, actualExpectationResult);
  }

  /**
   * Test {@link DICEModel#getValue()}.
   *
   * <ul>
   *   <li>Given {@link UnaryOperator} {@link UnaryOperator#apply(Object)} return ten.
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link DICEModel#getValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable DICEModel.getValue()"})
  public void testGetValue_givenUnaryOperatorApplyReturnTen_thenReturnScalar() {
    // Arrange
    UnaryOperator<Double> abatementFunction = mock(UnaryOperator.class);
    when(abatementFunction.apply(Mockito.<Double>any())).thenReturn(10.0d);
    DICEModel diceModel = new DICEModel(new TenorFromArray(10.0d, 10, 0.5d), abatementFunction);

    // Act
    RandomVariable actualValue = diceModel.getValue();

    // Assert
    verify(abatementFunction, atLeast(1)).apply(Mockito.<Double>any());
    assertTrue(actualValue instanceof Scalar);
    assertTrue(actualValue.abs() instanceof Scalar);
    assertTrue(actualValue.cos() instanceof Scalar);
    assertTrue(actualValue.exp() instanceof Scalar);
    assertTrue(actualValue.expm1() instanceof Scalar);
    assertTrue(actualValue.invert() instanceof Scalar);
    assertTrue(actualValue.isNaN() instanceof Scalar);
    assertTrue(actualValue.sin() instanceof Scalar);
    assertTrue(actualValue.sqrt() instanceof Scalar);
    assertTrue(actualValue.squared() instanceof Scalar);
    assertTrue(actualValue.variance() instanceof Scalar);
    assertNull(actualValue.getRealizations());
    assertNull(actualValue.getOperator());
    assertNull(actualValue.getRealizationsStream());
    assertEquals(0, actualValue.getTypePriority());
    assertEquals(0.0d, actualValue.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualValue.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualValue.getStandardError(), 0.0);
    assertEquals(0.0d, actualValue.getVariance(), 0.0);
    assertEquals(1, actualValue.size());
    assertEquals(22095.12161020445d, actualValue.getAverage(), 0.0);
    assertEquals(22095.12161020445d, actualValue.getMax(), 0.0);
    assertEquals(22095.12161020445d, actualValue.getMin(), 0.0);
    assertTrue(actualValue.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualValue.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualValue.expectation();
    assertSame(actualValue, actualExpectationResult);
  }

  /**
   * Test {@link DICEModel#getValues()}.
   *
   * <ul>
   *   <li>Given {@link UnaryOperator} {@link UnaryOperator#apply(Object)} return ten.
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link DICEModel#getValues()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] DICEModel.getValues()"})
  public void testGetValues_givenUnaryOperatorApplyReturnTen_thenFirstElementReturnScalar() {
    // Arrange
    UnaryOperator<Double> abatementFunction = mock(UnaryOperator.class);
    when(abatementFunction.apply(Mockito.<Double>any())).thenReturn(10.0d);
    DICEModel diceModel = new DICEModel(new TenorFromArray(10.0d, 10, 0.5d), abatementFunction);

    // Act
    RandomVariable[] actualValues = diceModel.getValues();

    // Assert
    verify(abatementFunction, atLeast(1)).apply(Mockito.<Double>any());
    assertTrue(actualValues[0] instanceof Scalar);
    assertTrue(actualValues[1] instanceof Scalar);
    assertTrue(actualValues[10] instanceof Scalar);
    assertTrue(actualValues[2] instanceof Scalar);
    assertTrue(actualValues[3] instanceof Scalar);
    assertTrue(actualValues[4] instanceof Scalar);
    assertTrue(actualValues[5] instanceof Scalar);
    assertTrue(actualValues[6] instanceof Scalar);
    assertTrue(actualValues[7] instanceof Scalar);
    assertTrue(actualValues[8] instanceof Scalar);
    assertTrue(actualValues[9] instanceof Scalar);
    assertEquals(11, actualValues.length);
  }

  /**
   * Test {@link DICEModel#getAbatement()}.
   *
   * <ul>
   *   <li>Given {@link UnaryOperator} {@link UnaryOperator#apply(Object)} return ten.
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link DICEModel#getAbatement()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] DICEModel.getAbatement()"})
  public void testGetAbatement_givenUnaryOperatorApplyReturnTen_thenFirstElementReturnScalar() {
    // Arrange
    UnaryOperator<Double> abatementFunction = mock(UnaryOperator.class);
    when(abatementFunction.apply(Mockito.<Double>any())).thenReturn(10.0d);
    DICEModel diceModel = new DICEModel(new TenorFromArray(10.0d, 10, 0.5d), abatementFunction);

    // Act
    RandomVariable[] actualAbatement = diceModel.getAbatement();

    // Assert
    verify(abatementFunction, atLeast(1)).apply(Mockito.<Double>any());
    assertTrue(actualAbatement[0] instanceof Scalar);
    assertTrue(actualAbatement[1] instanceof Scalar);
    assertTrue(actualAbatement[10] instanceof Scalar);
    assertTrue(actualAbatement[2] instanceof Scalar);
    assertTrue(actualAbatement[3] instanceof Scalar);
    assertTrue(actualAbatement[4] instanceof Scalar);
    assertTrue(actualAbatement[5] instanceof Scalar);
    assertTrue(actualAbatement[6] instanceof Scalar);
    assertTrue(actualAbatement[7] instanceof Scalar);
    assertTrue(actualAbatement[8] instanceof Scalar);
    assertTrue(actualAbatement[9] instanceof Scalar);
    assertEquals(11, actualAbatement.length);
  }

  /**
   * Test {@link DICEModel#getEmission()}.
   *
   * <ul>
   *   <li>Given {@link UnaryOperator} {@link UnaryOperator#apply(Object)} return ten.
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link DICEModel#getEmission()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] DICEModel.getEmission()"})
  public void testGetEmission_givenUnaryOperatorApplyReturnTen_thenFirstElementReturnScalar() {
    // Arrange
    UnaryOperator<Double> abatementFunction = mock(UnaryOperator.class);
    when(abatementFunction.apply(Mockito.<Double>any())).thenReturn(10.0d);
    DICEModel diceModel = new DICEModel(new TenorFromArray(10.0d, 10, 0.5d), abatementFunction);

    // Act
    RandomVariable[] actualEmission = diceModel.getEmission();

    // Assert
    verify(abatementFunction, atLeast(1)).apply(Mockito.<Double>any());
    assertTrue(actualEmission[0] instanceof Scalar);
    assertTrue(actualEmission[1] instanceof Scalar);
    assertTrue(actualEmission[10] instanceof Scalar);
    assertTrue(actualEmission[2] instanceof Scalar);
    assertTrue(actualEmission[3] instanceof Scalar);
    assertTrue(actualEmission[4] instanceof Scalar);
    assertTrue(actualEmission[5] instanceof Scalar);
    assertTrue(actualEmission[6] instanceof Scalar);
    assertTrue(actualEmission[7] instanceof Scalar);
    assertTrue(actualEmission[8] instanceof Scalar);
    assertTrue(actualEmission[9] instanceof Scalar);
    assertEquals(11, actualEmission.length);
  }

  /**
   * Test {@link DICEModel#getDamage()}.
   *
   * <ul>
   *   <li>Given {@link UnaryOperator} {@link UnaryOperator#apply(Object)} return ten.
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link DICEModel#getDamage()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] DICEModel.getDamage()"})
  public void testGetDamage_givenUnaryOperatorApplyReturnTen_thenFirstElementReturnScalar() {
    // Arrange
    UnaryOperator<Double> abatementFunction = mock(UnaryOperator.class);
    when(abatementFunction.apply(Mockito.<Double>any())).thenReturn(10.0d);
    DICEModel diceModel = new DICEModel(new TenorFromArray(10.0d, 10, 0.5d), abatementFunction);

    // Act
    RandomVariable[] actualDamage = diceModel.getDamage();

    // Assert
    verify(abatementFunction, atLeast(1)).apply(Mockito.<Double>any());
    assertTrue(actualDamage[0] instanceof Scalar);
    assertTrue(actualDamage[1] instanceof Scalar);
    assertTrue(actualDamage[10] instanceof Scalar);
    assertTrue(actualDamage[2] instanceof Scalar);
    assertTrue(actualDamage[3] instanceof Scalar);
    assertTrue(actualDamage[4] instanceof Scalar);
    assertTrue(actualDamage[5] instanceof Scalar);
    assertTrue(actualDamage[6] instanceof Scalar);
    assertTrue(actualDamage[7] instanceof Scalar);
    assertTrue(actualDamage[8] instanceof Scalar);
    assertTrue(actualDamage[9] instanceof Scalar);
    assertEquals(11, actualDamage.length);
  }

  /**
   * Test {@link DICEModel#getGDP()}.
   *
   * <ul>
   *   <li>Given {@link UnaryOperator} {@link UnaryOperator#apply(Object)} return ten.
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link DICEModel#getGDP()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] DICEModel.getGDP()"})
  public void testGetGDP_givenUnaryOperatorApplyReturnTen_thenFirstElementReturnScalar() {
    // Arrange
    UnaryOperator<Double> abatementFunction = mock(UnaryOperator.class);
    when(abatementFunction.apply(Mockito.<Double>any())).thenReturn(10.0d);
    DICEModel diceModel = new DICEModel(new TenorFromArray(10.0d, 10, 0.5d), abatementFunction);

    // Act
    RandomVariable[] actualGDP = diceModel.getGDP();

    // Assert
    verify(abatementFunction, atLeast(1)).apply(Mockito.<Double>any());
    assertTrue(actualGDP[0] instanceof Scalar);
    assertTrue(actualGDP[1] instanceof Scalar);
    assertTrue(actualGDP[10] instanceof Scalar);
    assertTrue(actualGDP[2] instanceof Scalar);
    assertTrue(actualGDP[3] instanceof Scalar);
    assertTrue(actualGDP[4] instanceof Scalar);
    assertTrue(actualGDP[5] instanceof Scalar);
    assertTrue(actualGDP[6] instanceof Scalar);
    assertTrue(actualGDP[7] instanceof Scalar);
    assertTrue(actualGDP[8] instanceof Scalar);
    assertTrue(actualGDP[9] instanceof Scalar);
    assertEquals(11, actualGDP.length);
  }

  /**
   * Test {@link DICEModel#getConsumptions()}.
   *
   * <ul>
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link DICEModel#getConsumptions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] DICEModel.getConsumptions()"})
  public void testGetConsumptions_thenFirstElementReturnScalar() {
    // Arrange
    UnaryOperator<Double> abatementFunction = mock(UnaryOperator.class);
    when(abatementFunction.apply(Mockito.<Double>any())).thenReturn(10.0d);
    DICEModel diceModel = new DICEModel(new TenorFromArray(10.0d, 10, 0.5d), abatementFunction);

    // Act
    RandomVariable[] actualConsumptions = diceModel.getConsumptions();

    // Assert
    verify(abatementFunction, atLeast(1)).apply(Mockito.<Double>any());
    assertTrue(actualConsumptions[0] instanceof Scalar);
    assertTrue(actualConsumptions[1] instanceof Scalar);
    assertTrue(actualConsumptions[10] instanceof Scalar);
    assertTrue(actualConsumptions[2] instanceof Scalar);
    assertTrue(actualConsumptions[3] instanceof Scalar);
    assertTrue(actualConsumptions[4] instanceof Scalar);
    assertTrue(actualConsumptions[5] instanceof Scalar);
    assertTrue(actualConsumptions[6] instanceof Scalar);
    assertTrue(actualConsumptions[7] instanceof Scalar);
    assertTrue(actualConsumptions[8] instanceof Scalar);
    assertTrue(actualConsumptions[9] instanceof Scalar);
    assertEquals(11, actualConsumptions.length);
  }

  /**
   * Test {@link DICEModel#getAbatementCosts()}.
   *
   * <ul>
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link DICEModel#getAbatementCosts()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] DICEModel.getAbatementCosts()"})
  public void testGetAbatementCosts_thenFirstElementReturnScalar() {
    // Arrange
    UnaryOperator<Double> abatementFunction = mock(UnaryOperator.class);
    when(abatementFunction.apply(Mockito.<Double>any())).thenReturn(10.0d);
    DICEModel diceModel = new DICEModel(new TenorFromArray(10.0d, 10, 0.5d), abatementFunction);

    // Act
    RandomVariable[] actualAbatementCosts = diceModel.getAbatementCosts();

    // Assert
    verify(abatementFunction, atLeast(1)).apply(Mockito.<Double>any());
    assertTrue(actualAbatementCosts[0] instanceof Scalar);
    assertTrue(actualAbatementCosts[1] instanceof Scalar);
    assertTrue(actualAbatementCosts[10] instanceof Scalar);
    assertTrue(actualAbatementCosts[2] instanceof Scalar);
    assertTrue(actualAbatementCosts[3] instanceof Scalar);
    assertTrue(actualAbatementCosts[4] instanceof Scalar);
    assertTrue(actualAbatementCosts[5] instanceof Scalar);
    assertTrue(actualAbatementCosts[6] instanceof Scalar);
    assertTrue(actualAbatementCosts[7] instanceof Scalar);
    assertTrue(actualAbatementCosts[8] instanceof Scalar);
    assertTrue(actualAbatementCosts[9] instanceof Scalar);
    assertEquals(11, actualAbatementCosts.length);
  }

  /**
   * Test {@link DICEModel#getAbatementCost()}.
   *
   * <ul>
   *   <li>Given {@link UnaryOperator} {@link UnaryOperator#apply(Object)} return ten.
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link DICEModel#getAbatementCost()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable DICEModel.getAbatementCost()"})
  public void testGetAbatementCost_givenUnaryOperatorApplyReturnTen_thenReturnScalar() {
    // Arrange
    UnaryOperator<Double> abatementFunction = mock(UnaryOperator.class);
    when(abatementFunction.apply(Mockito.<Double>any())).thenReturn(10.0d);
    DICEModel diceModel = new DICEModel(new TenorFromArray(10.0d, 10, 0.5d), abatementFunction);

    // Act
    RandomVariable actualAbatementCost = diceModel.getAbatementCost();

    // Assert
    verify(abatementFunction, atLeast(1)).apply(Mockito.<Double>any());
    assertTrue(actualAbatementCost instanceof Scalar);
    assertTrue(actualAbatementCost.abs() instanceof Scalar);
    assertTrue(actualAbatementCost.cos() instanceof Scalar);
    assertTrue(actualAbatementCost.exp() instanceof Scalar);
    assertTrue(actualAbatementCost.expm1() instanceof Scalar);
    assertTrue(actualAbatementCost.invert() instanceof Scalar);
    assertTrue(actualAbatementCost.isNaN() instanceof Scalar);
    assertTrue(actualAbatementCost.sin() instanceof Scalar);
    assertTrue(actualAbatementCost.sqrt() instanceof Scalar);
    assertTrue(actualAbatementCost.squared() instanceof Scalar);
    assertTrue(actualAbatementCost.variance() instanceof Scalar);
    assertNull(actualAbatementCost.getRealizations());
    assertNull(actualAbatementCost.getOperator());
    assertNull(actualAbatementCost.getRealizationsStream());
    assertEquals(-2651.8303665964468d, actualAbatementCost.getAverage(), 0.0);
    assertEquals(-2651.8303665964468d, actualAbatementCost.getMax(), 0.0);
    assertEquals(-2651.8303665964468d, actualAbatementCost.getMin(), 0.0);
    assertEquals(0, actualAbatementCost.getTypePriority());
    assertEquals(0.0d, actualAbatementCost.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualAbatementCost.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualAbatementCost.getStandardError(), 0.0);
    assertEquals(0.0d, actualAbatementCost.getVariance(), 0.0);
    assertEquals(1, actualAbatementCost.size());
    assertTrue(actualAbatementCost.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualAbatementCost.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualAbatementCost.expectation();
    assertSame(actualAbatementCost, actualExpectationResult);
  }

  /**
   * Test {@link DICEModel#getDamageCosts()}.
   *
   * <ul>
   *   <li>Given {@link UnaryOperator} {@link UnaryOperator#apply(Object)} return ten.
   *   <li>Then first element return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link DICEModel#getDamageCosts()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] DICEModel.getDamageCosts()"})
  public void testGetDamageCosts_givenUnaryOperatorApplyReturnTen_thenFirstElementReturnScalar() {
    // Arrange
    UnaryOperator<Double> abatementFunction = mock(UnaryOperator.class);
    when(abatementFunction.apply(Mockito.<Double>any())).thenReturn(10.0d);
    DICEModel diceModel = new DICEModel(new TenorFromArray(10.0d, 10, 0.5d), abatementFunction);

    // Act
    RandomVariable[] actualDamageCosts = diceModel.getDamageCosts();

    // Assert
    verify(abatementFunction, atLeast(1)).apply(Mockito.<Double>any());
    assertTrue(actualDamageCosts[0] instanceof Scalar);
    assertTrue(actualDamageCosts[1] instanceof Scalar);
    assertTrue(actualDamageCosts[10] instanceof Scalar);
    assertTrue(actualDamageCosts[2] instanceof Scalar);
    assertTrue(actualDamageCosts[3] instanceof Scalar);
    assertTrue(actualDamageCosts[4] instanceof Scalar);
    assertTrue(actualDamageCosts[5] instanceof Scalar);
    assertTrue(actualDamageCosts[6] instanceof Scalar);
    assertTrue(actualDamageCosts[7] instanceof Scalar);
    assertTrue(actualDamageCosts[8] instanceof Scalar);
    assertTrue(actualDamageCosts[9] instanceof Scalar);
    assertEquals(11, actualDamageCosts.length);
  }

  /**
   * Test {@link DICEModel#getDamageCost()}.
   *
   * <ul>
   *   <li>Given {@link UnaryOperator} {@link UnaryOperator#apply(Object)} return ten.
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link DICEModel#getDamageCost()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable DICEModel.getDamageCost()"})
  public void testGetDamageCost_givenUnaryOperatorApplyReturnTen_thenReturnScalar() {
    // Arrange
    UnaryOperator<Double> abatementFunction = mock(UnaryOperator.class);
    when(abatementFunction.apply(Mockito.<Double>any())).thenReturn(10.0d);
    DICEModel diceModel = new DICEModel(new TenorFromArray(10.0d, 10, 0.5d), abatementFunction);

    // Act
    RandomVariable actualDamageCost = diceModel.getDamageCost();

    // Assert
    verify(abatementFunction, atLeast(1)).apply(Mockito.<Double>any());
    assertTrue(actualDamageCost instanceof Scalar);
    assertTrue(actualDamageCost.abs() instanceof Scalar);
    assertTrue(actualDamageCost.cos() instanceof Scalar);
    assertTrue(actualDamageCost.exp() instanceof Scalar);
    assertTrue(actualDamageCost.expm1() instanceof Scalar);
    assertTrue(actualDamageCost.invert() instanceof Scalar);
    assertTrue(actualDamageCost.isNaN() instanceof Scalar);
    assertTrue(actualDamageCost.sin() instanceof Scalar);
    assertTrue(actualDamageCost.sqrt() instanceof Scalar);
    assertTrue(actualDamageCost.squared() instanceof Scalar);
    assertTrue(actualDamageCost.variance() instanceof Scalar);
    assertNull(actualDamageCost.getRealizations());
    assertNull(actualDamageCost.getOperator());
    assertNull(actualDamageCost.getRealizationsStream());
    assertEquals(0, actualDamageCost.getTypePriority());
    assertEquals(0.0d, actualDamageCost.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualDamageCost.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualDamageCost.getStandardError(), 0.0);
    assertEquals(0.0d, actualDamageCost.getVariance(), 0.0);
    assertEquals(1, actualDamageCost.size());
    assertEquals(1.8329723553871946d, actualDamageCost.getAverage(), 0.0);
    assertEquals(1.8329723553871946d, actualDamageCost.getMax(), 0.0);
    assertEquals(1.8329723553871946d, actualDamageCost.getMin(), 0.0);
    assertTrue(actualDamageCost.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualDamageCost.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualDamageCost.expectation();
    assertSame(actualDamageCost, actualExpectationResult);
  }

  /**
   * Test {@link DICEModel#getNumeraire(double)}.
   *
   * <ul>
   *   <li>Given {@link UnaryOperator} {@link UnaryOperator#apply(Object)} return ten.
   *   <li>Then return {@link Scalar}.
   * </ul>
   *
   * <p>Method under test: {@link DICEModel#getNumeraire(double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable DICEModel.getNumeraire(double)"})
  public void testGetNumeraire_givenUnaryOperatorApplyReturnTen_thenReturnScalar() {
    // Arrange
    UnaryOperator<Double> abatementFunction = mock(UnaryOperator.class);
    when(abatementFunction.apply(Mockito.<Double>any())).thenReturn(10.0d);
    DICEModel diceModel = new DICEModel(new TenorFromArray(10.0d, 10, 0.5d), abatementFunction);

    // Act
    RandomVariable actualNumeraire = diceModel.getNumeraire(10.0d);

    // Assert
    verify(abatementFunction, atLeast(1)).apply(Mockito.<Double>any());
    assertTrue(actualNumeraire instanceof Scalar);
    assertTrue(actualNumeraire.abs() instanceof Scalar);
    assertTrue(actualNumeraire.cos() instanceof Scalar);
    assertTrue(actualNumeraire.exp() instanceof Scalar);
    assertTrue(actualNumeraire.expm1() instanceof Scalar);
    assertTrue(actualNumeraire.invert() instanceof Scalar);
    assertTrue(actualNumeraire.isNaN() instanceof Scalar);
    assertTrue(actualNumeraire.sin() instanceof Scalar);
    assertTrue(actualNumeraire.sqrt() instanceof Scalar);
    assertTrue(actualNumeraire.squared() instanceof Scalar);
    assertTrue(actualNumeraire.variance() instanceof Scalar);
    assertNull(actualNumeraire.getRealizations());
    assertNull(actualNumeraire.getOperator());
    assertNull(actualNumeraire.getRealizationsStream());
    assertEquals(0, actualNumeraire.getTypePriority());
    assertEquals(0.0d, actualNumeraire.getSampleVariance(), 0.0);
    assertEquals(0.0d, actualNumeraire.getStandardDeviation(), 0.0);
    assertEquals(0.0d, actualNumeraire.getStandardError(), 0.0);
    assertEquals(0.0d, actualNumeraire.getVariance(), 0.0);
    assertEquals(0.7408182206817179d, actualNumeraire.getAverage(), 0.0);
    assertEquals(0.7408182206817179d, actualNumeraire.getMax(), 0.0);
    assertEquals(0.7408182206817179d, actualNumeraire.getMin(), 0.0);
    assertEquals(1, actualNumeraire.size());
    assertTrue(actualNumeraire.isDeterministic());
    assertEquals(Double.NEGATIVE_INFINITY, actualNumeraire.getFiltrationTime(), 0.0);
    RandomVariable actualExpectationResult = actualNumeraire.expectation();
    assertSame(actualNumeraire, actualExpectationResult);
  }
}
