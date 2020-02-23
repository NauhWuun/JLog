package org.Jlog;

public enum LogVersion 
{ 
	VersionINTERNAL(0x10000000), 
	VersionALPHA(0x01000000), 
	VersionBETA(0x00100000),
	VersionTEAM(0x00010000), 
	VersionSERVICE_PACK(0x00001000), 
	VersionDEBUG(0x00000100), 
	VersionRELEASE(0x00000010),
	VersionSTANDARD(0xFFFFFFFE);

	private static final LogVersion[] map = new LogVersion[256];
    static {
        for (LogVersion tt : LogVersion.values()) {
            map[tt.code] = tt;
        }
    }

    public static LogVersion of(byte code) {
        return map[0xff & code];
    }

    private int code;

    LogVersion(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public byte toByte() {
        return (byte) code;
    }
}
