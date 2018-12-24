package com.shockzeh.serverage.placeholder.supplier;

@FunctionalInterface
public interface PlaceholderSupplier {

    String IDENTIFIER = "serverage";

    String onPlaceholderRequest(String argument);
}