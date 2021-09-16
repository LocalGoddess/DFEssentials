package me.hammer86gn.dfessentials;

import me.hammer86gn.dfessentials.fabric.ModEntryPoint;
import me.hammer86gn.dfessentials.utils.Version;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DFE extends ModEntryPoint {
    private static DFE instance;

    private Logger modLogger = LogManager.getLogger("DFELogger");

    public DFE() {
        super(new Version(0,1,0,0));
    }

    @Override
    public void onInitialize() {
        instance = this;

        modLogger.info("[DFE]: Thank you for using DiamondFire Essentials!");
        modLogger.info("[DFE]: Current version in use: " + super.getModVersion().toString());

    }

    public Logger getModLogger() {
        return modLogger;
    }

    public static DFE getInstance() {
        return instance;
    }
}
