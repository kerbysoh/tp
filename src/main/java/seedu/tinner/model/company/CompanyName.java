package seedu.tinner.model.company;

import static java.util.Objects.requireNonNull;
import static seedu.tinner.commons.util.AppUtil.checkArgument;

/**
 * Represents a Company's name in the company list.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class CompanyName {

    public static final String MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the Name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String value;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public CompanyName(String name) {
        requireNonNull(name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        value = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompanyName // instanceof handles nulls
                && value.equals(((CompanyName) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
