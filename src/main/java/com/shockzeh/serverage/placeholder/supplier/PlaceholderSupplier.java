package com.shockzeh.serverage.placeholder.supplier;

/**
 * Represents a supplier of results for matching
 * placeholders.
 *
 * @author Shockzeh
 * @since 1.0
 */
@FunctionalInterface
public interface PlaceholderSupplier {

    /**
     * The global identifier used as a prefix for
     * the placeholders.
     */
    String IDENTIFIER = "serverage";

    /**
     * Returns a result for the placeholder based
     * on the given wildcard argument.
     *
     * @param argument the wildcard argument
     * @return the result
     */
    String onPlaceholderRequest(String argument);
}