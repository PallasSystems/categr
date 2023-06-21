package uk.pallas.systems.categr.services;

import jakarta.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.pallas.systems.categr.domain.CategoryRepository;
import uk.pallas.systems.categr.domain.entities.v1.CategoryDomain;
import uk.pallas.systems.categr.entities.v1.Category;

/**
 * Service which builds on CategoryRepository, this will retrieve objects from the database and process them to retrieve
 * specific data for other components to make use of (e.g. extract all name fields).
 */
@Service
public class CategoriesDAO implements CategoriesDAOService {
  /** Static Logger for the class. */
  private static final Log LOGGER = LogFactory.getLog(CategoriesDAO.class);

  /** The Sprign Data JPA component.*/
  @Autowired
  private CategoryRepository dao;

  /**
   * Retrieves a handle to the Spring Data JPA DAO which allows us to perform database queries.
   * @return should never be null unless something has happened to spring.
   */
  public CategoryRepository getDao() {
    return this.dao;
  }

  /**
   * Sets a handle to the Spring Data JPA DAO which allows us to perform database queries.
   * @param service handle to the Spring Data JPA DAO which allows us to perform database queries.
   */
  public void setDao(final CategoryRepository service) {
    this.dao = service;
  }

  /**
   * This will retrieve a specific CategoryDomain object using the primary key (e.g name).
   *
   * @param identifier the name of the Category to retrieve.
   * @return null if the category could not be located.
   */
  @Override
  public Category findByName(final String identifier) {
    Category result = null;

    if (null == identifier || identifier.isBlank()) {
      if (LOGGER.isInfoEnabled()) {
        LOGGER.info("findByName - Invalid Name supplied");
      }
    } else {
      final CategoryRepository theDAO = this.getDao();
      if (null == theDAO) {
        if (LOGGER.isErrorEnabled()) {
          LOGGER.error("findByName - DAO has broken when searching: " + identifier);
        }
      } else {
        final Optional<CategoryDomain> queryResult = theDAO.findById(identifier);
        if (queryResult.isPresent()) {
          result = queryResult.get();
        } else if (LOGGER.isInfoEnabled()) {
          LOGGER.info("findByName - Unable to find entity with nane: " + identifier);
        }
      }
    }

    return result;
  }

  /**
   * This retrieves all categories from the database and returns them.
   * @return an empty list if there is no category data.
   */
  @Override
  public Collection<Category> findAll() {
    final Collection<Category> results = new HashSet<>();

    final CategoryRepository theDAO = this.getDao();
    if (null == theDAO) {
      if (LOGGER.isErrorEnabled()) {
        LOGGER.error("findAll - DAO has broken when trying to retrieve all categories");
      }
    } else {
      try {
        results.addAll(theDAO.findAll());
      } catch (final EntityNotFoundException exception) {
        if (LOGGER.isInfoEnabled()) {
          LOGGER.info("findAll - Unable to find any entities in the data store");
        }
      }
    }

    return results;
  }
}
