package net.finmath.fouriermethod.products;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexField;
import org.apache.commons.math3.complex.ComplexUtils;
import org.apache.commons.math3.exception.MathIllegalArgumentException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DigitalOptionDiffblueTest {
  /**
   * Test {@link DigitalOption#DigitalOption(double, double)}.
   *
   * <p>Method under test: {@link DigitalOption#DigitalOption(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DigitalOption.<init>(double, double)"})
  public void testNewDigitalOption() {
    // Arrange and Act
    DigitalOption actualDigitalOption = new DigitalOption(10.0d, 10.0d);

    // Assert
    assertEquals(0.5d, actualDigitalOption.getIntegrationDomainImagLowerBound(), 0.0);
    assertEquals(10.0d, actualDigitalOption.getMaturity(), 0.0);
    assertEquals(2.5d, actualDigitalOption.getIntegrationDomainImagUpperBound(), 0.0);
  }

  /**
   * Test {@link DigitalOption#apply(Complex)} with {@code Complex}.
   *
   * <ul>
   *   <li>Given {@link DigitalOption#DigitalOption(double, double)} with maturity is ten and strike
   *       is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link DigitalOption#apply(Complex)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Complex DigitalOption.apply(Complex)"})
  public void testApplyWithComplex_givenDigitalOptionWithMaturityIsTenAndStrikeIsNaN() {
    // Arrange, Act and Assert
    assertSame(Complex.NaN, new DigitalOption(10.0d, Double.NaN).apply(Complex.valueOf(10.0d)));
  }

  /**
   * Test {@link DigitalOption#apply(Complex)} with {@code Complex}.
   *
   * <ul>
   *   <li>Then return abs is {@code 0.09999999999999999}.
   * </ul>
   *
   * <p>Method under test: {@link DigitalOption#apply(Complex)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Complex DigitalOption.apply(Complex)"})
  public void testApplyWithComplex_thenReturnAbsIs009999999999999999() {
    // Arrange and Act
    Complex actualApplyResult = new DigitalOption(10.0d, 1.0d).apply(Complex.valueOf(10.0d));

    // Assert
    assertEquals(0.09999999999999999d, actualApplyResult.abs(), 0.0);
    assertEquals(0.09999999999999999d, actualApplyResult.getImaginary(), 0.0);
    assertEquals(0.0d, actualApplyResult.getReal(), 0.0);
    assertEquals(1.5707963267948966d, actualApplyResult.getArgument(), 0.0);
    ComplexField field = actualApplyResult.getField();
    assertSame(field, field.getOne().getField());
    assertSame(field, field.getZero().getField());
  }

  /**
   * Test {@link DigitalOption#apply(Complex)} with {@code Complex}.
   *
   * <ul>
   *   <li>Then return Argument is {@code 0.9225098283750299}.
   * </ul>
   *
   * <p>Method under test: {@link DigitalOption#apply(Complex)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Complex DigitalOption.apply(Complex)"})
  public void testApplyWithComplex_thenReturnArgumentIs09225098283750299() {
    // Arrange and Act
    Complex actualApplyResult = new DigitalOption(10.0d, -0.5d).apply(Complex.valueOf(10.0d));

    // Assert
    assertEquals(0.9225098283750299d, actualApplyResult.getArgument(), 0.0);
    assertEquals(1.371339488202102E-15d, actualApplyResult.getReal(), 0.0);
    assertEquals(1.8103392142470014E-15d, actualApplyResult.getImaginary(), 0.0);
    assertEquals(2.2711010683240965E-15d, actualApplyResult.abs(), 0.0);
    ComplexField field = actualApplyResult.getField();
    assertSame(field, field.getOne().getField());
    assertSame(field, field.getZero().getField());
  }

  /**
   * Test {@link DigitalOption#apply(Complex)} with {@code Complex}.
   *
   * <ul>
   *   <li>Then return Argument is {@code 0.44534606445860425}.
   * </ul>
   *
   * <p>Method under test: {@link DigitalOption#apply(Complex)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Complex DigitalOption.apply(Complex)"})
  public void testApplyWithComplex_thenReturnArgumentIs044534606445860425()
      throws MathIllegalArgumentException {
    // Arrange
    DigitalOption digitalOption = new DigitalOption(10.0d, 10.0d);

    // Act
    Complex actualApplyResult = digitalOption.apply(ComplexUtils.polar2Complex(10.0d, 1.0d));

    // Assert
    assertEquals(0.44534606445860425d, actualApplyResult.getArgument(), 0.0);
    assertEquals(1.6578140650186195E-10d, actualApplyResult.getImaginary(), 0.0);
    assertEquals(3.473112933484309E-10d, actualApplyResult.getReal(), 0.0);
    assertEquals(3.8484881346977735E-10d, actualApplyResult.abs(), 0.0);
    ComplexField field = actualApplyResult.getField();
    assertSame(field, field.getOne().getField());
    assertSame(field, field.getZero().getField());
  }

  /**
   * Test {@link DigitalOption#apply(Complex)} with {@code Complex}.
   *
   * <ul>
   *   <li>Then return Imaginary is {@code -0.2100002406585157}.
   * </ul>
   *
   * <p>Method under test: {@link DigitalOption#apply(Complex)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Complex DigitalOption.apply(Complex)"})
  public void testApplyWithComplex_thenReturnImaginaryIs02100002406585157()
      throws MathIllegalArgumentException {
    // Arrange
    DigitalOption digitalOption = new DigitalOption(10.0d, -0.5d);

    // Act
    Complex actualApplyResult = digitalOption.apply(ComplexUtils.polar2Complex(1.0d, 1.0d));

    // Assert
    assertEquals(-0.2100002406585157d, actualApplyResult.getImaginary(), 0.0);
    assertEquals(-0.252214261655507d, actualApplyResult.getReal(), 0.0);
    assertEquals(-2.447271757249098d, actualApplyResult.getArgument(), 0.0);
    assertEquals(0.3281952694038521d, actualApplyResult.abs(), 0.0);
    ComplexField field = actualApplyResult.getField();
    assertSame(field, field.getOne().getField());
    assertSame(field, field.getZero().getField());
  }

  /**
   * Test {@link DigitalOption#apply(Complex)} with {@code Complex}.
   *
   * <ul>
   *   <li>Then return Real is {@code -0.0603821427116869}.
   * </ul>
   *
   * <p>Method under test: {@link DigitalOption#apply(Complex)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Complex DigitalOption.apply(Complex)"})
  public void testApplyWithComplex_thenReturnRealIs00603821427116869() {
    // Arrange and Act
    Complex actualApplyResult = new DigitalOption(10.0d, 2.0d).apply(Complex.valueOf(10.0d));

    // Assert
    assertEquals(-0.0603821427116869d, actualApplyResult.getReal(), 0.0);
    assertEquals(2.219082825214763d, actualApplyResult.getArgument(), 0.0);
    ComplexField field = actualApplyResult.getField();
    assertSame(field, field.getOne().getField());
    assertSame(field, field.getZero().getField());
  }

  /**
   * Test {@link DigitalOption#apply(Complex)} with {@code Complex}.
   *
   * <ul>
   *   <li>Then return Real is {@code -0.03481460044052988}.
   * </ul>
   *
   * <p>Method under test: {@link DigitalOption#apply(Complex)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Complex DigitalOption.apply(Complex)"})
  public void testApplyWithComplex_thenReturnRealIs003481460044052988()
      throws MathIllegalArgumentException {
    // Arrange
    DigitalOption digitalOption = new DigitalOption(10.0d, 10.0d);

    // Act
    Complex actualApplyResult = digitalOption.apply(ComplexUtils.polar2Complex(1.0d, 1.0d));

    // Assert
    assertEquals(-0.03481460044052988d, actualApplyResult.getReal(), 0.0);
    assertEquals(0.13978501870111704d, actualApplyResult.getImaginary(), 0.0);
    assertEquals(0.14405522502535403d, actualApplyResult.abs(), 0.0);
    assertEquals(1.8148883619971847d, actualApplyResult.getArgument(), 0.0);
    ComplexField field = actualApplyResult.getField();
    assertSame(field, field.getOne().getField());
    assertSame(field, field.getZero().getField());
  }

  /**
   * Test {@link DigitalOption#apply(Complex)} with {@code Complex}.
   *
   * <ul>
   *   <li>Then return Real is {@code 0.0603821427116869}.
   * </ul>
   *
   * <p>Method under test: {@link DigitalOption#apply(Complex)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Complex DigitalOption.apply(Complex)"})
  public void testApplyWithComplex_thenReturnRealIs006038214271168692() {
    // Arrange and Act
    Complex actualApplyResult = new DigitalOption(10.0d, 0.5d).apply(Complex.valueOf(10.0d));

    // Assert
    assertEquals(0.0603821427116869d, actualApplyResult.getReal(), 0.0);
    assertEquals(0.92250982837503d, actualApplyResult.getArgument(), 0.0);
    ComplexField field = actualApplyResult.getField();
    assertSame(field, field.getOne().getField());
    assertSame(field, field.getZero().getField());
  }

  /**
   * Test {@link DigitalOption#apply(Complex)} with {@code Complex}.
   *
   * <ul>
   *   <li>When valueOf {@code 0.5}.
   *   <li>Then return Real is {@code -1.8265823333155904}.
   * </ul>
   *
   * <p>Method under test: {@link DigitalOption#apply(Complex)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Complex DigitalOption.apply(Complex)"})
  public void testApplyWithComplex_whenValueOf05_thenReturnRealIs18265823333155904() {
    // Arrange and Act
    Complex actualApplyResult = new DigitalOption(10.0d, 10.0d).apply(Complex.valueOf(0.5d));

    // Assert
    assertEquals(-1.8265823333155904d, actualApplyResult.getReal(), 0.0);
    assertEquals(0.8146146203078937d, actualApplyResult.getImaginary(), 0.0);
    assertEquals(2.0d, actualApplyResult.abs(), 0.0);
    assertEquals(2.7220888732919195d, actualApplyResult.getArgument(), 0.0);
    ComplexField field = actualApplyResult.getField();
    assertSame(field, field.getOne().getField());
    assertSame(field, field.getZero().getField());
  }

  /**
   * Test {@link DigitalOption#apply(Complex)} with {@code Complex}.
   *
   * <ul>
   *   <li>When valueOf {@link Double#NaN}.
   *   <li>Then return {@link Complex#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link DigitalOption#apply(Complex)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Complex DigitalOption.apply(Complex)"})
  public void testApplyWithComplex_whenValueOfNaN_thenReturnNaN() {
    // Arrange, Act and Assert
    assertSame(Complex.NaN, new DigitalOption(10.0d, 10.0d).apply(Complex.valueOf(Double.NaN)));
  }

  /**
   * Test {@link DigitalOption#apply(Complex)} with {@code Complex}.
   *
   * <ul>
   *   <li>When valueOf one and one.
   *   <li>Then return Real is {@code -0.0706090923573903}.
   * </ul>
   *
   * <p>Method under test: {@link DigitalOption#apply(Complex)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Complex DigitalOption.apply(Complex)"})
  public void testApplyWithComplex_whenValueOfOneAndOne_thenReturnRealIs00706090923573903() {
    // Arrange and Act
    Complex actualApplyResult = new DigitalOption(10.0d, 10.0d).apply(Complex.valueOf(1.0d, 1.0d));

    // Assert
    assertEquals(-0.0706090923573903d, actualApplyResult.getReal(), 0.0);
    assertEquals(0.0037889413383589907d, actualApplyResult.getImaginary(), 0.0);
    assertEquals(0.07071067811865474d, actualApplyResult.abs(), 0.0);
    assertEquals(3.087983256391494d, actualApplyResult.getArgument(), 0.0);
    ComplexField field = actualApplyResult.getField();
    assertSame(field, field.getOne().getField());
    assertSame(field, field.getZero().getField());
  }

  /**
   * Test {@link DigitalOption#apply(Complex)} with {@code Complex}.
   *
   * <ul>
   *   <li>When valueOf ten.
   *   <li>Then return Imaginary is {@code -0.05107818439368524}.
   * </ul>
   *
   * <p>Method under test: {@link DigitalOption#apply(Complex)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Complex DigitalOption.apply(Complex)"})
  public void testApplyWithComplex_whenValueOfTen_thenReturnImaginaryIs005107818439368524() {
    // Arrange and Act
    Complex actualApplyResult = new DigitalOption(10.0d, 10.0d).apply(Complex.valueOf(10.0d));

    // Assert
    assertEquals(-0.05107818439368524d, actualApplyResult.getImaginary(), 0.0);
    assertEquals(-0.5360939719829886d, actualApplyResult.getArgument(), 0.0);
    assertEquals(0.08597103627992797d, actualApplyResult.getReal(), 0.0);
    assertEquals(0.10000000000000002d, actualApplyResult.abs(), 0.0);
    ComplexField field = actualApplyResult.getField();
    assertSame(field, field.getOne().getField());
    assertSame(field, field.getZero().getField());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DigitalOption#getIntegrationDomainImagLowerBound()}
   *   <li>{@link DigitalOption#getIntegrationDomainImagUpperBound()}
   *   <li>{@link DigitalOption#getMaturity()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double DigitalOption.getIntegrationDomainImagLowerBound()",
    "double DigitalOption.getIntegrationDomainImagUpperBound()",
    "double DigitalOption.getMaturity()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DigitalOption digitalOption = new DigitalOption(10.0d, 10.0d);

    // Act
    double actualIntegrationDomainImagLowerBound =
        digitalOption.getIntegrationDomainImagLowerBound();
    double actualIntegrationDomainImagUpperBound =
        digitalOption.getIntegrationDomainImagUpperBound();

    // Assert
    assertEquals(0.5d, actualIntegrationDomainImagLowerBound, 0.0);
    assertEquals(10.0d, digitalOption.getMaturity(), 0.0);
    assertEquals(2.5d, actualIntegrationDomainImagUpperBound, 0.0);
  }
}
