package net.finmath.marketdata.model.curves;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.marketdata.model.curves.CurveInterpolation.Builder;
import net.finmath.marketdata.model.curves.CurveInterpolation.ExtrapolationMethod;
import net.finmath.marketdata.model.curves.CurveInterpolation.InterpolationEntity;
import net.finmath.marketdata.model.curves.CurveInterpolation.InterpolationMethod;
import net.finmath.marketdata.model.curves.CurveInterpolation.Point;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CurveInterpolationDiffblueTest {
  @Mock private CurveInterpolation curveInterpolation;

  /**
   * Test Builder {@link Builder#addPoint(double, double, boolean)}.
   *
   * <p>Method under test: {@link Builder#addPoint(double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.addPoint(double, double, boolean)"})
  public void testBuilderAddPoint() throws CloneNotSupportedException {
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
    Builder actualAddPointResult = builder.addPoint(10.0d, 10.0d, true);

    // Assert
    assertSame(builder, actualAddPointResult);
  }

  /**
   * Test Builder {@link Builder#addPoint(double, double, boolean)}.
   *
   * <p>Method under test: {@link Builder#addPoint(double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.addPoint(double, double, boolean)"})
  public void testBuilderAddPoint2() {
    // Arrange
    Builder builder = new Builder();
    builder.addPoint(2.302585092994046d, Double.NaN, true);

    // Act
    Builder actualAddPointResult = builder.addPoint(10.0d, 10.0d, true);

    // Assert
    assertSame(builder, actualAddPointResult);
  }

  /**
   * Test Builder {@link Builder#addPoint(double, double, boolean)}.
   *
   * <p>Method under test: {@link Builder#addPoint(double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.addPoint(double, double, boolean)"})
  public void testBuilderAddPoint3() {
    // Arrange
    Builder builder = new Builder();
    builder.addPoint(10.0d, 10.0d, false);
    builder.addPoint(2.302585092994046d, Double.NaN, true);

    // Act
    Builder actualAddPointResult = builder.addPoint(10.0d, 10.0d, true);

    // Assert
    assertSame(builder, actualAddPointResult);
  }

  /**
   * Test Builder {@link Builder#addPoint(double, double, boolean)}.
   *
   * <p>Method under test: {@link Builder#addPoint(double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.addPoint(double, double, boolean)"})
  public void testBuilderAddPoint4() throws CloneNotSupportedException {
    // Arrange
    CurveInterpolation curveInterpolation =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.LOG_OF_VALUE_PER_TIME);
    Builder builder = new Builder(curveInterpolation);

    // Act
    Builder actualAddPointResult = builder.addPoint(10.0d, 10.0d, true);

    // Assert
    assertSame(builder, actualAddPointResult);
  }

  /**
   * Test Builder {@link Builder#addPoint(double, double, boolean)}.
   *
   * <p>Method under test: {@link Builder#addPoint(double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.addPoint(double, double, boolean)"})
  public void testBuilderAddPoint5() throws CloneNotSupportedException {
    // Arrange
    CurveInterpolation curveInterpolation =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.LOG_OF_VALUE_PER_TIME);
    Builder builder = new Builder(curveInterpolation);

    // Act
    Builder actualAddPointResult = builder.addPoint(0.0d, 1.0d, false);

    // Assert
    assertSame(builder, actualAddPointResult);
  }

  /**
   * Test Builder {@link Builder#addPoint(double, double, boolean)}.
   *
   * <ul>
   *   <li>Given {@link Builder#addPoint(double, double, boolean)} with time is {@link Double#NaN}
   *       and value is {@link Double#NaN} and isParameter is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#addPoint(double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.addPoint(double, double, boolean)"})
  public void testBuilderAddPoint_givenAddPointWithTimeIsNaNAndValueIsNaNAndIsParameterIsTrue() {
    // Arrange
    Builder builder = new Builder();
    builder.addPoint(Double.NaN, Double.NaN, true);

    // Act
    Builder actualAddPointResult = builder.addPoint(10.0d, 10.0d, true);

    // Assert
    assertSame(builder, actualAddPointResult);
  }

  /**
   * Test Builder {@link Builder#addPoint(double, double, boolean)}.
   *
   * <ul>
   *   <li>Given {@link Builder#addPoint(double, double, boolean)} with time is ten and value is ten
   *       and isParameter is {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#addPoint(double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.addPoint(double, double, boolean)"})
  public void testBuilderAddPoint_givenAddPointWithTimeIsTenAndValueIsTenAndIsParameterIsFalse() {
    // Arrange
    Builder builder = new Builder();
    builder.addPoint(10.0d, 10.0d, false);
    builder.addPoint(Double.NaN, Double.NaN, true);

    // Act
    Builder actualAddPointResult = builder.addPoint(10.0d, 10.0d, true);

    // Assert
    assertSame(builder, actualAddPointResult);
  }

  /**
   * Test Builder {@link Builder#addPoint(double, double, boolean)}.
   *
   * <ul>
   *   <li>Given {@link Builder#addPoint(double, double, boolean)} with time is ten and value is ten
   *       and isParameter is {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#addPoint(double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.addPoint(double, double, boolean)"})
  public void testBuilderAddPoint_givenAddPointWithTimeIsTenAndValueIsTenAndIsParameterIsFalse2() {
    // Arrange
    Builder builder = new Builder();
    builder.addPoint(10.0d, 10.0d, false);
    builder.addPoint(Double.NaN, Double.NaN, true);

    // Act
    Builder actualAddPointResult = builder.addPoint(0.0d, 1.0d, false);

    // Assert
    assertSame(builder, actualAddPointResult);
  }

  /**
   * Test Builder {@link Builder#addPoint(double, double, boolean)}.
   *
   * <ul>
   *   <li>Given {@link Builder#Builder()}.
   *   <li>When ten.
   *   <li>Then return {@link Builder#Builder()}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#addPoint(double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.addPoint(double, double, boolean)"})
  public void testBuilderAddPoint_givenBuilder_whenTen_thenReturnBuilder() {
    // Arrange
    Builder builder = new Builder();

    // Act
    Builder actualAddPointResult = builder.addPoint(10.0d, 10.0d, true);

    // Assert
    assertSame(builder, actualAddPointResult);
  }

  /**
   * Test Builder {@link Builder#addPoint(double, double, boolean)}.
   *
   * <ul>
   *   <li>Given {@link Builder#Builder()}.
   *   <li>When zero.
   *   <li>Then return {@link Builder#Builder()}.
   * </ul>
   *
   * <p>Method under test: {@link Builder#addPoint(double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Builder Builder.addPoint(double, double, boolean)"})
  public void testBuilderAddPoint_givenBuilder_whenZero_thenReturnBuilder() {
    // Arrange
    Builder builder = new Builder();

    // Act
    Builder actualAddPointResult = builder.addPoint(0.0d, 1.0d, false);

    // Assert
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
    "Builder Builder.setExtrapolationMethod(ExtrapolationMethod)",
    "Builder Builder.setInterpolationEntity(InterpolationEntity)",
    "Builder Builder.setInterpolationMethod(InterpolationMethod)"
  })
  public void testBuilderBuild() throws CloneNotSupportedException {
    // Arrange and Act
    Curve actualCurve = new Builder().build();

    // Assert
    assertTrue(actualCurve instanceof CurveInterpolation);
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

  /**
   * Test Point {@link Point#clone()}.
   *
   * <p>Method under test: {@link Point#clone()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object Point.clone()"})
  public void testPointClone() {
    // Arrange and Act
    Object actualCloneResult = new Point(10.0d, 10.0d, true).clone();

    // Assert
    assertTrue(actualCloneResult instanceof Point);
    assertEquals(10.0d, ((Point) actualCloneResult).getTime(), 0.0);
    assertEquals(10.0d, ((Point) actualCloneResult).getValue(), 0.0);
    assertTrue(((Point) actualCloneResult).isParameter());
  }

  /**
   * Test Point {@link Point#compareTo(Point)} with {@code Point}.
   *
   * <ul>
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link Point#compareTo(Point)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int Point.compareTo(Point)"})
  public void testPointCompareToWithPoint_thenReturnMinusOne() {
    // Arrange
    Point point = new Point(0.5d, 10.0d, true);

    // Act
    int actualCompareToResult = point.compareTo(new Point(10.0d, 10.0d, true));

    // Assert
    assertEquals(-1, actualCompareToResult);
  }

  /**
   * Test Point {@link Point#compareTo(Point)} with {@code Point}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link Point#compareTo(Point)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int Point.compareTo(Point)"})
  public void testPointCompareToWithPoint_thenReturnOne() {
    // Arrange
    Point point = new Point(10.0d, 10.0d, true);

    // Act
    int actualCompareToResult = point.compareTo(new Point(0.5d, 10.0d, true));

    // Assert
    assertEquals(1, actualCompareToResult);
  }

  /**
   * Test Point {@link Point#compareTo(Point)} with {@code Point}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link Point#compareTo(Point)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int Point.compareTo(Point)"})
  public void testPointCompareToWithPoint_thenReturnZero() {
    // Arrange
    Point point = new Point(10.0d, 10.0d, true);

    // Act
    int actualCompareToResult = point.compareTo(new Point(10.0d, 10.0d, true));

    // Assert
    assertEquals(0, actualCompareToResult);
  }

  /**
   * Test Point getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Point#toString()}
   *   <li>{@link Point#getTime()}
   *   <li>{@link Point#getValue()}
   *   <li>{@link Point#isParameter()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "double Point.getTime()",
    "double Point.getValue()",
    "boolean Point.isParameter()",
    "String Point.toString()"
  })
  public void testPointGettersAndSetters() {
    // Arrange
    Point point = new Point(10.0d, 10.0d, true);

    // Act
    String actualToStringResult = point.toString();
    double actualTime = point.getTime();
    double actualValue = point.getValue();

    // Assert
    assertEquals("Point [time=10.0, value=10.0, isParameter=true]", actualToStringResult);
    assertEquals(10.0d, actualTime, 0.0);
    assertEquals(10.0d, actualValue, 0.0);
    assertTrue(point.isParameter());
  }

  /**
   * Test Point {@link Point#Point(double, double, boolean)}.
   *
   * <p>Method under test: {@link Point#Point(double, double, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Point.<init>(double, double, boolean)"})
  public void testPointNewPoint() {
    // Arrange and Act
    Point actualPoint = new Point(10.0d, 10.0d, true);

    // Assert
    assertEquals(10.0d, actualPoint.getTime(), 0.0);
    assertEquals(10.0d, actualPoint.getValue(), 0.0);
    assertTrue(actualPoint.isParameter());
  }
}
