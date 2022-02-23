package uk.pallas.typing.entities.v1.jackson;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import uk.pallas.typing.entities.v1.StringFieldDefinition;

import java.util.Objects;

/**
 * We expect to store a list of valid Field definitions within a database, these will be referenced within the main
 * data definitions. By classifying fields beyond string, int, etc.. we are able to identify identical fields between
 * different data definitions, this allows us to translate data objects (as well as apply more stringent rules on
 * validating the incoming data).
 *
 * This class is focussed on String fields, allowing us to craft regular expressions to test the incoming data is valid.
 */
public class StringFieldDefinitionDTO extends AbstractFieldDefinitionDTO implements StringFieldDefinition {

    /** As this is a String type, assumption is we apply Regular Express to validate the string. */
    private String regex;

    /**
     * Default constructor, sets everything to null and makes validation optional.
     */
    public StringFieldDefinitionDTO() {
        this(null, null, null);
    }

    /**
     * Constructor, allows us to set the internal abstract fields
     * @param regularExp the Regular expression to apply to this field.
     * @param fieldName What is the name  of this kind of field, e.g. post code, uk mobile, IPv4, etc..
     * @param desc Can you describe what the field concerns?
     */
    public StringFieldDefinitionDTO(final String regularExp, final String fieldName, final String desc) {
        super(fieldName, desc);

        this.regex = regularExp;
    }

    /**
     * Copy Constructor, passes up to parent Copy Constructor.
     * @param data the object to create a duplicate off.
     */
    public StringFieldDefinitionDTO(final StringFieldDefinition data) {
        super(data);

        if (null == data) {
            this.regex = null;
        } else {
            this.regex = data.getRegex();
        }
    }

    /**
     * Compares the supplied object to this one, it checks the supplied object is a FieldDefinition and then checks
     * the definition applies validation, its name and description to see if they are matching objects.
     *
     * @param toCompare the object to compare (can be null or a child class, etc..)
     * @return false if the name/validation and description fields in a field definition are
     *        different (or it isn't a field definition).
     */
    @Override
    public boolean equals(final Object toCompare) {

        final boolean result;
        if (this == toCompare) {
            result = true;
        } else if (toCompare instanceof StringFieldDefinition) {
            final var that = (StringFieldDefinition) toCompare;
            result = super.equals(toCompare)
                             && Objects.equals(this.getRegex(), that.getRegex());
        } else {
            result = false;
        }

        return result;
    }

    /**
     * Generates a Unique hashcode for the field definition class.
     * @return a valid integer representation of this object,
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), this.getRegex());
    }

    /**
     *  The regular Expression to apply to a string defined by this field definition.
     * @return non null regular expression that can be used by Java Regex system.
     */
    public String getRegex() {
        return this.regex;
    }

    /**
     * Allows us to set the regular expression associated with the field definition.
     * @param validation a valid regular expression string.
     */
    public void setRegex(final String validation) {
        this.regex = validation;
    }

    /**
     * Is the supplied test object something that matches against our field definition regular expression?
     *
     * If validation optional is set to true this will return true, if the supplied object is null, this will always
     * return false. Otherwise this will call toString and then match the field definition regex tp confirm the object
     * matches our desired value.
     *
     * @param toTest to test is valid
     * @return false if the object fails the validation match.
     */
    @Override
    public boolean isValid(final Object toTest) {
        final boolean result;

        if (toTest instanceof String) {
            // Use to string as it leaves things the most flexible in our regex comparison.
            result = this.isValid((String)toTest);
        } else {
            result = false;
        }

        return result;
    }

    /**
     * Is the supplied test object something that matches against our field definition regular expression?
     *
     * If validation optional is set to true this will return true, if the supplied object is null, this will always
     * return false. Otherwise it will call matches on the supplied string.
     *
     * @param toTest to test is valid
     * @return false if the object fails the validation match.
     */
    @Override
    public boolean isValid(final String toTest) {
        final boolean result;

        if (null == toTest) {
            result = false;
        } else {
            result = toTest.matches(this.getRegex());
        }

        return result;
    }

    /**
     * Defines the class type so we can associate with it.
     * @return a valid field name for association.
     */
    public String getType() {
        return StringFieldDefinition.class.getSimpleName();
    }
}
