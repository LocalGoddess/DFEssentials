package me.hammer86gn.dfessentials.fabric;

import me.hammer86gn.dfessentials.utils.Version;
import net.fabricmc.api.ModInitializer;

public abstract class ModEntryPoint implements ModInitializer {

    private final Version version;

    public ModEntryPoint(Version version) {
        this.version = version;
    }

    public Version getModVersion() {
        return version;
    }

}
