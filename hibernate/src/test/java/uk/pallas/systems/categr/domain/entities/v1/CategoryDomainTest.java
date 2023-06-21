package uk.pallas.systems.categr.domain.entities.v1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uk.pallas.systems.categr.entities.v1.Category;

/**
 * This is a test class designed to check the various methods within a CategoryDomain class.
 */
class CategoryDomainTest {

  /** Public Constructor. */
  public CategoryDomainTest() {}

  @Test
  void testConstructor() {
    final String name = "name";
    final String description = "CategoryDomainTest-testConstructor";
    final Category basic = new CategoryDomain(name, description);
    Assertions.assertEquals(name, basic.getName());
    Assertions.assertEquals(description, basic.getDescription());
  }

  @Test
  void testCopyConstructor() {
    final String name = "name";
    final String description = "CategoryDomainTest-testCopyConstructor";
    final Category basic = new CategoryDomain(name, description);
    final Category copy = new CategoryDomain(basic);

    Assertions.assertEquals(basic, copy);
  }

  @Test
  void testEquals() {
    final String name = "name";
    final String description = "CategoryDomainTest-testEquals";
    final Category basic = new CategoryDomain(name, description);

    Assertions.assertEquals(basic, basic);
  }

  @Test
  void testEqualsWithInvalid() {
    final String name = "name";
    final String description = "CategoryDomainTest-testEqualsWithInvalid";
    final Category basic = new CategoryDomain(name, description);

    Assertions.assertNotEquals(null, basic);
    Assertions.assertNotEquals(basic, new CategoryDomain());
  }
}
