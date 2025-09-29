package net.finmath.fouriermethod.products.smile;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import java.util.function.Function;
import net.finmath.exception.CalculationException;
import net.finmath.fouriermethod.CharacteristicFunction;
import net.finmath.fouriermethod.models.CharacteristicFunctionModel;
import net.finmath.interpolation.RationalFunctionInterpolation;
import net.finmath.interpolation.RationalFunctionInterpolation.ExtrapolationMethod;
import net.finmath.interpolation.RationalFunctionInterpolation.InterpolationMethod;
import org.apache.commons.math3.complex.Complex;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class EuropeanOptionSmileByCarrMadanDiffblueTest {
  /**
   * Test {@link EuropeanOptionSmileByCarrMadan#EuropeanOptionSmileByCarrMadan(double, double[])}.
   *
   * <p>Method under test: {@link
   * EuropeanOptionSmileByCarrMadan#EuropeanOptionSmileByCarrMadan(double, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EuropeanOptionSmileByCarrMadan.<init>(double, double[])"})
  public void testNewEuropeanOptionSmileByCarrMadan() {
    // Arrange and Act
    EuropeanOptionSmileByCarrMadan actualEuropeanOptionSmileByCarrMadan =
        new EuropeanOptionSmileByCarrMadan(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Assert
    assertNull(actualEuropeanOptionSmileByCarrMadan.getUnderlyingName());
    assertEquals(
        -1.0d, actualEuropeanOptionSmileByCarrMadan.getIntegrationDomainImagUpperBound(), 0.0);
    assertEquals(
        0.0d, actualEuropeanOptionSmileByCarrMadan.getIntegrationDomainImagLowerBound(), 0.0);
    assertEquals(10.0d, actualEuropeanOptionSmileByCarrMadan.getMaturity(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d},
        actualEuropeanOptionSmileByCarrMadan.getStrikes(),
        0.0);
  }

  /**
   * Test {@link EuropeanOptionSmileByCarrMadan#EuropeanOptionSmileByCarrMadan(String, double,
   * double[])}.
   *
   * <p>Method under test: {@link
   * EuropeanOptionSmileByCarrMadan#EuropeanOptionSmileByCarrMadan(String, double, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EuropeanOptionSmileByCarrMadan.<init>(String, double, double[])"})
  public void testNewEuropeanOptionSmileByCarrMadan2() {
    // Arrange and Act
    EuropeanOptionSmileByCarrMadan actualEuropeanOptionSmileByCarrMadan =
        new EuropeanOptionSmileByCarrMadan(
            "Underlying Name", 10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Assert
    assertEquals("Underlying Name", actualEuropeanOptionSmileByCarrMadan.getUnderlyingName());
    assertEquals(
        -1.0d, actualEuropeanOptionSmileByCarrMadan.getIntegrationDomainImagUpperBound(), 0.0);
    assertEquals(
        0.0d, actualEuropeanOptionSmileByCarrMadan.getIntegrationDomainImagLowerBound(), 0.0);
    assertEquals(10.0d, actualEuropeanOptionSmileByCarrMadan.getMaturity(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d},
        actualEuropeanOptionSmileByCarrMadan.getStrikes(),
        0.0);
  }

  /**
   * Test {@link EuropeanOptionSmileByCarrMadan#EuropeanOptionSmileByCarrMadan(String, double,
   * double[], int, double, InterpolationMethod, ExtrapolationMethod)}.
   *
   * <p>Method under test: {@link
   * EuropeanOptionSmileByCarrMadan#EuropeanOptionSmileByCarrMadan(String, double, double[], int,
   * double, InterpolationMethod, RationalFunctionInterpolation.ExtrapolationMethod)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void EuropeanOptionSmileByCarrMadan.<init>(String, double, double[], int, double, InterpolationMethod, RationalFunctionInterpolation.ExtrapolationMethod)"
  })
  public void testNewEuropeanOptionSmileByCarrMadan3() {
    // Arrange and Act
    EuropeanOptionSmileByCarrMadan actualEuropeanOptionSmileByCarrMadan =
        new EuropeanOptionSmileByCarrMadan(
            "Underlying Name",
            10.0d,
            new double[] {10.0d, -1.0d, 10.0d, -1.0d},
            10,
            10.0d,
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT);

    // Assert
    assertEquals("Underlying Name", actualEuropeanOptionSmileByCarrMadan.getUnderlyingName());
    assertEquals(
        -1.0d, actualEuropeanOptionSmileByCarrMadan.getIntegrationDomainImagUpperBound(), 0.0);
    assertEquals(
        0.0d, actualEuropeanOptionSmileByCarrMadan.getIntegrationDomainImagLowerBound(), 0.0);
    assertEquals(10.0d, actualEuropeanOptionSmileByCarrMadan.getMaturity(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d},
        actualEuropeanOptionSmileByCarrMadan.getStrikes(),
        0.0);
  }

  /**
   * Test {@link EuropeanOptionSmileByCarrMadan#getValue(double, CharacteristicFunctionModel)}.
   *
   * <p>Method under test: {@link EuropeanOptionSmileByCarrMadan#getValue(double,
   * CharacteristicFunctionModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map EuropeanOptionSmileByCarrMadan.getValue(double, CharacteristicFunctionModel)"
  })
  public void testGetValue() throws CalculationException {
    // Arrange
    EuropeanOptionSmileByCarrMadan europeanOptionSmileByCarrMadan =
        new EuropeanOptionSmileByCarrMadan(
            "Underlying Name", 0.5d, new double[] {0.5d, -1.0d, 0.5d, -1.0d});

    CharacteristicFunction characteristicFunction = mock(CharacteristicFunction.class);
    when(characteristicFunction.apply(Mockito.<Complex>any())).thenReturn(Complex.valueOf(10.0d));

    CharacteristicFunctionModel model = mock(CharacteristicFunctionModel.class);
    when(model.apply(anyDouble())).thenReturn(characteristicFunction);

    // Act
    Map<String, Function<Double, Double>> actualValue =
        europeanOptionSmileByCarrMadan.getValue(10.0d, model);

    // Assert
    verify(characteristicFunction, atLeast(1)).apply(Mockito.<Complex>any());
    verify(model).apply(0.5d);
    assertEquals(1, actualValue.size());
    assertTrue(actualValue.containsKey("valuePerStrike"));
  }

  /**
   * Test {@link EuropeanOptionSmileByCarrMadan#getValue(double, CharacteristicFunctionModel)}.
   *
   * <p>Method under test: {@link EuropeanOptionSmileByCarrMadan#getValue(double,
   * CharacteristicFunctionModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map EuropeanOptionSmileByCarrMadan.getValue(double, CharacteristicFunctionModel)"
  })
  public void testGetValue2() throws CalculationException {
    // Arrange
    EuropeanOptionSmileByCarrMadan europeanOptionSmileByCarrMadan =
        new EuropeanOptionSmileByCarrMadan(
            "Underlying Name",
            0.5d,
            new double[] {0.5d, -1.0d, 0.5d, -1.0d},
            1,
            0.5d,
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT);

    CharacteristicFunction characteristicFunction = mock(CharacteristicFunction.class);
    when(characteristicFunction.apply(Mockito.<Complex>any())).thenReturn(Complex.valueOf(10.0d));

    CharacteristicFunctionModel model = mock(CharacteristicFunctionModel.class);
    when(model.apply(anyDouble())).thenReturn(characteristicFunction);

    // Act
    Map<String, Function<Double, Double>> actualValue =
        europeanOptionSmileByCarrMadan.getValue(10.0d, model);

    // Assert
    verify(characteristicFunction, atLeast(1)).apply(Mockito.<Complex>any());
    verify(model).apply(0.5d);
    assertEquals(1, actualValue.size());
    assertTrue(actualValue.containsKey("valuePerStrike"));
  }

  /**
   * Test {@link EuropeanOptionSmileByCarrMadan#getValue(double, CharacteristicFunctionModel)}.
   *
   * <p>Method under test: {@link EuropeanOptionSmileByCarrMadan#getValue(double,
   * CharacteristicFunctionModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map EuropeanOptionSmileByCarrMadan.getValue(double, CharacteristicFunctionModel)"
  })
  public void testGetValue3() throws CalculationException {
    // Arrange
    EuropeanOptionSmileByCarrMadan europeanOptionSmileByCarrMadan =
        new EuropeanOptionSmileByCarrMadan(
            "Underlying Name",
            0.5d,
            new double[] {0.5d, -1.0d, 0.5d, -1.0d},
            2,
            0.5d,
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT);

    CharacteristicFunction characteristicFunction = mock(CharacteristicFunction.class);
    when(characteristicFunction.apply(Mockito.<Complex>any())).thenReturn(Complex.valueOf(10.0d));

    CharacteristicFunctionModel model = mock(CharacteristicFunctionModel.class);
    when(model.apply(anyDouble())).thenReturn(characteristicFunction);

    // Act
    Map<String, Function<Double, Double>> actualValue =
        europeanOptionSmileByCarrMadan.getValue(10.0d, model);

    // Assert
    verify(characteristicFunction, atLeast(1)).apply(Mockito.<Complex>any());
    verify(model).apply(0.5d);
    assertEquals(1, actualValue.size());
    assertTrue(actualValue.containsKey("valuePerStrike"));
  }

  /**
   * Test {@link EuropeanOptionSmileByCarrMadan#getValue(double, CharacteristicFunctionModel)}.
   *
   * <p>Method under test: {@link EuropeanOptionSmileByCarrMadan#getValue(double,
   * CharacteristicFunctionModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map EuropeanOptionSmileByCarrMadan.getValue(double, CharacteristicFunctionModel)"
  })
  public void testGetValue4() throws CalculationException {
    // Arrange
    EuropeanOptionSmileByCarrMadan europeanOptionSmileByCarrMadan =
        new EuropeanOptionSmileByCarrMadan(
            "Underlying Name",
            0.5d,
            new double[] {0.5d, -1.0d, 0.5d, -1.0d},
            1,
            0.0d,
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT);

    CharacteristicFunction characteristicFunction = mock(CharacteristicFunction.class);
    when(characteristicFunction.apply(Mockito.<Complex>any())).thenReturn(Complex.valueOf(10.0d));

    CharacteristicFunctionModel model = mock(CharacteristicFunctionModel.class);
    when(model.apply(anyDouble())).thenReturn(characteristicFunction);

    // Act
    Map<String, Function<Double, Double>> actualValue =
        europeanOptionSmileByCarrMadan.getValue(10.0d, model);

    // Assert
    verify(characteristicFunction, atLeast(1)).apply(Mockito.<Complex>any());
    verify(model).apply(0.5d);
    assertEquals(1, actualValue.size());
    assertTrue(actualValue.containsKey("valuePerStrike"));
  }

  /**
   * Test {@link EuropeanOptionSmileByCarrMadan#getValue(double, CharacteristicFunctionModel)}.
   *
   * <p>Method under test: {@link EuropeanOptionSmileByCarrMadan#getValue(double,
   * CharacteristicFunctionModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map EuropeanOptionSmileByCarrMadan.getValue(double, CharacteristicFunctionModel)"
  })
  public void testGetValue5() throws CalculationException {
    // Arrange
    EuropeanOptionSmileByCarrMadan europeanOptionSmileByCarrMadan =
        new EuropeanOptionSmileByCarrMadan(
            "Underlying Name",
            0.5d,
            new double[] {0.5d, -1.0d, 0.5d, -1.0d},
            1,
            Double.NaN,
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT);

    CharacteristicFunction characteristicFunction = mock(CharacteristicFunction.class);
    when(characteristicFunction.apply(Mockito.<Complex>any())).thenReturn(Complex.valueOf(10.0d));

    CharacteristicFunctionModel model = mock(CharacteristicFunctionModel.class);
    when(model.apply(anyDouble())).thenReturn(characteristicFunction);

    // Act
    Map<String, Function<Double, Double>> actualValue =
        europeanOptionSmileByCarrMadan.getValue(10.0d, model);

    // Assert
    verify(characteristicFunction, atLeast(1)).apply(Mockito.<Complex>any());
    verify(model).apply(0.5d);
    assertEquals(1, actualValue.size());
    assertTrue(actualValue.containsKey("valuePerStrike"));
  }

  /**
   * Test {@link EuropeanOptionSmileByCarrMadan#getValue(double, CharacteristicFunctionModel)}.
   *
   * <ul>
   *   <li>Given {@link CharacteristicFunction} {@link CharacteristicFunction#apply(Object)} return
   *       valueOf {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link EuropeanOptionSmileByCarrMadan#getValue(double,
   * CharacteristicFunctionModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map EuropeanOptionSmileByCarrMadan.getValue(double, CharacteristicFunctionModel)"
  })
  public void testGetValue_givenCharacteristicFunctionApplyReturnValueOfNaN()
      throws CalculationException {
    // Arrange
    EuropeanOptionSmileByCarrMadan europeanOptionSmileByCarrMadan =
        new EuropeanOptionSmileByCarrMadan(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    CharacteristicFunction characteristicFunction = mock(CharacteristicFunction.class);
    when(characteristicFunction.apply(Mockito.<Complex>any()))
        .thenReturn(Complex.valueOf(Double.NaN));

    CharacteristicFunctionModel model = mock(CharacteristicFunctionModel.class);
    when(model.apply(anyDouble())).thenReturn(characteristicFunction);

    // Act
    Map<String, Function<Double, Double>> actualValue =
        europeanOptionSmileByCarrMadan.getValue(10.0d, model);

    // Assert
    verify(characteristicFunction, atLeast(1)).apply(Mockito.<Complex>any());
    verify(model).apply(10.0d);
    assertEquals(1, actualValue.size());
    assertTrue(actualValue.containsKey("valuePerStrike"));
  }

  /**
   * Test {@link EuropeanOptionSmileByCarrMadan#getCloneWithModifiedParameters(double, double[])}.
   *
   * <p>Method under test: {@link
   * EuropeanOptionSmileByCarrMadan#getCloneWithModifiedParameters(double, double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "EuropeanOptionSmile EuropeanOptionSmileByCarrMadan.getCloneWithModifiedParameters(double, double[])"
  })
  public void testGetCloneWithModifiedParameters() {
    // Arrange
    EuropeanOptionSmileByCarrMadan europeanOptionSmileByCarrMadan =
        new EuropeanOptionSmileByCarrMadan(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    EuropeanOptionSmile actualCloneWithModifiedParameters =
        europeanOptionSmileByCarrMadan.getCloneWithModifiedParameters(
            10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Assert
    assertTrue(actualCloneWithModifiedParameters instanceof EuropeanOptionSmileByCarrMadan);
    assertNull(actualCloneWithModifiedParameters.getUnderlyingName());
    assertEquals(
        -1.0d, actualCloneWithModifiedParameters.getIntegrationDomainImagUpperBound(), 0.0);
    assertEquals(0.0d, actualCloneWithModifiedParameters.getIntegrationDomainImagLowerBound(), 0.0);
    assertEquals(10.0d, actualCloneWithModifiedParameters.getMaturity(), 0.0);
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d},
        actualCloneWithModifiedParameters.getStrikes(),
        0.0);
  }
}
