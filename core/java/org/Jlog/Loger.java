package org.Jlog;

import java.io.IOException;

public class Loger extends Loglog 
{    
    /**
     *
     */
    private static final long serialVersionUID = 8144666293401324397L;

    public Loger(final String LogFile2Where, LogVersion version) throws IOException {
        super(LogFile2Where, version);
    }

    public Loger(final String LogFile2Where) throws IOException {
        super(LogFile2Where, LogVersion.VersionSTANDARD);
    }

    public Loger() throws IOException {
        super(".\\Logging.log", LogVersion.VersionSTANDARD);
    }

    public static <T> Loger getInstance(T instance) {
        return SingleTon.GetInstance(instance.getClass());
    }
}
