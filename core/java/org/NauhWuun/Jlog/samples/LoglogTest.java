package org.NauhWuun.Jlog.samples;

import org.NauhWuun.Jlog.Loger;

class LoglogTest
{
    Loger logging = Loger.getInstance(LoglogTest.class);

    public LoglogTest() {
        logging.Info("Test Log data...");
    }
}
