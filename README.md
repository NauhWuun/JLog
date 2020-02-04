# JLog
 Java Tiny And Security And Fast Libraries that log the behavior of an application.

 WoW

# Code Example

<Code>
    package core.samples;
  
    import core.Loger;

    class LoglogTest 
    {
        Loger logging = Loger.getInstance(LoglogTest.class);
        public LoglogTest() {
            logging.Info("Test Log data...");
        }
    }
