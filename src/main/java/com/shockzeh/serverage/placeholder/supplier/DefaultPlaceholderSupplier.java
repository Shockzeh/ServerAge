package com.shockzeh.serverage.placeholder.supplier;

/**
 * A default implementation of a {@link PlaceholderSupplier}.
 *
 * @author Shockzeh
 * @since 1.0
 */
public final class DefaultPlaceholderSupplier implements PlaceholderSupplier {

    /**
     * Constants for calculating milliseconds durations
     * to readable formats.
     */
    private static final long SEC = 1000;
    private static final long MIN = 60 * SEC;
    private static final long HOUR = 60 * MIN;
    private static final long DAYS = 24 * HOUR;

    @Override
    public String onPlaceholderRequest(String argument) {
        long timestamp = System.currentTimeMillis();

        try {
            timestamp -= Long.parseLong(argument) * 1000L;
        } catch (NumberFormatException e) {
            return null;
        }

        return formatTime(timestamp);
    }

    /**
     * Formats the given milliseconds timestamp
     * to a human readable format.
     *
     * @param timestamp the millis timestamp
     * @return the readable format
     */
    private static String formatTime(long timestamp) {
        long days = timestamp / DAYS;
        long hours = (timestamp % DAYS) / HOUR;
        long minutes = (timestamp % HOUR) / MIN;
        long seconds = (timestamp % MIN) / SEC;

        StringBuilder formatted = new StringBuilder();

        if (days > 0) {
            formatted.append(days).append("d ");
        }

        if (hours > 0) {
            formatted.append(hours).append("h ");
        }

        if (minutes > 0) {
            formatted.append(minutes).append("m ");
        }

        if (seconds > 0 || formatted.length() == 0) {
            formatted.append(seconds).append("s");
        }

        return formatted.toString().trim();
    }
}