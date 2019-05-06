package co.uk.jaycarr.serverage.placeholder;

/**
 * Represents all available placeholder dependency
 * hooks for the plugin.
 *
 * @author AsyncJay
 * @since 1.0
 */
public enum PlaceholderHook {

    PLACEHOLDERAPI("me.clip.placeholderapi.expansion.PlaceholderExpansion"),
    MVDWPLACEHOLDERAPI("be.maximvdw.placeholderapi.PlaceholderReplacer");

    /**
     * The class packaged into the dependency used to
     * hook into and add placeholders.
     */
    private final String hookClass;

    PlaceholderHook(String hookClass) {
        this.hookClass = hookClass;
    }

    /**
     * Returns whether the hook is available by checking
     * for existance of the corresponding class.
     *
     * @return true if enabled, otherwise false
     */
    public boolean isEnabled() {
        try {
            Class.forName(this.hookClass);
        } catch (ClassNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * Matches the given input to a placeholder hook type,
     * throwing a {@link IllegalArgumentException} if no
     * matches could be found.
     *
     * @param input the input to match against
     * @throws IllegalArgumentException if no match is found
     * @throws NullPointerException if input is null
     * @return the matching hook
     */
    public static PlaceholderHook parse(String input) {
        if (input == null) {
            throw new NullPointerException("Input cannot be null");
        }

        for (PlaceholderHook hook : PlaceholderHook.values()) {
            if (hook.name().equalsIgnoreCase(input)) {
                return hook;
            }
        }

        throw new IllegalArgumentException("No hook found for: " + input);
    }
}