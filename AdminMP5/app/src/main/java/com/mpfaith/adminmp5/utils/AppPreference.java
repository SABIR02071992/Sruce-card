package com.mpfaith.adminmp5.utils;

public class AppPreference {
    private boolean isOverrideResultScreen = false;
    public static int selectedTheme = -1;

    public boolean isOverrideResultScreen() {
        return isOverrideResultScreen;
    }

    public void setOverrideResultScreen(boolean overrideResultScreen) {
        isOverrideResultScreen = overrideResultScreen;
    }

    public static int getSelectedTheme() {
        return selectedTheme;
    }

    public static void setSelectedTheme(int selectedTheme) {
        AppPreference.selectedTheme = selectedTheme;
    }
}
