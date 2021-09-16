package me.hammer86gn.dfessentials.utils;

public class Version {

    private final int major;
    private final int minor;
    private final int bug;
    private final int build;

    public Version(int major, int minor, int bug, int build) {
        this.major = major;
        this.minor = minor;
        this.bug = bug;
        this.build = build;
    }

    private Version(String m, String m1, String m2, String m3) {
        this(Integer.parseInt(m), Integer.parseInt(m1), Integer.parseInt(m2), Integer.parseInt(m3));
    }

    public static Version parseVersion(String version) {
        String[] r1 = version.split("\\.");
        String[] r2 = r1[2].split("_");

        return new Version(r1[0],r1[1],r2[0], r2[1]);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Version) {
            Version o = (Version) obj;

            if (o.major == this.major && o.minor == this.minor) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

    public boolean equalsExact(Object obj) {
        if (obj instanceof Version) {
            Version o = (Version) obj;

            if (o.major == this.major && o.minor == this.minor && o.bug == this.bug && o.build == build) {
                return true;
            } else {
                return false;
            }

        } else {
           return false;
        }
    }

    @Override
    public String toString() {
        return major + "." + minor + "." + bug + "_" + build;
    }
}
