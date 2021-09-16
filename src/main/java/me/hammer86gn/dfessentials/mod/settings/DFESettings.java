package me.hammer86gn.dfessentials.mod.settings;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import me.hammer86gn.dfessentials.DFE;
import me.hammer86gn.dfessentials.utils.Version;

import java.io.*;

public class DFESettings {
    private static DFESettings instance;

    private File settingsFile = new File(DFE.getInstance().getModDir().getName() + "/settings.json");
    private JsonObject defaultSettings = new JsonObject();

    private DFESettings() {
        instance = this;

        defaultSettings.addProperty("version", DFE.getInstance().getModVersion().toString());

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

    }

    public Version getVersion() {
        return Version.parseVersion(read().get("version").getAsString());
    }



    public static DFESettings getInstance() {
        return instance == null ? new DFESettings() : instance;
    }
}
