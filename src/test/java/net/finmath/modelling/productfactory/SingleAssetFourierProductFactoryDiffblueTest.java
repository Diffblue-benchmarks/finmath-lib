package net.finmath.modelling.productfactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import net.finmath.modelling.DescribedProduct;
import net.finmath.modelling.ProductDescriptor;
import net.finmath.modelling.SingleAssetProductDescriptor;
import net.finmath.modelling.descriptor.InterestRateSwapLegProductDescriptor;
import net.finmath.modelling.descriptor.ScheduleDescriptor;
import net.finmath.modelling.descriptor.SingleAssetDigitalOptionProductDescriptor;
import net.finmath.modelling.descriptor.SingleAssetEuropeanOptionProductDescriptor;
import net.finmath.modelling.productfactory.SingleAssetFourierProductFactory.DigitalOptionFourierMethod;
import net.finmath.modelling.productfactory.SingleAssetFourierProductFactory.EuropeanOptionFourierMethod;
import net.finmath.time.RegularSchedule;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SingleAssetFourierProductFactoryDiffblueTest {
  /**
   * Test DigitalOptionFourierMethod {@link DigitalOptionFourierMethod#getDescriptor()}.
   *
   * <p>Method under test: {@link DigitalOptionFourierMethod#getDescriptor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SingleAssetDigitalOptionProductDescriptor DigitalOptionFourierMethod.getDescriptor()"
  })
  public void testDigitalOptionFourierMethodGetDescriptor() {
    // Arrange
    SingleAssetDigitalOptionProductDescriptor descriptor =
        new SingleAssetDigitalOptionProductDescriptor(
            "Name Of Underlying", LocalDate.of(1970, 1, 1), 10.0d);
    DigitalOptionFourierMethod digitalOptionFourierMethod =
        new DigitalOptionFourierMethod(descriptor, LocalDate.of(1970, 1, 1));

    // Act and Assert
    assertSame(descriptor, digitalOptionFourierMethod.getDescriptor());
  }

  /**
   * Test DigitalOptionFourierMethod {@link
   * DigitalOptionFourierMethod#DigitalOptionFourierMethod(SingleAssetDigitalOptionProductDescriptor,
   * LocalDate)}.
   *
   * <p>Method under test: {@link
   * DigitalOptionFourierMethod#DigitalOptionFourierMethod(SingleAssetDigitalOptionProductDescriptor,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DigitalOptionFourierMethod.<init>(SingleAssetDigitalOptionProductDescriptor, LocalDate)"
  })
  public void testDigitalOptionFourierMethodNewDigitalOptionFourierMethod() {
    // Arrange
    SingleAssetDigitalOptionProductDescriptor descriptor =
        new SingleAssetDigitalOptionProductDescriptor(
            "Name Of Underlying", LocalDate.of(1970, 1, 1), 10.0d);

    // Act
    DigitalOptionFourierMethod actualDigitalOptionFourierMethod =
        new DigitalOptionFourierMethod(descriptor, LocalDate.of(1970, 1, 1));

    // Assert
    assertEquals(0.0d, actualDigitalOptionFourierMethod.getMaturity(), 0.0);
    assertEquals(0.5d, actualDigitalOptionFourierMethod.getIntegrationDomainImagLowerBound(), 0.0);
    assertEquals(2.5d, actualDigitalOptionFourierMethod.getIntegrationDomainImagUpperBound(), 0.0);
    assertSame(descriptor, actualDigitalOptionFourierMethod.getDescriptor());
  }

  /**
   * Test EuropeanOptionFourierMethod {@link EuropeanOptionFourierMethod#getDescriptor()}.
   *
   * <p>Method under test: {@link EuropeanOptionFourierMethod#getDescriptor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SingleAssetEuropeanOptionProductDescriptor EuropeanOptionFourierMethod.getDescriptor()"
  })
  public void testEuropeanOptionFourierMethodGetDescriptor() {
    // Arrange
    SingleAssetEuropeanOptionProductDescriptor descriptor =
        new SingleAssetEuropeanOptionProductDescriptor(
            "Underlying Name", LocalDate.of(1970, 1, 1), 10.0d);
    EuropeanOptionFourierMethod europeanOptionFourierMethod =
        new EuropeanOptionFourierMethod(descriptor, LocalDate.of(1970, 1, 1));

    // Act and Assert
    assertSame(descriptor, europeanOptionFourierMethod.getDescriptor());
  }

  /**
   * Test EuropeanOptionFourierMethod {@link
   * EuropeanOptionFourierMethod#EuropeanOptionFourierMethod(SingleAssetEuropeanOptionProductDescriptor,
   * LocalDate)}.
   *
   * <p>Method under test: {@link
   * EuropeanOptionFourierMethod#EuropeanOptionFourierMethod(SingleAssetEuropeanOptionProductDescriptor,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void EuropeanOptionFourierMethod.<init>(SingleAssetEuropeanOptionProductDescriptor, LocalDate)"
  })
  public void testEuropeanOptionFourierMethodNewEuropeanOptionFourierMethod() {
    // Arrange
    SingleAssetEuropeanOptionProductDescriptor descriptor =
        new SingleAssetEuropeanOptionProductDescriptor(
            "Underlying Name", LocalDate.of(1970, 1, 1), 10.0d);

    // Act
    EuropeanOptionFourierMethod actualEuropeanOptionFourierMethod =
        new EuropeanOptionFourierMethod(descriptor, LocalDate.of(1970, 1, 1));

    // Assert
    assertEquals(0.0d, actualEuropeanOptionFourierMethod.getMaturity(), 0.0);
    assertEquals(0.5d, actualEuropeanOptionFourierMethod.getIntegrationDomainImagLowerBound(), 0.0);
    assertEquals(2.5d, actualEuropeanOptionFourierMethod.getIntegrationDomainImagUpperBound(), 0.0);
    assertSame(descriptor, actualEuropeanOptionFourierMethod.getDescriptor());
  }

  /**
   * Test {@link SingleAssetFourierProductFactory#getProductFromDescriptor(ProductDescriptor)}.
   *
   * <ul>
   *   <li>Then return {@link DigitalOptionFourierMethod}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SingleAssetFourierProductFactory#getProductFromDescriptor(ProductDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DescribedProduct SingleAssetFourierProductFactory.getProductFromDescriptor(ProductDescriptor)"
  })
  public void testGetProductFromDescriptor_thenReturnDigitalOptionFourierMethod() {
    // Arrange
    SingleAssetFourierProductFactory singleAssetFourierProductFactory =
        new SingleAssetFourierProductFactory(LocalDate.of(1970, 1, 1));
    SingleAssetDigitalOptionProductDescriptor descriptor =
        new SingleAssetDigitalOptionProductDescriptor(
            "Name Of Underlying", LocalDate.of(1970, 1, 1), 10.0d);

    // Act
    DescribedProduct<? extends SingleAssetProductDescriptor> actualProductFromDescriptor =
        singleAssetFourierProductFactory.getProductFromDescriptor(descriptor);

    // Assert
    assertTrue(actualProductFromDescriptor instanceof DigitalOptionFourierMethod);
    assertEquals(
        0.0d, ((DigitalOptionFourierMethod) actualProductFromDescriptor).getMaturity(), 0.0);
    assertEquals(
        0.5d,
        ((DigitalOptionFourierMethod) actualProductFromDescriptor)
            .getIntegrationDomainImagLowerBound(),
        0.0);
    assertEquals(
        2.5d,
        ((DigitalOptionFourierMethod) actualProductFromDescriptor)
            .getIntegrationDomainImagUpperBound(),
        0.0);
    assertSame(descriptor, actualProductFromDescriptor.getDescriptor());
  }

  /**
   * Test {@link SingleAssetFourierProductFactory#getProductFromDescriptor(ProductDescriptor)}.
   *
   * <ul>
   *   <li>Then return {@link EuropeanOptionFourierMethod}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SingleAssetFourierProductFactory#getProductFromDescriptor(ProductDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DescribedProduct SingleAssetFourierProductFactory.getProductFromDescriptor(ProductDescriptor)"
  })
  public void testGetProductFromDescriptor_thenReturnEuropeanOptionFourierMethod() {
    // Arrange
    SingleAssetFourierProductFactory singleAssetFourierProductFactory =
        new SingleAssetFourierProductFactory(LocalDate.of(1970, 1, 1));
    SingleAssetEuropeanOptionProductDescriptor descriptor =
        new SingleAssetEuropeanOptionProductDescriptor(
            "Underlying Name", LocalDate.of(1970, 1, 1), 10.0d);

    // Act
    DescribedProduct<? extends SingleAssetProductDescriptor> actualProductFromDescriptor =
        singleAssetFourierProductFactory.getProductFromDescriptor(descriptor);

    // Assert
    assertTrue(actualProductFromDescriptor instanceof EuropeanOptionFourierMethod);
    assertEquals(
        0.0d, ((EuropeanOptionFourierMethod) actualProductFromDescriptor).getMaturity(), 0.0);
    assertEquals(
        0.5d,
        ((EuropeanOptionFourierMethod) actualProductFromDescriptor)
            .getIntegrationDomainImagLowerBound(),
        0.0);
    assertEquals(
        2.5d,
        ((EuropeanOptionFourierMethod) actualProductFromDescriptor)
            .getIntegrationDomainImagUpperBound(),
        0.0);
    assertSame(descriptor, actualProductFromDescriptor.getDescriptor());
  }

  /**
   * Test {@link SingleAssetFourierProductFactory#getProductFromDescriptor(ProductDescriptor)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SingleAssetFourierProductFactory#getProductFromDescriptor(ProductDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DescribedProduct SingleAssetFourierProductFactory.getProductFromDescriptor(ProductDescriptor)"
  })
  public void testGetProductFromDescriptor_thenThrowIllegalArgumentException() {
    // Arrange
    SingleAssetFourierProductFactory singleAssetFourierProductFactory =
        new SingleAssetFourierProductFactory(LocalDate.now());
    ScheduleDescriptor legSchedule =
        new ScheduleDescriptor(new RegularSchedule(new TenorFromArray(10.0d, 10, 0.5d)));
    InterestRateSwapLegProductDescriptor descriptor =
        new InterestRateSwapLegProductDescriptor(
            "Forward Curve Name",
            "3",
            legSchedule,
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            new double[] {10.0d, 0.5d, 10.0d, 0.5d},
            true);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> singleAssetFourierProductFactory.getProductFromDescriptor(descriptor));
  }
}
