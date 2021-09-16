package me.hammer86gn.dfessentials;

import com.google.gson.JsonParser;
import me.hammer86gn.dfessentials.fabric.ModEntryPoint;
import me.hammer86gn.dfessentials.mod.settings.DFESettings;
import me.hammer86gn.dfessentials.utils.Version;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;


public class DFE extends ModEntryPoint {
    private static DFE instance;

    private Logger modLogger = LogManager.getLogger("DFELogger");
    private final JsonParser JSON_PARSER = new JsonParser();

    private File modDir = new File("DFE/");

    public DFE() {
        super(new Version(0,1,0,0));

        if (!modDir.exists()) {
            if (!modDir.mkdir()) {
                modLogger.warn("[DFE] Failed to create Mod Directory");
                throw new RuntimeException("[DFE] Failed to create Mod Directory! Report this to the creators of DFE");
            }
        }
    }

    @Override
    public void onInitialize() {
        instance = this;

        modLogger.info("[DFE] Thank you for using DiamondFire Essentials!");
        modLogger.info("[DFE] Current version in use: " + super.getModVersion().toString());

        DFESettings.getInstance();

        if (settingsUpdateReq()) {
            modLogger.info("[DFE] Updating settings file");
            DFESettings.getInstance().validate();
        }
    }

    private boolean settingsUpdateReq() {
        return this.getModVersion().equals(DFESettings.getInstance().getVersion());
    }

    public Logger getModLogger() {
        return modLogger;
    }

    public JsonParser getJsonParser() {
        return JSON_PARSER;
    }

    public File getModDir() {
        return modDir;
    }

    public static DFE getInstance() {
        return instance;
    }
}
