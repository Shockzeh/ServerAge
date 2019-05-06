package co.uk.jaycarr.serverage.placeholder;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;
import be.maximvdw.placeholderapi.PlaceholderReplacer;
import co.uk.jaycarr.serverage.placeholder.supplier.PlaceholderSupplier;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The hook implementation for MVdWPlaceholderAPI.
 *
 * @author AsyncJay
 * @since 1.0
 */
public final class MvdwPlaceholderHook implements PlaceholderReplacer {

    private static final String SPLITTER = PlaceholderSupplier.IDENTIFIER + "_";

    /**
     * The supplier being used to supply results to this
     * hook.
     */
    private final PlaceholderSupplier supplier;

    public MvdwPlaceholderHook(PlaceholderSupplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public String onPlaceholderReplace(PlaceholderReplaceEvent event) {
        if (this.supplier == null) {
            return null;
        }

        String placeholder = event.getPlaceholder();
        if (!placeholder.startsWith(SPLITTER)) {
            return null;
        }

        String argument = placeholder.substring(SPLITTER.length());
        return this.supplier.onPlaceholderRequest(argument);
    }

    /**
     * Registers this hook with the given plugin.
     *
     * @param plugin the associative plugin
     */
    public void register(JavaPlugin plugin) {
        PlaceholderAPI.registerPlaceholder(plugin, SPLITTER + "*", this);
    }
}