package net.collective.enchanced;

import eu.midnightdust.lib.config.MidnightConfig;

public class EnchancedConfig extends MidnightConfig {
    private static final String CLIENT = "client";
    private static final String SERVER = "server";

    @Entry(
            category = SERVER,
            isSlider = true,
            min = 0.5f,
            max = 3f
    )
    public static float scatterDivergence = 1.4f;
}
