package nl.rug.blackboard.findObjectsFast.util;

import blackboard.platform.plugin.PluginLocalizationUtil;


public class FindObjectsFastLocalizationUtil extends PluginLocalizationUtil {
    private static FindObjectsFastLocalizationUtil INSTANCE = new FindObjectsFastLocalizationUtil();

    public FindObjectsFastLocalizationUtil() {
        super("UG", "FindObjectsFast");
    }

    public static FindObjectsFastLocalizationUtil get() {
        return INSTANCE;
    }
}
