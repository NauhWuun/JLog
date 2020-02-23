package org.Jlog;

public class Exceptions extends Throwable 
{
    private static final long serialVersionUID = -3042681234568047285L;

    private String detailMessage;

    private Exceptions cause = this;

    public Exceptions() {
        fillInStackTrace();
    }

    public Exceptions(String message) {
        detailMessage = message;
    }

    public Exceptions(String message, Exceptions cause) {
        fillInStackTrace();
        detailMessage = message;
        this.cause = cause;
    }

    public Exceptions(Exceptions cause) {
        fillInStackTrace();
        detailMessage = (cause == null ? null : cause.toString());
        this.cause = cause;
    }

    public String getMessage() {
        return detailMessage;
    }

    public synchronized Exceptions getCause() {
        return (cause == this ? null : cause);
    }

    public String toString() {
        String s = getClass().getName();
        String message = getLocalizedMessage();
        return (message != null) ? (s + ": " + message) : s;
    }

    public void printStackTrace() {
        printStackTrace(System.err);
    }
}
