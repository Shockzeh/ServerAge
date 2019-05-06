package co.uk.jaycarr.serverage;

import co.uk.jaycarr.serverage.placeholder.MvdwPlaceholderHook;
import co.uk.jaycarr.serverage.placeholder.PapiPlaceholderHook;
import co.uk.jaycarr.serverage.placeholder.PlaceholderHook;
import co.uk.jaycarr.serverage.placeholder.supplier.DefaultPlaceholderSupplier;
import co.uk.jaycarr.serverage.placeholder.supplier.PlaceholderSupplier;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main class for the ServerAge plugin.
 *
 * @author AsyncJay
 * @since 1.0
 */
public final class ServerAgePlugin extends JavaPlugin {

    /**
     * The global supplier for placeholders, which supplies
     * results to all placeholder dependencies.
     */
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

    /**
     * Retrieves the current supplier for placeholders.
     *
     * @return the supplier
     */
    public PlaceholderSupplier getSupplier() {
        return this.supplier;
    }

    /**
     * Sets the placeholder supplier providing the given
     * supplier is not null.
     *
     * @param supplier the new supplier
     */
    public void setSupplier(PlaceholderSupplier supplier) {
        if (supplier == null) {
            throw new NullPointerException("Supplier cannot be null");
        }

        this.supplier = supplier;
    }
}