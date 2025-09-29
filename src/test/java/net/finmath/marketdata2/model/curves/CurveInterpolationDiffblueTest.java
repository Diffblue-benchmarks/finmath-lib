package net.finmath.marketdata2.model.curves;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.marketdata2.model.curves.CurveInterpolation.Builder;
import net.finmath.marketdata2.model.curves.CurveInterpolation.ExtrapolationMethod;
import net.finmath.marketdata2.model.curves.CurveInterpolation.InterpolationEntity;
import net.finmath.marketdata2.model.curves.CurveInterpolation.InterpolationMethod;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableAAD;
import net.finmath.montecarlo.automaticdifferentiation.backward.alternative.RandomVariableDifferentiableAADPathwise;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CurveInterpolationDiffblueTest {
  @Mock private CurveInterpolation curveInterpolation;

  /**
   * Test Builder {@link Builder#addPoint(double, RandomVariable, boolean)}.
   *
   * <p>Method under test: {@link Builder#addPoint(double, RandomVariable, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CurveBuilder Builder.addPoint(double, RandomVariable, boolean)"})
  public void testBuilderAddPoint() {
    // Arrange
    Builder builder = new Builder();
    builder.addPoint(Double.NaN, new RandomVariableFromDoubleArray(Double.NaN), true);

    // Act
    CurveBuilder actualAddPointResult =
        builder.addPoint(10.0d, new RandomVariableFromDoubleArray(10.0d), true);

    // Assert
    assertTrue(actualAddPointResult instanceof Builder);
    assertSame(builder, actualAddPointResult);
  }

  /**
   * Test Builder {@link Builder#addPoint(double, RandomVariable, boolean)}.
   *
   * <p>Method under test: {@link Builder#addPoint(double, RandomVariable, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CurveBuilder Builder.addPoint(double, RandomVariable, boolean)"})
  public void testBuilderAddPoint2() {
    // Arrange
    Builder builder = new Builder();
    builder.addPoint(
        Double.NEGATIVE_INFINITY, new RandomVariableFromDoubleArray(Double.NaN), false);
    builder.addPoint(Double.NaN, new RandomVariableFromDoubleArray(Double.NaN), true);

    // Act
    CurveBuilder actualAddPointResult =
        builder.addPoint(10.0d, new RandomVariableFromDoubleArray(10.0d), true);

    // Assert
    assertTrue(actualAddPointResult instanceof Builder);
    assertSame(builder, actualAddPointResult);
  }

  /**
   * Test Builder {@link Builder#addPoint(double, RandomVariable, boolean)}.
   *
   * <p>Method under test: {@link Builder#addPoint(double, RandomVariable, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CurveBuilder Builder.addPoint(double, RandomVariable, boolean)"})
  public void testBuilderAddPoint3() {
    // Arrange
    Builder builder = new Builder();

    RandomVariableAAD value = mock(RandomVariableAAD.class);
    RandomVariableDifferentiableAADPathwise randomVariableDifferentiableAADPathwise =
        new RandomVariableDifferentiableAADPathwise(
            10.0d,
            new double[] {
              Double.NaN, Double.NEGATIVE_INFINITY, Double.NaN, Double.NEGATIVE_INFINITY
            });
    when(value.floor(anyDouble())).thenReturn(randomVariableDifferentiableAADPathwise);

    // Act
    CurveBuilder actualAddPointResult = builder.addPoint(10.0d, value, true);

    // Assert
    verify(value).floor(0.0d);
    assertTrue(actualAddPointResult instanceof Builder);
    assertSame(builder, actualAddPointResult);
  }

  /**
   * Test Builder {@link Builder#addPoint(double, RandomVariable, boolean)}.
   *
   * <p>Method under test: {@link Builder#addPoint(double, RandomVariable, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CurveBuilder Builder.addPoint(double, RandomVariable, boolean)"})
  public void testBuilderAddPoint4() {
    // Arrange
    Builder builder = new Builder("Name", LocalDate.of(1970, 1, 1));
    builder.addPoint(10.0d, new RandomVariableFromDoubleArray(10.0d), true);

    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.floor(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    CurveBuilder actualAddPointResult = builder.addPoint(Double.NEGATIVE_INFINITY, value, false);

    // Assert
    verify(value).floor(0.0d);
    assertTrue(actualAddPointResult instanceof Builder);
    assertSame(builder, actualAddPointResult);
  }

  /**
   * Test Builder {@link Builder#addPoint(double, RandomVariable, boolean)}.
   *
   * <p>Method under test: {@link Builder#addPoint(double, RandomVariable, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CurveBuilder Builder.addPoint(double, RandomVariable, boolean)"})
  public void testBuilderAddPoint5() {
    // Arrange
    Builder builder = new Builder("Name", LocalDate.of(1970, 1, 1));
    builder.addPoint(
        Double.NEGATIVE_INFINITY, new RandomVariableFromDoubleArray(Double.NaN), false);
    builder.addPoint(0.0d, new RandomVariableFromDoubleArray(10.0d), true);

    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.floor(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    CurveBuilder actualAddPointResult = builder.addPoint(10.0d, value, false);

    // Assert
    verify(value).floor(0.0d);
    assertTrue(actualAddPointResult instanceof Builder);
    assertSame(builder, actualAddPointResult);
  }

  /**
   * Test Builder {@link Builder#addPoint(double, RandomVariable, boolean)}.
   *
   * <p>Method under test: {@link Builder#addPoint(double, RandomVariable, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CurveBuilder Builder.addPoint(double, RandomVariable, boolean)"})
  public void testBuilderAddPoint6() {
    // Arrange
    Builder builder = new Builder("Name", LocalDate.of(1970, 1, 1));
    builder.addPoint(10.0d, new RandomVariableFromDoubleArray(10.0d), true);

    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.floor(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    CurveBuilder actualAddPointResult = builder.addPoint(Double.NEGATIVE_INFINITY, value, true);

    // Assert
    verify(value).floor(0.0d);
    assertTrue(actualAddPointResult instanceof Builder);
    assertSame(builder, actualAddPointResult);
  }

  /**
   * Test Builder {@link Builder#addPoint(double, RandomVariable, boolean)}.
   *
   * <ul>
   *   <li>Given {@link Builder#Builder()}.
   *   <li>When {@link Scalar} with value is {@link Double#NaN}.
   *   <li>Then return {@link Builder#Builder()}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#addPoint(double, RandomVariable, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CurveBuilder Builder.addPoint(double, RandomVariable, boolean)"})
  public void testBuilderAddPoint_givenBuilder_whenScalarWithValueIsNaN_thenReturnBuilder() {
    // Arrange
    Builder builder = new Builder();

    // Act
    CurveBuilder actualAddPointResult = builder.addPoint(10.0d, Scalar.of(Double.NaN), true);

    // Assert
    assertTrue(actualAddPointResult instanceof Builder);
    assertSame(builder, actualAddPointResult);
  }

  /**
   * Test Builder {@link Builder#addPoint(double, RandomVariable, boolean)}.
   *
   * <ul>
   *   <li>Given {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   *   <li>When {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#addPoint(double, RandomVariable, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CurveBuilder Builder.addPoint(double, RandomVariable, boolean)"})
  public void testBuilderAddPoint_givenRandomVariableFromDoubleArrayWithValueIsTen_whenFalse() {
    // Arrange
    Builder builder = new Builder();

    RandomVariableAAD value = mock(RandomVariableAAD.class);
    when(value.floor(anyDouble())).thenReturn(new RandomVariableFromDoubleArray(10.0d));

    // Act
    CurveBuilder actualAddPointResult = builder.addPoint(10.0d, value, false);

    // Assert
    verify(value).floor(0.0d);
    assertTrue(actualAddPointResult instanceof Builder);
    assertSame(builder, actualAddPointResult);
  }

  /**
   * Test Builder {@link Builder#addPoint(double, RandomVariable, boolean)}.
   *
   * <ul>
   *   <li>Then return {@link Builder#Builder(CurveInterpolation)} with curveInterpolation is {@link
   *       CurveInterpolation#CurveInterpolation(String, LocalDate, InterpolationMethod,
   *       ExtrapolationMethod, InterpolationEntity)}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#addPoint(double, RandomVariable, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CurveBuilder Builder.addPoint(double, RandomVariable, boolean)"})
  public void testBuilderAddPoint_thenReturnBuilderWithCurveInterpolationIsCurveInterpolation()
      throws CloneNotSupportedException {
    // Arrange
    CurveInterpolation curveInterpolation =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE);
    Builder builder = new Builder(curveInterpolation);

    // Act
    CurveBuilder actualAddPointResult =
        builder.addPoint(10.0d, new RandomVariableFromDoubleArray(10.0d), true);

    // Assert
    assertTrue(actualAddPointResult instanceof Builder);
    assertSame(builder, actualAddPointResult);
  }

  /**
   * Test Builder {@link Builder#addPoint(double, RandomVariable, boolean)}.
   *
   * <ul>
   *   <li>When {@link RandomVariableFromDoubleArray#RandomVariableFromDoubleArray(double)} with
   *       value is ten.
   * </ul>
   *
   * <p>Method under test: {@link Builder#addPoint(double, RandomVariable, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CurveBuilder Builder.addPoint(double, RandomVariable, boolean)"})
  public void testBuilderAddPoint_whenRandomVariableFromDoubleArrayWithValueIsTen() {
    // Arrange
    Builder builder = new Builder();

    // Act
    CurveBuilder actualAddPointResult =
        builder.addPoint(10.0d, new RandomVariableFromDoubleArray(10.0d), true);

    // Assert
    assertTrue(actualAddPointResult instanceof Builder);
    assertSame(builder, actualAddPointResult);
  }

  /**
   * Test Builder {@link Builder#build()}.
   *
   * <p>Method under test: {@link Builder#build()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Curve Builder.build()",
    "CurveBuilder Builder.setExtrapolationMethod(ExtrapolationMethod)",
    "CurveBuilder Builder.setInterpolationEntity(InterpolationEntity)",
    "CurveBuilder Builder.setInterpolationMethod(InterpolationMethod)"
  })
  public void testBuilderBuild() {
    // Arrange and Act
    Curve actualCurve = new Builder().build();

    // Assert
    assertTrue(actualCurve instanceof CurveInterpolation);
  }

  /**
   * Test Builder {@link Builder#Builder(CurveInterpolation)}.
   *
   * <ul>
   *   <li>Given one.
   * </ul>
   *
   * <p>Method under test: {@link Builder#Builder(CurveInterpolation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Builder.<init>(CurveInterpolation)"})
  public void testBuilderNewBuilder_givenOne() throws CloneNotSupportedException {
    // Arrange
    CurveInterpolation curveInterpolation =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE);
    curveInterpolation.addPoint(1.0d, new RandomVariableFromDoubleArray(10.0d), false);
    curveInterpolation.addPoint(10.0d, new RandomVariableFromDoubleArray(10.0d), true);

    // Act
    Builder actualBuilder = new Builder(curveInterpolation);

    // Assert
    CurveBuilder actualAddPointResult = actualBuilder.addPoint(10.0d, null, true);
    assertSame(actualBuilder, actualAddPointResult);
  }

  /**
   * Test Builder {@link Builder#Builder(CurveInterpolation)}.
   *
   * <ul>
   *   <li>Given ten.
   * </ul>
   *
   * <p>Method under test: {@link Builder#Builder(CurveInterpolation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Builder.<init>(CurveInterpolation)"})
  public void testBuilderNewBuilder_givenTen() throws CloneNotSupportedException {
    // Arrange
    CurveInterpolation curveInterpolation =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE);
    curveInterpolation.addPoint(10.0d, new RandomVariableFromDoubleArray(10.0d), true);

    // Act
    Builder actualBuilder = new Builder(curveInterpolation);

    // Assert
    CurveBuilder actualAddPointResult = actualBuilder.addPoint(10.0d, null, true);
    assertSame(actualBuilder, actualAddPointResult);
  }

  /**
   * Test Builder {@link Builder#Builder(CurveInterpolation)}.
   *
   * <ul>
   *   <li>Then throw {@link CloneNotSupportedException}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#Builder(CurveInterpolation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Builder.<init>(CurveInterpolation)"})
  public void testBuilderNewBuilder_thenThrowCloneNotSupportedException()
      throws CloneNotSupportedException {
    // Arrange
    when(curveInterpolation.clone()).thenThrow(new CloneNotSupportedException());

    // Act and Assert
    assertThrows(CloneNotSupportedException.class, () -> new Builder(curveInterpolation));
    verify(curveInterpolation).clone();
  }

  /**
   * Test Builder {@link Builder#Builder(CurveInterpolation)}.
   *
   * <ul>
   *   <li>When {@link LocalDate} with {@code 1970} and one and one.
   * </ul>
   *
   * <p>Method under test: {@link Builder#Builder(CurveInterpolation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Builder.<init>(CurveInterpolation)"})
  public void testBuilderNewBuilder_whenLocalDateWith1970AndOneAndOne()
      throws CloneNotSupportedException {
    // Arrange
    CurveInterpolation curveInterpolation =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE);

    // Act
    Builder actualBuilder = new Builder(curveInterpolation);

    // Assert
    CurveBuilder actualAddPointResult = actualBuilder.addPoint(10.0d, null, true);
    assertSame(actualBuilder, actualAddPointResult);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CurveInterpolation#getCloneBuilder()}
   *   <li>{@link CurveInterpolation#getExtrapolationMethod()}
   *   <li>{@link CurveInterpolation#getInterpolationEntity()}
   *   <li>{@link CurveInterpolation#getInterpolationMethod()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CurveBuilder CurveInterpolation.getCloneBuilder()",
    "ExtrapolationMethod CurveInterpolation.getExtrapolationMethod()",
    "InterpolationEntity CurveInterpolation.getInterpolationEntity()",
    "InterpolationMethod CurveInterpolation.getInterpolationMethod()"
  })
  public void testGettersAndSetters() throws CloneNotSupportedException {
    // Arrange
    CurveInterpolation curveInterpolation =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE);

    // Act
    CurveBuilder actualCloneBuilder = curveInterpolation.getCloneBuilder();
    ExtrapolationMethod actualExtrapolationMethod = curveInterpolation.getExtrapolationMethod();
    InterpolationEntity actualInterpolationEntity = curveInterpolation.getInterpolationEntity();

    // Assert
    assertTrue(actualCloneBuilder instanceof Builder);
    assertEquals(ExtrapolationMethod.DEFAULT, actualExtrapolationMethod);
    assertEquals(InterpolationEntity.VALUE, actualInterpolationEntity);
    assertEquals(
        InterpolationMethod.PIECEWISE_CONSTANT, curveInterpolation.getInterpolationMethod());
  }
}
