package net.finmath.montecarlo.interestrate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.interestrate.products.AbstractTermStructureMonteCarloProduct;
import net.finmath.montecarlo.interestrate.products.ForwardRateVolatilitySurfaceCurvature;
import net.finmath.stochastic.RandomVariable;
import net.finmath.stochastic.Scalar;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CalibrationProductDiffblueTest {
  /**
   * Test {@link CalibrationProduct#CalibrationProduct(String,
   * AbstractTermStructureMonteCarloProduct, double, double)}.
   *
   * <p>Method under test: {@link CalibrationProduct#CalibrationProduct(String,
   * AbstractTermStructureMonteCarloProduct, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibrationProduct.<init>(String, AbstractTermStructureMonteCarloProduct, double, double)"
  })
  public void testNewCalibrationProduct() {
    // Arrange
    ForwardRateVolatilitySurfaceCurvature product =
        new ForwardRateVolatilitySurfaceCurvature(10.0d);

    // Act
    CalibrationProduct actualCalibrationProduct =
        new CalibrationProduct("Name", product, 10.0d, 10.0d);

    // Assert
    AbstractTermStructureMonteCarloProduct product2 = actualCalibrationProduct.getProduct();
    assertTrue(product2 instanceof ForwardRateVolatilitySurfaceCurvature);
    assertTrue(actualCalibrationProduct.getTargetValue() instanceof Scalar);
    assertEquals("Name", actualCalibrationProduct.getName());
    assertEquals(0, actualCalibrationProduct.getPriority().intValue());
    assertEquals(10.0d, actualCalibrationProduct.getWeight(), 0.0);
    assertSame(product, product2);
  }

  /**
   * Test {@link CalibrationProduct#CalibrationProduct(String,
   * AbstractTermStructureMonteCarloProduct, RandomVariable, double)}.
   *
   * <p>Method under test: {@link CalibrationProduct#CalibrationProduct(String,
   * AbstractTermStructureMonteCarloProduct, RandomVariable, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibrationProduct.<init>(String, AbstractTermStructureMonteCarloProduct, RandomVariable, double)"
  })
  public void testNewCalibrationProduct2() {
    // Arrange
    ForwardRateVolatilitySurfaceCurvature product =
        new ForwardRateVolatilitySurfaceCurvature(10.0d);
    RandomVariableFromDoubleArray targetValue = new RandomVariableFromDoubleArray(10.0d);

    // Act
    CalibrationProduct actualCalibrationProduct =
        new CalibrationProduct("Name", product, targetValue, 10.0d);

    // Assert
    RandomVariable targetValue2 = actualCalibrationProduct.getTargetValue();
    assertTrue(targetValue2 instanceof RandomVariableFromDoubleArray);
    AbstractTermStructureMonteCarloProduct product2 = actualCalibrationProduct.getProduct();
    assertTrue(product2 instanceof ForwardRateVolatilitySurfaceCurvature);
    assertEquals("Name", actualCalibrationProduct.getName());
    assertEquals(0, actualCalibrationProduct.getPriority().intValue());
    assertEquals(10.0d, actualCalibrationProduct.getWeight(), 0.0);
    assertSame(targetValue, targetValue2);
    assertSame(product, product2);
  }

  /**
   * Test {@link CalibrationProduct#CalibrationProduct(String,
   * AbstractTermStructureMonteCarloProduct, RandomVariable, double, int)}.
   *
   * <p>Method under test: {@link CalibrationProduct#CalibrationProduct(String,
   * AbstractTermStructureMonteCarloProduct, RandomVariable, double, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibrationProduct.<init>(String, AbstractTermStructureMonteCarloProduct, RandomVariable, double, int)"
  })
  public void testNewCalibrationProduct3() {
    // Arrange
    ForwardRateVolatilitySurfaceCurvature product =
        new ForwardRateVolatilitySurfaceCurvature(10.0d);
    RandomVariableFromDoubleArray targetValue = new RandomVariableFromDoubleArray(10.0d);

    // Act
    CalibrationProduct actualCalibrationProduct =
        new CalibrationProduct("Name", product, targetValue, 10.0d, 1);

    // Assert
    RandomVariable targetValue2 = actualCalibrationProduct.getTargetValue();
    assertTrue(targetValue2 instanceof RandomVariableFromDoubleArray);
    AbstractTermStructureMonteCarloProduct product2 = actualCalibrationProduct.getProduct();
    assertTrue(product2 instanceof ForwardRateVolatilitySurfaceCurvature);
    assertEquals("Name", actualCalibrationProduct.getName());
    assertEquals(1, actualCalibrationProduct.getPriority().intValue());
    assertEquals(10.0d, actualCalibrationProduct.getWeight(), 0.0);
    assertSame(targetValue, targetValue2);
    assertSame(product, product2);
  }

  /**
   * Test {@link CalibrationProduct#CalibrationProduct(AbstractTermStructureMonteCarloProduct,
   * double, double)}.
   *
   * <p>Method under test: {@link
   * CalibrationProduct#CalibrationProduct(AbstractTermStructureMonteCarloProduct, double, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibrationProduct.<init>(AbstractTermStructureMonteCarloProduct, double, double)"
  })
  public void testNewCalibrationProduct4() {
    // Arrange
    ForwardRateVolatilitySurfaceCurvature product =
        new ForwardRateVolatilitySurfaceCurvature(10.0d);

    // Act
    CalibrationProduct actualCalibrationProduct = new CalibrationProduct(product, 10.0d, 10.0d);

    // Assert
    AbstractTermStructureMonteCarloProduct product2 = actualCalibrationProduct.getProduct();
    assertTrue(product2 instanceof ForwardRateVolatilitySurfaceCurvature);
    assertTrue(actualCalibrationProduct.getTargetValue() instanceof Scalar);
    assertEquals("AbstractMonteCarloProduct [currency=null]", actualCalibrationProduct.getName());
    assertEquals(0, actualCalibrationProduct.getPriority().intValue());
    assertEquals(10.0d, actualCalibrationProduct.getWeight(), 0.0);
    assertSame(product, product2);
  }

  /**
   * Test {@link CalibrationProduct#CalibrationProduct(AbstractTermStructureMonteCarloProduct,
   * RandomVariable, double)}.
   *
   * <p>Method under test: {@link
   * CalibrationProduct#CalibrationProduct(AbstractTermStructureMonteCarloProduct, RandomVariable,
   * double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CalibrationProduct.<init>(AbstractTermStructureMonteCarloProduct, RandomVariable, double)"
  })
  public void testNewCalibrationProduct5() {
    // Arrange
    ForwardRateVolatilitySurfaceCurvature product =
        new ForwardRateVolatilitySurfaceCurvature(10.0d);
    RandomVariableFromDoubleArray targetValue = new RandomVariableFromDoubleArray(10.0d);

    // Act
    CalibrationProduct actualCalibrationProduct =
        new CalibrationProduct(product, targetValue, 10.0d);

    // Assert
    RandomVariable targetValue2 = actualCalibrationProduct.getTargetValue();
    assertTrue(targetValue2 instanceof RandomVariableFromDoubleArray);
    AbstractTermStructureMonteCarloProduct product2 = actualCalibrationProduct.getProduct();
    assertTrue(product2 instanceof ForwardRateVolatilitySurfaceCurvature);
    assertEquals("AbstractMonteCarloProduct [currency=null]", actualCalibrationProduct.getName());
    assertEquals(0, actualCalibrationProduct.getPriority().intValue());
    assertEquals(10.0d, actualCalibrationProduct.getWeight(), 0.0);
    assertSame(targetValue, targetValue2);
    assertSame(product, product2);
  }

  /**
   * Test {@link CalibrationProduct#getName()}.
   *
   * <ul>
   *   <li>Then return {@code AbstractMonteCarloProduct [currency=null]}.
   * </ul>
   *
   * <p>Method under test: {@link CalibrationProduct#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CalibrationProduct.getName()"})
  public void testGetName_thenReturnAbstractMonteCarloProductCurrencyNull() {
    // Arrange, Act and Assert
    assertEquals(
        "AbstractMonteCarloProduct [currency=null]",
        new CalibrationProduct(new ForwardRateVolatilitySurfaceCurvature(10.0d), 10.0d, 10.0d)
            .getName());
  }

  /**
   * Test {@link CalibrationProduct#getName()}.
   *
   * <ul>
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link CalibrationProduct#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String CalibrationProduct.getName()"})
  public void testGetName_thenReturnName() {
    // Arrange
    CalibrationProduct calibrationProduct =
        new CalibrationProduct(
            "Name", new ForwardRateVolatilitySurfaceCurvature(10.0d), 10.0d, 10.0d);

    // Act and Assert
    assertEquals("Name", calibrationProduct.getName());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CalibrationProduct#toString()}
   *   <li>{@link CalibrationProduct#getPriority()}
   *   <li>{@link CalibrationProduct#getProduct()}
   *   <li>{@link CalibrationProduct#getTargetValue()}
   *   <li>{@link CalibrationProduct#getWeight()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Integer CalibrationProduct.getPriority()",
    "AbstractTermStructureMonteCarloProduct CalibrationProduct.getProduct()",
    "RandomVariable CalibrationProduct.getTargetValue()",
    "double CalibrationProduct.getWeight()",
    "String CalibrationProduct.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ForwardRateVolatilitySurfaceCurvature product =
        new ForwardRateVolatilitySurfaceCurvature(10.0d);
    CalibrationProduct calibrationProduct = new CalibrationProduct(product, 10.0d, 10.0d);

    // Act
    String actualToStringResult = calibrationProduct.toString();
    Integer actualPriority = calibrationProduct.getPriority();
    AbstractTermStructureMonteCarloProduct actualProduct = calibrationProduct.getProduct();
    RandomVariable actualTargetValue = calibrationProduct.getTargetValue();
    double actualWeight = calibrationProduct.getWeight();

    // Assert
    assertTrue(actualProduct instanceof ForwardRateVolatilitySurfaceCurvature);
    assertTrue(actualTargetValue instanceof Scalar);
    assertEquals(
        "CalibrationProduct [product=AbstractMonteCarloProduct [currency=null], targetValue=Scalar [value=10.0,"
            + " filtrationTime=-Infinity, typePriority()=0], weight=10.0]",
        actualToStringResult);
    assertEquals(0, actualPriority.intValue());
    assertEquals(10.0d, actualWeight, 0.0);
    assertSame(product, actualProduct);
  }
}
