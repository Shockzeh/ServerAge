package com.shockzeh.serverage.placeholder;

import com.shockzeh.serverage.placeholder.supplier.PlaceholderSupplier;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;

/**
 * The hook implementation for PlaceholderAPI.
 *
 * @author Shockzeh
 * @since 1.0
 */
public final class PapiPlaceholderHook extends PlaceholderExpansion {

    /**
     * The supplier being used to supply results to this
     * hook.
     */
    private final PlaceholderSupplier supplier;

    public PapiPlaceholderHook(PlaceholderSupplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        if (identifier == null || this.supplier == null) {
            return null;
        }

        return this.supplier.onPlaceholderRequest(identifier);
    }

    @Override
    public String getIdentifier() {
        return PlaceholderSupplier.IDENTIFIER;
    }

    @Override
    public String getAuthor() {
        return "Shockzeh";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }
}