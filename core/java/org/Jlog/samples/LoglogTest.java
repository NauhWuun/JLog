package org.Jlog.samples;

import org.Jlog.Loger;

class LoglogTest
{
    Loger logging = Loger.getInstance(LoglogTest.class);

    public LoglogTest() {
        logging.Info("Test Log data...");
    }
}
