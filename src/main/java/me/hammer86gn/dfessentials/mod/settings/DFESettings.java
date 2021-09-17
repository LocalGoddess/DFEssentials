package me.hammer86gn.dfessentials.mod.settings;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.hammer86gn.dfessentials.DFE;
import me.hammer86gn.dfessentials.utils.Version;

import java.io.*;

/**
 * Class used to interface with the settings file
 *
 * @since 0.1.0
 * @author Chloe
 */
public class DFESettings {
    private static DFESettings instance;

    private File settingsFile = new File(DFE.getInstance().getModDir().getName() + "/settings.json");
    private JsonObject defaultSettings = new JsonObject();

    private JsonArray moduleDefaults = new JsonArray();

    private DFESettings() {
        instance = this;

        defaultSettings.addProperty("version", DFE.getInstance().getModVersion().toString());
        defaultSettings.add("modules",moduleDefaults);

        if (!settingsFile.exists()) {
            try {
                if (!settingsFile.createNewFile()) {
                    DFE.getInstance().getModLogger().warn("[DFE] Failed to create settings file");
                } else {
                    FileWriter fw = new FileWriter(settingsFile);
                    fw.write(defaultSettings.toString());
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private JsonObject read() {
        try {
            FileReader reader = new FileReader(settingsFile);
            return DFE.getInstance().getJsonParser().parse(reader).getAsJsonObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void validate() {
        JsonObject o = read();

        if(!o.has("version")) {
            o.addProperty("version", DFE.getInstance().getModVersion().toString());
        }

        if (!o.has("modules")) {
            validateModules();
        }

    }

    private void validateModule(String id) {

    }

    private void validateModules() {

    }

    public Version getVersion() {
        return Version.parseVersion(read().get("version").getAsString());
    }



    public static DFESettings getInstance() {
        return instance == null ? new DFESettings() : instance;
    }
}
