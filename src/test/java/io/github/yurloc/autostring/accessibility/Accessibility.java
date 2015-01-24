package io.github.yurloc.autostring.accessibility;

import io.github.yurloc.autostring.AutoString;
import io.github.yurloc.autostring.AutoStringBuilder;

/**
 * This class MUST be in a different package than {@link AutoStringBuilder}.
 */
public class Accessibility {

    // fields without modifiers
    @AutoString
    private String nPvt = "nPvt;";
    @AutoString
    String nPkg = "nPkg;";
    @AutoString
    protected String nPtd = "nPtd;";
    @AutoString
    public String nPub = "nPub;";
    // static fields
    @AutoString
    static private String sPvt = "sPvt;";
    @AutoString
    static String sPkg = "sPkg;";
    @AutoString
    static protected String sPtd = "sPtd;";
    @AutoString
    static public String sPub = "sPub;";
    // final fields
    @AutoString
    final private String fPvt = "fPvt;";
    @AutoString
    final String fPkg = "fPkg;";
    @AutoString
    final protected String fPtd = "fPtd;";
    @AutoString
    final public String fPub = "fPub;";
    // volatile fields
    @AutoString
    volatile private String vPvt = "vPvt;";
    @AutoString
    volatile String vPkg = "vPkg;";
    @AutoString
    volatile protected String vPtd = "vPtd;";
    @AutoString
    volatile public String vPub = "vPub;";
    // transient fields
    @AutoString
    transient private String tPvt = "tPvt;";
    @AutoString
    transient String tPkg = "tPkg;";
    @AutoString
    transient protected String tPtd = "tPtd;";
    @AutoString
    transient public String tPub = "tPub;";

    @Override
    public String toString() {
        return AutoStringBuilder.build(this);
    }
}
