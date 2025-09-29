package net.finmath.fouriermethod.products.smile;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.Map;
import net.finmath.modelling.descriptor.SingleAssetEuropeanOptionProductDescriptor;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexField;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class EuropeanOptionSmileDiffblueTest {
  /**
   * Test {@link EuropeanOptionSmile#getMaturity()}.
   *
   * <p>Method under test: {@link EuropeanOptionSmile#getMaturity()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double EuropeanOptionSmile.getMaturity()"})
  public void testGetMaturity() {
    // Arrange
    EuropeanOptionSmileByCarrMadan europeanOptionSmileByCarrMadan =
        new EuropeanOptionSmileByCarrMadan(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertEquals(10.0d, europeanOptionSmileByCarrMadan.getMaturity(), 0.0);
  }

  /**
   * Test {@link EuropeanOptionSmile#getStrikes()}.
   *
   * <p>Method under test: {@link EuropeanOptionSmile#getStrikes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] EuropeanOptionSmile.getStrikes()"})
  public void testGetStrikes() {
    // Arrange
    EuropeanOptionSmileByCarrMadan europeanOptionSmileByCarrMadan =
        new EuropeanOptionSmileByCarrMadan(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertArrayEquals(
        new double[] {10.0d, -1.0d, 10.0d, -1.0d},
        europeanOptionSmileByCarrMadan.getStrikes(),
        0.0);
  }

  /**
   * Test {@link EuropeanOptionSmile#getUnderlyingName()}.
   *
   * <p>Method under test: {@link EuropeanOptionSmile#getUnderlyingName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String EuropeanOptionSmile.getUnderlyingName()"})
  public void testGetUnderlyingName() {
    // Arrange
    EuropeanOptionSmileByCarrMadan europeanOptionSmileByCarrMadan =
        new EuropeanOptionSmileByCarrMadan(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertNull(europeanOptionSmileByCarrMadan.getUnderlyingName());
  }

  /**
   * Test {@link EuropeanOptionSmile#getIntegrationDomainImagLowerBound()}.
   *
   * <p>Method under test: {@link EuropeanOptionSmile#getIntegrationDomainImagLowerBound()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double EuropeanOptionSmile.getIntegrationDomainImagLowerBound()"})
  public void testGetIntegrationDomainImagLowerBound() {
    // Arrange
    EuropeanOptionSmileByCarrMadan europeanOptionSmileByCarrMadan =
        new EuropeanOptionSmileByCarrMadan(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertEquals(0.0d, europeanOptionSmileByCarrMadan.getIntegrationDomainImagLowerBound(), 0.0);
  }

  /**
   * Test {@link EuropeanOptionSmile#getIntegrationDomainImagUpperBound()}.
   *
   * <p>Method under test: {@link EuropeanOptionSmile#getIntegrationDomainImagUpperBound()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double EuropeanOptionSmile.getIntegrationDomainImagUpperBound()"})
  public void testGetIntegrationDomainImagUpperBound() {
    // Arrange
    EuropeanOptionSmileByCarrMadan europeanOptionSmileByCarrMadan =
        new EuropeanOptionSmileByCarrMadan(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertEquals(-1.0d, europeanOptionSmileByCarrMadan.getIntegrationDomainImagUpperBound(), 0.0);
  }

  /**
   * Test {@link EuropeanOptionSmile#apply(Complex)} with {@code Complex}.
   *
   * <ul>
   *   <li>When valueOf {@link Double#NaN}.
   *   <li>Then return {@link Complex#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link EuropeanOptionSmile#apply(Complex)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Complex EuropeanOptionSmile.apply(Complex)"})
  public void testApplyWithComplex_whenValueOfNaN_thenReturnNaN() {
    // Arrange
    EuropeanOptionSmileByCarrMadan europeanOptionSmileByCarrMadan =
        new EuropeanOptionSmileByCarrMadan(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act and Assert
    assertSame(Complex.NaN, europeanOptionSmileByCarrMadan.apply(Complex.valueOf(Double.NaN)));
  }

  /**
   * Test {@link EuropeanOptionSmile#apply(Complex)} with {@code Complex}.
   *
   * <ul>
   *   <li>When valueOf ten.
   *   <li>Then return Real is minus one hundred.
   * </ul>
   *
   * <p>Method under test: {@link EuropeanOptionSmile#apply(Complex)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Complex EuropeanOptionSmile.apply(Complex)"})
  public void testApplyWithComplex_whenValueOfTen_thenReturnRealIsMinusOneHundred() {
    // Arrange
    EuropeanOptionSmileByCarrMadan europeanOptionSmileByCarrMadan =
        new EuropeanOptionSmileByCarrMadan(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    Complex actualApplyResult = europeanOptionSmileByCarrMadan.apply(Complex.valueOf(10.0d));

    // Assert
    assertEquals(-100.0d, actualApplyResult.getReal(), 0.0);
    assertEquals(10.0d, actualApplyResult.getImaginary(), 0.0);
    assertEquals(100.4987562112089d, actualApplyResult.abs(), 0.0);
    assertEquals(3.0419240010986313d, actualApplyResult.getArgument(), 0.0);
    assertFalse(actualApplyResult.isNaN());
    ComplexField field = actualApplyResult.getField();
    assertSame(field, field.getOne().getField());
    assertSame(field, field.getZero().getField());
  }

  /**
   * Test {@link EuropeanOptionSmile#getDescriptors(LocalDate)}.
   *
   * <ul>
   *   <li>Then return minus one Maturity toString is {@code 1979-12-30}.
   * </ul>
   *
   * <p>Method under test: {@link EuropeanOptionSmile#getDescriptors(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map EuropeanOptionSmile.getDescriptors(LocalDate)"})
  public void testGetDescriptors_thenReturnMinusOneMaturityToStringIs19791230() {
    // Arrange
    EuropeanOptionSmileByCarrMadan europeanOptionSmileByCarrMadan =
        new EuropeanOptionSmileByCarrMadan(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    Map<Double, SingleAssetEuropeanOptionProductDescriptor> actualDescriptors =
        europeanOptionSmileByCarrMadan.getDescriptors(LocalDate.of(1970, 1, 1));

    // Assert
    assertEquals(2, actualDescriptors.size());
    SingleAssetEuropeanOptionProductDescriptor getResult = actualDescriptors.get(-1.0d);
    LocalDate maturity = getResult.getMaturity();
    assertEquals("1979-12-30", maturity.toString());
    assertNull(getResult.getUnderlyingName());
    SingleAssetEuropeanOptionProductDescriptor getResult2 = actualDescriptors.get(10.0d);
    assertNull(getResult2.getUnderlyingName());
    assertEquals(-1.0d, getResult.getStrike().doubleValue(), 0.0);
    assertEquals(10.0d, getResult2.getStrike().doubleValue(), 0.0);
    assertSame(maturity, getResult2.getMaturity());
  }

  /**
   * Test {@link EuropeanOptionSmile#getDescriptors(LocalDate)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return minus one Maturity is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link EuropeanOptionSmile#getDescriptors(LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map EuropeanOptionSmile.getDescriptors(LocalDate)"})
  public void testGetDescriptors_whenNull_thenReturnMinusOneMaturityIsNull() {
    // Arrange
    EuropeanOptionSmileByCarrMadan europeanOptionSmileByCarrMadan =
        new EuropeanOptionSmileByCarrMadan(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    Map<Double, SingleAssetEuropeanOptionProductDescriptor> actualDescriptors =
        europeanOptionSmileByCarrMadan.getDescriptors(null);

    // Assert
    assertEquals(2, actualDescriptors.size());
    SingleAssetEuropeanOptionProductDescriptor getResult = actualDescriptors.get(-1.0d);
    assertNull(getResult.getUnderlyingName());
    SingleAssetEuropeanOptionProductDescriptor getResult2 = actualDescriptors.get(10.0d);
    assertNull(getResult2.getUnderlyingName());
    assertNull(getResult.getMaturity());
    assertNull(getResult2.getMaturity());
    assertEquals(-1.0d, getResult.getStrike().doubleValue(), 0.0);
    assertEquals(10.0d, getResult2.getStrike().doubleValue(), 0.0);
  }

  /**
   * Test {@link EuropeanOptionSmile#getDescriptor(LocalDate, int)}.
   *
   * <ul>
   *   <li>Then return Maturity toString is {@code 1979-12-30}.
   * </ul>
   *
   * <p>Method under test: {@link EuropeanOptionSmile#getDescriptor(LocalDate, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SingleAssetEuropeanOptionProductDescriptor EuropeanOptionSmile.getDescriptor(LocalDate, int)"
  })
  public void testGetDescriptor_thenReturnMaturityToStringIs19791230()
      throws ArrayIndexOutOfBoundsException {
    // Arrange
    EuropeanOptionSmileByCarrMadan europeanOptionSmileByCarrMadan =
        new EuropeanOptionSmileByCarrMadan(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    SingleAssetEuropeanOptionProductDescriptor actualDescriptor =
        europeanOptionSmileByCarrMadan.getDescriptor(LocalDate.of(1970, 1, 1), 1);

    // Assert
    assertEquals("1979-12-30", actualDescriptor.getMaturity().toString());
    assertNull(actualDescriptor.getUnderlyingName());
    assertEquals(-1.0d, actualDescriptor.getStrike().doubleValue(), 0.0);
  }

  /**
   * Test {@link EuropeanOptionSmile#getDescriptor(LocalDate, int)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Maturity is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link EuropeanOptionSmile#getDescriptor(LocalDate, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SingleAssetEuropeanOptionProductDescriptor EuropeanOptionSmile.getDescriptor(LocalDate, int)"
  })
  public void testGetDescriptor_whenNull_thenReturnMaturityIsNull()
      throws ArrayIndexOutOfBoundsException {
    // Arrange
    EuropeanOptionSmileByCarrMadan europeanOptionSmileByCarrMadan =
        new EuropeanOptionSmileByCarrMadan(10.0d, new double[] {10.0d, -1.0d, 10.0d, -1.0d});

    // Act
    SingleAssetEuropeanOptionProductDescriptor actualDescriptor =
        europeanOptionSmileByCarrMadan.getDescriptor(null, 1);

    // Assert
    assertNull(actualDescriptor.getUnderlyingName());
    assertNull(actualDescriptor.getMaturity());
    assertEquals(-1.0d, actualDescriptor.getStrike().doubleValue(), 0.0);
  }
}
