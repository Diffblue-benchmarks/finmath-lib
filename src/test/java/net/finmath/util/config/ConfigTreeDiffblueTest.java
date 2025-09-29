package net.finmath.util.config;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ConfigTreeDiffblueTest {
  /**
   * Test {@link ConfigTree#ConfigTree(List, List)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()}.
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return Config is {@code null} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ConfigTree#ConfigTree(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConfigTree.<init>(List, List)"})
  public void testNewConfigTree_givenHashMap_whenArrayList_thenReturnConfigIsNullIsNull() {
    // Arrange
    ArrayList<String> keyOrder = new ArrayList<>();

    ArrayList<Map<String, Object>> configs = new ArrayList<>();
    configs.add(new HashMap<>());

    // Act
    ConfigTree actualConfigTree = new ConfigTree(keyOrder, configs);

    // Assert
    assertNull(actualConfigTree.getConfig(null));
  }

  /**
   * Test {@link ConfigTree#ConfigTree(List, List)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()}.
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link ConfigTree#ConfigTree(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConfigTree.<init>(List, List)"})
  public void testNewConfigTree_givenHashMap_whenArrayList_thenThrowIllegalArgumentException() {
    // Arrange
    ArrayList<String> keyOrder = new ArrayList<>();

    ArrayList<Map<String, Object>> configs = new ArrayList<>();
    configs.add(new HashMap<>());
    configs.add(new HashMap<>());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new ConfigTree(keyOrder, configs));
  }

  /**
   * Test {@link ConfigTree#ConfigTree(List, List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link ConfigTree#ConfigTree(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ConfigTree.<init>(List, List)"})
  public void testNewConfigTree_whenArrayList_thenThrowIllegalArgumentException() {
    // Arrange
    ArrayList<String> keyOrder = new ArrayList<>();

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new ConfigTree(keyOrder, new ArrayList<>()));
  }

  /**
   * Test {@link ConfigTree#getConfig(Map)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code foo}.
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link ConfigTree#getConfig(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ConfigTree.getConfig(Map)"})
  public void testGetConfig_givenArrayListAddFoo_whenHashMap_thenThrowIllegalArgumentException() {
    // Arrange
    ArrayList<String> keyOrder = new ArrayList<>();
    keyOrder.add("foo");
    ConfigTree configTree = new ConfigTree(keyOrder, new ArrayList<>());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> configTree.getConfig(new HashMap<>()));
  }

  /**
   * Test {@link ConfigTree#getConfig(Map)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link HashMap#HashMap()}.
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ConfigTree#getConfig(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ConfigTree.getConfig(Map)"})
  public void testGetConfig_givenArrayListAddHashMap_whenHashMap_thenReturnNull() {
    // Arrange
    ArrayList<Map<String, Object>> configs = new ArrayList<>();
    configs.add(new HashMap<>());
    ConfigTree configTree = new ConfigTree(new ArrayList<>(), configs);

    // Act and Assert
    assertNull(configTree.getConfig(new HashMap<>()));
  }

  /**
   * Test {@link ConfigTree#getConfig(Map)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>When {@link HashMap#HashMap()} {@code foo} is {@code 42}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link ConfigTree#getConfig(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ConfigTree.getConfig(Map)"})
  public void testGetConfig_givenFoo_whenHashMapFooIs42_thenThrowIllegalArgumentException() {
    // Arrange
    ArrayList<String> keyOrder = new ArrayList<>();
    keyOrder.add("foo");
    ConfigTree configTree = new ConfigTree(keyOrder, new ArrayList<>());

    HashMap<String, Object> selector = new HashMap<>();
    selector.put("foo", "42");

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> configTree.getConfig(selector));
  }
}
