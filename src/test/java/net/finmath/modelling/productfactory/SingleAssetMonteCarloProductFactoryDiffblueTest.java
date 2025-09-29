package net.finmath.modelling.productfactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
import net.finmath.modelling.productfactory.SingleAssetMonteCarloProductFactory.DigitalOptionMonteCarlo;
import net.finmath.modelling.productfactory.SingleAssetMonteCarloProductFactory.EuropeanOptionMonteCarlo;
import net.finmath.time.RegularSchedule;
import net.finmath.time.TenorFromArray;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SingleAssetMonteCarloProductFactoryDiffblueTest {
  /**
   * Test DigitalOptionMonteCarlo {@link DigitalOptionMonteCarlo#getDescriptor()}.
   *
   * <p>Method under test: {@link DigitalOptionMonteCarlo#getDescriptor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SingleAssetDigitalOptionProductDescriptor DigitalOptionMonteCarlo.getDescriptor()"
  })
  public void testDigitalOptionMonteCarloGetDescriptor() {
    // Arrange
    SingleAssetDigitalOptionProductDescriptor descriptor =
        new SingleAssetDigitalOptionProductDescriptor(
            "Name Of Underlying", LocalDate.of(1970, 1, 1), 10.0d);
    DigitalOptionMonteCarlo digitalOptionMonteCarlo =
        new DigitalOptionMonteCarlo(descriptor, LocalDate.of(1970, 1, 1));

    // Act and Assert
    assertSame(descriptor, digitalOptionMonteCarlo.getDescriptor());
  }

  /**
   * Test DigitalOptionMonteCarlo {@link
   * DigitalOptionMonteCarlo#DigitalOptionMonteCarlo(SingleAssetDigitalOptionProductDescriptor,
   * LocalDate)}.
   *
   * <ul>
   *   <li>Then return {@code Name Of Underlying}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DigitalOptionMonteCarlo#DigitalOptionMonteCarlo(SingleAssetDigitalOptionProductDescriptor,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DigitalOptionMonteCarlo.<init>(SingleAssetDigitalOptionProductDescriptor, LocalDate)"
  })
  public void testDigitalOptionMonteCarloNewDigitalOptionMonteCarlo_thenReturnNameOfUnderlying() {
    // Arrange
    SingleAssetDigitalOptionProductDescriptor descriptor =
        new SingleAssetDigitalOptionProductDescriptor(
            "Name Of Underlying", LocalDate.of(1970, 1, 1), 10.0d);

    // Act
    DigitalOptionMonteCarlo actualDigitalOptionMonteCarlo =
        new DigitalOptionMonteCarlo(descriptor, LocalDate.of(1970, 1, 1));

    // Assert
    assertEquals("Name Of Underlying", actualDigitalOptionMonteCarlo.getNameOfUnderlying());
    assertNull(actualDigitalOptionMonteCarlo.getCurrency());
    assertEquals(0, actualDigitalOptionMonteCarlo.getUnderlyingIndex().intValue());
    assertEquals(0.0d, actualDigitalOptionMonteCarlo.getMaturity(), 0.0);
    assertEquals(10.0d, actualDigitalOptionMonteCarlo.getStrike(), 0.0);
    assertSame(descriptor, actualDigitalOptionMonteCarlo.getDescriptor());
  }

  /**
   * Test EuropeanOptionMonteCarlo {@link EuropeanOptionMonteCarlo#getDescriptor()}.
   *
   * <p>Method under test: {@link EuropeanOptionMonteCarlo#getDescriptor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SingleAssetEuropeanOptionProductDescriptor EuropeanOptionMonteCarlo.getDescriptor()"
  })
  public void testEuropeanOptionMonteCarloGetDescriptor() {
    // Arrange
    SingleAssetEuropeanOptionProductDescriptor descriptor =
        new SingleAssetEuropeanOptionProductDescriptor(
            "Underlying Name", LocalDate.of(1970, 1, 1), 10.0d);
    EuropeanOptionMonteCarlo europeanOptionMonteCarlo =
        new EuropeanOptionMonteCarlo(descriptor, LocalDate.of(1970, 1, 1));

    // Act and Assert
    assertSame(descriptor, europeanOptionMonteCarlo.getDescriptor());
  }

  /**
   * Test EuropeanOptionMonteCarlo {@link
   * EuropeanOptionMonteCarlo#EuropeanOptionMonteCarlo(SingleAssetEuropeanOptionProductDescriptor,
   * LocalDate)}.
   *
   * <p>Method under test: {@link
   * EuropeanOptionMonteCarlo#EuropeanOptionMonteCarlo(SingleAssetEuropeanOptionProductDescriptor,
   * LocalDate)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void EuropeanOptionMonteCarlo.<init>(SingleAssetEuropeanOptionProductDescriptor, LocalDate)"
  })
  public void testEuropeanOptionMonteCarloNewEuropeanOptionMonteCarlo() {
    // Arrange
    SingleAssetEuropeanOptionProductDescriptor descriptor =
        new SingleAssetEuropeanOptionProductDescriptor(
            "Underlying Name", LocalDate.of(1970, 1, 1), 10.0d);

    // Act
    EuropeanOptionMonteCarlo actualEuropeanOptionMonteCarlo =
        new EuropeanOptionMonteCarlo(descriptor, LocalDate.of(1970, 1, 1));

    // Assert
    assertEquals("Underlying Name", actualEuropeanOptionMonteCarlo.getNameOfUnderliyng());
    assertNull(actualEuropeanOptionMonteCarlo.getCurrency());
    assertEquals(0, actualEuropeanOptionMonteCarlo.getUnderlyingIndex().intValue());
    assertEquals(0.0d, actualEuropeanOptionMonteCarlo.getMaturity(), 0.0);
    assertEquals(10.0d, actualEuropeanOptionMonteCarlo.getStrike(), 0.0);
    assertSame(descriptor, actualEuropeanOptionMonteCarlo.getDescriptor());
  }

  /**
   * Test {@link SingleAssetMonteCarloProductFactory#getProductFromDescriptor(ProductDescriptor)}.
   *
   * <ul>
   *   <li>Then return {@link DigitalOptionMonteCarlo}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SingleAssetMonteCarloProductFactory#getProductFromDescriptor(ProductDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DescribedProduct SingleAssetMonteCarloProductFactory.getProductFromDescriptor(ProductDescriptor)"
  })
  public void testGetProductFromDescriptor_thenReturnDigitalOptionMonteCarlo() {
    // Arrange
    SingleAssetMonteCarloProductFactory singleAssetMonteCarloProductFactory =
        new SingleAssetMonteCarloProductFactory(LocalDate.of(1970, 1, 1));
    SingleAssetDigitalOptionProductDescriptor descriptor =
        new SingleAssetDigitalOptionProductDescriptor(
            "Name Of Underlying", LocalDate.of(1970, 1, 1), 10.0d);

    // Act
    DescribedProduct<? extends SingleAssetProductDescriptor> actualProductFromDescriptor =
        singleAssetMonteCarloProductFactory.getProductFromDescriptor(descriptor);

    // Assert
    assertTrue(actualProductFromDescriptor instanceof DigitalOptionMonteCarlo);
    assertEquals(
        "Name Of Underlying",
        ((DigitalOptionMonteCarlo) actualProductFromDescriptor).getNameOfUnderlying());
    assertEquals(
        0, ((DigitalOptionMonteCarlo) actualProductFromDescriptor).getUnderlyingIndex().intValue());
    assertEquals(0.0d, ((DigitalOptionMonteCarlo) actualProductFromDescriptor).getMaturity(), 0.0);
    assertEquals(10.0d, ((DigitalOptionMonteCarlo) actualProductFromDescriptor).getStrike(), 0.0);
    assertSame(descriptor, actualProductFromDescriptor.getDescriptor());
  }

  /**
   * Test {@link SingleAssetMonteCarloProductFactory#getProductFromDescriptor(ProductDescriptor)}.
   *
   * <ul>
   *   <li>Then return {@link EuropeanOptionMonteCarlo}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SingleAssetMonteCarloProductFactory#getProductFromDescriptor(ProductDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DescribedProduct SingleAssetMonteCarloProductFactory.getProductFromDescriptor(ProductDescriptor)"
  })
  public void testGetProductFromDescriptor_thenReturnEuropeanOptionMonteCarlo() {
    // Arrange
    SingleAssetMonteCarloProductFactory singleAssetMonteCarloProductFactory =
        new SingleAssetMonteCarloProductFactory(LocalDate.of(1970, 1, 1));
    SingleAssetEuropeanOptionProductDescriptor descriptor =
        new SingleAssetEuropeanOptionProductDescriptor(
            "Underlying Name", LocalDate.of(1970, 1, 1), 10.0d);

    // Act
    DescribedProduct<? extends SingleAssetProductDescriptor> actualProductFromDescriptor =
        singleAssetMonteCarloProductFactory.getProductFromDescriptor(descriptor);

    // Assert
    assertTrue(actualProductFromDescriptor instanceof EuropeanOptionMonteCarlo);
    assertEquals(
        "Underlying Name",
        ((EuropeanOptionMonteCarlo) actualProductFromDescriptor).getNameOfUnderliyng());
    assertEquals(
        0,
        ((EuropeanOptionMonteCarlo) actualProductFromDescriptor).getUnderlyingIndex().intValue());
    assertEquals(0.0d, ((EuropeanOptionMonteCarlo) actualProductFromDescriptor).getMaturity(), 0.0);
    assertEquals(10.0d, ((EuropeanOptionMonteCarlo) actualProductFromDescriptor).getStrike(), 0.0);
    assertSame(descriptor, actualProductFromDescriptor.getDescriptor());
  }

  /**
   * Test {@link SingleAssetMonteCarloProductFactory#getProductFromDescriptor(ProductDescriptor)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SingleAssetMonteCarloProductFactory#getProductFromDescriptor(ProductDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DescribedProduct SingleAssetMonteCarloProductFactory.getProductFromDescriptor(ProductDescriptor)"
  })
  public void testGetProductFromDescriptor_thenThrowIllegalArgumentException() {
    // Arrange
    SingleAssetMonteCarloProductFactory singleAssetMonteCarloProductFactory =
        new SingleAssetMonteCarloProductFactory(LocalDate.now());
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
        () -> singleAssetMonteCarloProductFactory.getProductFromDescriptor(descriptor));
  }
}
