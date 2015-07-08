package com.alex.xyrality_test.xyrality_test.rest.results;

public class WorldsResult {

    private boolean googleLoginSwitchOn;
    private String serverVersion;
    private boolean facebookLoginSwitchOn;
    private World[] allAvailableWorlds;
    private String featureHelpshift;
    private String info;

    public World[] getWorlds() {
        return allAvailableWorlds;
    }
}