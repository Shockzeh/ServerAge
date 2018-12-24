package com.shockzeh.serverage;

import com.shockzeh.serverage.placeholder.MvdwPlaceholderHook;
import com.shockzeh.serverage.placeholder.PapiPlaceholderHook;
import com.shockzeh.serverage.placeholder.PlaceholderHook;
import com.shockzeh.serverage.placeholder.supplier.DefaultPlaceholderSupplier;
import com.shockzeh.serverage.placeholder.supplier.PlaceholderSupplier;
import org.bukkit.plugin.java.JavaPlugin;

public final class ServerAgePlugin extends JavaPlugin {

    private PlaceholderSupplier supplier;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        String defaultProvider = this.getConfig().getString("default-provider");

        PlaceholderHook defaultHook = PlaceholderHook.parse(defaultProvider);
        PlaceholderHook foundHook = null;

        for (PlaceholderHook hook : PlaceholderHook.values()) {
            if (!hook.isEnabled()) {
                continue;
            }

            if (foundHook != null && hook != defaultHook) {
                continue;
            }

            if ((foundHook = hook) == defaultHook) {
                break;
            }
        }

        if (foundHook == null) {
            this.getLogger().info("Could not find supported placeholder plugin, disabling...");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.supplier = new DefaultPlaceholderSupplier();

        switch (foundHook) {
            case PLACEHOLDERAPI:
                new PapiPlaceholderHook(this.supplier).register();
                break;
            case MVDWPLACEHOLDERAPI:
                new MvdwPlaceholderHook(this.supplier).register(this);
                break;
            default:
                throw new AssertionError();
        }

        this.getLogger().info("Hooked into: " + foundHook.name());
    }

    public PlaceholderSupplier getSupplier() {
        return this.supplier;
    }

    public void setSupplier(PlaceholderSupplier supplier) {
        this.supplier = supplier;
    }
}