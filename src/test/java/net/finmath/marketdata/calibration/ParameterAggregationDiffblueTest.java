package net.finmath.marketdata.calibration;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import net.finmath.marketdata.model.bond.BondCurve;
import net.finmath.marketdata.model.bond.BondCurve.Type;
import net.finmath.marketdata.model.curves.CurveInterpolation;
import net.finmath.marketdata.model.curves.CurveInterpolation.ExtrapolationMethod;
import net.finmath.marketdata.model.curves.CurveInterpolation.InterpolationEntity;
import net.finmath.marketdata.model.curves.CurveInterpolation.InterpolationMethod;
import net.finmath.marketdata.model.curves.DiscountCurveFromForwardCurve;
import net.finmath.marketdata.model.curves.DiscountCurveInterpolation;
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
    assertArrayEquals(new double[] {}, actualParameterAggregation.getParameter(), 0.0);
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
    assertArrayEquals(new double[] {}, actualParameterAggregation.getParameter(), 0.0);
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
    assertArrayEquals(new double[] {}, actualParameterAggregation.getParameter(), 0.0);
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
    assertArrayEquals(new double[] {}, actualParameterAggregation.getParameter(), 0.0);
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
    assertArrayEquals(new double[] {}, actualParameterAggregation.getParameter(), 0.0);
  }

  /**
   * Test {@link ParameterAggregation#getParameter()}.
   *
   * <p>Method under test: {@link ParameterAggregation#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] ParameterAggregation.getParameter()"})
  public void testGetParameter() {
    // Arrange
    ParameterObject[] parameters = new ParameterObject[] {new ParameterAggregation<>()};
    ParameterAggregation<ParameterObject> parameterAggregation =
        new ParameterAggregation<>(parameters);

    // Act and Assert
    assertArrayEquals(new double[] {}, parameterAggregation.getParameter(), 0.0);
  }

  /**
   * Test {@link ParameterAggregation#getParameter()}.
   *
   * <p>Method under test: {@link ParameterAggregation#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] ParameterAggregation.getParameter()"})
  public void testGetParameter2() {
    // Arrange
    CurveInterpolation curveInterpolation =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});
    ParameterObject[] parameters = new ParameterObject[] {curveInterpolation};
    ParameterAggregation<ParameterObject> parameterAggregation =
        new ParameterAggregation<>(parameters);

    // Act and Assert
    assertArrayEquals(new double[] {}, parameterAggregation.getParameter(), 0.0);
  }

  /**
   * Test {@link ParameterAggregation#getParameter()}.
   *
   * <ul>
   *   <li>Given {@link DiscountCurveFromForwardCurve#DiscountCurveFromForwardCurve(String)} with
   *       {@code Forward Curve Name}.
   * </ul>
   *
   * <p>Method under test: {@link ParameterAggregation#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] ParameterAggregation.getParameter()"})
  public void testGetParameter_givenDiscountCurveFromForwardCurveWithForwardCurveName() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve referenceCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    BondCurve bondCurve =
        new BondCurve(
            "Name",
            referenceDate,
            referenceCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            Type.DISCOUNTFACTOR_DISCOUNTFACTOR);
    ParameterObject[] parameters = new ParameterObject[] {bondCurve};
    ParameterAggregation<ParameterObject> parameterAggregation =
        new ParameterAggregation<>(parameters);

    // Act and Assert
    assertArrayEquals(new double[] {}, parameterAggregation.getParameter(), 0.0);
  }

  /**
   * Test {@link ParameterAggregation#getParameter()}.
   *
   * <ul>
   *   <li>Given {@link ParameterAggregation#ParameterAggregation()}.
   *   <li>Then return empty array of {@code double}.
   * </ul>
   *
   * <p>Method under test: {@link ParameterAggregation#getParameter()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double[] ParameterAggregation.getParameter()"})
  public void testGetParameter_givenParameterAggregation_thenReturnEmptyArrayOfDouble() {
    // Arrange
    ParameterAggregation<ParameterObject> parameterAggregation = new ParameterAggregation<>();

    // Act and Assert
    assertArrayEquals(new double[] {}, parameterAggregation.getParameter(), 0.0);
  }

  /**
   * Test {@link ParameterAggregation#setParameter(double[])}.
   *
   * <p>Method under test: {@link ParameterAggregation#setParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ParameterAggregation.setParameter(double[])"})
  public void testSetParameter() {
    // Arrange
    ParameterObject[] parameters = new ParameterObject[] {new ParameterAggregation<>()};
    ParameterAggregation<ParameterObject> parameterAggregation =
        new ParameterAggregation<>(parameters);

    // Act
    parameterAggregation.setParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert that nothing has changed
    assertArrayEquals(new double[] {}, parameterAggregation.getParameter(), 0.0);
  }

  /**
   * Test {@link ParameterAggregation#setParameter(double[])}.
   *
   * <p>Method under test: {@link ParameterAggregation#setParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ParameterAggregation.setParameter(double[])"})
  public void testSetParameter2() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve referenceCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    BondCurve bondCurve =
        new BondCurve(
            "Name",
            referenceDate,
            referenceCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            Type.DISCOUNTFACTOR_DISCOUNTFACTOR);
    ParameterObject[] parameters = new ParameterObject[] {bondCurve};
    ParameterAggregation<ParameterObject> parameterAggregation =
        new ParameterAggregation<>(parameters);

    // Act
    parameterAggregation.setParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert that nothing has changed
    assertArrayEquals(new double[] {}, parameterAggregation.getParameter(), 0.0);
  }

  /**
   * Test {@link ParameterAggregation#setParameter(double[])}.
   *
   * <p>Method under test: {@link ParameterAggregation#setParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ParameterAggregation.setParameter(double[])"})
  public void testSetParameter3() {
    // Arrange
    ParameterObject[] parameters = new ParameterObject[] {new ParameterAggregation<>()};
    ParameterAggregation<ParameterObject> parameterAggregation =
        new ParameterAggregation<>(parameters);
    ParameterObject[] parameters2 = new ParameterObject[] {parameterAggregation};
    ParameterAggregation<ParameterObject> parameterAggregation2 =
        new ParameterAggregation<>(parameters2);

    // Act
    parameterAggregation2.setParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert that nothing has changed
    assertArrayEquals(new double[] {}, parameterAggregation2.getParameter(), 0.0);
  }

  /**
   * Test {@link ParameterAggregation#setParameter(double[])}.
   *
   * <p>Method under test: {@link ParameterAggregation#setParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ParameterAggregation.setParameter(double[])"})
  public void testSetParameter4() {
    // Arrange
    DiscountCurveInterpolation discountCurveInterpolation = mock(DiscountCurveInterpolation.class);
    doNothing().when(discountCurveInterpolation).setParameter(Mockito.<double[]>any());
    when(discountCurveInterpolation.getParameter())
        .thenReturn(new double[] {10.0d, 1.0d, 10.0d, 1.0d});
    ParameterObject[] parameters = new ParameterObject[] {discountCurveInterpolation};
    ParameterAggregation<ParameterObject> parameterAggregation =
        new ParameterAggregation<>(parameters);

    // Act
    parameterAggregation.setParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert
    verify(discountCurveInterpolation).getParameter();
    verify(discountCurveInterpolation).setParameter(isA(double[].class));
    assertArrayEquals(
        new double[] {10.0d, 0.5d, 10.0d, 0.5d}, parameterAggregation.getParameter(), 0.0);
  }

  /**
   * Test {@link ParameterAggregation#setParameter(double[])}.
   *
   * <ul>
   *   <li>Then {@link ParameterAggregation#ParameterAggregation()} Parameter is empty array of
   *       {@code double}.
   * </ul>
   *
   * <p>Method under test: {@link ParameterAggregation#setParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ParameterAggregation.setParameter(double[])"})
  public void testSetParameter_thenParameterAggregationParameterIsEmptyArrayOfDouble() {
    // Arrange
    ParameterAggregation<ParameterObject> parameterAggregation = new ParameterAggregation<>();

    // Act
    parameterAggregation.setParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d});

    // Assert that nothing has changed
    assertArrayEquals(new double[] {}, parameterAggregation.getParameter(), 0.0);
  }

  /**
   * Test {@link ParameterAggregation#getObjectsToModifyForParameter(double[])}.
   *
   * <p>Method under test: {@link ParameterAggregation#getObjectsToModifyForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Map ParameterAggregation.getObjectsToModifyForParameter(double[])"})
  public void testGetObjectsToModifyForParameter() {
    // Arrange
    ParameterObject[] parameters = new ParameterObject[] {new ParameterAggregation<>()};
    ParameterAggregation<ParameterObject> parameterAggregation =
        new ParameterAggregation<>(parameters);

    // Act and Assert
    assertEquals(
        1,
        parameterAggregation
            .getObjectsToModifyForParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d})
            .size());
  }

  /**
   * Test {@link ParameterAggregation#getObjectsToModifyForParameter(double[])}.
   *
   * <p>Method under test: {@link ParameterAggregation#getObjectsToModifyForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Map ParameterAggregation.getObjectsToModifyForParameter(double[])"})
  public void testGetObjectsToModifyForParameter2() {
    // Arrange
    ParameterObject[] parameters = new ParameterObject[] {new ParameterAggregation<>()};
    ParameterAggregation<ParameterObject> parameterAggregation =
        new ParameterAggregation<>(parameters);
    ParameterObject[] parameters2 = new ParameterObject[] {parameterAggregation};
    ParameterAggregation<ParameterObject> parameterAggregation2 =
        new ParameterAggregation<>(parameters2);

    // Act and Assert
    assertEquals(
        1,
        parameterAggregation2
            .getObjectsToModifyForParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d})
            .size());
  }

  /**
   * Test {@link ParameterAggregation#getObjectsToModifyForParameter(double[])}.
   *
   * <p>Method under test: {@link ParameterAggregation#getObjectsToModifyForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Map ParameterAggregation.getObjectsToModifyForParameter(double[])"})
  public void testGetObjectsToModifyForParameter3() {
    // Arrange
    CurveInterpolation curveInterpolation =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});
    ParameterObject[] parameters = new ParameterObject[] {curveInterpolation};
    ParameterAggregation<ParameterObject> parameterAggregation =
        new ParameterAggregation<>(parameters);

    // Act and Assert
    assertEquals(
        1,
        parameterAggregation
            .getObjectsToModifyForParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d})
            .size());
  }

  /**
   * Test {@link ParameterAggregation#getObjectsToModifyForParameter(double[])}.
   *
   * <p>Method under test: {@link ParameterAggregation#getObjectsToModifyForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Map ParameterAggregation.getObjectsToModifyForParameter(double[])"})
  public void testGetObjectsToModifyForParameter4() {
    // Arrange
    LocalDate referenceDate = LocalDate.of(1970, 1, 1);
    DiscountCurveFromForwardCurve referenceCurve =
        new DiscountCurveFromForwardCurve("Forward Curve Name");

    BondCurve bondCurve =
        new BondCurve(
            "Name",
            referenceDate,
            referenceCurve,
            new DiscountCurveFromForwardCurve("Forward Curve Name"),
            Type.DISCOUNTFACTOR_DISCOUNTFACTOR);
    ParameterObject[] parameters = new ParameterObject[] {bondCurve};
    ParameterAggregation<ParameterObject> parameterAggregation =
        new ParameterAggregation<>(parameters);
    ParameterObject[] parameters2 = new ParameterObject[] {parameterAggregation};
    ParameterAggregation<ParameterObject> parameterAggregation2 =
        new ParameterAggregation<>(parameters2);

    // Act and Assert
    assertEquals(
        1,
        parameterAggregation2
            .getObjectsToModifyForParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d})
            .size());
  }

  /**
   * Test {@link ParameterAggregation#getObjectsToModifyForParameter(double[])}.
   *
   * <p>Method under test: {@link ParameterAggregation#getObjectsToModifyForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Map ParameterAggregation.getObjectsToModifyForParameter(double[])"})
  public void testGetObjectsToModifyForParameter5() {
    // Arrange
    CurveInterpolation curveInterpolation =
        new CurveInterpolation(
            "Name",
            LocalDate.of(1970, 1, 1),
            InterpolationMethod.PIECEWISE_CONSTANT,
            ExtrapolationMethod.DEFAULT,
            InterpolationEntity.VALUE,
            new double[] {10.0d, 1.0d, 10.0d, 1.0d},
            new double[] {10.0d, 1.0d, 10.0d, 1.0d});
    ParameterObject[] parameters = new ParameterObject[] {curveInterpolation};
    ParameterAggregation<ParameterObject> parameterAggregation =
        new ParameterAggregation<>(parameters);
    ParameterObject[] parameters2 = new ParameterObject[] {parameterAggregation};
    ParameterAggregation<ParameterObject> parameterAggregation2 =
        new ParameterAggregation<>(parameters2);

    // Act and Assert
    assertEquals(
        1,
        parameterAggregation2
            .getObjectsToModifyForParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d})
            .size());
  }

  /**
   * Test {@link ParameterAggregation#getObjectsToModifyForParameter(double[])}.
   *
   * <ul>
   *   <li>Given {@link ParameterAggregation#ParameterAggregation()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link ParameterAggregation#getObjectsToModifyForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Map ParameterAggregation.getObjectsToModifyForParameter(double[])"})
  public void testGetObjectsToModifyForParameter_givenParameterAggregation_thenReturnEmpty() {
    // Arrange
    ParameterAggregation<ParameterObject> parameterAggregation = new ParameterAggregation<>();

    // Act and Assert
    assertTrue(
        parameterAggregation
            .getObjectsToModifyForParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d})
            .isEmpty());
  }

  /**
   * Test {@link ParameterAggregation#getCloneForParameter(double[])}.
   *
   * <p>Method under test: {@link ParameterAggregation#getCloneForParameter(double[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.marketdata.model.curves.Curve ParameterAggregation.getCloneForParameter(double[])"
  })
  public void testGetCloneForParameter() throws CloneNotSupportedException {
    // Arrange
    ParameterAggregation<ParameterObject> parameterAggregation = new ParameterAggregation<>();

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> parameterAggregation.getCloneForParameter(new double[] {10.0d, 0.5d, 10.0d, 0.5d}));
  }
}
