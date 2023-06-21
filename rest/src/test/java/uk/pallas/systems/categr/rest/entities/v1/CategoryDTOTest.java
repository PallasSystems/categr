package uk.pallas.systems.categr.rest.entities.v1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.categr.entities.v1.Category;

/**
 * A set of tests to confirm the category object is created and destroyed as needed.
 */
public class CategoryDTOTest {

  /** Public Constructor. */
  public CategoryDTOTest() {}

  @Test
  void testConstructor() {
    final String name = "name";
    final String description = "CategoryDTOTest-testConstructor";
    final Category basic = new CategoryDTO(name, description);
    Assertions.assertEquals(name, basic.getName());
    Assertions.assertEquals(description, basic.getDescription());
  }

  @Test
  void testCopyConstructor() {
    final String name = "name";
    final String description = "CategoryDTOTest-testCopyConstructor";
    final Category basic = new CategoryDTO(name, description);
    final Category copy = new CategoryDTO(basic);

    Assertions.assertEquals(basic, copy);
  }

  @Test
  void testEquals() {
    final String name = "name";
    final String description = "CategoryDTOTest-testEquals";
    final Category basic = new CategoryDTO(name, description);

    Assertions.assertEquals(basic, basic);
  }

  @Test
  void testEqualsWithInvalid() {
    final String name = "name";
    final String description = "CategoryDTOTest-testEqualsWithInvalid";
    final Category basic = new CategoryDTO(name, description);

    Assertions.assertNotEquals(null, basic);
    Assertions.assertNotEquals(basic, new CategoryDTO());
  }
}
