package uk.pallas.systems.categr;

/**
 * Utility class to hold Magic Numbers as part of DTO/Domain annotations.
 */
public final class CategoryConstants {

  /** The maximum size any string parameters should be limited to. */
  public static final int MAX_DESCRIPTION_LENGTH = 4096;

  /** The maximum size of the categories name field. */
  public static final int MAX_NAME_LENGTH = 100;

  /** Unused Utility Class constructor. */
  private CategoryConstants() {
    // Do Nothing
  }
}
