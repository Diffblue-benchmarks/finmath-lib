package net.finmath.modelling.modelfactory;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.finmath.modelling.descriptor.AssetModelDescriptor;
import net.finmath.modelling.descriptor.BlackScholesModelDescriptor;
import net.finmath.modelling.descriptor.HestonModelDescriptor;
import net.finmath.modelling.descriptor.MertonModelDescriptor;
import net.finmath.modelling.descriptor.VarianceGammaModelDescriptor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AssetModelFourierMethodFactoryDiffblueTest {
  /**
   * Test {@link AssetModelFourierMethodFactory#getModelFromDescriptor(AssetModelDescriptor)} with
   * {@code AssetModelDescriptor}.
   *
   * <ul>
   *   <li>Given {@code Name}.
   *   <li>Then calls {@link AssetModelDescriptor#name()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AssetModelFourierMethodFactory#getModelFromDescriptor(AssetModelDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.modelling.DescribedModel AssetModelFourierMethodFactory.getModelFromDescriptor(AssetModelDescriptor)"
  })
  public void testGetModelFromDescriptorWithAssetModelDescriptor_givenName_thenCallsName() {
    // Arrange
    AssetModelFourierMethodFactory assetModelFourierMethodFactory =
        new AssetModelFourierMethodFactory();

    AssetModelDescriptor descriptor = mock(AssetModelDescriptor.class);
    when(descriptor.name()).thenReturn("Name");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> assetModelFourierMethodFactory.getModelFromDescriptor(descriptor));
    verify(descriptor).name();
  }

  /**
   * Test {@link AssetModelFourierMethodFactory#getModelFromDescriptor(AssetModelDescriptor)} with
   * {@code AssetModelDescriptor}.
   *
   * <ul>
   *   <li>Then calls {@link BlackScholesModelDescriptor#getInitialValue()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AssetModelFourierMethodFactory#getModelFromDescriptor(AssetModelDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.modelling.DescribedModel AssetModelFourierMethodFactory.getModelFromDescriptor(AssetModelDescriptor)"
  })
  public void testGetModelFromDescriptorWithAssetModelDescriptor_thenCallsGetInitialValue() {
    // Arrange
    AssetModelFourierMethodFactory assetModelFourierMethodFactory =
        new AssetModelFourierMethodFactory();

    BlackScholesModelDescriptor descriptor = mock(BlackScholesModelDescriptor.class);
    when(descriptor.getInitialValue()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> assetModelFourierMethodFactory.getModelFromDescriptor(descriptor));
    verify(descriptor).getInitialValue();
  }

  /**
   * Test {@link AssetModelFourierMethodFactory#getModelFromDescriptor(AssetModelDescriptor)} with
   * {@code AssetModelDescriptor}.
   *
   * <ul>
   *   <li>Then calls {@link HestonModelDescriptor#getInitialValue()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AssetModelFourierMethodFactory#getModelFromDescriptor(AssetModelDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.modelling.DescribedModel AssetModelFourierMethodFactory.getModelFromDescriptor(AssetModelDescriptor)"
  })
  public void testGetModelFromDescriptorWithAssetModelDescriptor_thenCallsGetInitialValue2() {
    // Arrange
    AssetModelFourierMethodFactory assetModelFourierMethodFactory =
        new AssetModelFourierMethodFactory();

    HestonModelDescriptor descriptor = mock(HestonModelDescriptor.class);
    when(descriptor.getInitialValue()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> assetModelFourierMethodFactory.getModelFromDescriptor(descriptor));
    verify(descriptor).getInitialValue();
  }

  /**
   * Test {@link AssetModelFourierMethodFactory#getModelFromDescriptor(AssetModelDescriptor)} with
   * {@code AssetModelDescriptor}.
   *
   * <ul>
   *   <li>Then calls {@link MertonModelDescriptor#getInitialValue()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AssetModelFourierMethodFactory#getModelFromDescriptor(AssetModelDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.modelling.DescribedModel AssetModelFourierMethodFactory.getModelFromDescriptor(AssetModelDescriptor)"
  })
  public void testGetModelFromDescriptorWithAssetModelDescriptor_thenCallsGetInitialValue3() {
    // Arrange
    AssetModelFourierMethodFactory assetModelFourierMethodFactory =
        new AssetModelFourierMethodFactory();

    MertonModelDescriptor descriptor = mock(MertonModelDescriptor.class);
    when(descriptor.getInitialValue()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> assetModelFourierMethodFactory.getModelFromDescriptor(descriptor));
    verify(descriptor).getInitialValue();
  }

  /**
   * Test {@link AssetModelFourierMethodFactory#getModelFromDescriptor(AssetModelDescriptor)} with
   * {@code AssetModelDescriptor}.
   *
   * <ul>
   *   <li>Then calls {@link VarianceGammaModelDescriptor#getInitialValue()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AssetModelFourierMethodFactory#getModelFromDescriptor(AssetModelDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "net.finmath.modelling.DescribedModel AssetModelFourierMethodFactory.getModelFromDescriptor(AssetModelDescriptor)"
  })
  public void testGetModelFromDescriptorWithAssetModelDescriptor_thenCallsGetInitialValue4() {
    // Arrange
    AssetModelFourierMethodFactory assetModelFourierMethodFactory =
        new AssetModelFourierMethodFactory();

    VarianceGammaModelDescriptor descriptor = mock(VarianceGammaModelDescriptor.class);
    when(descriptor.getInitialValue()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> assetModelFourierMethodFactory.getModelFromDescriptor(descriptor));
    verify(descriptor).getInitialValue();
  }
}
