package me.hammer86gn.dfessentials;

import me.hammer86gn.dfessentials.fabric.ModEntryPoint;

import java.util.logging.Logger;

public class DFE extends ModEntryPoint {
    private static DFE instance;

    private Logger modLogger = Logger.getLogger("me.hammer86gn.dfessentials.DFE");

    @Override
    public void onInitialize() {
        instance = this;
        modLogger.info("[DFE]: Thank you for using DiamondFire Essentials!");


    }

    public Logger getModLogger() {
        return modLogger;
    }

    public DFE getInstance() {
        return instance;
    }
}
