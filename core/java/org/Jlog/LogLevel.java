package org.Jlog;

public enum LogLevel 
{
    LogINFO(0x00000001), 
	LogWARNING(0x00000010), 
	LogERROR(0x00000100), 
	LogCRITICAL(0x00001000),
	LogALLOCATE(0x00010000),
	LogSYSTEM(0x00100000),
	LogFATAL(0x01000000), 
    LogTERMINATED(0x10000000);
    
    private static final LogLevel[] map = new LogLevel[256];
    static {
        for (LogLevel tt : LogLevel.values()) {
            map[tt.code] = tt;
        }
    }

    public static LogLevel of(byte code) {
        return map[0xff & code];
    }

    private int code;

    LogLevel(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public byte toByte() {
        return (byte) code;
    }
}
