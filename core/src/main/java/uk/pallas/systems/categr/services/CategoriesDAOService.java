package uk.pallas.systems.categr.services;

import java.util.Collection;
import uk.pallas.systems.categr.entities.v1.Category;

/**
 * Interface for various DAO implementations to provide as a service, so we can switch out the backend as we need to.
 */
public interface CategoriesDAOService {

  /**
   * This will retrieve a specific CategoryDomain object using the primary key (e.g name).
   *
   * @param identifier the name of the Category to retrieve.
   * @return null if the category could not be located.
   */
  Category findByName(String identifier);

  /**
   * This retrieves all categories from the database and returns them.
   * @return an empty list if there is no category data.
   */
  Collection<Category> findAll();
}
