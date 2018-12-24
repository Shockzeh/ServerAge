package com.shockzeh.serverage.placeholder;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import be.maximvdw.placeholderapi.PlaceholderReplaceEvent;
import be.maximvdw.placeholderapi.PlaceholderReplacer;
import com.shockzeh.serverage.ServerAgePlugin;
import com.shockzeh.serverage.placeholder.supplier.PlaceholderSupplier;

public final class MvdwPlaceholderHook implements PlaceholderReplacer {

    private static final String SPLITTER = PlaceholderSupplier.IDENTIFIER + "_";

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

    public void register(ServerAgePlugin plugin) {
        PlaceholderAPI.registerPlaceholder(plugin, SPLITTER + "*", this);
    }
}