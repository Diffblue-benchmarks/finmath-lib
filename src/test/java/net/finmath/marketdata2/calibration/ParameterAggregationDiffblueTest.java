package net.finmath.marketdata2.calibration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.finmath.marketdata2.model.curves.DiscountCurveInterpolation;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.stochastic.RandomVariable;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ParameterAggregationDiffblueTest {
  /**
   * Test {@link ParameterAggregation#ParameterAggregation()}.
   *
   * <p>Method under test: {@link ParameterAggregation#ParameterAggregation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ParameterAggregation.<init>()"})
  public void testNewParameterAggregation() {
    // Arrange and Act
    ParameterAggregation<ParameterObject> actualParameterAggregation = new ParameterAggregation<>();

    // Assert
    assertEquals(0, actualParameterAggregation.getParameter().length);
  }

  /**
   * Test {@link ParameterAggregation#ParameterAggregation(ParameterObject[])}.
   *
   * <p>Method under test: {@link ParameterAggregation#ParameterAggregation(ParameterObject[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ParameterAggregation.<init>(ParameterObject[])"})
  public void testNewParameterAggregation2() {
    // Arrange
    ParameterObject[] parameters = new ParameterObject[] {new ParameterAggregation<>()};

    // Act
    ParameterAggregation<ParameterObject> actualParameterAggregation =
        new ParameterAggregation<>(parameters);

    // Assert
    assertEquals(0, actualParameterAggregation.getParameter().length);
  }

  /**
   * Test {@link ParameterAggregation#ParameterAggregation(Set)}.
   *
   * <ul>
   *   <li>Given {@link ParameterAggregation#ParameterAggregation()}.
   * </ul>
   *
   * <p>Method under test: {@link ParameterAggregation#ParameterAggregation(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ParameterAggregation.<init>(Set)"})
  public void testNewParameterAggregation_givenParameterAggregation() {
    // Arrange
    HashSet<ParameterObject> parameters = new HashSet<>();
    parameters.add(new ParameterAggregation<>());

    // Act
    ParameterAggregation<ParameterObject> actualParameterAggregation =
        new ParameterAggregation<>(parameters);

    // Assert
    assertEquals(0, actualParameterAggregation.getParameter().length);
  }

  /**
   * Test {@link ParameterAggregation#ParameterAggregation(Set)}.
   *
   * <ul>
   *   <li>Given {@link ParameterAggregation#ParameterAggregation()}.
   * </ul>
   *
   * <p>Method under test: {@link ParameterAggregation#ParameterAggregation(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ParameterAggregation.<init>(Set)"})
  public void testNewParameterAggregation_givenParameterAggregation2() {
    // Arrange
    HashSet<ParameterObject> parameters = new HashSet<>();
    parameters.add(new ParameterAggregation<>());
    parameters.add(new ParameterAggregation<>());

    // Act
    ParameterAggregation<ParameterObject> actualParameterAggregation =
        new ParameterAggregation<>(parameters);

    // Assert
    assertEquals(0, actualParameterAggregation.getParameter().length);
  }

  /**
   * Test {@link ParameterAggregation#ParameterAggregation(Set)}.
   *
   * <ul>
   *   <li>When {@link HashSet#HashSet()}.
   * </ul>
   *
   * <p>Method under test: {@link ParameterAggregation#ParameterAggregation(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ParameterAggregation.<init>(Set)"})
  public void testNewParameterAggregation_whenHashSet() {
    // Arrange and Act
    ParameterAggregation<ParameterObject> actualParameterAggregation =
        new ParameterAggregation<>(new HashSet<>());

    // Assert
    assertEquals(0, actualParameterAggregation.getParameter().length);
  }

  /**
   * Test {@link ParameterAggregation#getParameter()}.
   *
   * <p>Method under test: {@link ParameterAggregation#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] ParameterAggregation.getParameter()"})
  public void testGetParameter() {
    // Arrange
    ParameterObject[] parameters = new ParameterObject[] {new ParameterAggregation<>()};
    ParameterAggregation<ParameterObject> parameterAggregation =
        new ParameterAggregation<>(parameters);

    // Act and Assert
    assertEquals(0, parameterAggregation.getParameter().length);
  }

  /**
   * Test {@link ParameterAggregation#getParameter()}.
   *
   * <ul>
   *   <li>Given {@link DiscountCurveInterpolation} {@link
   *       DiscountCurveInterpolation#getParameter()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ParameterAggregation#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] ParameterAggregation.getParameter()"})
  public void testGetParameter_givenDiscountCurveInterpolationGetParameterReturnNull() {
    // Arrange
    DiscountCurveInterpolation discountCurveInterpolation = mock(DiscountCurveInterpolation.class);
    when(discountCurveInterpolation.getParameter()).thenReturn(null);
    ParameterObject[] parameters = new ParameterObject[] {discountCurveInterpolation};
    ParameterAggregation<ParameterObject> parameterAggregation =
        new ParameterAggregation<>(parameters);

    // Act
    RandomVariable[] actualParameter = parameterAggregation.getParameter();

    // Assert
    verify(discountCurveInterpolation, atLeast(1)).getParameter();
    assertEquals(0, actualParameter.length);
  }

  /**
   * Test {@link ParameterAggregation#getParameter()}.
   *
   * <ul>
   *   <li>Given {@link ParameterAggregation#ParameterAggregation()}.
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link ParameterAggregation#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] ParameterAggregation.getParameter()"})
  public void testGetParameter_givenParameterAggregation_thenReturnArrayLengthIsZero() {
    // Arrange
    ParameterAggregation<ParameterObject> parameterAggregation = new ParameterAggregation<>();

    // Act and Assert
    assertEquals(0, parameterAggregation.getParameter().length);
  }

  /**
   * Test {@link ParameterAggregation#getParameter()}.
   *
   * <ul>
   *   <li>Then first element return {@link RandomVariableFromDoubleArray}.
   * </ul>
   *
   * <p>Method under test: {@link ParameterAggregation#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] ParameterAggregation.getParameter()"})
  public void testGetParameter_thenFirstElementReturnRandomVariableFromDoubleArray() {
    // Arrange
    DiscountCurveInterpolation discountCurveInterpolation = mock(DiscountCurveInterpolation.class);
    RandomVariableFromDoubleArray randomVariableFromDoubleArray =
        new RandomVariableFromDoubleArray(10.0d);
    when(discountCurveInterpolation.getParameter())
        .thenReturn(new RandomVariable[] {randomVariableFromDoubleArray});
    ParameterObject[] parameters = new ParameterObject[] {discountCurveInterpolation};
    ParameterAggregation<ParameterObject> parameterAggregation =
        new ParameterAggregation<>(parameters);

    // Act
    RandomVariable[] actualParameter = parameterAggregation.getParameter();

    // Assert
    verify(discountCurveInterpolation, atLeast(1)).getParameter();
    RandomVariable randomVariable = actualParameter[0];
    assertTrue(randomVariable instanceof RandomVariableFromDoubleArray);
    assertEquals(1, actualParameter.length);
    assertSame(randomVariableFromDoubleArray, randomVariable);
  }

  /**
   * Test {@link ParameterAggregation#getParameter()}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link ParameterAggregation#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RandomVariable[] ParameterAggregation.getParameter()"})
  public void testGetParameter_thenThrowUnsupportedOperationException() {
    // Arrange
    DiscountCurveInterpolation discountCurveInterpolation = mock(DiscountCurveInterpolation.class);
    when(discountCurveInterpolation.getParameter()).thenThrow(new UnsupportedOperationException());
    ParameterObject[] parameters = new ParameterObject[] {discountCurveInterpolation};
    ParameterAggregation<ParameterObject> parameterAggregation =
        new ParameterAggregation<>(parameters);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> parameterAggregation.getParameter());
    verify(discountCurveInterpolation).getParameter();
  }

  /**
   * Test {@link ParameterAggregation#setParameter(RandomVariable[])}.
   *
   * <p>Method under test: {@link ParameterAggregation#setParameter(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ParameterAggregation.setParameter(RandomVariable[])"})
  public void testSetParameter() {
    // Arrange
    DiscountCurveInterpolation discountCurveInterpolation = mock(DiscountCurveInterpolation.class);
    when(discountCurveInterpolation.getParameter()).thenThrow(new UnsupportedOperationException());
    ParameterObject[] parameters = new ParameterObject[] {discountCurveInterpolation};
    ParameterAggregation<ParameterObject> parameterAggregation =
        new ParameterAggregation<>(parameters);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            parameterAggregation.setParameter(
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)}));
    verify(discountCurveInterpolation).getParameter();
  }

  /**
   * Test {@link ParameterAggregation#setParameter(RandomVariable[])}.
   *
   * <p>Method under test: {@link ParameterAggregation#setParameter(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ParameterAggregation.setParameter(RandomVariable[])"})
  public void testSetParameter2() {
    // Arrange
    DiscountCurveInterpolation discountCurveInterpolation = mock(DiscountCurveInterpolation.class);
    doThrow(new UnsupportedOperationException())
        .when(discountCurveInterpolation)
        .setParameter(Mockito.<RandomVariable[]>any());
    when(discountCurveInterpolation.getParameter())
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    ParameterObject[] parameters = new ParameterObject[] {discountCurveInterpolation};
    ParameterAggregation<ParameterObject> parameterAggregation =
        new ParameterAggregation<>(parameters);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            parameterAggregation.setParameter(
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)}));
    verify(discountCurveInterpolation).getParameter();
    verify(discountCurveInterpolation).setParameter(isA(RandomVariable[].class));
  }

  /**
   * Test {@link ParameterAggregation#setParameter(RandomVariable[])}.
   *
   * <ul>
   *   <li>Given {@link DiscountCurveInterpolation} {@link
   *       DiscountCurveInterpolation#getParameter()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ParameterAggregation#setParameter(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ParameterAggregation.setParameter(RandomVariable[])"})
  public void testSetParameter_givenDiscountCurveInterpolationGetParameterReturnNull() {
    // Arrange
    DiscountCurveInterpolation discountCurveInterpolation = mock(DiscountCurveInterpolation.class);
    when(discountCurveInterpolation.getParameter()).thenReturn(null);
    ParameterObject[] parameters = new ParameterObject[] {discountCurveInterpolation};
    ParameterAggregation<ParameterObject> parameterAggregation =
        new ParameterAggregation<>(parameters);

    // Act
    parameterAggregation.setParameter(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(discountCurveInterpolation).getParameter();
  }

  /**
   * Test {@link ParameterAggregation#setParameter(RandomVariable[])}.
   *
   * <ul>
   *   <li>Then calls {@link DiscountCurveInterpolation#setParameter(RandomVariable[])}.
   * </ul>
   *
   * <p>Method under test: {@link ParameterAggregation#setParameter(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ParameterAggregation.setParameter(RandomVariable[])"})
  public void testSetParameter_thenCallsSetParameter() {
    // Arrange
    DiscountCurveInterpolation discountCurveInterpolation = mock(DiscountCurveInterpolation.class);
    doNothing().when(discountCurveInterpolation).setParameter(Mockito.<RandomVariable[]>any());
    when(discountCurveInterpolation.getParameter())
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    ParameterObject[] parameters = new ParameterObject[] {discountCurveInterpolation};
    ParameterAggregation<ParameterObject> parameterAggregation =
        new ParameterAggregation<>(parameters);

    // Act
    parameterAggregation.setParameter(
        new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(discountCurveInterpolation).getParameter();
    verify(discountCurveInterpolation).setParameter(isA(RandomVariable[].class));
  }

  /**
   * Test {@link ParameterAggregation#getObjectsToModifyForParameter(RandomVariable[])}.
   *
   * <p>Method under test: {@link
   * ParameterAggregation#getObjectsToModifyForParameter(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ParameterAggregation.getObjectsToModifyForParameter(RandomVariable[])"})
  public void testGetObjectsToModifyForParameter() {
    // Arrange
    ParameterObject[] parameters = new ParameterObject[] {new ParameterAggregation<>()};
    ParameterAggregation<ParameterObject> parameterAggregation =
        new ParameterAggregation<>(parameters);

    // Act and Assert
    assertEquals(
        1,
        parameterAggregation
            .getObjectsToModifyForParameter(
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)})
            .size());
  }

  /**
   * Test {@link ParameterAggregation#getObjectsToModifyForParameter(RandomVariable[])}.
   *
   * <ul>
   *   <li>Given {@link ParameterAggregation#ParameterAggregation()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link
   * ParameterAggregation#getObjectsToModifyForParameter(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ParameterAggregation.getObjectsToModifyForParameter(RandomVariable[])"})
  public void testGetObjectsToModifyForParameter_givenParameterAggregation_thenReturnEmpty() {
    // Arrange
    ParameterAggregation<ParameterObject> parameterAggregation = new ParameterAggregation<>();

    // Act and Assert
    assertTrue(
        parameterAggregation
            .getObjectsToModifyForParameter(
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)})
            .isEmpty());
  }

  /**
   * Test {@link ParameterAggregation#getObjectsToModifyForParameter(RandomVariable[])}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * ParameterAggregation#getObjectsToModifyForParameter(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ParameterAggregation.getObjectsToModifyForParameter(RandomVariable[])"})
  public void testGetObjectsToModifyForParameter_thenReturnSizeIsOne() {
    // Arrange
    DiscountCurveInterpolation discountCurveInterpolation = mock(DiscountCurveInterpolation.class);
    when(discountCurveInterpolation.getParameter())
        .thenReturn(new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});
    ParameterObject[] parameters = new ParameterObject[] {discountCurveInterpolation};
    ParameterAggregation<ParameterObject> parameterAggregation =
        new ParameterAggregation<>(parameters);

    // Act
    Map<ParameterObject, RandomVariable[]> actualObjectsToModifyForParameter =
        parameterAggregation.getObjectsToModifyForParameter(
            new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)});

    // Assert
    verify(discountCurveInterpolation).getParameter();
    assertEquals(1, actualObjectsToModifyForParameter.size());
  }

  /**
   * Test {@link ParameterAggregation#getObjectsToModifyForParameter(RandomVariable[])}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ParameterAggregation#getObjectsToModifyForParameter(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ParameterAggregation.getObjectsToModifyForParameter(RandomVariable[])"})
  public void testGetObjectsToModifyForParameter_thenThrowUnsupportedOperationException() {
    // Arrange
    DiscountCurveInterpolation discountCurveInterpolation = mock(DiscountCurveInterpolation.class);
    when(discountCurveInterpolation.getParameter()).thenThrow(new UnsupportedOperationException());
    ParameterObject[] parameters = new ParameterObject[] {discountCurveInterpolation};
    ParameterAggregation<ParameterObject> parameterAggregation =
        new ParameterAggregation<>(parameters);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            parameterAggregation.getObjectsToModifyForParameter(
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)}));
    verify(discountCurveInterpolation).getParameter();
  }

  /**
   * Test {@link ParameterAggregation#getCloneForParameter(RandomVariable[])}.
   *
   * <p>Method under test: {@link ParameterAggregation#getCloneForParameter(RandomVariable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.marketdata2.model.curves.Curve ParameterAggregation.getCloneForParameter(RandomVariable[])"
  })
  public void testGetCloneForParameter() throws CloneNotSupportedException {
    // Arrange
    ParameterAggregation<ParameterObject> parameterAggregation = new ParameterAggregation<>();

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            parameterAggregation.getCloneForParameter(
                new RandomVariable[] {new RandomVariableFromDoubleArray(10.0d)}));
  }
}
