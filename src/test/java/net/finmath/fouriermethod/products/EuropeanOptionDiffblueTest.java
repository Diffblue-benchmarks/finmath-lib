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

public class EuropeanOptionDiffblueTest {
  /**
   * Test {@link EuropeanOption#EuropeanOption(double, double)}.
   *
   * <p>Method under test: {@link EuropeanOption#EuropeanOption(double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EuropeanOption.<init>(double, double)"})
  public void testNewEuropeanOption() {
    // Arrange and Act
    EuropeanOption actualEuropeanOption = new EuropeanOption(10.0d, 10.0d);

    // Assert
    assertEquals(0.5d, actualEuropeanOption.getIntegrationDomainImagLowerBound(), 0.0);
    assertEquals(10.0d, actualEuropeanOption.getMaturity(), 0.0);
    assertEquals(2.5d, actualEuropeanOption.getIntegrationDomainImagUpperBound(), 0.0);
  }

  /**
   * Test {@link EuropeanOption#EuropeanOption(String, double, double)}.
   *
   * <p>Method under test: {@link EuropeanOption#EuropeanOption(String, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EuropeanOption.<init>(String, double, double)"})
  public void testNewEuropeanOption2() {
    // Arrange and Act
    EuropeanOption actualEuropeanOption = new EuropeanOption("Underlying Name", 10.0d, 10.0d);

    // Assert
    assertEquals(0.5d, actualEuropeanOption.getIntegrationDomainImagLowerBound(), 0.0);
    assertEquals(10.0d, actualEuropeanOption.getMaturity(), 0.0);
    assertEquals(2.5d, actualEuropeanOption.getIntegrationDomainImagUpperBound(), 0.0);
  }

  /**
   * Test {@link EuropeanOption#apply(Complex)} with {@code Complex}.
   *
   * <ul>
   *   <li>Given {@link EuropeanOption#EuropeanOption(double, double)} with maturity is ten and
   *       strike is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link EuropeanOption#apply(Complex)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Complex EuropeanOption.apply(Complex)"})
  public void testApplyWithComplex_givenEuropeanOptionWithMaturityIsTenAndStrikeIsNaN() {
    // Arrange, Act and Assert
    assertSame(Complex.NaN, new EuropeanOption(10.0d, Double.NaN).apply(Complex.valueOf(10.0d)));
  }

  /**
   * Test {@link EuropeanOption#apply(Complex)} with {@code Complex}.
   *
   * <ul>
   *   <li>Then return Argument is {@code -0.5486178459287048}.
   * </ul>
   *
   * <p>Method under test: {@link EuropeanOption#apply(Complex)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Complex EuropeanOption.apply(Complex)"})
  public void testApplyWithComplex_thenReturnArgumentIs05486178459287048() {
    // Arrange and Act
    Complex actualApplyResult = new EuropeanOption(10.0d, -0.5d).apply(Complex.valueOf(10.0d));

    // Assert
    assertEquals(-0.5486178459287048d, actualApplyResult.getArgument(), 0.0);
    assertEquals(-5.892601815729703E-17d, actualApplyResult.getImaginary(), 0.0);
    assertEquals(1.1299150128540547E-16d, actualApplyResult.abs(), 0.0);
    assertEquals(9.640956252807959E-17d, actualApplyResult.getReal(), 0.0);
    ComplexField field = actualApplyResult.getField();
    assertSame(field, field.getOne().getField());
    assertSame(field, field.getZero().getField());
  }

  /**
   * Test {@link EuropeanOption#apply(Complex)} with {@code Complex}.
   *
   * <ul>
   *   <li>Then return Argument is {@code 1.0750685351774554}.
   * </ul>
   *
   * <p>Method under test: {@link EuropeanOption#apply(Complex)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Complex EuropeanOption.apply(Complex)"})
  public void testApplyWithComplex_thenReturnArgumentIs10750685351774554()
      throws MathIllegalArgumentException {
    // Arrange
    EuropeanOption europeanOption = new EuropeanOption(10.0d, 10.0d);

    // Act
    Complex actualApplyResult = europeanOption.apply(ComplexUtils.polar2Complex(10.0d, 1.0d));

    // Assert
    assertEquals(1.0750685351774554d, actualApplyResult.getArgument(), 0.0);
    assertEquals(1.9953429084684574E-10d, actualApplyResult.getReal(), 0.0);
    assertEquals(3.6898307493051776E-10d, actualApplyResult.getImaginary(), 0.0);
    assertEquals(4.1947877515904626E-10d, actualApplyResult.abs(), 0.0);
    ComplexField field = actualApplyResult.getField();
    assertSame(field, field.getOne().getField());
    assertSame(field, field.getZero().getField());
  }

  /**
   * Test {@link EuropeanOption#apply(Complex)} with {@code Complex}.
   *
   * <ul>
   *   <li>Then return Imaginary is {@code -1.292204780290431}.
   * </ul>
   *
   * <p>Method under test: {@link EuropeanOption#apply(Complex)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Complex EuropeanOption.apply(Complex)"})
  public void testApplyWithComplex_thenReturnImaginaryIs1292204780290431()
      throws MathIllegalArgumentException {
    // Arrange
    EuropeanOption europeanOption = new EuropeanOption(10.0d, 10.0d);

    // Act
    Complex actualApplyResult = europeanOption.apply(ComplexUtils.polar2Complex(1.0d, 1.0d));

    // Assert
    assertEquals(-1.292204780290431d, actualApplyResult.getImaginary(), 0.0);
    assertEquals(-2.208019885919847d, actualApplyResult.getReal(), 0.0);
    assertEquals(-2.612102454990057d, actualApplyResult.getArgument(), 0.0);
    assertEquals(2.558348101964026d, actualApplyResult.abs(), 0.0);
    ComplexField field = actualApplyResult.getField();
    assertSame(field, field.getOne().getField());
    assertSame(field, field.getZero().getField());
  }

  /**
   * Test {@link EuropeanOption#apply(Complex)} with {@code Complex}.
   *
   * <ul>
   *   <li>Then return Imaginary is {@code -0.013535314630469659}.
   * </ul>
   *
   * <p>Method under test: {@link EuropeanOption#apply(Complex)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Complex EuropeanOption.apply(Complex)"})
  public void testApplyWithComplex_thenReturnImaginaryIs0013535314630469659() {
    // Arrange and Act
    Complex actualApplyResult = new EuropeanOption(10.0d, 2.0d).apply(Complex.valueOf(10.0d));

    // Assert
    assertEquals(-0.013535314630469659d, actualApplyResult.getImaginary(), 0.0);
    assertEquals(-0.014588860881322777d, actualApplyResult.getReal(), 0.0);
    assertEquals(-2.3936375026787644d, actualApplyResult.getArgument(), 0.0);
    assertEquals(0.01990074380419978d, actualApplyResult.abs(), 0.0);
    ComplexField field = actualApplyResult.getField();
    assertSame(field, field.getOne().getField());
    assertSame(field, field.getZero().getField());
  }

  /**
   * Test {@link EuropeanOption#apply(Complex)} with {@code Complex}.
   *
   * <ul>
   *   <li>Then return Real is {@code -0.009900990099009901}.
   * </ul>
   *
   * <p>Method under test: {@link EuropeanOption#apply(Complex)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Complex EuropeanOption.apply(Complex)"})
  public void testApplyWithComplex_thenReturnRealIs0009900990099009901() {
    // Arrange and Act
    Complex actualApplyResult = new EuropeanOption(10.0d, 1.0d).apply(Complex.valueOf(10.0d));

    // Assert
    assertEquals(-0.009900990099009901d, actualApplyResult.getReal(), 0.0);
    assertEquals(-3.0419240010986313d, actualApplyResult.getArgument(), 0.0);
    assertEquals(-9.900990099009901E-4d, actualApplyResult.getImaginary(), 0.0);
    assertEquals(0.00995037190209989d, actualApplyResult.abs(), 0.0);
    ComplexField field = actualApplyResult.getField();
    assertSame(field, field.getOne().getField());
    assertSame(field, field.getZero().getField());
  }

  /**
   * Test {@link EuropeanOption#apply(Complex)} with {@code Complex}.
   *
   * <ul>
   *   <li>Then return Real is {@code -0.0042450582174761095}.
   * </ul>
   *
   * <p>Method under test: {@link EuropeanOption#apply(Complex)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Complex EuropeanOption.apply(Complex)"})
  public void testApplyWithComplex_thenReturnRealIs00042450582174761095() {
    // Arrange and Act
    Complex actualApplyResult = new EuropeanOption(10.0d, 0.5d).apply(Complex.valueOf(10.0d));

    // Assert
    assertEquals(-0.0042450582174761095d, actualApplyResult.getReal(), 0.0);
    assertEquals(0.0025946013138367346d, actualApplyResult.getImaginary(), 0.0);
    assertEquals(0.004975185951049945d, actualApplyResult.abs(), 0.0);
    assertEquals(2.5929748076610886d, actualApplyResult.getArgument(), 0.0);
    ComplexField field = actualApplyResult.getField();
    assertSame(field, field.getOne().getField());
    assertSame(field, field.getZero().getField());
  }

  /**
   * Test {@link EuropeanOption#apply(Complex)} with {@code Complex}.
   *
   * <ul>
   *   <li>When valueOf {@code 0.5}.
   *   <li>Then return Argument is {@code -0.8831513892986798}.
   * </ul>
   *
   * <p>Method under test: {@link EuropeanOption#apply(Complex)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Complex EuropeanOption.apply(Complex)"})
  public void testApplyWithComplex_whenValueOf05_thenReturnArgumentIs08831513892986798() {
    // Arrange and Act
    Complex actualApplyResult = new EuropeanOption(10.0d, 10.0d).apply(Complex.valueOf(0.5d));

    // Assert
    assertEquals(-0.8831513892986798d, actualApplyResult.getArgument(), 0.0);
    assertEquals(-13.823246295725514d, actualApplyResult.getImaginary(), 0.0);
    assertEquals(11.35420018529315d, actualApplyResult.getReal(), 0.0);
    assertEquals(17.888543819998322d, actualApplyResult.abs(), 0.0);
    ComplexField field = actualApplyResult.getField();
    assertSame(field, field.getOne().getField());
    assertSame(field, field.getZero().getField());
  }

  /**
   * Test {@link EuropeanOption#apply(Complex)} with {@code Complex}.
   *
   * <ul>
   *   <li>When valueOf {@link Double#NaN}.
   *   <li>Then return {@link Complex#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link EuropeanOption#apply(Complex)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Complex EuropeanOption.apply(Complex)"})
  public void testApplyWithComplex_whenValueOfNaN_thenReturnNaN() {
    // Arrange, Act and Assert
    assertSame(Complex.NaN, new EuropeanOption(10.0d, 10.0d).apply(Complex.valueOf(Double.NaN)));
  }

  /**
   * Test {@link EuropeanOption#apply(Complex)} with {@code Complex}.
   *
   * <ul>
   *   <li>When valueOf one and one.
   *   <li>Then return Real is {@code -0.03788941338358992}.
   * </ul>
   *
   * <p>Method under test: {@link EuropeanOption#apply(Complex)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Complex EuropeanOption.apply(Complex)"})
  public void testApplyWithComplex_whenValueOfOneAndOne_thenReturnRealIs003788941338358992() {
    // Arrange and Act
    Complex actualApplyResult = new EuropeanOption(10.0d, 10.0d).apply(Complex.valueOf(1.0d, 1.0d));

    // Assert
    assertEquals(-0.03788941338358992d, actualApplyResult.getReal(), 0.0);
    assertEquals(-0.706090923573903d, actualApplyResult.getImaginary(), 0.0);
    assertEquals(-1.6244057239931955d, actualApplyResult.getArgument(), 0.0);
    assertEquals(0.7071067811865475d, actualApplyResult.abs(), 0.0);
    ComplexField field = actualApplyResult.getField();
    assertSame(field, field.getOne().getField());
    assertSame(field, field.getZero().getField());
  }

  /**
   * Test {@link EuropeanOption#apply(Complex)} with {@code Complex}.
   *
   * <ul>
   *   <li>When valueOf ten.
   *   <li>Then return Real is {@code 0.04206047600563608}.
   * </ul>
   *
   * <p>Method under test: {@link EuropeanOption#apply(Complex)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Complex EuropeanOption.apply(Complex)"})
  public void testApplyWithComplex_whenValueOfTen_thenReturnRealIs004206047600563608() {
    // Arrange and Act
    Complex actualApplyResult = new EuropeanOption(10.0d, 10.0d).apply(Complex.valueOf(10.0d));

    // Assert
    assertEquals(0.04206047600563608d, actualApplyResult.getReal(), 0.0);
    assertEquals(0.09017708388049159d, actualApplyResult.getImaginary(), 0.0);
    assertEquals(0.09950371902099894d, actualApplyResult.abs(), 0.0);
    assertEquals(1.1343710073030702d, actualApplyResult.getArgument(), 0.0);
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
   *   <li>{@link EuropeanOption#getIntegrationDomainImagLowerBound()}
   *   <li>{@link EuropeanOption#getIntegrationDomainImagUpperBound()}
   *   <li>{@link EuropeanOption#getMaturity()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double EuropeanOption.getIntegrationDomainImagLowerBound()",
    "double EuropeanOption.getIntegrationDomainImagUpperBound()",
    "double EuropeanOption.getMaturity()"
  })
  public void testGettersAndSetters() {
    // Arrange
    EuropeanOption europeanOption = new EuropeanOption(10.0d, 10.0d);

    // Act
    double actualIntegrationDomainImagLowerBound =
        europeanOption.getIntegrationDomainImagLowerBound();
    double actualIntegrationDomainImagUpperBound =
        europeanOption.getIntegrationDomainImagUpperBound();

    // Assert
    assertEquals(0.5d, actualIntegrationDomainImagLowerBound, 0.0);
    assertEquals(10.0d, europeanOption.getMaturity(), 0.0);
    assertEquals(2.5d, actualIntegrationDomainImagUpperBound, 0.0);
  }
}
