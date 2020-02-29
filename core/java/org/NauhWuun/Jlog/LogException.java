package org.NauhWuun.Jlog;

public class LogException extends Exceptions
{
    private static final long serialVersionUID = -8342082065077907191L;

    public LogException() {
        super();
    }

    public LogException(String message) {
        super(message);
    }

    public LogException(String message, Exceptions cause) {
        super(message, cause);
    }

    public LogException(Exceptions cause) {
        super(cause);
    }
}
